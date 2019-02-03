package team.abnormals.neutronia.utils.helpers;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import team.abnormals.neutronia.enums.CarvedFaceTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarvedBlockHelper {

    private static Map<ICarvable, List<Block>> carvableBlocks = new HashMap<>();

    public static void init(ICarvable carvable) {
        List<Block> blocks = new ArrayList<>();
        for(CarvedFaceTypes type : CarvedFaceTypes.values()) {
            carvableBlocks.put(carvable.newInstance(new Identifier("neutronia", 
                String.format(carvable.getFormatString(), type.asString()))), blocks);
        }
    }

    public static void initAlt(ICarvable carvable) {
        List<Block> blocks = new ArrayList<>();
        for(CarvedFaceTypes type : CarvedFaceTypes.values()) {
            carvableBlocks.put(carvable.newInstance(new Identifier("minecraft",
                    String.format(carvable.getFormatString(), type.asString()))), blocks);
        }
    }

    public static BlockState getNext(ICarvable carvable, Identifier identifier){
        CarvedFaceTypes type = carvable.fromIdentifier(identifier);
        if(type == null){
            return carvableBlocks.get(carvable).get(0).getDefaultState();
        }
        int ordinal = type.ordinal();
        ordinal++;
        if(ordinal == carvableBlocks.values().size()) {
            return carvable.getUncarvedBlock().getDefaultState();
        } else {
            return carvableBlocks.get(carvable).get(ordinal).getDefaultState();
        }
    }

    public static BlockState getLast(ICarvable carvable, Identifier identifier){
        CarvedFaceTypes type = carvable.fromIdentifier(identifier);
        if(type == null){
            return carvableBlocks.get(carvable).get(carvableBlocks.get(carvable).size() - 1).getDefaultState();
        }
        int ordinal = type.ordinal();
        ordinal--;
        if(ordinal == -1){
            return carvable.getUncarvedBlock().getDefaultState();
        } else {
            return carvableBlocks.get(carvable).get(ordinal).getDefaultState();
        }            
    }

}