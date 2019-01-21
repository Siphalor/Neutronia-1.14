package team.abnormals.neutronia.blocks;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.state.property.DirectionProperty;

public abstract class BlockDirectional extends BlockModBase {
    public static final DirectionProperty FACING = DirectionProperty.create("facing");

    protected BlockDirectional(Material materialIn, String name) {
        super(materialIn, name);
    }

    public BlockDirectional(Settings builder, String name) {
        super(builder, name);
    }

    public BlockDirectional(FabricBlockSettings blockSettings, String name) {
        super(blockSettings.build(), name);
    }

}