package team.abnormals.neutronia.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.Direction;

public class BlockNeutroniaPillar extends BlockModBase implements INeutroniaBlock {

    private static final EnumProperty<Direction.Axis> AXIS = EnumProperty.create("axis", Direction.Axis.class);

    public BlockNeutroniaPillar(Material materialIn, String name) {
        super(materialIn, name);
        setDefaultState(this.stateFactory.getDefaultState().with(AXIS, Direction.Axis.Y));
    }

    public BlockNeutroniaPillar(Settings builder, String name) {
        super(builder, name);
    }

    public BlockNeutroniaPillar(Material material, String name, float hardnesss, float resistance) {
        super(Settings.of(material).strength(hardnesss, resistance), name);
        setDefaultState(this.stateFactory.getDefaultState().with(AXIS, Direction.Axis.Y));
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
        stateFactory$Builder_1.with(AXIS);
    }

    @Override
    public BlockState applyRotation(BlockState blockState_1, Rotation rotation_1) {
        switch (rotation_1) {
            case ROT_270:
            case ROT_90:
                switch (blockState_1.get(AXIS)) {
                    case X:
                        return blockState_1.with(AXIS, Direction.Axis.Z);
                    case Z:
                        return blockState_1.with(AXIS, Direction.Axis.X);
                    default:
                        return blockState_1;
                }
            default:
                return blockState_1;
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
        return super.getPlacementState(itemPlacementContext_1).with(AXIS, itemPlacementContext_1.getFacing().getAxis());
    }

}