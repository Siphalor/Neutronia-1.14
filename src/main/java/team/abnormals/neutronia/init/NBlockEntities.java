package team.abnormals.neutronia.init;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import team.abnormals.neutronia.blocks.entity.TileStoneChest;

public class NBlockEntities {

    public static BlockEntityType<TileStoneChest> TILE_STONE_CHEST;

    public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType.Builder<T> builder) {
        BlockEntityType<T> blockEntityType = builder.build(null);
        Registry.register(Registry.BLOCK_ENTITY, "neutronia" + ":" + name, blockEntityType);
        return blockEntityType;
    }

    public static void init() {
        TILE_STONE_CHEST = register("wooden_chest", BlockEntityType.Builder.create(TileStoneChest::new));
    }

}