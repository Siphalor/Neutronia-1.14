package team.hollow.neutronia.modules.exploration.trees;

import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Blocks;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;

public class ColoredLeavesTreeFeature extends TreeFeature {
	public ColoredLeavesTreeFeature(String name, SaplingGenerator saplingGenerator) {
		super(name, saplingGenerator);
	}

	@Override
	protected void applyEnabled() {
		super.applyEnabled();

		if(FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
			ColorProviderRegistry.BLOCK.register((block, world, pos, layer) -> {
				BlockColorProvider provider = ColorProviderRegistry.BLOCK.get(Blocks.OAK_LEAVES);
				return provider == null ? -1 : provider.getColor(block, world, pos, layer);
			}, leaves);
			ColorProviderRegistry.ITEM.register((item, layer) -> {
				ItemColorProvider provider = ColorProviderRegistry.ITEM.get(Blocks.OAK_LEAVES);
				return provider == null ? -1 : provider.getColor(item, layer);
			}, leaves);
		}
	}
}
