package team.abnormals.neutronia.init;

import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.abnormals.neutronia.Neutronia;

public class NPaintingMotives {

    public static final PaintingMotive TAIGA_LAKE = register("taiga_lake", 16, 16);
    public static final PaintingMotive END_ISLAND = register("end_island", 16, 16);
    public static final PaintingMotive THE_NETHER = register("the_nether", 32, 32);
    public static final PaintingMotive FOUR_X_FOUR_BOOKSHELF = register("4x4_bookshelf", 32, 32);
    public static final PaintingMotive BLUE_AND_GOLD_FANCY_BANNER = register("blue_and_gold_fancy_banner", 16, 32);
    public static final PaintingMotive BLUE_BANNER = register("blue_banner", 16, 32);
    public static final PaintingMotive BLUE_FANCY_BANNER = register("blue_fancy_banner", 16, 32);
    public static final PaintingMotive DARK_BLUE_AND_GOLD_FANCY_BANNER = register("dark_blue_and_gold_fancy_banner", 16, 32);
    public static final PaintingMotive OLD_GREEN_BANNER = register("old_green_banner", 16, 32);
    public static final PaintingMotive PURPLE_PIRATE_BANNER = register("purple_pirate_banner", 16, 32);
    public static final PaintingMotive RED_FANCY_BANNER = register("red_fancy_banner", 16, 32);

    private static PaintingMotive register(String string_1, int width, int textureY) {
        return Registry.register(Registry.MOTIVE, new Identifier(Neutronia.MODID, string_1), new PaintingMotive(width, textureY));
    }

}