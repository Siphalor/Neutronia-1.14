package team.hollow.neutronia.modules.origins.storage;

import net.minecraft.block.Block;
import net.minecraft.item.ItemProvider;
import team.hollow.module_api.api.features.SingleBlockFeature;
import team.hollow.neutronia.registry.ContentBuilder;

public class CompressionFeature extends SingleBlockFeature {
	protected ItemProvider itemProvider;

	public CompressionFeature(Block block, String name, ItemProvider itemProvider, String baseItemsName) {
		super(block, name);
		this.itemProvider = itemProvider;
		enabledEntry.setComment("Adds compression for " + baseItemsName + " to " + formatName(name));
	}

	@Override
	protected void applyEnabled() {
		ContentBuilder.getInstance().newCompressedBlock(name, block, itemProvider);
	}
}
