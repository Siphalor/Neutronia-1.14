package team.hollow.neutronia.utils;

import net.fabricmc.loader.api.ModContainer;
import net.minecraft.recipe.RecipeManager;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Utils {

    private static final boolean DEBUG_LOAD_MINECRAFT = false;

    public static boolean findFiles(ModContainer mod, String base, Function<Path, Boolean> preprocessor, BiFunction<Path, Path, Boolean> processor,
                                    boolean defaultUnfoundRoot, boolean visitAllFiles) {

        File source = mod.getRoot().toFile();

        if ("minecraft".equals(mod.getMetadata().getId())) {
            if (!DEBUG_LOAD_MINECRAFT)
                return true;

            try {
                URI tmp = RecipeManager.class.getResource("/assets/.mcassetsroot").toURI();
                source = new File(tmp.resolve("..").getPath());
            } catch (URISyntaxException e) {
                System.out.printf("Error finding Minecraft jar: ", e);
                return false;
            }
        }

        FileSystem fs = null;
        boolean success = true;

        try {
            Path root = null;

            if (source.isFile()) {
                try {
                    fs = FileSystems.newFileSystem(source.toPath(), null);
                    root = fs.getPath("/" + base);
                } catch (IOException e) {
                    System.out.printf("Error loading FileSystem from jar: ", e);
                    return false;
                }
            } else if (source.isDirectory()) {
                root = source.toPath().resolve(base);
            }

            if (root == null || !Files.exists(root))
                return defaultUnfoundRoot;

            if (preprocessor != null) {
                Boolean cont = preprocessor.apply(root);
                if (cont == null || !cont)
                    return false;
            }

            if (processor != null) {
                Iterator<Path> itr = null;
                try {
                    itr = Files.walk(root).iterator();
                } catch (IOException e) {
                    System.out.printf("Error iterating filesystem for: {}", mod.getMetadata().getId(), e);
                    return false;
                }

                while (itr.hasNext()) {
                    Boolean cont = processor.apply(root, itr.next());

                    if (visitAllFiles) {
                        success &= cont != null && cont;
                    } else if (cont == null || !cont) {
                        return false;
                    }
                }
            }
        } finally {
            IOUtils.closeQuietly(fs);
        }

        return success;
    }

}
