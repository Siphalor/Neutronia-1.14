package team.abnormals.neutronia.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.block.BlockItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ViewableWorld;

import java.util.Map;

public class WallStandingBlockItem extends BlockItem {
    protected final Block block;

    public WallStandingBlockItem(Block block_1, Block block_2, Item.Settings item$Settings_1) {
        super(block_1, item$Settings_1);
        this.block = block_2;
    }

    protected BlockState getBlockState(ItemPlacementContext itemPlacementContext_1) {
        BlockState blockState_1 = this.block.getPlacementState(itemPlacementContext_1);
        BlockState blockState_2 = null;
        ViewableWorld viewableWorld_1 = itemPlacementContext_1.getWorld();
        BlockPos blockPos_1 = itemPlacementContext_1.getBlockPos();
        Direction[] var6 = itemPlacementContext_1.getPlacementFacings();
        int var7 = var6.length;

        for (int var8 = 0; var8 < var7; ++var8) {
            Direction direction_1 = var6[var8];
            if (direction_1 != Direction.UP) {
                BlockState blockState_3 = direction_1 == Direction.DOWN ? this.getBlock().getPlacementState(itemPlacementContext_1) : blockState_1;
                if (blockState_3 != null && blockState_3.canPlaceAt(viewableWorld_1, blockPos_1)) {
                    blockState_2 = blockState_3;
                    break;
                }
            }
        }

        return blockState_2 != null && viewableWorld_1.method_8628(blockState_2, blockPos_1, VerticalEntityPosition.minValue()) ? blockState_2 : null;
    }

    public void registerBlockItemMap(Map<Block, Item> map_1, Item item_1) {
        super.registerBlockItemMap(map_1, item_1);
        map_1.put(this.block, item_1);
    }
}
