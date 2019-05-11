package team.hollow.neutronia.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.network.packet.EntitySpawnS2CPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.util.TagHelper;
import net.minecraft.util.math.BoundingBox;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import team.hollow.neutronia.modules.endecorations.PaperLanternsFeature;

public class FlyingLanternEntity extends Entity {
	public static final double MAX_SPEED = 0.04;

	protected int targetHeight;

	public BlockState getBlockState() {
		return blockState;
	}

	protected BlockState blockState;

	public FlyingLanternEntity(EntityType<FlyingLanternEntity> entityType_1, World world_1) {
		super(entityType_1, world_1);
		this.field_6033 = true;
		this.noClip = false;
		blockState = Blocks.GLOWSTONE.getDefaultState();
		targetHeight = 250;
	}

	public static FlyingLanternEntity create(World world, double x, double y, double z, BlockState blockState) {
		FlyingLanternEntity entity = new FlyingLanternEntity(PaperLanternsFeature.flyingLanternEntityEntityType, world);
		entity.setPosition(x, y, z);
		entity.blockState = blockState;
		entity.targetHeight = Math.min(265, (int) y + 55) + entity.random.nextInt(20) - 10;
		return entity;
	}

	public void offsetXZVelocity(double x, double z) {
        addVelocity(x, 0, z);
	}

	@Override
	public BoundingBox method_5708(Entity entity_1) {
		return entity_1.isPushable() ? entity_1.getBoundingBox() : null;
	}

	@Override
	public BoundingBox method_5827() {
		return this.getBoundingBox();
	}

	@Override
	public boolean collides() {
		return !removed;
	}

	protected void dropItem() {
		world.spawnEntity(new ItemEntity(world, x, y, z, new ItemStack(blockState.getBlock())));
	}

	@Override
	public boolean damage(DamageSource damageSource_1, float float_1) {
        if(world.isClient() || removed) return false;
        if(isInvulnerableTo(damageSource_1)) return false;
        if(world.getGameRules().getBoolean("doEntityDrops"))
        	dropItem();
        remove();
        return true;
	}

	@Override
	protected void initDataTracker() {
	}

	@Override
	protected void readCustomDataFromTag(CompoundTag nbt) {
		blockState = TagHelper.deserializeBlockState(nbt.getCompound("BlockState"));
		targetHeight = nbt.getInt("targetHeight");
	}

	@Override
	protected void writeCustomDataToTag(CompoundTag nbt) {
		nbt.put("BlockState", TagHelper.serializeBlockState(blockState));
		nbt.putInt("targetHeight", targetHeight);
	}

	@Override
	public Packet<?> createSpawnPacket() {
		return new EntitySpawnS2CPacket(this, Block.getRawIdFromState(blockState));
	}

	@Override
	public int getLightmapCoordinates() {
		return 15728880;
	}

	@Override
	public void tick() {
		double yVelo = getVelocity().y + 0.001 * (targetHeight - y);
		setVelocity(getVelocity().x * 0.99, MathHelper.clamp(yVelo, -MAX_SPEED, MAX_SPEED), getVelocity().z * 0.99);

		move(MovementType.SELF, getVelocity());

		super.tick();
	}
}
