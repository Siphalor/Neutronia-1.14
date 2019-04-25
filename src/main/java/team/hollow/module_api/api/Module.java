package team.hollow.module_api.api;

import de.siphalor.tweed.config.ConfigCategory;
import net.minecraft.util.Identifier;
import team.hollow.module_api.api.features.Feature;
import team.hollow.module_api.api.features.OptionalFeature;

import java.util.ArrayDeque;

public abstract class Module extends OptionalFeature {

    public String name;
    public String description;
    private ArrayDeque<Feature> features;
    private Identifier backgroundTexture;

    private ConfigCategory configCategory;

    public Module(String name, String description) {
        super(name, "Enable/disable this module.");
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

    public Module setBackgroundTexture(Identifier identifier) {
        backgroundTexture = identifier;
        return this;
    }

    public ConfigCategory getConfigCategory() {
        if(configCategory == null)
            buildConfigCategory();
        return configCategory;
    }

    private void buildConfigCategory() {
        configCategory = new ConfigCategory();
        if(backgroundTexture != null)
            configCategory.setBackgroundTexture(backgroundTexture);
        configCategory.setComment(description);
        configEntries.forEach(pair -> configCategory.register(pair.getLeft(), pair.getRight()));
        features.forEach(feature -> feature.getConfigEntries().forEach(pair -> configCategory.register(pair.getLeft(), pair.getRight())));
    }
}