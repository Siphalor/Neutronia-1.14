package team.hollow.neutronia.modules.origins;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.item.Items;
import team.hollow.abnormalib.modules.api.features.OptionalFeature;
import team.hollow.neutronia.Neutronia;

public class CompostablesFeature extends OptionalFeature {
	public CompostablesFeature() {
		super("more-compostables", "Adds more compostale items such as rotten flesh, chicken and cooked chicken.");
	}

	@Override
	protected void applyEnabled() {
		Neutronia.getContentBuilder().runGameTask(() -> {
			CompostingChanceRegistry.INSTANCE.add(Items.ROTTEN_FLESH, 0.5F);
			CompostingChanceRegistry.INSTANCE.add(Items.CHICKEN, 0.5F);
			CompostingChanceRegistry.INSTANCE.add(Items.COOKED_CHICKEN, 0.5F);
		});
	}
}
