package team.hollow.neutronia.modules.origins.storage;

import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import team.hollow.abnormalib.modules.api.features.OptionalFeature;
import team.hollow.neutronia.Neutronia;

public class CompressionFeature extends OptionalFeature {
	public final Block block;
	protected ItemConvertible itemConvertible;

	public CompressionFeature(Block block, String name, ItemConvertible itemConvertible, String baseItemsName) {
		super(name, "Adds compression for " + baseItemsName + " to " + formatName(name));
		this.block = block;
		this.itemConvertible = itemConvertible;
	}

	@Override
	protected void applyEnabled() {
		Neutronia.getContentBuilder().newCompressedBlock(name, block, itemConvertible);
	}
}
