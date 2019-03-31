package team.hollow.neutronia.blocks.entity;

import net.minecraft.block.entity.ChestBlockEntity;
import team.hollow.neutronia.enums.CustomChestTypes;
import team.hollow.neutronia.init.NBlockEntities;

public class CustomChestBlockEntity extends ChestBlockEntity {

    private final CustomChestTypes stoneChestType;

    public CustomChestBlockEntity() {
        this(CustomChestTypes.ACACIA);
    }

    public CustomChestBlockEntity(CustomChestTypes type) {
        super(NBlockEntities.TILE_STONE_CHEST);
        this.stoneChestType = type;
    }

    public CustomChestTypes getStoneChestType() {
        return stoneChestType;
    }
}