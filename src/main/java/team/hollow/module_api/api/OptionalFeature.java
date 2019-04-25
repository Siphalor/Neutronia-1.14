package team.hollow.module_api.api;

import de.siphalor.tweed.config.ConfigEnvironment;
import de.siphalor.tweed.config.ConfigScope;
import de.siphalor.tweed.config.entry.BooleanEntry;

public abstract class OptionalFeature extends Feature {
	protected BooleanEntry enabledEntry;
	public String name;

	public OptionalFeature(String name, String enablesDescription) {
		this.name = name;
		enabledEntry = register("enable-" + name, new BooleanEntry(true).setComment(enablesDescription).setScope(ConfigScope.GAME).setEnvironment(ConfigEnvironment.SERVER));
	}

	@Override
	public final void apply() {
		if(enabledEntry.value) {
			applyEnabled();
		}
	}

	protected abstract void applyEnabled();
}
