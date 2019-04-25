package team.hollow.neutronia.modules.variation;

import team.hollow.module_api.api.features.woodtype.WoodTypeFeature;
import team.hollow.neutronia.unsure.WoodType;
import team.hollow.neutronia.utils.registry.BlockRegistryBuilder;

import java.util.Collections;

public class SidingsFeature extends WoodTypeFeature {

	public SidingsFeature() {
		super("sidings", "Adds sideways slabs and corners", Collections.emptySet());
	}

	@Override
	protected void process(WoodType woodType) {
		BlockRegistryBuilder.getInstance(woodType.getName(), woodType.getBaseBlock()).siding().corner();
	}
}
