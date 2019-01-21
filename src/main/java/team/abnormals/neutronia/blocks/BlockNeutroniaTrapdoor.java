package team.abnormals.neutronia.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.block.BlockItem;
import net.minecraft.util.registry.Registry;

public class BlockNeutroniaTrapdoor extends TrapdoorBlock implements INeutroniaBlock {

    private final String bareName;

    public BlockNeutroniaTrapdoor(String name) {
        super(Settings.of(Material.WOOD).strength(3.0F, 1.0F));

        bareName = name;

        register(name, this, ItemGroup.REDSTONE);
    }

    public BlockNeutroniaTrapdoor(Material material, String name) {
        super(Settings.of(material).strength(3.0F, 10F).friction(material == Material.ICE || material == Material.PACKED_ICE ? 0.98F : 0.0F));

        bareName = name;

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

}
