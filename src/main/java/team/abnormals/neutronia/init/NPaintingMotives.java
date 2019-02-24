package team.abnormals.neutronia.init;

import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.abnormals.neutronia.Neutronia;

public class NPaintingMotives {

    //Original paintings
    public static final PaintingMotive A_CRUEL_FORTRESS = register("a_cruel_fortress", 32, 32);
    public static final PaintingMotive ALEX = register("alex", 16, 16);
    public static final PaintingMotive BLAZING_HAIR = register("blazing_hair", 48, 48);
    public static final PaintingMotive BLAZING_SILHOUETTE = register("blazing_silhouette", 48, 48);
    public static final PaintingMotive BLUE = register("blue", 16, 16);
    public static final PaintingMotive CAKE = register("cake", 16, 16);
    public static final PaintingMotive CLUCKER = register("cake", 16, 16);
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
    public static final PaintingMotive GUARDIAN_CYCLOPS = register("guardian_cyclops", 16, 16);
    public static final PaintingMotive HIEROGLYPHS = register("hieroglyphs", 32, 32);
    public static final PaintingMotive ISLANDS = register("islands", 16, 32);
    public static final PaintingMotive LAYERED_WORLD = register("layered_world", 32, 32);
    public static final PaintingMotive MCA = register("mca", 32, 32);
    public static final PaintingMotive PIXEL_STUDIO = register("pixel_studio", 32, 32);
    public static final PaintingMotive REUBEN = register("reuben", 32, 32);
    public static final PaintingMotive SAVANNA_SUNRISE = register("savanna_sunrise", 16, 32);
    public static final PaintingMotive SAVANNA_SUNSET = register("savanna_sunset", 48, 16);
    public static final PaintingMotive SHULKER = register("shulker", 32, 32);
    public static final PaintingMotive SLAM_A_COW = register("slam_a_cow", 16, 16);
    public static final PaintingMotive SLIME = register("slime", 16, 16);
    public static final PaintingMotive SO_YOU_THINK_YOU_COUL_FIGHT_A_GOD = register("so_you_think_you_can_fight_a_god", 128, 80);
    public static final PaintingMotive STEVE = register("steve", 16, 16);
    public static final PaintingMotive STICK_WITH_ME = register("stick_with_me", 32, 32);
    public static final PaintingMotive SWIPER = register("swiper", 16, 16);
    public static final PaintingMotive TAIGA_LAKE = register("taiga_lake", 16, 16);
    public static final PaintingMotive THE_DEVIL_BELOW = register("the_devil_below", 32, 32);
    public static final PaintingMotive THE_DRAGON = register("the_dragon", 32, 32);
    public static final PaintingMotive THE_END = register("the_end", 32, 16);
    public static final PaintingMotive THE_NETHER = register("the_nether", 32, 32);
    public static final PaintingMotive WATERFALL = register("waterfall", 32, 32);
    public static final PaintingMotive WILLOW_TREE = register("willow_tree", 16, 16);
    public static final PaintingMotive WINTER_WONDERLAND = register("winter_wonderland", 32, 16);
    public static final PaintingMotive X_MARKS_THE_SPOT = register("x_marks_the_spot", 32, 32);

    private static PaintingMotive register(String string_1, int width, int textureY) {
        return Registry.register(Registry.MOTIVE, new Identifier(Neutronia.MOD_ID, string_1), new PaintingMotive(width, textureY));
    }

}