package team.hollow.module_api.api.features.woodtype;

import team.hollow.neutronia.unsure.WoodType;
import team.hollow.neutronia.unsure.WoodTypeRegistry;

import java.util.Collections;
import java.util.Set;

public abstract class WoodTypeFeature extends ModdedWoodTypeFeature {
	public static final Set<WoodType> SKIP_OAK = Collections.singleton(WoodTypeRegistry.OAK);
	protected Set<WoodType> skipWoodTypes;

	public WoodTypeFeature(String name, String enablesDescription, Set<WoodType> skipWoodTypes) {
		super(name, enablesDescription);
		this.skipWoodTypes = skipWoodTypes;
	}

	@Override
	protected void applyEnabled() {
		for(WoodType woodType : WoodTypeRegistry.VANILLA) {
			if(skipWoodTypes.contains(woodType))
				continue;
			process(woodType);
		}
	}

	@Override
	public void onModdedWoodTypeRegistered(WoodType woodType) {
		if(isEnabled()) {
			if(!skipWoodTypes.contains(woodType))
				process(woodType);
		}
	}

	protected abstract void process(WoodType woodType);
}