package team.hollow.neutronia.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.ModifiableWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

import java.util.Random;
import java.util.function.Function;

public abstract class MidnightAbstractFeature<T extends FeatureConfig> extends Feature<T> implements IMidnightFeature {
    public MidnightAbstractFeature(Function<Dynamic<?>, ? extends T> function_1) {
        super(function_1, false);
    }

    public MidnightAbstractFeature(Function<Dynamic<?>, ? extends T> function_1, boolean boolean_1) {
        super(function_1, boolean_1);
    }

    @Override
    public boolean generate(IWorld var1, ChunkGenerator<? extends ChunkGeneratorConfig> var2, Random var3, BlockPos var4, T var5) {
        return this.placeFeature(var1, var3, var4);
    }

    @Override
    protected void setBlockState(ModifiableWorld modifiableWorld_1, BlockPos blockPos_1, BlockState blockState_1) {
        modifiableWorld_1.setBlockState(blockPos_1, blockState_1, 2 | 16);
    }

}