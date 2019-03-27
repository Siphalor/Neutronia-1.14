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

    public final Map<String, Component> components = new HashMap<>();
    private final List<Component> enabledComponents = new ArrayList<>();
    public String name, desc;
    public boolean enabled, enabledByDefault;
    private ItemStack iconStack;
    protected Class<?> configFile;

    public Group(Builder builder) {
        this.name = builder.name;
        this.desc = builder.desc;
        this.enabled = builder.enabled;
        this.enabledByDefault = builder.enabledByDefault;
        for (Component component : builder.components) {
            registerComponent(component, component.stateManager.enabledByDefault);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Group createGroup(Builder builder) {
        GroupLoader.registerGroup(new Group(builder));
        return new Group(builder);
    }

    public void registerComponent(Component component, boolean enabledByDefault) {
        registerComponent(component, convertName(component.getClass().getSimpleName()), enabledByDefault);
    }

    private String convertName(String origName) {
        String withSpaces = origName.replaceAll("(?<=.)([A-Z])", " $1").toLowerCase();
        return Character.toUpperCase(withSpaces.charAt(0)) + withSpaces.substring(1);
    }

    private void registerComponent(Component component, String name, boolean enabledByDefault) {
        Class<? extends Component> clazz = component.getClass();
        if (GroupLoader.componentInstances.containsKey(clazz))
            throw new IllegalArgumentException("Component " + clazz + " is already registered!");

        GroupLoader.componentInstances.put(clazz, component);
        components.put(name, component);

        component.stateManager.enabledByDefault = enabledByDefault;
        component.prevEnabled = false;

        component.group = this;
    }

    public void init() {
        forEachEnabledComponent(Component::init);
    }

    @Environment(EnvType.CLIENT)
    void initClient() {
        forEachEnabledComponent(Component::initClient);
    }

    boolean canBeDisabled() {
        return true;
    }

    boolean isEnabledByDefault() {
        return enabledByDefault;
    }

    String getModuleDescription() {
        return desc;
    }

    public ItemStack getIconStack() {
        if (iconStack != null) {
            return iconStack;
        } else {
            return new ItemStack(Blocks.BARRIER);
        }
    }

    public void forEachComponent(Consumer<Component> consumer) {
        components.values().forEach(consumer);
    }

    private void forEachEnabledComponent(Consumer<Component> consumer) {
        enabledComponents.forEach(consumer);
    }

    @Override
    public int compareTo(Group group) {
        return name.compareTo(group.name);
    }

    public boolean isEnabled() {
        return enabled;
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
        private Group group;
        private boolean enabled, enabledByDefault;
        private List<Component> components = new ArrayList<>();
        private Class<?> configFile;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String desc) {
            this.desc = desc;
            return this;
        }

        public Builder addComponent(Component component) {
            components.add(component);
            return this;
        }

        public Builder configFile(Class<?> config) {
            this.configFile = config;
            return this;
        }

        public Builder addComponent(Component component, boolean enabled) {
            component.stateManager.enabled = enabled;
            components.add(component);
            return this;
        }

        public Builder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder enabledByDefault(boolean enabledByDefault) {
            this.enabledByDefault = enabledByDefault;
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
            group = new Group(this);
            group.iconStack = icon;
            group.enabled = enabled;
            group.enabledByDefault = enabledByDefault;
            group.configFile = configFile;
            GroupLoader.registerGroup(group);
            return group;
        }

    }

}
