package team.hollow.neutronia.utils.helpers;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.hollow.neutronia.enums.CarvedFaceTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarvedBlockHelper {

    private static Map<ICarvable, List<Block>> carvableBlocks = new HashMap<>();

    public static void init(ICarvable carvable) {
        List<Block> blocks = new ArrayList<>();
        for (CarvedFaceTypes type : CarvedFaceTypes.values()) {
            blocks.add(Registry.BLOCK.get(new Identifier("neutronia",
                    String.format(carvable.getFormatString(), type.toSnakeCase()))));
            carvableBlocks.put(carvable.newInstance(new Identifier("neutronia",
                    String.format(carvable.getFormatString(), type.toSnakeCase()))), blocks);
        }
    }

    public static void initAlt(ICarvable carvable) {
        List<Block> blocks = new ArrayList<>();
        for (CarvedFaceTypes type : CarvedFaceTypes.values()) {
            blocks.add(Registry.BLOCK.get(new Identifier("minecraft",
                    String.format(carvable.getFormatString(), type.toSnakeCase()))));
            carvableBlocks.put(carvable.newInstance(new Identifier("minecraft",
                    String.format(carvable.getFormatString(), type.toSnakeCase()))), blocks);
        }
    }

    public static BlockState getNext(ICarvable carvable, Identifier identifier) {
        CarvedFaceTypes type = carvable.fromIdentifier(identifier);
        if (type == null) {
            return carvableBlocks.get(carvable).get(0).getDefaultState();
        }
        int ordinal = type.ordinal();
        ordinal++;
        System.out.println("Carvable block: " + carvableBlocks.get(carvable));
        System.out.println("Carvable block blockstate: " + carvableBlocks.get(carvable).get(type.ordinal()).getDefaultState());
        System.out.println("Carvable block id: " + carvableBlocks.get(carvable).get(ordinal));
        if (ordinal == carvableBlocks.values().size()) {
            return carvable.getUncarvedBlock().getDefaultState();
        } else {
            return carvableBlocks.get(carvable).get(ordinal).getDefaultState();
        }
    }

    public static BlockState getLast(ICarvable carvable, Identifier identifier) {
        CarvedFaceTypes type = carvable.fromIdentifier(identifier);
        if (type == null) {
            return carvableBlocks.get(carvable).get(carvableBlocks.get(carvable).size() - 1).getDefaultState();
        }
        int ordinal = type.ordinal();
        ordinal--;
        if (ordinal == -1) {
            return carvable.getUncarvedBlock().getDefaultState();
        } else {
            return carvableBlocks.get(carvable).get(ordinal).getDefaultState();
        }
    }

}