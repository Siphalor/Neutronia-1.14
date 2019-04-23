package team.hollow.module_api.api;

import net.minecraft.item.ItemStack;
import net.minecraft.text.TextComponent;

public class Module {

    public ItemStack iconStack;
    public TextComponent description;
    public boolean enabled, enabledByDefault;

    public Module(ItemStack iconStack, TextComponent description) {
        this.iconStack = iconStack;
        this.description = description;
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

}