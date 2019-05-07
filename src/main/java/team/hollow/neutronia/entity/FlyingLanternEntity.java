package team.hollow.neutronia.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.network.packet.EntitySpawnS2CPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.util.TagHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import team.hollow.neutronia.ExampleMod;

public class FlyingLanternEntity extends Entity {
	public static final TrackedData<BlockPos> INITIAL_BLOCK_POS = DataTracker.registerData(FlyingLanternEntity.class, TrackedDataHandlerRegistry.BLOCK_POS);

	public BlockState getBlockState() {
		return blockState;
	}

	protected BlockState blockState;

	public FlyingLanternEntity(EntityType<FlyingLanternEntity> entityType_1, World world_1) {
		super(entityType_1, world_1);
		this.field_6033 = true;
		blockState = Blocks.GLOWSTONE.getDefaultState();
	}

	public FlyingLanternEntity(World world, double x, double y, double z, BlockState blockState) {
		this(ExampleMod.FLYING_LANTERN_ENTITY_TYPE, world);
		blockState = blockState;
		this.setPosition(x, y + (double)((1.0F - this.getHeight()) / 2.0F), z);
		this.setVelocity(Vec3d.ZERO);
		this.prevX = x;
		this.prevY = y;
		this.prevZ = z;
		dataTracker.set(INITIAL_BLOCK_POS, new BlockPos(this));
	}

	protected void dropItem() {
		world.spawnEntity(new ItemEntity(world, x, y, z, new ItemStack(blockState.getBlock())));
	}

	@Override
	public boolean handlePlayerAttack(Entity entity_1) {
		this.dropItem();
		this.remove();
		return super.handlePlayerAttack(entity_1);
	}

	@Override
	protected void initDataTracker() {
		this.dataTracker.startTracking(INITIAL_BLOCK_POS, BlockPos.ORIGIN);
	}

	@Override
	protected void readCustomDataFromTag(CompoundTag nbt) {
		blockState = TagHelper.deserializeBlockState(nbt.getCompound("BlockState"));
	}

	@Override
	protected void writeCustomDataToTag(CompoundTag nbt) {
		nbt.put("BlockState", TagHelper.serializeBlockState(blockState));
	}

	@Override
	public Packet<?> createSpawnPacket() {
		return new EntitySpawnS2CPacket(this, Block.getRawIdFromState(blockState));
	}
}
