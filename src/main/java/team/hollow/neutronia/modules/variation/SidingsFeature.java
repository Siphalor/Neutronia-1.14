package team.hollow.neutronia.modules.variation;

import team.hollow.module_api.api.features.woodtype.WoodTypeFeature;
import team.hollow.neutronia.unsure.ContentBuilder;
import team.hollow.neutronia.unsure.WoodType;

import java.util.Collections;

public class SidingsFeature extends WoodTypeFeature {

	public SidingsFeature() {
		super("sidings", "Adds sideways slabs and corners", Collections.emptySet());
	}

	@Override
	protected void process(WoodType woodType) {
		ContentBuilder contentBuilder = ContentBuilder.getInstance();
		contentBuilder.asBaseBlock(woodType.getBaseBlock(), woodType.getBaseBlockName());
		contentBuilder.siding();
		contentBuilder.corner();
	}
}
