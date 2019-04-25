package team.hollow.neutronia.blocks.entity;

import net.minecraft.block.entity.ChestBlockEntity;
import team.hollow.neutronia.enums.CustomChestType;
import team.hollow.neutronia.init.NBlockEntities;

public class CustomChestBlockEntity extends ChestBlockEntity {

    private final CustomChestType stoneChestType;

    public CustomChestBlockEntity() {
        this(CustomChestType.ACACIA);
    }

    public CustomChestBlockEntity(CustomChestType type) {
        super(NBlockEntities.TILE_STONE_CHEST);
        this.stoneChestType = type;
    }

    public CustomChestType getStoneChestType() {
        return stoneChestType;
    }
}