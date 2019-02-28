package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;
import team.hollow.neutronia.init.NBlocks;

public class CoconutBlock extends NeutroniaBaseBlock {

	public CoconutBlock()
	{
		super(FabricBlockSettings.of(Material.ORGANIC).hardness(0.4F).resistance(7.0F).sounds(BlockSoundGroup.WOOD).build(), "coconut");
	}

    @Override
    public boolean canPlaceAt(BlockState blockState_1, ViewableWorld viewableWorld_1, BlockPos blockPos_1) {
        return this.canBlockStay(viewableWorld_1, blockPos_1);
    }

    public boolean canBlockStay(ViewableWorld worldIn, BlockPos pos)
    {
        BlockState iblockstate = worldIn.getBlockState(pos.up());
        return iblockstate.getBlock() == NBlocks.PALM_LEAVES;
    }

    @Override
    public boolean isSimpleFullBlock(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
        return false;
    }

    @Override
    public boolean isTranslucent(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
        return false;
    }

    @Override
    public void neighborUpdate(BlockState blockState_1, World world_1, BlockPos blockPos_1, Block block_1, BlockPos blockPos_2) {
        if(!this.canBlockStay(world_1, blockPos_1)) {
            this.dropBlock(world_1, blockPos_1, blockState_1);
        }
    }

    private void dropBlock(World worldIn, BlockPos pos, BlockState state)
    {
        worldIn.clearBlockState(pos);
        dropStacks(state, worldIn, pos);
    }
}