package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import team.hollow.neutronia.entity.FlyingLanternEntity;

public class PaperLanternBlock extends PillarBlock {
	public PaperLanternBlock() {
		super(FabricBlockSettings.copy(Blocks.OAK_PLANKS).lightLevel(15).build());
	}

	@Override
	public boolean activate(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockHitResult blockHitResult) {
        if(playerEntity.getStackInHand(hand).getItem() instanceof FlintAndSteelItem) {
        	playerEntity.getStackInHand(hand).applyDamage(1, playerEntity, playerEntity1 -> world.playSound(playerEntity1, playerEntity1.getBlockPos(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS, 1.0F, 1.0F));
            if(!world.isClient()) {
				FlyingLanternEntity entity = FlyingLanternEntity.create(world, blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5, blockState);
				world.spawnEntity(entity);
				//entity.offsetXZVelocity((blockHitResult.getPos().x - blockPos.getX()) * 0.01, (blockPos.getZ() - blockHitResult.getPos().z) * 0.01);
				Vec3d vector = playerEntity.getRotationVector().multiply(0.025);
				entity.offsetXZVelocity(vector.x, vector.z);
				world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
				world.playSound(null, blockPos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);
			}
            return true;
		}
        return super.activate(blockState, world, blockPos, playerEntity, hand, blockHitResult);
	}
}
