package team.abnormals.test;

import net.fabricmc.api.ModInitializer;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.StructureFeature;
import team.abnormals.neutronia.Neutronia;

public class ExampleMod implements ModInitializer {

	public static final StructurePieceType pillagerMansionPieceType = Registry.register(Registry.STRUCTURE_PIECE, new Identifier(Neutronia.MODID, "pillager_mansion_piece"), PillagerOutpostGenerator.Piece::new);
	public static final StructureFeature<PillagerOutpostFeatureConfig> pillagerMansionFeature = Registry.register(Registry.FEATURE, new Identifier(Neutronia.MODID, "pillager_mansion_feature"), new PillagerOutpostFeature());
	public static final StructureFeature<?> pillagerMansion = Registry.register(Registry.STRUCTURE_FEATURE, new Identifier(Neutronia.MODID, "pillager_mansion"), pillagerMansionFeature);

	@Override
	public void onInitialize() {
		Feature.STRUCTURES.put("Pillager_Mansion", pillagerMansionFeature);

		for(Biome biome : Registry.BIOME)
		{
			if(biome.getCategory() != Biome.Category.OCEAN && biome.getCategory() != Biome.Category.RIVER)
			{
				biome.addStructureFeature(pillagerMansionFeature, new PillagerOutpostFeatureConfig(10000000));
				biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, Biome.configureFeature(pillagerMansionFeature, new PillagerOutpostFeatureConfig(100), Decorator.CHANCE_PASSTHROUGH, new ChanceDecoratorConfig(10)));
			}
		}

	}
}