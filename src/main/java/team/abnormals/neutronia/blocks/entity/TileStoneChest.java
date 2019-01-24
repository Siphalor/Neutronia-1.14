package team.abnormals.neutronia.blocks.entity;

import net.minecraft.block.entity.ChestBlockEntity;
import team.abnormals.neutronia.enums.CustomChestTypes;
import team.abnormals.neutronia.init.NBlockEntities;

public class TileStoneChest extends ChestBlockEntity {

    private final CustomChestTypes stoneChestType;

    public TileStoneChest()
    {
        this(CustomChestTypes.ACACIA);
    }

    public TileStoneChest(CustomChestTypes type)
    {
        super(NBlockEntities.TILE_STONE_CHEST);
        this.stoneChestType = type;
    }

    public CustomChestTypes getStoneChestType() {
        return stoneChestType;
    }
}