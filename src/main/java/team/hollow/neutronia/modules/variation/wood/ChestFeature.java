package team.hollow.neutronia.modules.variation.wood;

import team.hollow.abnormalib.modules.api.features.woodtype.WoodTypeFeature;
import team.hollow.abnormalib.utils.ContentBuilder;
import team.hollow.abnormalib.utils.registry.WoodType;
import team.hollow.neutronia.Neutronia;

public class ChestFeature extends WoodTypeFeature {
	public ChestFeature() {
		super("chests", "Enables loads of variants for chests", WoodTypeFeature.SKIP_OAK);
	}

	@Override
	protected void process(WoodType woodType) {
		ContentBuilder contentBuilder = Neutronia.getContentBuilder();
        contentBuilder.asBaseBlock(woodType.baseBlock, woodType.getIdentifier());
		contentBuilder.setBaseTexture(woodType.getBaseBlockIdentifier());
		contentBuilder.chest();
	}
}
