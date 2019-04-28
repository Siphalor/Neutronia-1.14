package team.hollow.neutronia.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.block.BlockColorMapper;
import net.minecraft.client.render.item.ItemColorMapper;

import java.util.ArrayList;
import java.util.List;

public class NeutroniaLeavesColors implements ClientModInitializer {

    public static final List<Block> COLORED_LEAVES_LIST = new ArrayList<>();

    @Override
    public void onInitializeClient() {
        for(Block leavesBlock : COLORED_LEAVES_LIST) {
            ColorProviderRegistry.BLOCK.register((block, world, pos, layer) -> {
                BlockColorMapper provider = ColorProviderRegistry.BLOCK.get(Blocks.OAK_LEAVES);
                return provider == null ? -1 : provider.getColor(block, world, pos, layer);
            }, leavesBlock);
            ColorProviderRegistry.ITEM.register((item, layer) -> {
                ItemColorMapper provider = ColorProviderRegistry.ITEM.get(Blocks.OAK_LEAVES);
                return provider == null ? -1 : provider.getColor(item, layer);
            }, leavesBlock);
        }
    }
}