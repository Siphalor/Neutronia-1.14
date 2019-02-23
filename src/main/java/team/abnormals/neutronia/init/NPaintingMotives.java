package team.abnormals.neutronia.init;

import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.abnormals.neutronia.Neutronia;

public class NPaintingMotives {

    //Original paintings
    public static final PaintingMotive A_CRUEL_FORTRESS = register("a_cruel_fortress", 32, 32);
    public static final PaintingMotive ALEX = register("alex", 16, 16);
    public static final PaintingMotive CAKE = register("cake", 16, 16);
    public static final PaintingMotive COOKIE = register("cookie", 16, 16);
    public static final PaintingMotive DIAMOND = register("diamond", 32, 16);
    public static final PaintingMotive ECHOLOCATION = register("echolocation", 32, 32);
    public static final PaintingMotive END_ISLAND = register("end_island", 16, 16);
    public static final PaintingMotive ENDER_DRAGON = register("ender_dragon", 16, 32);
    public static final PaintingMotive ENDER_THING = register("ender_thing", 32, 16);
    public static final PaintingMotive FLAT_EARTH = register("flat_earth", 48, 32);
    public static final PaintingMotive GLOOP = register("gloop", 16, 16);
    public static final PaintingMotive GUARDIAN = register("guardian", 48, 32);
    public static final PaintingMotive GUARDIAN_ALT = register("guardian_alt", 48, 32);
    public static final PaintingMotive HIEROGLYPHS = register("hieroglyphs", 32, 32);
    public static final PaintingMotive ISLANDS = register("islands", 16, 32);
    public static final PaintingMotive LAYERED_WORLD = register("layered_world", 32, 32);
    public static final PaintingMotive MCA = register("mca", 32, 32);
    public static final PaintingMotive SAVANNA_SUNRISE = register("savanna_sunrise", 16, 32);
    public static final PaintingMotive SAVANNA_SUNSET = register("savanna_sunset", 48, 16);
    public static final PaintingMotive SHULKER = register("shulker", 32, 32);
    public static final PaintingMotive STEVE = register("steve", 16, 16);
    public static final PaintingMotive SWIPER = register("swiper", 16, 16);
    public static final PaintingMotive TAIGA_LAKE = register("taiga_lake", 16, 16);
    public static final PaintingMotive THE_DEVIL_BELOW = register("the_devil_below", 32, 32);
    public static final PaintingMotive THE_END = register("the_end", 32, 16);
    public static final PaintingMotive THE_NETHER = register("the_nether", 32, 32);
    public static final PaintingMotive WATERFALL = register("waterfall", 32, 32);
    public static final PaintingMotive WILLOW_TREE = register("willow_tree", 16, 16);
    public static final PaintingMotive WINTER_WONDERLAND = register("winter_wonderland", 32, 16);
    public static final PaintingMotive X_MARKS_THE_SPOT = register("x_marks_the_spot", 32, 32);

    private static PaintingMotive register(String string_1, int width, int textureY) {
        return Registry.register(Registry.MOTIVE, new Identifier(Neutronia.MODID, string_1), new PaintingMotive(width, textureY));
    }

}