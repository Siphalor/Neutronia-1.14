package team.hollow.module_api.api;

import de.siphalor.tweed.config.entry.ConfigEntry;
import net.minecraft.util.Pair;

import java.util.ArrayDeque;
import java.util.Collection;

public abstract class Feature {
	protected ArrayDeque<Pair<String, ConfigEntry>> configEntries;

	public Feature() {
		configEntries = new ArrayDeque<>();
	}

	public final <T extends ConfigEntry> T register(String name, T configEntry) {
		configEntries.add(new Pair<>(name, configEntry));
		return configEntry;
	}

	public Collection<Pair<String, ConfigEntry>> getConfigEntries() {
		return configEntries;
	}

	abstract void apply();
}
