package team.hollow.neutronia.modules.variation;

import team.hollow.module_api.api.features.woodtype.WoodTypeFeature;
import team.hollow.neutronia.unsure.WoodType;
import team.hollow.neutronia.utils.registry.BlockRegistryBuilder;

import java.util.Collections;

public class StairsAndSlabsFeature extends WoodTypeFeature {
	public StairsAndSlabsFeature() {
		super("stairs-and-slabs", "Adds more stairs and slabs", Collections.emptySet());
	}

	@Override
	protected void process(WoodType woodType) {
		BlockRegistryBuilder.getInstance(woodType.getName(), woodType.getBaseBlock()).slab().stair();
	}

}
