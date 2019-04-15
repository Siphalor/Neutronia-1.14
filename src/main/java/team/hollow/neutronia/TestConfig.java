package team.hollow.neutronia;

import blue.endless.jankson.Comment;
import team.hollow.neutronia.configNew.annontations.ConfigFile;

@ConfigFile(name = "neutronia_config")
public class TestConfig {

    public Client client = new Client();
    public Common common = new Common();

    public static class Client {

        @Comment("Adds more informative text on the main menu")
        public boolean mainMenuExtra = false;

        @Comment("Adds more info about progress on splash screen")
        public boolean splashScreenExtra = false;

    }

    public static class Common {

        @Comment("Replaces normal biomes with mini biomes")
        public boolean doMiniBiomes = false;

    }

}