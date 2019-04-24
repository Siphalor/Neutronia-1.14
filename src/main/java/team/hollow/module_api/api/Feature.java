package team.hollow.module_api.api;

import de.siphalor.tweed.config.entry.ConfigEntry;
import net.minecraft.util.Pair;

import java.util.Collection;

public interface Feature {

	Collection<Pair<String, ConfigEntry>> getConfigEntries();

	void apply();
}
