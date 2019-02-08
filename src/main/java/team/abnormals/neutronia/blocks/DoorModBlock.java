package team.abnormals.neutronia.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.block.TallBlockItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

public abstract class DoorModBlock extends DoorBlock implements IModBlockInfo {

    private final String bareName;

    public DoorModBlock(Material materialIn, String name, ItemGroup itemGroup) {
        super(Settings.of(materialIn));

        this.bareName = name;
        if (registerInConstruction()) {
            register(name, this, itemGroup);
        }
    }

    @Override
    public ItemStack getPickStack(BlockView blockView_1, BlockPos blockPos_1, BlockState blockState_1) {
        return new ItemStack(this);
    }

    private void register(String name, Block block, ItemGroup tab) {
        Registry.register(Registry.BLOCK, getPrefix() + name, block);
        TallBlockItem item = new TallBlockItem(block, new Item.Settings().itemGroup(tab));
        item.registerBlockItemMap(Item.BLOCK_ITEM_MAP, item);
        Registry.register(Registry.ITEM, getPrefix() + name, item);
    }

    public boolean registerInConstruction() {
        return true;
    }

}
