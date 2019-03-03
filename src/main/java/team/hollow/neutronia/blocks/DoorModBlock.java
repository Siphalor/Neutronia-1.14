package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.block.TallBlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import team.hollow.neutronia.Neutronia;

public abstract class DoorModBlock extends DoorBlock {

    public DoorModBlock(Material materialIn, String name, ItemGroup itemGroup) {
        super(FabricBlockSettings.of(materialIn).build());
        register(name, this, itemGroup);
    }

    @Override
    public ItemStack getPickStack(BlockView blockView_1, BlockPos blockPos_1, BlockState blockState_1) {
        return new ItemStack(this);
    }

    private void register(String name, Block block, ItemGroup tab) {
        Registry.register(Registry.BLOCK, new Identifier(Neutronia.MOD_ID, name), block);
        Registry.register(Registry.ITEM, new Identifier(Neutronia.MOD_ID, name), new TallBlockItem(block, new Item.Settings().itemGroup(tab)));
    }

}
