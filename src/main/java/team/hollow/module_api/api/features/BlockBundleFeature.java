package team.hollow.module_api.api.features;

import net.minecraft.block.Block;
import team.hollow.neutronia.utils.registry.RegistryUtils;

import java.util.function.Supplier;

public class BlockBundleFeature extends OptionalFeature {
	private Supplier<Block> block;
	private String[] ids;

	public BlockBundleFeature(String name, String enablesDescription, Supplier<Block> block, String... ids) {
		super(name, enablesDescription);
		this.block = block;
		this.ids = ids;
	}

	@Override
	protected void applyEnabled() {
        for(String id : ids) {
        	RegistryUtils.register(block.get(), id + "_" + name);
		}
	}
}
