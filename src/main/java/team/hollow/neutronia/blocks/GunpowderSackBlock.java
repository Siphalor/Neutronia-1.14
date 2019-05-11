package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FireBlock;
import net.minecraft.block.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class GunpowderSackBlock extends NeutroniaBottomTopBlock {
	public GunpowderSackBlock() {
		super(FabricBlockSettings.of(Material.WOOL).strength(1.0F, 2.5F));
	}

	@Override
	public boolean activate(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockHitResult blockHitResult) {
		if(playerEntity.getStackInHand(hand).getItem() instanceof FlintAndSteelItem) {
			playerEntity.getStackInHand(hand).applyDamage(1, playerEntity, playerEntity1 -> world.playSound(null, blockPos, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS, 1.0F, 1.0F));
			explode(world, blockPos, playerEntity);
			return true;
		}
		return super.activate(blockState, world, blockPos, playerEntity, hand, blockHitResult);
	}

	@Override
	public void onDestroyedByExplosion(World world, BlockPos blockPos, Explosion explosion) {
		explode(world, blockPos, explosion.getCausingEntity());
		super.onDestroyedByExplosion(world, blockPos, explosion);
	}

	public void explode(World world, BlockPos blockPos, LivingEntity entity) {
		world.createExplosion(entity, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 7.0F, Explosion.DestructionType.BREAK);
	}

	@Override
	public void neighborUpdate(BlockState blockState_1, World world_1, BlockPos blockPos_1, Block block_1, BlockPos blockPos_2, boolean boolean_1) {
		if(world_1.getBlockState(blockPos_2).getBlock() instanceof FireBlock) explode(world_1, blockPos_2, null);
		super.neighborUpdate(blockState_1, world_1, blockPos_1, block_1, blockPos_2, boolean_1);
	}
}
