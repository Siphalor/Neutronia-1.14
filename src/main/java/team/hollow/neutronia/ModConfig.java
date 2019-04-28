package team.hollow.neutronia;

import me.sargunvohra.mcmods.autoconfig1.ConfigData;
import me.sargunvohra.mcmods.autoconfig1.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1.serializer.PartitioningSerializer;
import me.sargunvohra.mcmods.autoconfig1.shadowed.blue.endless.jankson.Comment;

@Config(name = Neutronia.MOD_ID)
@Config.Gui.Background("minecraft:textures/block/oak_planks.png")
@Config.Gui.CategoryBackground(category = "common", background = "minecraft:textures/block/stone.png")
public class ModConfig extends PartitioningSerializer.GlobalData {

    @ConfigEntry.Category("client")
    @ConfigEntry.Gui.TransitiveObject
    public Client client = new Client();

    @Config(name = "client")
    public static class Client implements ConfigData {
        @Comment("Adds more informative text on the main menu")
        public boolean mainMenuExtra = false;

        @Comment("Adds more info about progress on splash screen")
        public boolean splashScreenExtra = false;
    }

    @ConfigEntry.Category("common")
    @ConfigEntry.Gui.TransitiveObject
    public Common common = new Common();

    @Config(name = "common")
    public static class Common implements ConfigData {

        @Comment("Replaces normal biomes with mini biomes")
        public boolean doMiniBiomes = false;

    }
}