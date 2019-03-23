package team.hollow.neutronia.api.groups;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;

public class Component {

    public Group group;
    public ComponentStateManager stateManager = new ComponentStateManager(this);
    boolean loadtimeDone;
    boolean enabledAtLoadtime;
    boolean prevEnabled;
    boolean forceLoad;

    public Component(boolean enabled) {
        stateManager.enabled = enabled;
    }

    public Component(boolean enabled, boolean enabledByDefault) {
        stateManager.enabled = enabled;
        stateManager.enabledByDefault = enabledByDefault;
    }

    public Component() {
        this(true, true);
    }

    public void onEnabled() {
        // NO-OP
    }

    public void onDisabled() {
        // NO-OP
    }

    public void init(ModInitializer initializer) {
        // NO-OP
    }

    @Environment(EnvType.CLIENT)
    public void initClient(ClientModInitializer clientInitializer) {
        // NO-OP
    }

    public String[] getIncompatibleMods() {
        return null;
    }

    public boolean hasSubscriptions() {
        return false;
    }

    public boolean hasTerrainSubscriptions() {
        return false;
    }

    public boolean hasOreGenSubscriptions() {
        return false;
    }

    public boolean requiresMinecraftRestartToEnable() {
        return hasSubscriptions() || hasOreGenSubscriptions() || hasTerrainSubscriptions();
    }

    public boolean isEnabledAtLoadtime() {
        return enabledAtLoadtime;
    }

    public boolean isPrevEnabled() {
        return prevEnabled;
    }

}