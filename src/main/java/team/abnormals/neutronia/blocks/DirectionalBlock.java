package team.abnormals.neutronia.blocks;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.state.property.DirectionProperty;

public abstract class DirectionalBlock extends BaseModBlock {
    public static final DirectionProperty FACING = DirectionProperty.create("facing");

    protected DirectionalBlock(Material materialIn, String name) {
        super(materialIn, name);
    }

    public DirectionalBlock(Settings builder, String name) {
        super(builder, name);
    }

    public DirectionalBlock(FabricBlockSettings blockSettings, String name) {
        super(blockSettings.build(), name);
    }

}