package team.hollow.neutronia.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * A provider of variables to a template. Usually from json.
 */
@Environment(EnvType.CLIENT)
public interface VariableProvider<T> {

    /**
     * Gets the value assigned to the variable passed in.
     * May throw an exception if it doesn't exist.
     */
    T get(String key);

    /**
     * Returns if a variable exists or not.
     */
    boolean has(String key);

}