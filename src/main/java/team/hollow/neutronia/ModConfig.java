package team.hollow.neutronia;

import me.sargunvohra.mcmods.autoconfig.api.ConfigData;
import me.sargunvohra.mcmods.autoconfig.api.ConfigGuiEntry;
import me.sargunvohra.mcmods.autoconfig.shadowed.blue.endless.jankson.Comment;

public class ModConfig implements ConfigData {

    @ConfigGuiEntry(category = "client")
    @ConfigGuiEntry.Transitive
    public Client client = new Client();

    public static class Client {
        @Comment("Adds more informative text on the main menu")
        public boolean mainMenuExtra = false;

        @Comment("Adds more info about progress on splash screen")
        public boolean splashScreenExtra = false;
    }

    @ConfigGuiEntry(category = "common")
    @ConfigGuiEntry.Transitive
    public Common common = new Common();

    public static class Common {

        @Comment("Replaces normal biomes with mini biomes")
        public boolean doMiniBiomes = false;

    }
}