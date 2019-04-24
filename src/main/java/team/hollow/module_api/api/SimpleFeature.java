package team.hollow.module_api.api;

import de.siphalor.tweed.config.entry.BooleanEntry;
import de.siphalor.tweed.config.entry.ConfigEntry;
import net.minecraft.util.Pair;

import java.util.Collection;
import java.util.Collections;

public abstract class SimpleFeature implements Feature {
	protected BooleanEntry enabledEntry;
	public String name;

	public SimpleFeature(String name, String description) {
		this.name = name;
		enabledEntry = new BooleanEntry(true).setComment(description);
	}

	@Override
	public Collection<Pair<String, ConfigEntry>> getConfigEntries() {
		return Collections.singleton(new Pair<>("enable-" + name, enabledEntry));
	}

	@Override
	public final void apply() {
		if(enabledEntry.value) {
			applyEnabled();
		}
	}

	protected abstract void applyEnabled();
}
