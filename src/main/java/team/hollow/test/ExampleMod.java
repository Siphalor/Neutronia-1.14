package team.hollow.test;

import net.fabricmc.api.ModInitializer;
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
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.world.gen.RitualSiteGenerator;
import team.hollow.neutronia.world.gen.features.RitualSiteFeature;
import team.hollow.neutronia.world.gen.features.RitualSiteFeatureConfig;
import team.hollow.neutronia.world.gen.features.celebrating_vinny.CelebratingVinnyFeature;
import team.hollow.neutronia.world.gen.features.celebrating_vinny.CelebratingVinnyGenerator;
import team.hollow.neutronia.world.gen.features.pillager_mansion.PillagerMansionFeature;
import team.hollow.neutronia.world.gen.features.pillager_mansion.PillagerMansionFeatureConfig;
import team.hollow.neutronia.world.gen.features.pillager_mansion.PillagerMansionGenerator;
import team.hollow.neutronia.world.gen.features.totem_poles.TotemPoleFeature;
import team.hollow.neutronia.world.gen.features.totem_poles.TotemPoleGenerator;

import java.util.Calendar;

public class ExampleMod implements ModInitializer {

    Calendar calendar_1 = Calendar.getInstance();

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

    public static final StructurePieceType CELEBRATING_VINNY_PIECE_TYPE = Registry.register(
            Registry.STRUCTURE_PIECE,
            new Identifier(Neutronia.MOD_ID, "celebrating_vinny"),
            CelebratingVinnyGenerator.Piece::new
    );
    public static final StructureFeature<DefaultFeatureConfig> CELEBRATING_VINNY_FEATURE = Registry.register(
            Registry.FEATURE,
            new Identifier(Neutronia.MOD_ID, "celebrating_vinny"),
            new CelebratingVinnyFeature()
    );
    public static final StructureFeature<?> CELEBRATING_VINNY_STRUCTURE = Registry.register(
            Registry.STRUCTURE_FEATURE,
            new Identifier(Neutronia.MOD_ID, "celebrating_vinny"),
            CELEBRATING_VINNY_FEATURE
    );

    @Override
    public void onInitialize() {
        Feature.STRUCTURES.put("neutronia:pillager_mansion", PILLAGER_MANSION_FEATURE);
        Feature.STRUCTURES.put("neutronia:ritual_site", RITUAL_SITE_FEATURE);
        Feature.STRUCTURES.put("neutronia:totem_pole", TOTEM_POLE_FEATURE);
//        if(calendar_1.get(Calendar.MONTH) + 1 == 4 && calendar_1.get(Calendar.DATE) >= 9) Feature.STRUCTURES.put("neutronia:celebrating_vinny", CELEBRATING_VINNY_FEATURE);

        for (Biome b : Registry.BIOME) {
            if (b.getCategory() != Biome.Category.OCEAN && b.getCategory() != Biome.Category.RIVER) {
                b.addStructureFeature(PILLAGER_MANSION_FEATURE, new PillagerMansionFeatureConfig(1));
                b.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, Biome.configureFeature(PILLAGER_MANSION_FEATURE, new PillagerMansionFeatureConfig(1),
                        Decorator.NOPE, new NopeDecoratorConfig()));
                /*if (calendar_1.get(Calendar.MONTH) + 1 == 4 && calendar_1.get(Calendar.DATE) >= 9) {
                    b.addStructureFeature(CELEBRATING_VINNY_FEATURE, new DefaultFeatureConfig());
                    b.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, Biome.configureFeature(CELEBRATING_VINNY_FEATURE, new DefaultFeatureConfig(),
                            Decorator.CHANCE_PASSTHROUGH, new ChanceDecoratorConfig(1000)));
                }*/
            }
            /*if (b.getCategory() == Biome.Category.TAIGA) {
                b.addStructureFeature(RITUAL_SITE_FEATURE, new RitualSiteFeatureConfig(1));
                b.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, Biome.configureFeature(RITUAL_SITE_FEATURE, new RitualSiteFeatureConfig(1),
                        Decorator.CHANCE_PASSTHROUGH, new ChanceDecoratorConfig(100)));
            }*/
            if (b.getCategory() == Biome.Category.TAIGA || b.getCategory() == Biome.Category.ICY) {
                b.addStructureFeature(TOTEM_POLE_FEATURE, new DefaultFeatureConfig());
                b.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, Biome.configureFeature(TOTEM_POLE_FEATURE, new DefaultFeatureConfig(), Decorator.CHANCE_PASSTHROUGH,
                        new ChanceDecoratorConfig(40)));
            }
        }
    }
}