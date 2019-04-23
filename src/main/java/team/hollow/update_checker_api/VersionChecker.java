package team.hollow.update_checker_api;

import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.maven.artifact.versioning.ComparableVersion;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static team.hollow.update_checker_api.VersionChecker.Status.*;

public class VersionChecker
{
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int MAX_HTTP_REDIRECTS = Integer.getInteger("http.maxRedirects", 20);

    public enum Status
    {
        PENDING(),
        FAILED(),
        UP_TO_DATE(),
        OUTDATED(3, true),
        AHEAD(),
        BETA(),
        BETA_OUTDATED(6, true);

        final int sheetOffset;
        final boolean draw, animated;

        Status()
        {
            this(0, false, false);
        }

        Status(int sheetOffset)
        {
            this(sheetOffset, true, false);
        }

        Status(int sheetOffset, boolean animated)
        {
            this(sheetOffset, true, animated);
        }

        Status(int sheetOffset, boolean draw, boolean animated)
        {
            this.sheetOffset = sheetOffset;
            this.draw = draw;
            this.animated = animated;
        }

        public int getSheetOffset()
        {
            return sheetOffset;
        }

        public boolean shouldDraw()
        {
            return draw;
        }

        public boolean isAnimated()
        {
            return animated;
        }

    }

    public static class CheckResult
    {
        @Nonnull
        public final Status status;
        @Nullable
        public final ComparableVersion target;
        @Nullable
        public final Map<ComparableVersion, String> changes;
        @Nullable
        public final String url;

        private CheckResult(@Nonnull Status status, @Nullable ComparableVersion target, @Nullable Map<ComparableVersion, String> changes, @Nullable String url)
        {
            this.status = status;
            this.target = target;
            this.changes = changes == null ? Collections.<ComparableVersion, String>emptyMap() : Collections.unmodifiableMap(changes);
            this.url = url;
        }
    }

    public static void startVersionCheck()
    {
        new Thread("Fabric Version Check")
        {
            @Override
            public void run()
            {
                gatherMods().forEach(this::process);
            }

            /**
             * Opens stream for given URL while following redirects
             */
            private InputStream openUrlStream(URL url) throws IOException
            {
                URL currentUrl = url;
                for (int redirects = 0; redirects < MAX_HTTP_REDIRECTS; redirects++)
                {
                    URLConnection c = currentUrl.openConnection();
                    if (c instanceof HttpURLConnection)
                    {
                        HttpURLConnection huc = (HttpURLConnection) c;
                        huc.setInstanceFollowRedirects(false);
                        int responseCode = huc.getResponseCode();
                        if (responseCode >= 300 && responseCode <= 399)
                        {
                            try
                            {
                                String loc = huc.getHeaderField("Location");
                                currentUrl = new URL(currentUrl, loc);
                                continue;
                            }
                            finally
                            {
                                huc.disconnect();
                            }
                        }
                    }

                    return c.getInputStream();
                }
                throw new IOException("Too many redirects while trying to fetch " + url);
            }

            @SuppressWarnings("UnstableApiUsage")
            private void process(ModContainer mod)
            {
                Status status = PENDING;
                ComparableVersion target = null;
                Map<ComparableVersion, String> changes = null;
                String display_url = null;
                try
                {
                    URL url = new URL(mod.getMetadata().getCustomElement("updatechecker:update_link").getAsString());
                    LOGGER.info("[{}] Starting version check at {}", mod.getMetadata().getId(), url.toString());

                    InputStream con = openUrlStream(url);
                    String data = new String(ByteStreams.toByteArray(con), StandardCharsets.UTF_8);
                    con.close();

                    LOGGER.debug("[{}] Received version check data:\n{}", mod.getMetadata().getId(), data);


                    @SuppressWarnings("unchecked")
                    Map<String, Object> json = new Gson().fromJson(data, Map.class);
                    @SuppressWarnings("unchecked")
                    Map<String, String> promos = (Map<String, String>)json.get("promos");
                    display_url = (String)json.get("homepage");

                    String mcVersion = MCPVersion.getMCVersion();
                    String rec = promos.get(mcVersion + "-recommended");
                    String lat = promos.get(mcVersion + "-latest");
                    ComparableVersion current = new ComparableVersion(mod.getMetadata().getVersion().toString());

                    if (rec != null)
                    {
                        ComparableVersion recommended = new ComparableVersion(rec);
                        int diff = recommended.compareTo(current);

                        if (diff == 0)
                            status = UP_TO_DATE;
                        else if (diff < 0)
                        {
                            status = AHEAD;
                            if (lat != null)
                            {
                                ComparableVersion latest = new ComparableVersion(lat);
                                if (current.compareTo(latest) < 0)
                                {
                                    status = OUTDATED;
                                    target = latest;
                                }
                            }
                        }
                        else
                        {
                            status = OUTDATED;
                            target = recommended;
                        }
                    }
                    else if (lat != null)
                    {
                        ComparableVersion latest = new ComparableVersion(lat);
                        if (current.compareTo(latest) < 0)
                        {
                            status = BETA_OUTDATED;
                            target = latest;
                        }
                        else
                            status = BETA;
                    }
                    else
                        status = BETA;

                    LOGGER.info("[{}] Found status: {} Target: {}", mod.getMetadata().getId(), status, target);

                    changes = new LinkedHashMap<>();
                    @SuppressWarnings("unchecked")
                    Map<String, String> tmp = (Map<String, String>)json.get(mcVersion);
                    if (tmp != null)
                    {
                        List<ComparableVersion> ordered = new ArrayList<>();
                        for (String key : tmp.keySet())
                        {
                            ComparableVersion ver = new ComparableVersion(key);
                            if (ver.compareTo(current) > 0 && (target == null || ver.compareTo(target) < 1))
                            {
                                ordered.add(ver);
                            }
                        }
                        Collections.sort(ordered);

                        for (ComparableVersion ver : ordered)
                        {
                            changes.put(ver, tmp.get(ver.toString()));
                        }
                    }
                }
                catch (Exception e)
                {
                    LOGGER.warn("Failed to process update information", e);
                    status = FAILED;
                }
                results.put(mod, new CheckResult(status, target, changes, display_url));
            }
        }.start();
    }

    // Gather a list of mods that have opted in to this update system by providing a URL.
    private static List<ModContainer> gatherMods()
    {
        List<ModContainer> ret = new LinkedList<>();
        for (ModContainer info : FabricLoader.getInstance().getAllMods()) {
            if(info.getMetadata().containsCustomElement("updatechecker:update_link")) {
                URL url = null;
                try {
                    url = new URL(info.getMetadata().getCustomElement("updatechecker:update_link").getAsString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                if (url != null)
                    ret.add(info);
                return ret;
            }
        }
        return new ArrayList<>();
    }

    private static Map<ModContainer, CheckResult> results = new ConcurrentHashMap<>();
    private static final CheckResult PENDING_CHECK = new CheckResult(PENDING, null, null, null);

    public static CheckResult getResult(ModContainer mod)
    {
        return results.getOrDefault(mod, PENDING_CHECK);
    }

}