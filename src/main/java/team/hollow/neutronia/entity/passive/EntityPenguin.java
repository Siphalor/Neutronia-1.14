package team.abnormals.neutronia.entity.passive;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.class_1394;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.SpawnType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import team.abnormals.neutronia.init.NEntityTypes;

public class EntityPenguin extends AnimalEntity {
	private static final TrackedData<Boolean> IS_SLIDING = DataTracker.registerData(EntityPenguin.class,
			TrackedDataHandlerRegistry.BOOLEAN);
	private static final Ingredient TEMPTATION_ITEMS = Ingredient.ofItems(Items.PUFFERFISH, Items.SALMON, Items.TROPICAL_FISH, Items.COD);
	public float wingRotation;
	public float destPos;
	public float oFlapSpeed;
	public float oFlap;
	public float wingRotDelta = 1.0F;

	public int timeUntilNextEgg;

	public EntityPenguin(World worldIn) {
		super(NEntityTypes.PENGUIN, worldIn);
		this.timeUntilNextEgg = this.getRand().nextInt(6000) + 6000;
		this.setPathNodeTypeWeight(PathNodeType.WATER, 0.0F);
	}

	protected void initGoals() {
		this.goalSelector.add(0, new SwimGoal(this));
		this.goalSelector.add(1, new AnimalMateGoal(this, 1.0D));
		this.goalSelector.add(2, new TemptGoal(this, 1.25D, false, TEMPTATION_ITEMS));
		this.goalSelector.add(3, new EntityPenguin.AISlideAway(this, PlayerEntity.class, 8.0F));
		this.goalSelector.add(3, new EntityPenguin.AISlideAway(this, ArrowEntity.class, 16.0F));
		this.goalSelector.add(4, new FollowParentGoal(this, 1.25D));
		this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.add(6, new class_1394(this, 1.0D));
		this.goalSelector.add(7, new LookAroundGoal(this));
	}

	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.set(IS_SLIDING, Boolean.FALSE);
	}

    @Override
    public float getEyeHeight(EntityPose entityPose_1) {
        return this.getHeight() - 0.15F;
    }

    protected void initAttributes() {
		super.initAttributes();
		this.getAttributeContainer().register(EntityAttributes.MAX_HEALTH).setBaseValue(6.0D);
		this.getAttributeContainer().register(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}

	/**
	 * Called frequently so the entity can update its state every tick as
	 * required. For example, zombies and skeletons use this to react to
	 * sunlight and start to burn.
	 */
	public void update() {
		super.update();
		this.oFlap = this.wingRotation;
		this.oFlapSpeed = this.destPos;
		this.destPos = (float) ((double) this.destPos + (double) (this.onGround ? -1 : 2) * 0.3D);
		this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);

		if (!this.onGround && this.wingRotDelta < 1.0F) {
			this.wingRotDelta = 1.0F;
		}

		this.wingRotDelta = (float) ((double) this.wingRotDelta * 0.9D);

		if (!this.onGround && this.x < 0.0D) {
			this.x *= 0.8D;
		}

		this.wingRotation += this.wingRotDelta * 2.0F;

		if (!this.world.isClient && !this.isChild() && --this.timeUntilNextEgg <= 0) {
			this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F,
					(this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
//			this.dropItem(HailstormItems.PENGUIN_EGG, 1);
			this.timeUntilNextEgg = this.random.nextInt(6000) + 6000;
		}
	}

	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_CHICKEN_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_CHICKEN_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_CHICKEN_DEATH;
	}

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, 1.0F);
	}

	public EntityPenguin createChild(PassiveEntity entity) {
		return new EntityPenguin(this.world);
	}

	public boolean isSliding() {
		return this.dataTracker.get(IS_SLIDING).booleanValue();
	}

	public void setSliding(boolean standing) {
		this.dataTracker.set(IS_SLIDING, standing);
	}

	/**
	 * Checks if the parameter is an item which this animal can be fed to breed
	 * it (wheat, carrots or seeds depending on the animal type)
	 */
	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return TEMPTATION_ITEMS.matches(stack);
	}

    @Override
    protected int getCurrentExperience(PlayerEntity playerEntity_1) {
        return super.getCurrentExperience(playerEntity_1);
    }

    /**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readCustomDataFromTag(CompoundTag compound) {
		super.readCustomDataFromTag(compound);
		if (compound.hasUuid("EggLayTime")) {
			this.timeUntilNextEgg = compound.getInt("EggLayTime");
		}
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeCustomDataToTag(CompoundTag compound) {
		super.writeCustomDataToTag(compound);
		compound.putInt("EggLayTime", this.timeUntilNextEgg);
	}

    @Override
    public EntityData prepareEntityData(IWorld iWorld_1, LocalDifficulty localDifficulty_1, SpawnType spawnType_1, EntityData entityData_1, CompoundTag compoundTag_1) {
        if (random.nextInt(8) == 0) {
            if (entityData_1 instanceof EntityPenguin.GroupData) {
                if (((EntityPenguin.GroupData) entityData_1).madeParent) {
                    this.setBreedingAge(-24000);
                }
            } else {
                EntityPenguin.GroupData entitypenguin$groupdata = new EntityPenguin.GroupData();
                entitypenguin$groupdata.madeParent = true;
                entityData_1 = entitypenguin$groupdata;
            }
        }
	    return entityData_1;
    }

    @Override
    public boolean canSpawn(IWorld iWorld_1, SpawnType spawnType_1) {
	    int i = MathHelper.floor(this.x);
        int j = MathHelper.floor(this.getBoundingBox().minY);
        int k = MathHelper.floor(this.z);
        BlockPos blockpos = new BlockPos(i, j, k);
        boolean isOverworld = this.world.dimension.getType().getRawId() == DimensionType.OVERWORLD.getRawId();
        return isOverworld && this.world.getBlockState(blockpos.down()).getBlock() == Blocks.GRASS_BLOCK && super.canSpawn(iWorld_1, spawnType_1);
    }

    static class GroupData implements EntityData {
		public boolean madeParent;

		private GroupData() {
		}
	}

	class AISlideAway extends FleeEntityGoal {

		private EntityPenguin entity;
		private Class entityToAvoid;
		private float avoidDistance;

		public AISlideAway(EntityPenguin entityIn, Class entityToAvoidIn, float avoidDistanceIn) {
			super(entityIn, entityToAvoidIn, avoidDistanceIn, 1.75D, 2.0D);
			this.entity = entityIn;
			this.entityToAvoid = entityToAvoidIn;
			this.avoidDistance = avoidDistanceIn;
		}

		public void start() {
			EntityPenguin.this.setSliding(true);
			super.start();
		}

		public void onRemove() {
			EntityPenguin.this.setSliding(false);
			super.onRemove();
		}
	}
}