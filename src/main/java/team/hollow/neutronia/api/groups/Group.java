package team.hollow.neutronia.api.groups;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Group implements Comparable<Group> {

    public final Map<String, IComponent> components = new HashMap<>();
    private final List<IComponent> enabledComponents = new ArrayList<>();
    public String name, desc;
    protected Class<?> configFile;
    private ItemStack iconStack;

    public Group(Builder builder) {
        this.name = builder.name;
        this.desc = builder.desc;
        for (IComponent component : builder.components) {
            registerComponent(component);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private void registerComponent(IComponent component) {
        registerComponent(component, convertName(component.getClass().getSimpleName()));
    }

    private String convertName(String origName) {
        String withSpaces = origName.replaceAll("(?<=.)([A-Z])", " $1").toLowerCase();
        return Character.toUpperCase(withSpaces.charAt(0)) + withSpaces.substring(1);
    }

    private void registerComponent(IComponent component, String name) {
        Class<? extends IComponent> clazz = component.getClass();
        if (GroupLoader.componentInstances.containsKey(clazz))
            throw new IllegalArgumentException("Component " + clazz + " is already registered!");

        GroupLoader.componentInstances.put(clazz, component);
        components.put(name, component);
    }

    public void init() {
        forEachEnabledComponent(IComponent::onInit);
    }

    @Environment(EnvType.CLIENT)
    void initClient() {
        forEachEnabledComponent(IComponent::onInitClient);
    }

    private void forEachEnabledComponent(Consumer<IComponent> consumer) {
        enabledComponents.forEach(consumer);
    }

    @Override
    public int compareTo(Group group) {
        return name.compareTo(group.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Builder {

        private String name, desc;
        private ItemStack icon;
        private List<IComponent> components = new ArrayList<>();
        private Class<?> configFile;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String desc) {
            this.desc = desc;
            return this;
        }

        public Builder addComponent(IComponent component) {
            components.add(component);
            return this;
        }

        public Builder configFile(Class<?> config) {
            this.configFile = config;
            return this;
        }

        public Builder iconStack(ItemStack icon) {
            if (!icon.isEmpty()) {
                this.icon = icon;
            } else {
                this.icon = new ItemStack(Blocks.AIR);
            }
            return this;
        }

        public Group register() {
            Group group = new Group(this);
            group.iconStack = icon;
            group.configFile = configFile;
            GroupLoader.registerGroup(group);
            return group;
        }

    }

}
