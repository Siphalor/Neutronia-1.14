package team.hollow.neutronia.blocks.vanillish;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.Direction;
import team.hollow.neutronia.blocks.NeutroniaBlock;

public class NeutroniaPillarBlock extends NeutroniaBlock {

    private static final EnumProperty<Direction.Axis> AXIS = EnumProperty.create("axis", Direction.Axis.class);

    public NeutroniaPillarBlock(Material materialIn) {
        super(materialIn);
        setDefaultState(this.stateFactory.getDefaultState().with(AXIS, Direction.Axis.Y));
    }

    public NeutroniaPillarBlock(FabricBlockSettings builder) {
        super(builder);
    }

    public NeutroniaPillarBlock(Material material, float hardnesss, float resistance) {
        super(FabricBlockSettings.of(material).strength(hardnesss, resistance));
        setDefaultState(this.stateFactory.getDefaultState().with(AXIS, Direction.Axis.Y));
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
        stateFactory$Builder_1.add(AXIS);
    }

    @Override
    public BlockState rotate(BlockState blockState_1, BlockRotation rotation_1) {
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
        return this.getDefaultState().with(AXIS, itemPlacementContext_1.getFacing().getOpposite().getAxis());
    }

}