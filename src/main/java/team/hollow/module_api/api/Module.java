package team.hollow.module_api.api;

import de.siphalor.tweed.config.ConfigCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TextComponent;
import team.hollow.neutronia.utils.registry.RegistryUtils;

import java.util.ArrayList;

public abstract class Module {

    public String name;
    public ItemStack iconStack;
    public TextComponent description;
    public boolean enabled, enabledByDefault;
    private ArrayList<Feature> features;

    private ConfigCategory configCategory;

    public Module(String name, ItemStack iconStack, TextComponent description) {
        this.name = name;
        this.iconStack = iconStack;
        this.description = description;
        this.features = new ArrayList<>();
    }

    public ItemStack getIconStack() {
        return iconStack;
    }

    public void setIconStack(ItemStack iconStack) {
        this.iconStack = iconStack;
    }

    public TextComponent getDescription() {
        return description;
    }

    public void setDescription(TextComponent description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabledByDefault() {
        return enabledByDefault;
    }

    public void setEnabledByDefault(boolean enabledByDefault) {
        this.enabledByDefault = enabledByDefault;
    }

    public <T extends Feature> T register(T feature) {
        features.add(feature);
        return feature;
    }

    public void apply() {
        features.forEach(Feature::apply);
    }

    public ConfigCategory getConfigCategory() {
        if(configCategory == null)
            buildConfigCategory();
        return configCategory;
    }

    private void buildConfigCategory() {
        configCategory = new ConfigCategory();
        configCategory.setComment(description.getFormattedText());
        features.forEach(feature -> feature.getConfigEntries().forEach(pair -> configCategory.register(pair.getLeft(), pair.getRight())));
    }
}