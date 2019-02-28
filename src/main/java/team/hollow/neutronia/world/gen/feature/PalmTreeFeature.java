package team.hollow.neutronia.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.ModifiableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import team.hollow.neutronia.init.NBlocks;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class PalmTreeFeature extends AbstractTreeFeature<DefaultFeatureConfig> {

    private static final BlockState LOG;
    private static final BlockState LOG_TOP;
    private static final BlockState LEAVES;

    static {
        LOG = NBlocks.PALM_LOG.getDefaultState();
        LOG_TOP = NBlocks.PALM_LOG_TOP.getDefaultState();
        LEAVES = NBlocks.PALM_LEAVES.getDefaultState();
    }

    public PalmTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function_1, boolean boolean_1) {
        super(function_1, boolean_1);
    }

    public boolean generate(Set<BlockPos> set_1, ModifiableTestableWorld modifiableTestableWorld_1, Random random_1, BlockPos position) {
        int int_1 = random_1.nextInt(3) + random_1.nextInt(3) + 5;
        boolean boolean_1 = true;
        if (position.getY() >= 1 && position.getY() + int_1 + 1 <= 256) {
            int int_8;
            int int_9;
            for (int int_2 = position.getY(); int_2 <= position.getY() + 1 + int_1; ++int_2) {
                int int_3 = 1;
                if (int_2 == position.getY()) {
                    int_3 = 0;
                }

                if (int_2 >= position.getY() + 1 + int_1 - 2) {
                    int_3 = 2;
                }

                BlockPos.Mutable blockPos$Mutable_1 = new BlockPos.Mutable();

                for (int_8 = position.getX() - int_3; int_8 <= position.getX() + int_3 && boolean_1; ++int_8) {
                    for (int_9 = position.getZ() - int_3; int_9 <= position.getZ() + int_3 && boolean_1; ++int_9) {
                        if (int_2 >= 0 && int_2 < 256) {
                            if (!canTreeReplace(modifiableTestableWorld_1, blockPos$Mutable_1.set(int_8, int_2, int_9))) {
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
            } else if (isNaturalDirtOrGrass(modifiableTestableWorld_1, position.down()) && position.getY() < 256 - int_1 - 1) {
                this.setToDirt(modifiableTestableWorld_1, position.down());
                Direction direction_1 = Direction.Type.HORIZONTAL.random(random_1);
                int int_6 = int_1 - random_1.nextInt(4) - 1;
                int int_7 = 3 - random_1.nextInt(3);
                int_8 = position.getX();
                int_9 = position.getZ();
                int int_10 = 0;

                int int_17;
                for (int int_11 = 0; int_11 < int_1; ++int_11) {
                    int_17 = position.getY() + int_11;
                    if (int_11 >= int_6 && int_7 > 0) {
                        int_8 += direction_1.getOffsetX();
                        int_9 += direction_1.getOffsetZ();
                        --int_7;
                    }

                    BlockPos blockPos_2 = new BlockPos(int_8, int_17, int_9);
                    if (isAirOrLeaves(modifiableTestableWorld_1, blockPos_2)) {
                        this.placeLog(set_1, modifiableTestableWorld_1, blockPos_2);
                        int_10 = int_17;
                    }
                }

                BlockPos blockPos_3 = new BlockPos(int_8, int_10, int_9);

                int int_18;
                for (int_17 = -3; int_17 <= 3; ++int_17) {
                    for (int_18 = -3; int_18 <= 3; ++int_18) {
                        if (Math.abs(int_17) != 3 || Math.abs(int_18) != 3) {
                            this.placeLeaves(modifiableTestableWorld_1, blockPos_3.add(int_17, 0, int_18));
                        }
                    }
                }

                blockPos_3 = blockPos_3.up();

                for (int_17 = -1; int_17 <= 1; ++int_17) {
                    for (int_18 = -1; int_18 <= 1; ++int_18) {
                        this.placeLeaves(modifiableTestableWorld_1, blockPos_3.add(int_17, 0, int_18));
                    }
                }

                this.placeLeaves(modifiableTestableWorld_1, blockPos_3.east(2));
                this.placeLeaves(modifiableTestableWorld_1, blockPos_3.west(2));
                this.placeLeaves(modifiableTestableWorld_1, blockPos_3.south(2));
                this.placeLeaves(modifiableTestableWorld_1, blockPos_3.north(2));
                int_8 = position.getX();
                int_9 = position.getZ();
                Direction direction_2 = Direction.Type.HORIZONTAL.random(random_1);
                if (direction_2 != direction_1) {
                    int_17 = int_6 - random_1.nextInt(2) - 1;
                    int_18 = 1 + random_1.nextInt(3);
                    int_10 = 0;

                    int int_23;
                    for (int int_19 = int_17; int_19 < int_1 && int_18 > 0; --int_18) {
                        if (int_19 >= 1) {
                            int_23 = position.getY() + int_19;
                            int_8 += direction_2.getOffsetX();
                            int_9 += direction_2.getOffsetZ();
                            BlockPos blockPos_4 = new BlockPos(int_8, int_23, int_9);
                            if (isAirOrLeaves(modifiableTestableWorld_1, blockPos_4)) {
                                this.placeTopLog(set_1, modifiableTestableWorld_1, blockPos_4);
                                int_10 = int_23;
                            }
                        }

                        ++int_19;
                    }

                    if (int_10 > 0) {
                        BlockPos blockPos_5 = new BlockPos(int_8, int_10, int_9);

                        int int_24;
                        for (int_23 = -2; int_23 <= 2; ++int_23) {
                            for (int_24 = -2; int_24 <= 2; ++int_24) {
                                if (Math.abs(int_23) != 2 || Math.abs(int_24) != 2) {
                                    this.placeLeaves(modifiableTestableWorld_1, blockPos_5.add(int_23, 0, int_24));
                                }
                            }
                        }

                        blockPos_5 = blockPos_5.up();

                        for (int_23 = -1; int_23 <= 1; ++int_23) {
                            for (int_24 = -1; int_24 <= 1; ++int_24) {
                                this.placeLeaves(modifiableTestableWorld_1, blockPos_5.add(int_23, 0, int_24));
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

    private void placeLog(Set<BlockPos> set_1, ModifiableWorld modifiableWorld_1, BlockPos position) {
        this.setBlockState(set_1, modifiableWorld_1, position, LOG);
    }

    private void placeTopLog(Set<BlockPos> set_1, ModifiableWorld modifiableWorld_1, BlockPos position) {
        this.setBlockState(set_1, modifiableWorld_1, position, LOG_TOP);
    }

    private void placeLeaves(ModifiableTestableWorld modifiableTestableWorld_1, BlockPos position) {
        if (isAirOrLeaves(modifiableTestableWorld_1, position)) {
            this.setBlockState(modifiableTestableWorld_1, position, LEAVES);
        }

    }
}
