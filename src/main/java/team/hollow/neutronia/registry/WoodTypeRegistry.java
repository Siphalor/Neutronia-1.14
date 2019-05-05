package team.hollow.neutronia.registry;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.util.StringRepresentable;
import team.hollow.neutronia.blocks.NeutroniaBlock;

import java.util.ArrayDeque;
import java.util.ArrayList;

public abstract class WoodTypeRegistry implements StringRepresentable {
    private static ArrayList<WoodType> woodTypes = new ArrayList<>();

    private static ArrayDeque<ModdedTypeListener> listeners = new ArrayDeque<>();

    public static WoodType registerVanilla(WoodType woodType) {
        woodTypes.add(woodType);
        return woodType;
    }

    public static WoodType registerModded(WoodType woodType, float hardness, float resistance) {
        registerVanilla(woodType);

        ContentBuilder contentBuilder = ContentBuilder.getInstance();
		woodType.baseBlock = contentBuilder.newBaseBlock(woodType.getIdentifier().getPath() + "_planks", new NeutroniaBlock(
			FabricBlockSettings.of(Material.WOOD, MaterialColor.WOOD).hardness(hardness).resistance(resistance)
		));

		contentBuilder.setBaseName(woodType.getIdentifier());
		contentBuilder.slab();
		contentBuilder.stairs();
		contentBuilder.button(true);
		contentBuilder.pressurePlate(PressurePlateBlock.Type.WOOD);
		contentBuilder.fence();
		contentBuilder.fenceGate();
		contentBuilder.door();
		contentBuilder.trapDoor();

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