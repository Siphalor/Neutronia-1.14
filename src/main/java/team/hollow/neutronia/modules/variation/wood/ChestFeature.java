package team.hollow.neutronia.modules.variation.wood;

import team.hollow.module_api.api.features.woodtype.WoodTypeFeature;
import team.hollow.neutronia.registry.ContentBuilder;
import team.hollow.neutronia.registry.WoodType;

import java.util.Collections;

public class ChestFeature extends WoodTypeFeature {
	public ChestFeature() {
		super("chests", "Enables loads of variants for chests", Collections.singleton(WoodType.OAK));
	}

	@Override
	protected void process(WoodType woodType) {
		ContentBuilder contentBuilder = ContentBuilder.getInstance();
        contentBuilder.asBaseBlock(woodType.baseBlock, woodType.getIdentifier());
		contentBuilder.setBaseTexture(woodType.getBaseBlockIdentifier());
		contentBuilder.chest();
	}
}
