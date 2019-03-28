/*
package team.hollow.neutronia.client;

import com.google.common.collect.ImmutableSet;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.network.ScoreboardEntry;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.Identifier;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.resource.IResourceType;
import net.minecraftforge.client.resource.ISelectiveResourceReloadListener;
import net.minecraftforge.client.resource.VanillaResourceType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import team.hdt.neutronia.base.lib.LibMisc;
import team.hdt.neutronia.base.lib.LibObfuscation;
import team.hdt.neutronia.entity.render.layer.LayerCustomVinny;
import team.hollow.neutronia.LibObfuscation;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.utils.ReflectionHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.function.Predicate;

public class ContributorRewardHandler {

    private static final ImmutableSet<String> MOD_CAPE = ImmutableSet.of("75c298f9-27c8-415b-9a16-329e3884054b", "b344687b-ec74-479a-9540-1aa8ccb13e92",
            "bf3379cd-efb1-4194-91ba-408bbbb12055");
    private static final ImmutableSet<String> TEAM_CAPE = ImmutableSet.of("0901c870-aa68-4453-8061-42ff2e9172c8", "ddb01615-c982-4fa0-99b4-0aef9480751c",
            "5dfe80bb-40e5-4bec-a862-89df71868301", "336375e1-84da-4c08-87b3-40be8c872896");
    private static final ImmutableSet<String> MCA_CAPE = ImmutableSet.of("caaeff74-cbbc-415c-9c22-21e65ad6c33f");

    private static final Set<PlayerEntity> done = Collections.newSetFromMap(new WeakHashMap<>());

    private static URL contributorJsonFile;
    private static InputStream file;

    static {
        try {
            contributorJsonFile = new URL("https://github.com/HuskysDevelopmentTeam/Neutronia/blob/module-system/contributors.properties");
            file = contributorJsonFile.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int localPatronTier = 0;
    public static String featuredPatron = "";

    public static void init() {
        new ThreadContributorListLoader();
        System.out.print(file.toString() + "\n");
    }

    public static void onRenderPlayerPost(RenderPlayerEvent.Post event) {
        PlayerEntity player = event.getEntityPlayer();
        String uuid = PlayerEntity.getUuidFromProfile(player.getGameProfile()).toString();
        AbstractClientPlayerEntity clplayer = (AbstractClientPlayerEntity) event.getEntityPlayer();
        ScoreboardEntry info = ReflectionHelper.getPrivateValue(AbstractClientPlayerEntity.class, clplayer, LibObfuscation.PLAYER_INFO);
        Map<Type, Identifier> textures = ReflectionHelper.getPrivateValue(ScoreboardEntry.class, info, LibObfuscation.PLAYER_TEXTURES);
        if (!done.contains(player) && clplayer.method_3125()) {
            if (MOD_CAPE.contains(uuid)) {
                Identifier loc = new Identifier(Neutronia.MOD_ID, "textures/misc/mod_cape.png");
                textures.put(Type.CAPE, loc);
                textures.put(Type.ELYTRA, loc);
                done.add(player);
            }
            if (TEAM_CAPE.contains(uuid)) {
                Identifier loc = new Identifier(Neutronia.MOD_ID, "textures/misc/team_cape.png");
                textures.put(Type.CAPE, loc);
                textures.put(Type.ELYTRA, loc);
                done.add(player);
            }
            if (MCA_CAPE.contains(uuid)) {
                Identifier loc = new Identifier(Neutronia.MOD_ID, "textures/misc/mca_cape.png");
                textures.put(Type.CAPE, loc);
                textures.put(Type.ELYTRA, loc);
                done.add(player);
            }
        }
    }

    private static void load(Properties props) {
        List<String> allPatrons = new ArrayList<>(props.size());

        String name = MinecraftClient.getInstance().getSession().getUsername().toLowerCase();
        props.forEach((k, v) -> {
            String key = (String) k;
            String value = (String) v;

            int tier = Integer.parseInt(value);
            if (tier < 10)
                allPatrons.add(key);

            if (key.toLowerCase().equals(name))
                localPatronTier = tier;
        });

        if (!allPatrons.isEmpty())
            featuredPatron = allPatrons.get((int) (Math.random() * allPatrons.size()));
    }

    private static class ThreadContributorListLoader extends Thread {

        public ThreadContributorListLoader() {
            setName("Neutronia Contributor Loading Thread");
            setDaemon(true);
            start();
        }

        @Override
        public void run() {
            try {
                URL url = new URL("https://raw.githubusercontent.com/HuskysDevelopmentTeam/Neutronia/module-system/contributors.properties");
                Properties props = new Properties();
                try (InputStreamReader reader = new InputStreamReader(url.openStream())) {
                    props.load(reader);
                    load(props);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
*/
