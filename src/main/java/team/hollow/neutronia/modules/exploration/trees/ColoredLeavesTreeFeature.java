package team.hollow.neutronia.modules.exploration.trees;

import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.client.render.block.BlockColorMapper;
import net.minecraft.client.render.item.ItemColorMapper;

public class ColoredLeavesTreeFeature extends TreeFeature {
	public ColoredLeavesTreeFeature(String name, SaplingGenerator saplingGenerator) {
		super(name, saplingGenerator);
	}

	@Override
	protected void applyEnabled() {
		super.applyEnabled();

		ColorProviderRegistry.BLOCK.register((block, world, pos, layer) -> {
			BlockColorMapper provider = ColorProviderRegistry.BLOCK.get(Blocks.OAK_LEAVES);
			return provider == null ? -1 : provider.getColor(block, world, pos, layer);
		}, leaves);
		ColorProviderRegistry.ITEM.register((item, layer) -> {
			ItemColorMapper provider = ColorProviderRegistry.ITEM.get(Blocks.OAK_LEAVES);
			return provider == null ? -1 : provider.getColor(item, layer);
		}, leaves);
	}
}
