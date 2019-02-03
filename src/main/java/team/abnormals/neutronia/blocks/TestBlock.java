package team.abnormals.neutronia.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import team.abnormals.neutronia.enums.CarvedFaceTypes;
import team.abnormals.neutronia.utils.helpers.ICarvable;

public class TestBlock extends BlockModBase implements INeutroniaBlock, ICarvable {

    public TestBlock(Identifier identifier) {
        super(Material.STONE, identifier.getPath());
    }

    public TestBlock() {
        super(Material.STONE, "test_block");
    }

    @Override
    public String getFormatString() {
        return "%s_test_block";
    }

    @Override
    public CarvedFaceTypes fromIdentifier(Identifier identifier) {
        String[] values = identifier.getPath().split("_");
        if(values.length == 2){
            return null;
        }
        return CarvedFaceTypes.valueOf(values[0].toUpperCase());
    }

    @Override
    public ICarvable newInstance(Identifier identifier) {
        return new TestBlock();
    }

    @Override
    public Block getUncarvedBlock() {
        return Blocks.DIRT;
    }
}
