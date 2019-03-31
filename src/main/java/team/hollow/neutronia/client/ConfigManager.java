package team.hollow.neutronia.client;

import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.TestConfig;

public class ConfigManager {
    private TestConfig config;

    public ConfigManager(TestConfig config) {
        this.config = config;
        team.hollow.neutronia.configNew.ConfigManager.loadConfig(config.getClass());
        Neutronia.getLogger().info("[Neutronia] Config is loaded.");
    }

    public TestConfig getConfig() {
        return this.config;
    }

}
