package team.hollow.neutronia.modules.variation;

import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.hollow.abnormalib.modules.api.features.OptionalFeature;
import team.hollow.neutronia.Neutronia;

public class PaintingMotivesFeature extends OptionalFeature {
	public PaintingMotivesFeature() {
		super("paintings", "Adds more painting motives");
	}

	@Override
	protected void applyEnabled() {
		register("a_cruel_fortress", 32, 32);
		register("alex", 16, 16);
		register("blazing_hair", 48, 48);
		register("blazing_silhouette", 48, 48);
		register("blue", 16, 16);
		register("cake", 16, 16);
		register("cake", 16, 16);
		register("cookie", 16, 16);
		register("diamond", 32, 16);
		register("echolocation", 32, 32);
		register("end_island", 16, 16);
		register("ender_dragon", 16, 32);
		register("ender_thing", 32, 16);
		register("flat_earth", 48, 32);
		register("gloop", 16, 16);
		register("guardian", 48, 32);
		register("guardian_alt", 48, 32);
		register("guardian_cyclops", 16, 16);
		register("hieroglyphs", 32, 32);
		register("islands", 16, 32);
		register("layered_world", 32, 32);
		register("mca", 32, 32);
		register("pixel_studio", 32, 32);
		register("reuben", 32, 32);
		register("savanna_sunrise", 16, 32);
		register("savanna_sunset", 48, 16);
		register("shulker", 32, 32);
		register("slam_a_cow", 16, 16);
		register("slime", 16, 16);
		register("so_you_think_you_can_fight_a_god", 128, 80);
		register("steve", 16, 16);
		register("stick_by_me", 32, 32);
		register("swiper", 16, 16);
		register("taiga_lake", 16, 16);
		register("the_devil_below", 32, 32);
		register("the_dragon", 32, 32);
		register("the_end", 32, 16);
		register("the_nether", 32, 32);
		register("waterfall", 32, 32);
		register("willow_tree", 16, 16);
		register("winter_wonderland", 32, 16);
		register("x_marks_the_spot", 32, 32);
	}

	private static PaintingMotive register(String string_1, int width, int textureY) {
		return Registry.register(Registry.MOTIVE, new Identifier(Neutronia.MOD_ID, string_1), new PaintingMotive(width, textureY));
	}

}
