package team.hollow.neutronia.init;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.blocks.entity.ChestBaseBlockEntity;
import team.hollow.neutronia.blocks.entity.CustomChestBlockEntity;
import team.hollow.neutronia.blocks.entity.SignBlockEntity;

public class NBlockEntities {

    public static BlockEntityType<CustomChestBlockEntity> TILE_STONE_CHEST;
    public static BlockEntityType<ChestBaseBlockEntity> CHEST_BASE_BE;
    public static BlockEntityType<SignBlockEntity> TILE_SIGN;

    public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType.Builder<T> builder) {
        BlockEntityType<T> blockEntityType = builder.build(null);
        Registry.register(Registry.BLOCK_ENTITY, new Identifier(Neutronia.MOD_ID, name), blockEntityType);
        return blockEntityType;
    }

    static {
        TILE_STONE_CHEST = register("wooden_chest", BlockEntityType.Builder.create(CustomChestBlockEntity::new));
        CHEST_BASE_BE = register("chest_base_be", BlockEntityType.Builder.create(ChestBaseBlockEntity::new));
        TILE_SIGN = register("sign", BlockEntityType.Builder.create(SignBlockEntity::new));
    }

}
