package team.abnormals.neutronia.init;

import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.abnormals.neutronia.Neutronia;

public class NPaintingMotives {

    //Original paintings
    public static final PaintingMotive TAIGA_LAKE = register("taiga_lake", 16, 16);
    public static final PaintingMotive END_ISLAND = register("end_island", 16, 16);
    public static final PaintingMotive THE_NETHER = register("the_nether", 32, 32);
    public static final PaintingMotive SAVANNA_SUNSET = register("savanna_sunset", 48, 16);
    public static final PaintingMotive SAVANNA_SUNRISE = register("savanna_sunrise", 16, 32);
    public static final PaintingMotive WILLOW_TREE = register("willow_tree", 16, 16);
    public static final PaintingMotive ECHOLOCATION = register("echolocation", 32, 32);
    public static final PaintingMotive WINTER_WONDERLAND = register("winter_wonderland", 32, 16);
    public static final PaintingMotive WATERFALL = register("waterfall", 32, 32);
    public static final PaintingMotive GLOOPP = register("gloop", 16, 16);
    public static final PaintingMotive HIEROGLYPHS = register("hieroglyphs", 32, 32);
    public static final PaintingMotive FLAT_EARTH = register("flat_earth", 48, 32);
    public static final PaintingMotive THE_DEVIL_BELOW = register("the_devil_below", 32, 32);
    public static final PaintingMotive DIAMOND = register("diamond", 16, 16);
    public static final PaintingMotive ENDER_THING = register("ender_thing", 32, 16);
    public static final PaintingMotive THE_END = register("the_end", 16, 16);
    public static final PaintingMotive X_MARKS_THE_SPOT = register("x_marks_the_spot", 32, 32);
    public static final PaintingMotive A_CRUEL_FORTRESS = register("a_cruel_fortress", 32, 32);
    public static final PaintingMotive ISLANDS = register("islands", 16, 32);
    public static final PaintingMotive SHULKER = register("shulker", 32, 32);
    public static final PaintingMotive ENDER_DRAGON = register("ender_dragon", 16, 32);

    //Borrowed 1x2 banners
    public static final PaintingMotive BLUE_AND_GOLD_FANCY_BANNER = register("blue_and_gold_fancy_banner", 16, 32);
    public static final PaintingMotive BLUE_BANNER = register("blue_banner", 16, 32);
    public static final PaintingMotive BLUE_FANCY_BANNER = register("blue_fancy_banner", 16, 32);
    public static final PaintingMotive DARK_BLUE_AND_GOLD_FANCY_BANNER = register("dark_blue_and_gold_fancy_banner", 16, 32);
    public static final PaintingMotive OLD_GREEN_BANNER = register("old_green_banner", 16, 32);
    public static final PaintingMotive PURPLE_PIRATE_BANNER = register("purple_pirate_banner", 16, 32);
    public static final PaintingMotive RED_FANCY_BANNER = register("red_fancy_banner", 16, 32);

    //Borrowed 2x2 paintings
    public static final PaintingMotive PIRATE_GIRL = register("pirate_girl", 32, 32);
    public static final PaintingMotive COWBOY = register("cowboy", 32, 32);
    public static final PaintingMotive KING = register("king", 32, 32);

    //Borrowed 2x2 decoration paintings
    public static final PaintingMotive IRON_SHIELD = register("iron_shield", 32, 32);
    public static final PaintingMotive RADIATION_SIGN = register("radiation_sign", 32, 32);
    public static final PaintingMotive RED_SHIELD = register("red_shield", 32, 32);
    public static final PaintingMotive SCARY_PUMPKIN = register("scary_pumpkin", 32, 32);
    public static final PaintingMotive WHITE_AND_BLUE_SHIELD = register("white_and_blue_shield", 32, 32);
    public static final PaintingMotive WOODEN_SHIELD = register("wooden_shield", 32, 32);
    public static final PaintingMotive FOUR_X_FOUR_BOOKSHELF = register("4x4_bookshelf", 32, 32);

    private static PaintingMotive register(String string_1, int width, int textureY) {
        return Registry.register(Registry.MOTIVE, new Identifier(Neutronia.MODID, string_1), new PaintingMotive(width, textureY));
    }

}