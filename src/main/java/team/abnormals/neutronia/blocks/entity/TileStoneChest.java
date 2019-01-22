package team.abnormals.neutronia.blocks.entity;

import net.minecraft.block.entity.ChestBlockEntity;
import team.abnormals.neutronia.enums.WoodenChestTypes;
import team.abnormals.neutronia.init.NBlockEntities;

public class TileStoneChest extends ChestBlockEntity {

    private final WoodenChestTypes stoneChestType;

    public TileStoneChest()
    {
        this(WoodenChestTypes.ACACIA);
    }

    public TileStoneChest(WoodenChestTypes type)
    {
        super(NBlockEntities.TILE_STONE_CHEST);
        this.stoneChestType = type;
    }

    public WoodenChestTypes getStoneChestType() {
        return stoneChestType;
    }
}