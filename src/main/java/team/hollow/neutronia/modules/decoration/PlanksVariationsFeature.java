package team.hollow.neutronia.modules.decoration;

import net.minecraft.block.Block;
import net.minecraft.util.Pair;
import team.hollow.module_api.api.OptionalFeature;
import team.hollow.neutronia.enums.WoodType;
import team.hollow.neutronia.utils.registry.RegistryUtils;

import java.util.function.Supplier;

public class PlanksVariationsFeature extends OptionalFeature {


	private Pair<String, Supplier<Block>>[] blocks;

	@SafeVarargs
	public PlanksVariationsFeature(String configName, String enablesDescription, Pair<String, Supplier<Block>>... blocks) {
		super(configName, enablesDescription);
		this.blocks = blocks;
	}

	@Override
	protected void applyEnabled() {
        for(WoodType woodType : WoodType.VANILLA_WOODS) {
        	if(woodType == WoodType.OAK)
        		continue;
        	for(Pair<String, Supplier<Block>> pair : blocks) {
				RegistryUtils.register(pair.getRight().get(), pair.getLeft() + "_" + woodType.asString() + "_planks");
			}
		}
	}

	public void applyForModdedWood(String woodName) {
		if(isEnabled()) {
			for(Pair<String, Supplier<Block>> pair : blocks) {
				RegistryUtils.register(pair.getRight().get(), pair.getLeft() + "_" + woodName + "_planks");
			}
		}
	}
}
