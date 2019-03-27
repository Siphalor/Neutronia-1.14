package team.hollow.neutronia.world.biome;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.*;

public class NBiome extends Biome {

    protected boolean genDoubleGrass = false;
    protected int doubleGrassPerChunk;

    protected static final BlockState SAND = Blocks.SAND.getDefaultState();
    protected static final BlockState RED_SAND = Blocks.RED_SAND.getDefaultState();
    protected static final BlockState HARDENED_CLAY = Blocks.TERRACOTTA.getDefaultState();
    protected static final BlockState GRASS = Blocks.GRASS.getDefaultState();
    protected static final BlockState DIRT = Blocks.DIRT.getDefaultState();
    protected static final BlockState COARSE_DIRT = Blocks.COARSE_DIRT.getDefaultState();
    protected static final BlockState PODZOL = Blocks.PODZOL.getDefaultState();
    protected static final BlockState CLAY = Blocks.CLAY.getDefaultState();
    protected static final BlockState OAK_LEAVES = Blocks.OAK_LEAVES.getDefaultState().with(LeavesBlock.PERSISTENT, false);
    protected static final BlockState JUNGLE_LEAVES = Blocks.JUNGLE_LEAVES.getDefaultState().with(LeavesBlock.PERSISTENT, false);
    protected static final BlockState PACKED_ICE = Blocks.PACKED_ICE.getDefaultState();
    protected static final BlockState SNOW = Blocks.SNOW.getDefaultState();
    protected static final BlockState WATER_LILY = Blocks.LILY_PAD.getDefaultState();
    protected static final BlockState JUNGLE_LOG = Blocks.JUNGLE_LOG.getDefaultState();
    protected static final BlockState OAK_LOG = Blocks.OAK_LOG.getDefaultState();

    protected static final PineTreeFeature PINE_TREE = new PineTreeFeature(DefaultFeatureConfig::deserialize);
    protected static final SpruceTreeFeature SPRUCE_TREE = new SpruceTreeFeature(DefaultFeatureConfig::deserialize, false);
    protected static final BirchTreeFeature SUPER_BIRCH_TREE = new BirchTreeFeature(DefaultFeatureConfig::deserialize, false, true);
    protected static final BirchTreeFeature BIRCH_TREE = new BirchTreeFeature(DefaultFeatureConfig::deserialize, false, false);
    protected static final DarkOakTreeFeature ROOF_TREE = new DarkOakTreeFeature(DefaultFeatureConfig::deserialize, false);
    protected static final SavannaTreeFeature SAVANNA_TREE = new SavannaTreeFeature(DefaultFeatureConfig::deserialize, false);

    public NBiome(Settings properties) {
        super(properties);
    }

}