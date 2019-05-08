package team.hollow.neutronia.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.MegaTreeFeature;
import team.hollow.neutronia.init.WoodRegistries;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class SmallRedwoodFeature extends MegaTreeFeature<DefaultFeatureConfig> {

    public SmallRedwoodFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function_1) {
        super(function_1, false, 13, 15, WoodRegistries.REDWOOD.getLog().getDefaultState(), WoodRegistries.REDWOOD.getLeaves().getDefaultState());
    }

    public boolean generate(Set<BlockPos> set_1, ModifiableTestableWorld modifiableTestableWorld_1, Random random_1, BlockPos blockPos_1, MutableIntBoundingBox mutableIntBoundingBox) {
        int int_1 = this.getHeight(random_1);
        if (!this.checkTreeFitsAndReplaceGround(modifiableTestableWorld_1, blockPos_1, int_1)) {
            return false;
        } else {
            BlockPos origin = blockPos_1.add(0, int_1 - 1, 0);

            this.makeTopLeaves(set_1, mutableIntBoundingBox, modifiableTestableWorld_1, blockPos_1.getX(), blockPos_1.getZ(), blockPos_1.getY() + int_1, 0, random_1);

            for (int int_2 = 0; int_2 < int_1; ++int_2) {
                if (isAirOrLeaves(modifiableTestableWorld_1, blockPos_1.up(int_2))) {
                    this.setBlockState(set_1, modifiableTestableWorld_1, blockPos_1.up(int_2), WoodRegistries.REDWOOD.getLog().getDefaultState(), mutableIntBoundingBox);
                }

                if (int_2 < int_1 - 1) {
                    if (isAirOrLeaves(modifiableTestableWorld_1, blockPos_1.add(1, int_2, 0))) {
                        this.setBlockState(set_1, modifiableTestableWorld_1, blockPos_1.add(1, int_2, 0), WoodRegistries.REDWOOD.getLog().getDefaultState(), mutableIntBoundingBox);
                    }

                    if (isAirOrLeaves(modifiableTestableWorld_1, blockPos_1.add(1, int_2, 1))) {
                        this.setBlockState(set_1, modifiableTestableWorld_1, blockPos_1.add(1, int_2, 1), WoodRegistries.REDWOOD.getLog().getDefaultState(), mutableIntBoundingBox);
                    }

                    if (isAirOrLeaves(modifiableTestableWorld_1, blockPos_1.add(0, int_2, 1))) {
                        this.setBlockState(set_1, modifiableTestableWorld_1, blockPos_1.add(0, int_2, 1), WoodRegistries.REDWOOD.getLog().getDefaultState(), mutableIntBoundingBox);
                    }
                }
            }

            return true;
        }
    }

    private void makeTopLeaves(Set<BlockPos> posSet, MutableIntBoundingBox mutableIntBoundingBox, ModifiableTestableWorld modifiableTestableWorld_1, int int_1, int int_2, int int_3, int int_4, Random random_1) {
        int int_5 = random_1.nextInt(5) + 3;
        int int_6 = 0;

        for (int int_7 = int_3 - int_5; int_7 <= int_3; ++int_7) {
            int int_8 = int_3 - int_7;
            int int_9 = int_4 + MathHelper.floor((float) int_8 / (float) int_5 * 3.5F);
            this.makeSquaredLeafLayer(modifiableTestableWorld_1, new BlockPos(int_1, int_7, int_2), int_9 + (int_8 > 0 && int_9 == int_6 && (int_7 & 1) == 0 ? 1 : 0), mutableIntBoundingBox, posSet);
            int_6 = int_9;
        }

    }

}