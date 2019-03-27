package team.hollow.neutronia;

import team.hollow.neutronia.config.ForgeConfigSpec;

public class NeutroniaConfig {

    public static void init(ForgeConfigSpec.Builder SERVER_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        new ClientFeatures(CLIENT_BUILDER);
    }

    public static class ClientFeatures {

        public static ForgeConfigSpec.BooleanValue mainMenuExtra;
        public static ForgeConfigSpec.BooleanValue splashScreenExtra;

        public ClientFeatures(ForgeConfigSpec.Builder BUILDER) {
            BUILDER.comment("Client Features");

            mainMenuExtra = BUILDER
                    .comment("Adds more informative text on the main menu")
                    .define("moreFeatures.mainMenu", true);
            splashScreenExtra = BUILDER
                    .comment("Adds more info about progress on splash screen")
                    .define("moreFeatures.splashScreen", true);
        }

    }

}