package team.hollow.neutronia.world.gen.features;

import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import team.hollow.neutronia.world.MyFeature;
import team.hollow.neutronia.world.MyGenerator;

public class OreGeneration {

    public static final StructurePieceType myStructurePieceType = Registry.register(Registry.STRUCTURE_PIECE, "my_piece", MyGenerator.Piece::new);
    public static final StructureFeature<DefaultFeatureConfig> myFeature = Registry.register(Registry.FEATURE, "my_feature", new MyFeature());
    public static final StructureFeature<?> myStructure = Registry.register(Registry.STRUCTURE_FEATURE, "my_structure", myFeature);

    public static void registerOres() {
        for (Biome biome : Registry.BIOME) {

        }
    }
}