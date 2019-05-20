package team.hollow.neutronia.blocks.entity;

import net.minecraft.block.entity.ChestBlockEntity;
import team.hollow.neutronia.blocks.ChestBaseBlock;
import team.hollow.neutronia.init.NBlockEntities;

public class ChestBaseBlockEntity extends ChestBlockEntity {

    public ChestBaseBlock baseBlock;

    public ChestBaseBlockEntity() {
        this(new ChestBaseBlock());
    }

    public ChestBaseBlockEntity(ChestBaseBlock chestBaseBlock) {
        super(NBlockEntities.CHEST_BASE_BE);
        this.baseBlock = chestBaseBlock;
    }

}