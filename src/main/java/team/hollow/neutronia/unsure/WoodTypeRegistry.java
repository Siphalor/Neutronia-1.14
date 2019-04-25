package team.hollow.neutronia.unsure;

import net.minecraft.block.Blocks;
import net.minecraft.util.StringRepresentable;

import java.util.*;

public abstract class WoodTypeRegistry implements StringRepresentable {
    private static ArrayList<WoodType> woodTypes = new ArrayList<>();

    private static ArrayDeque<ModdedTypeListener> listeners = new ArrayDeque<>();

    public static WoodType registerVanilla(WoodType woodType) {
        woodTypes.add(woodType);
        return woodType;
    }

    public static WoodType registerModded(WoodType woodType) {
        registerVanilla(woodType);
        listeners.forEach(listener -> listener.onModdedWoodTypeRegistered(woodType));
        return woodType;
    }

    public static void registerModdedTypeListener(ModdedTypeListener listener) {
        listeners.add(listener);
    }

    public interface ModdedTypeListener {
        void onModdedWoodTypeRegistered(WoodType woodType);
    }
}