package team.hollow.module_api.api;

import de.siphalor.tweed.config.entry.ConfigEntry;
import net.minecraft.util.Pair;

import java.util.Collection;
import java.util.Collections;

public class SubModule extends Module {
	public SubModule(String name, String description) {
		super(name, description);
	}

	@Override
	public Collection<Pair<String, ConfigEntry>> getConfigEntries() {
        return Collections.singleton(new Pair<>(name, getConfigCategory()));
	}
}
