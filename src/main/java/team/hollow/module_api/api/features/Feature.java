package team.hollow.module_api.api.features;

import de.siphalor.tweed.config.entry.ConfigEntry;
import net.minecraft.util.Pair;
import org.apache.commons.lang3.text.WordUtils;

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

	public abstract void apply();

    public static String formatName(String name) {
        return WordUtils.capitalizeFully(name.replace("_", " "));
	}
}
