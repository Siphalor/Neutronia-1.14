package team.abnormals.neutronia.blocks;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.block.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class BlockCustomLadder extends LadderBlock implements INeutroniaBlock {

    public BlockCustomLadder(String variant) {
        super(FabricBlockSettings.of(Material.PART).hardness(0.4F).sounds(BlockSoundGroup.LADDER).build());
        setDefaultState(this.getDefaultState().with(field_11253, Direction.NORTH).with(field_11257, false));
        register(variant + "_ladder", this, ItemGroup.DECORATIONS);
    }

    private void register(String name, Block block, ItemGroup tab) {
        Registry.register(Registry.BLOCK, getPrefix() + name, block);
        BlockItem item = new BlockItem(block, new Item.Settings().itemGroup(tab));
        item.registerBlockItemMap(Item.BLOCK_ITEM_MAP, item);
        Registry.register(Registry.ITEM, getPrefix() + name, item);
    }

    @Override
    public void onEntityCollision(BlockState blockState_1, World world_1, BlockPos blockPos_1, Entity entity_1) {
        entity_1.velocityX = MathHelper.clamp(entity_1.velocityX, (double)-0.15F, (double)0.15F);
        entity_1.velocityZ = MathHelper.clamp(entity_1.velocityZ, (double)-0.15F, (double)0.15F);
        entity_1.fallDistance = 0.0F;
        if (entity_1.velocityY < -0.15D) {
            entity_1.velocityY = -0.15D;
        }

        boolean flag = entity_1.isSneaking() && entity_1 instanceof PlayerEntity;
        if (flag && entity_1.velocityY < 0.0D) {
            entity_1.velocityY = 0.0D;
        }
    }

}