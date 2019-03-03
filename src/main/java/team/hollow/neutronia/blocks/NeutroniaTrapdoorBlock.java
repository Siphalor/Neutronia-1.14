package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.block.BlockItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import team.hollow.neutronia.INeutroniaInfo;
import team.hollow.neutronia.api.Climbable;

public class NeutroniaTrapdoorBlock extends TrapdoorBlock implements INeutroniaInfo, Climbable {

    public NeutroniaTrapdoorBlock(String name) {
        super(FabricBlockSettings.of(Material.WOOD).strength(3.0F, 1.0F).build());
        register(name, this, ItemGroup.REDSTONE);
    }

    public NeutroniaTrapdoorBlock(Material material, String name) {
        super(FabricBlockSettings.of(material).strength(3.0F, 10F).friction(material == Material.ICE || material == Material.PACKED_ICE ? 0.98F : 0.0F).build());
        register(name, this, ItemGroup.REDSTONE);
    }

    /*@Override
    public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity) {
        if (state.getValue(OPEN)) {
            IBlockState down = world.getBlockState(pos.down());
            if (down.getBlock() == Blocks.LADDER)
                return down.getValue(FACING) == state.getValue(FACING);
        }

        return false;
    }*/

    private void register(String name, Block block, ItemGroup tab) {
        Registry.register(Registry.BLOCK, getPrefix() + name, block);
        BlockItem item = new BlockItem(block, new Item.Settings().itemGroup(tab));
        item.registerBlockItemMap(Item.BLOCK_ITEM_MAP, item);
        Registry.register(Registry.ITEM, getPrefix() + name, item);
    }

    /**
     * Determines if the passed LivingEntity can climb this block.
     *
     * @param entity The LivingEntity that is attempting to climb this block.
     * @param state  The block state of the ladder being climbed.
     * @param pos    The position of the block.
     */
    @Override
    public boolean canClimb(LivingEntity entity, BlockState state, BlockPos pos) {
        return true;
    }
}
