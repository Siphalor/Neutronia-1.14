package team.hollow.module_api.api;

import de.siphalor.tweed.config.ConfigCategory;

import java.util.ArrayDeque;

public abstract class Module extends OptionalFeature {

    public String name;
    public String description;
    private ArrayDeque<Feature> features;

    private ConfigCategory configCategory;

    public Module(String name, String description) {
        super(name, "Enables this module.");
        this.description = description;
        this.name = name;
        this.features = new ArrayDeque<>();
    }

    public <T extends Feature> T register(T feature) {
        features.add(feature);
        return feature;
    }

    @Override
    protected void applyEnabled() {
        features.forEach(Feature::apply);
    }

    public ConfigCategory getConfigCategory() {
        if(configCategory == null)
            buildConfigCategory();
        return configCategory;
    }

    private void buildConfigCategory() {
        configCategory = new ConfigCategory();
        configCategory.setComment(description);
        getConfigEntries().forEach(pair -> configCategory.register(pair.getLeft(), pair.getRight()));
        features.forEach(feature -> feature.getConfigEntries().forEach(pair -> configCategory.register(pair.getLeft(), pair.getRight())));
    }
}