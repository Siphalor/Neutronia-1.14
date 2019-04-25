package team.hollow.neutronia.unsure;

import net.minecraft.block.Blocks;
import net.minecraft.util.StringRepresentable;

import java.util.*;

public abstract class WoodTypeRegistry implements StringRepresentable {
    private static ArrayList<WoodType> woodTypes = new ArrayList<>();

	public static final WoodType OAK = register(new WoodType("oak", Blocks.OAK_PLANKS));
    public static final WoodType SPRUCE = register(new WoodType("spruce", Blocks.SPRUCE_PLANKS));
    public static final WoodType BIRCH = register(new WoodType("birch", Blocks.BIRCH_PLANKS));
    public static final WoodType JUNGLE = register(new WoodType("jungle", Blocks.JUNGLE_PLANKS));
    public static final WoodType ACACIA = register(new WoodType("acacia", Blocks.ACACIA_PLANKS));
    public static final WoodType DARK_OAK = register(new WoodType("dark_oak", Blocks.DARK_OAK_PLANKS));

    public static final WoodType[] VANILLA = new WoodType[]{OAK, SPRUCE, BIRCH, JUNGLE, ACACIA, DARK_OAK};
    private static ArrayDeque<ModdedTypeListener> listeners = new ArrayDeque<>();

    private static WoodType register(WoodType woodType) {
        woodTypes.add(woodType);
        return woodType;
    }

    public static WoodType registerModded(WoodType woodType) {
        register(woodType);
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