package team.hollow.test;

import net.fabricmc.api.ModInitializer;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.StructureFeature;
import team.hollow.neutronia.Neutronia;

public class ExampleMod implements ModInitializer {

	public static final StructurePieceType PILLAGER_MANSION_PIECE = Registry.register(Registry.STRUCTURE_PIECE, new Identifier(Neutronia.MOD_ID, "pillager_mansion_piece"), PillagerMansionGenerator.Piece::new);
	public static final StructureFeature<PillagerMansionFeatureConfig> PILLAGER_MANSION_FEATURE = Registry.register(Registry.FEATURE, new Identifier(Neutronia.MOD_ID, "pillager_mansion_feature"), new PillagerMansionFeature());

	@Override
	public void onInitialize() {
		Registry.register(Registry.STRUCTURE_FEATURE, new Identifier(Neutronia.MOD_ID, "pillager_mansion"), PILLAGER_MANSION_FEATURE);

		Feature.STRUCTURES.put("Pillager_Mansion", PILLAGER_MANSION_FEATURE);

		for(Biome biome : Registry.BIOME)
		{
			if(biome.getCategory() != Biome.Category.OCEAN && biome.getCategory() != Biome.Category.RIVER)
			{
//				biome.addStructureFeature(PILLAGER_MANSION_FEATURE, new PillagerMansionFeatureConfig(100000));
//				biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, Biome.configureFeature(PILLAGER_MANSION_FEATURE, new PillagerMansionFeatureConfig(100000), Decorator.CHANCE_PASSTHROUGH, new ChanceDecoratorConfig(0)));
			}
		}

	}
}