package team.hollow.neutronia.blocks.entity;

import net.minecraft.block.entity.ChestBlockEntity;
import team.hollow.neutronia.enums.CustomChestTypes;
import team.hollow.neutronia.init.NBlockEntities;

public class StoneChestBlockEntity extends ChestBlockEntity {

    private final CustomChestTypes stoneChestType;

    public StoneChestBlockEntity() {
        this(CustomChestTypes.ACACIA);
    }

    public StoneChestBlockEntity(CustomChestTypes type) {
        super(NBlockEntities.TILE_STONE_CHEST);
        this.stoneChestType = type;
    }

    public CustomChestTypes getStoneChestType() {
        return stoneChestType;
    }
}