package team.hollow.neutronia.unsure;

import net.minecraft.block.Material;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.util.StringRepresentable;
import team.hollow.neutronia.blocks.NeutroniaBlock;
import team.hollow.neutronia.blocks.NeutroniaDoorBlock;
import team.hollow.neutronia.blocks.NeutroniaTrapdoorBlock;

import java.util.ArrayDeque;
import java.util.ArrayList;

public abstract class WoodTypeRegistry implements StringRepresentable {
    private static ArrayList<WoodType> woodTypes = new ArrayList<>();

    private static ArrayDeque<ModdedTypeListener> listeners = new ArrayDeque<>();

    public static WoodType registerVanilla(WoodType woodType) {
        woodTypes.add(woodType);
        return woodType;
    }

    public static WoodType registerModded(WoodType woodType) {
        registerVanilla(woodType);

        ContentBuilder contentBuilder = ContentBuilder.getInstance();
		woodType.baseBlock = contentBuilder.newBaseBlock(woodType.getIdentifier().getPath() + "_planks", new NeutroniaBlock(Material.WOOD));

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