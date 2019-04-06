package team.hollow.neutronia.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.minecraft.client.render.block.BlockColorMapper;
import net.minecraft.client.render.item.ItemColorMapper;
import team.hollow.neutronia.init.NBlocks;

public class NeutroniaLeavesColors implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ColorProviderRegistry.BLOCK.register((block, world, pos, layer) -> {
			BlockColorMapper provider = ColorProviderRegistry.BLOCK.get(block.getBlock());
			return provider == null ? -1 : provider.getColor(block, world, pos, layer);
		}, NBlocks.BOLIVIAN_ROSEWOOD_LEAVES, NBlocks.LACEWOOD_LEAVES);
		ColorProviderRegistry.ITEM.register((item, layer) -> {
			ItemColorMapper provider = ColorProviderRegistry.ITEM.get(item.getItem());
			return provider == null ? -1 : provider.getColor(item, layer);
	    }, NBlocks.BOLIVIAN_ROSEWOOD_LEAVES, NBlocks.LACEWOOD_LEAVES);
    }
}