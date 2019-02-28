package team.hollow.neutronia.world.gen.feature;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.ModifiableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import team.hollow.neutronia.blocks.NeutroniaLeavesBlock;
import team.hollow.neutronia.init.NBlocks;

import java.util.Random;
import java.util.Set;

public class WorldGenPalmTree extends AbstractTreeFeature<DefaultFeatureConfig>
{
    private static final BlockState LOG = NBlocks.PALM_LOG.getDefaultState();
    
    private static final BlockState LEAF = NBlocks.PALM_LEAVES.getDefaultState()
    		.with(NeutroniaLeavesBlock.PERSISTENT, Boolean.FALSE);
    
    private static final BlockState COCONUT = NBlocks.COCONUT.getDefaultState();
    
	public WorldGenPalmTree()
	{
		super(DefaultFeatureConfig::deserialize, true);
	}

	@Override
	public boolean generate(Set<BlockPos> pos, ModifiableTestableWorld modifiableTestableWorld, Random random_1, BlockPos blockPos_1) {
        int int_1 = random_1.nextInt(3) + random_1.nextInt(3) + 5;
        boolean boolean_1 = true;
        if (blockPos_1.getY() >= 1 && blockPos_1.getY() + int_1 + 1 <= 256) {
            int int_8;
            int int_9;
            for(int int_2 = blockPos_1.getY(); int_2 <= blockPos_1.getY() + 1 + int_1; ++int_2) {
                int int_3 = 1;
                if (int_2 == blockPos_1.getY()) {
                    int_3 = 0;
                }

                if (int_2 >= blockPos_1.getY() + 1 + int_1 - 2) {
                    int_3 = 2;
                }

                BlockPos.Mutable blockPos$Mutable_1 = new BlockPos.Mutable();

                for(int_8 = blockPos_1.getX() - int_3; int_8 <= blockPos_1.getX() + int_3 && boolean_1; ++int_8) {
                    for(int_9 = blockPos_1.getZ() - int_3; int_9 <= blockPos_1.getZ() + int_3 && boolean_1; ++int_9) {
                        if (int_2 >= 0 && int_2 < 256) {
                            if (!canTreeReplace(modifiableTestableWorld, blockPos$Mutable_1.set(int_8, int_2, int_9))) {
                                boolean_1 = false;
                            }
                        } else {
                            boolean_1 = false;
                        }
                    }
                }
            }

            if (!boolean_1) {
                return false;
            } else if (isNaturalDirtOrGrass(modifiableTestableWorld, blockPos_1.down()) && blockPos_1.getY() < 256 - int_1 - 1) {
                this.setToDirt(modifiableTestableWorld, blockPos_1.down());
                Direction direction_1 = Direction.Type.HORIZONTAL.random(random_1);
                int int_6 = int_1 - random_1.nextInt(4) - 1;
                int int_7 = 3 - random_1.nextInt(3);
                int_8 = blockPos_1.getX();
                int_9 = blockPos_1.getZ();
                int int_10 = 0;

                int int_17;
                for(int int_11 = 0; int_11 < int_1; ++int_11) {
                    int_17 = blockPos_1.getY() + int_11;
                    if (int_11 >= int_6 && int_7 > 0) {
                        int_8 += direction_1.getOffsetX();
                        int_9 += direction_1.getOffsetZ();
                        --int_7;
                    }

                    BlockPos blockPos_2 = new BlockPos(int_8, int_17, int_9);
                    if (isAirOrLeaves(modifiableTestableWorld, blockPos_2)) {
                        this.addLog(pos, modifiableTestableWorld, blockPos_2);
                        int_10 = int_17;
                    }
                }

                BlockPos blockPos_3 = new BlockPos(int_8, int_10, int_9);

                int int_18;
                for(int_17 = -3; int_17 <= 3; ++int_17) {
                    for(int_18 = -3; int_18 <= 3; ++int_18) {
                        if (Math.abs(int_17) != 3 || Math.abs(int_18) != 3) {
                            this.addLeaves(modifiableTestableWorld, blockPos_3.add(int_17, 0, int_18));
                        }
                    }
                }

                blockPos_3 = blockPos_3.up();

                for(int_17 = -1; int_17 <= 1; ++int_17) {
                    for(int_18 = -1; int_18 <= 1; ++int_18) {
                        this.addLeaves(modifiableTestableWorld, blockPos_3.add(int_17, 0, int_18));
                    }
                }

                this.addLeaves(modifiableTestableWorld, blockPos_3.east(2));
                this.addLeaves(modifiableTestableWorld, blockPos_3.west(2));
                this.addLeaves(modifiableTestableWorld, blockPos_3.south(2));
                this.addLeaves(modifiableTestableWorld, blockPos_3.north(2));
                int_8 = blockPos_1.getX();
                int_9 = blockPos_1.getZ();
                Direction direction_2 = Direction.Type.HORIZONTAL.random(random_1);
                if (direction_2 != direction_1) {
                    int_17 = int_6 - random_1.nextInt(2) - 1;
                    int_18 = 1 + random_1.nextInt(3);
                    int_10 = 0;

                    int int_23;
                    for(int int_19 = int_17; int_19 < int_1 && int_18 > 0; --int_18) {
                        if (int_19 >= 1) {
                            int_23 = blockPos_1.getY() + int_19;
                            int_8 += direction_2.getOffsetX();
                            int_9 += direction_2.getOffsetZ();
                            BlockPos blockPos_4 = new BlockPos(int_8, int_23, int_9);
                            if (isAirOrLeaves(modifiableTestableWorld, blockPos_4)) {
                                this.addLog(pos, modifiableTestableWorld, blockPos_4);
                                int_10 = int_23;
                            }
                        }

                        ++int_19;
                    }

                    if (int_10 > 0) {
                        BlockPos blockPos_5 = new BlockPos(int_8, int_10, int_9);

                        int int_24;
                        for(int_23 = -2; int_23 <= 2; ++int_23) {
                            for(int_24 = -2; int_24 <= 2; ++int_24) {
                                if (Math.abs(int_23) != 2 || Math.abs(int_24) != 2) {
                                    this.addLeaves(modifiableTestableWorld, blockPos_5.add(int_23, 0, int_24));
                                }
                            }
                        }

                        blockPos_5 = blockPos_5.up();

                        for(int_23 = -1; int_23 <= 1; ++int_23) {
                            for(int_24 = -1; int_24 <= 1; ++int_24) {
                                this.addLeaves(modifiableTestableWorld, blockPos_5.add(int_23, 0, int_24));
                            }
                        }
                    }
                }

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
	}

    private void addLog(Set<BlockPos> set_1, ModifiableWorld modifiableWorld_1, BlockPos blockPos_1) {
        this.setBlockState(set_1, modifiableWorld_1, blockPos_1, LOG);
    }

    private void addLeaves(ModifiableTestableWorld modifiableTestableWorld, BlockPos blockPos_1) {
        if (isAirOrLeaves(modifiableTestableWorld, blockPos_1)) {
            this.setBlockState(modifiableTestableWorld, blockPos_1, LEAF);
        }
    }
	
}