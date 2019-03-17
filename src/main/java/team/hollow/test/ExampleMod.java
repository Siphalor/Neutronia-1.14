package team.hollow.test;

import net.fabricmc.api.ModInitializer;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.NopeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.StructureFeature;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.world.gen.RitualSiteGenerator;
import team.hollow.neutronia.world.gen.features.*;

public class ExampleMod implements ModInitializer {

    public static final StructureFeature<PillagerMansionFeatureConfig> PILLAGER_MANSION_FEATURE = Registry.register(
            Registry.FEATURE,
            new Identifier(Neutronia.MOD_ID, "pillager_mansion"),
            new PillagerMansionFeature()
    );
    public static final StructureFeature<?> PILLAGER_MANSION_STRUCTURE = Registry.register(
            Registry.STRUCTURE_FEATURE,
            new Identifier(Neutronia.MOD_ID, "pillager_mansion"),
            PILLAGER_MANSION_FEATURE
    );
    public static final StructurePieceType PILLAGER_MANSION_PIECE = Registry.register(
            Registry.STRUCTURE_PIECE,
            new Identifier(Neutronia.MOD_ID, "pillager_mansion"),
            PillagerMansionGenerator.Piece::new
    );

    public static final StructureFeature<RitualSiteFeatureConfig> RITUAL_SITE_FEATURE = Registry.register(
            Registry.FEATURE,
            new Identifier(Neutronia.MOD_ID, "ritual_site"),
            new RitualSiteFeature(RitualSiteFeatureConfig::deserialize)
    );
    public static final StructureFeature<?> RITUAL_SITE_STRUCTURE = Registry.register(
            Registry.STRUCTURE_FEATURE,
            new Identifier(Neutronia.MOD_ID, "ritual_site"),
            RITUAL_SITE_FEATURE
    );
    public static final StructurePieceType RITUAL_SITE_PIECE_TYPE = Registry.register(
            Registry.STRUCTURE_PIECE,
            new Identifier(Neutronia.MOD_ID, "ritual_site"),
            RitualSiteGenerator.Piece::new
    );

    @Override
    public void onInitialize() {
        Feature.STRUCTURES.put("pillager_mansion", PILLAGER_MANSION_FEATURE);
        for (Biome b : Registry.BIOME) {
            if (b.getCategory() != Biome.Category.OCEAN && b.getCategory() != Biome.Category.RIVER) {
				b.addStructureFeature(PILLAGER_MANSION_FEATURE, new PillagerMansionFeatureConfig(1));
				b.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, Biome.configureFeature(PILLAGER_MANSION_FEATURE, new PillagerMansionFeatureConfig(1),
                        Decorator.NOPE, new NopeDecoratorConfig()));
            }
        }

        Feature.STRUCTURES.put("ritual_site", RITUAL_SITE_FEATURE);
        for(Biome b: Registry.BIOME) {
            if(b.getCategory() == Biome.Category.TAIGA) {
                b.addStructureFeature(RITUAL_SITE_FEATURE, new RitualSiteFeatureConfig(1));
                b.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, Biome.configureFeature(RITUAL_SITE_FEATURE, new RitualSiteFeatureConfig(1),
                        Decorator.NOPE, new NopeDecoratorConfig()));
            }
        }
    }
}