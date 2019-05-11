package team.hollow.neutronia;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.NopeDecoratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.StructureFeature;
import team.hollow.neutronia.client.renderer.FlyingLanternEntityRenderer;
import team.hollow.neutronia.entity.FlyingLanternEntity;
import team.hollow.neutronia.world.gen.features.pillager_mansion.PillagerMansionFeature;
import team.hollow.neutronia.world.gen.features.pillager_mansion.PillagerMansionFeatureConfig;
import team.hollow.neutronia.world.gen.features.pillager_mansion.PillagerMansionGenerator;
import team.hollow.neutronia.world.gen.features.totem_poles.TotemPoleFeature;
import team.hollow.neutronia.world.gen.features.totem_poles.TotemPoleGenerator;

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

    public static final StructurePieceType TOTEM_POLE_PIECE_TYPE = Registry.register(
            Registry.STRUCTURE_PIECE,
            new Identifier(Neutronia.MOD_ID, "totem_pole"),
            TotemPoleGenerator.Piece::new
    );
    public static final StructureFeature<DefaultFeatureConfig> TOTEM_POLE_FEATURE = Registry.register(
            Registry.FEATURE,
            new Identifier(Neutronia.MOD_ID, "totem_pole"),
            new TotemPoleFeature()
    );
    public static final StructureFeature<?> TOTEM_POLE_STRUCTURE = Registry.register(
            Registry.STRUCTURE_FEATURE,
            new Identifier(Neutronia.MOD_ID, "totem_pole"),
            TOTEM_POLE_FEATURE
    );

    @Override
    public void onInitialize() {
        EntityRendererRegistry.INSTANCE.register(FlyingLanternEntity.class, (entityRenderDispatcher, context) -> new FlyingLanternEntityRenderer(entityRenderDispatcher));

        Feature.STRUCTURES.put("neutronia:pillager_mansion", PILLAGER_MANSION_FEATURE);
        Feature.STRUCTURES.put("neutronia:totem_pole", TOTEM_POLE_FEATURE);

        for (Biome b : Registry.BIOME) {
            if (b.getCategory() != Biome.Category.OCEAN && b.getCategory() != Biome.Category.RIVER) {
                b.addStructureFeature(PILLAGER_MANSION_FEATURE, new PillagerMansionFeatureConfig(1));
                b.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, Biome.configureFeature(PILLAGER_MANSION_FEATURE, new PillagerMansionFeatureConfig(1),
                        Decorator.NOPE, new NopeDecoratorConfig()));
            }
            if (b.getCategory() == Biome.Category.TAIGA || b.getCategory() == Biome.Category.ICY) {
                b.addStructureFeature(TOTEM_POLE_FEATURE, new DefaultFeatureConfig());
                b.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, Biome.configureFeature(TOTEM_POLE_FEATURE, new DefaultFeatureConfig(), Decorator.CHANCE_PASSTHROUGH,
                        new ChanceDecoratorConfig(40)));
            }
        }
    }
}