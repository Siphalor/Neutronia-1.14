package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.BlockView;
import team.hollow.neutronia.blocks.entity.ChestBaseBlockEntity;

public class ChestBaseBlock extends ChestBlock {

    private Identifier chestTexture;
    private Identifier doubleChestTexture;
    private Identifier trappedChestTexture;
    private Identifier trappedDoubleChestTexture;

    public ChestBaseBlock() {
        super(FabricBlockSettings.of(Material.STONE).hardness(3.0f).resistance(30.0f).build());
    }

    @Override
    public boolean hasBlockEntity() {
        return true;
    }

    @Override
    public BlockEntity createBlockEntity(BlockView var1) {
        return new ChestBaseBlockEntity(this);
    }

    public Identifier getModelTexture() {
        return chestTexture;
    }

    public ChestBaseBlock setChestTexture(Identifier chestTexture) {
        this.chestTexture = chestTexture;
        return this;
    }

    public Identifier getDoubleModelTexture() {
        return doubleChestTexture;
    }

    public ChestBaseBlock setDoubleChestTexture(Identifier doubleChestTexture) {
        this.doubleChestTexture = doubleChestTexture;
        return this;
    }

    public Identifier getTrappedChestTexture() {
        return trappedChestTexture;
    }

    public ChestBaseBlock setTrappedChestTexture(Identifier trappedChestTexture) {
        this.trappedChestTexture = trappedChestTexture;
        return this;
    }

    public Identifier getTrappedDoubleChestTexture() {
        return trappedDoubleChestTexture;
    }

    public ChestBaseBlock setTrappedDoubleChestTexture(Identifier trappedDoubleChestTexture) {
        this.trappedDoubleChestTexture = trappedDoubleChestTexture;
        return this;
    }

}