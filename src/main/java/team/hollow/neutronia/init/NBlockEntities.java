package team.hollow.neutronia.init;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import team.hollow.neutronia.blocks.entity.SignBlockEntity;
import team.hollow.neutronia.blocks.entity.StoneChestBlockEntity;

public class NBlockEntities {

    public static BlockEntityType<StoneChestBlockEntity> TILE_STONE_CHEST;
    public static BlockEntityType<SignBlockEntity> TILE_SIGN;

    public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType.Builder<T> builder) {
        BlockEntityType<T> blockEntityType = builder.build(null);
        Registry.register(Registry.BLOCK_ENTITY, "neutronia" + ":" + name, blockEntityType);
        return blockEntityType;
    }

    public static void init() {
        TILE_STONE_CHEST = register("wooden_chest", BlockEntityType.Builder.create(StoneChestBlockEntity::new));
        TILE_SIGN = register("sign", BlockEntityType.Builder.create(SignBlockEntity::new));
    }

}
