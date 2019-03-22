package team.hollow.neutronia.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import team.hollow.neutronia.entity.ai.goal.WolfBegGoal;
import team.hollow.neutronia.init.NEntityTypes;

import java.util.Objects;
import java.util.UUID;

import static net.minecraft.entity.passive.WolfEntity.field_18004;

public class ArcticWolfEntity extends TameableEntity {
    private static final TrackedData<Float> HEALTH = DataTracker.registerData(ArcticWolfEntity.class, TrackedDataHandlerRegistry.FLOAT);
    private static final TrackedData<Boolean> BEGGING = DataTracker.registerData(ArcticWolfEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Integer> COLLAR_COLOR = DataTracker.registerData(ArcticWolfEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private float headRotationCourse;
    private float headRotationCourseOld;
    private boolean isWet;
    private boolean isShaking;
    private float timeWolfIsShaking;
    private float prevTimeWolfIsShaking;

    public ArcticWolfEntity(World worldIn) {
        super(NEntityTypes.ARCTIC_WOLF, worldIn);
        this.setTamed(false);
    }

    protected void initEntityAI() {
        this.sitGoal = new SitGoal(this);
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, this.sitGoal);
        this.goalSelector.add(3, new ArcticWolfEntity.AIAvoidEntity<>(this, LlamaEntity.class, 24.0F, 1.5D, 1.5D));
        this.goalSelector.add(4, new PounceAtTargetGoal(this, 0.4F));
        this.goalSelector.add(5, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.add(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F));
        this.goalSelector.add(7, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(8, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(9, new WolfBegGoal(this, 8.0F));
        this.goalSelector.add(10, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(10, new LookAroundGoal(this));
        this.targetSelector.add(1, new TrackAttackerGoal(this));
        this.targetSelector.add(2, new AttackWithOwnerGoal(this));
        this.targetSelector.add(3, (new AvoidGoal(this)).method_6318());
        this.targetSelector.add(4, new FollowTargetIfTamedGoal<>(this, AnimalEntity.class, false, field_18004));
        this.targetSelector.add(4, new FollowTargetIfTamedGoal<>(this, TurtleEntity.class, false, TurtleEntity.BABY_TURTLE_ON_LAND_FILTER));
        this.targetSelector.add(5, new FollowTargetGoal<>(this, AbstractSkeletonEntity.class, false));
    }

    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeContainer().get(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);

        if (this.isTamed()) {
            this.getAttributeContainer().get(EntityAttributes.MAX_HEALTH).setBaseValue(20.0D);
        } else {
            this.getAttributeContainer().get(EntityAttributes.MAX_HEALTH).setBaseValue(8.0D);
        }

        this.getAttributeContainer().register(EntityAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
    }

    public void setTarget(LivingEntity livingEntity_1) {
        super.setTarget(livingEntity_1);
        if (livingEntity_1 == null) {
            this.setAngry(false);
        } else if (!this.isTamed()) {
            this.setAngry(true);
        }

    }

    protected void mobTick() {
        this.dataTracker.set(HEALTH, this.getHealth());
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(HEALTH, this.getHealth());
        this.dataTracker.startTracking(BEGGING, false);
        this.dataTracker.startTracking(COLLAR_COLOR, DyeColor.RED.getId());
    }

    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
    }

    public void writeCustomDataToTag(CompoundTag compoundTag_1) {
        super.writeCustomDataToTag(compoundTag_1);
        compoundTag_1.putBoolean("Angry", this.isAngry());
        compoundTag_1.putByte("CollarColor", (byte) this.getCollarColor().getId());
    }

    public void readCustomDataFromTag(CompoundTag compoundTag_1) {
        super.readCustomDataFromTag(compoundTag_1);
        this.setAngry(compoundTag_1.getBoolean("Angry"));
        if (compoundTag_1.containsKey("CollarColor", 99)) {
            this.setCollarColor(DyeColor.byId(compoundTag_1.getInt("CollarColor")));
        }

    }

    protected SoundEvent getAmbientSound() {
        if (this.isAngry()) {
            return SoundEvents.ENTITY_WOLF_GROWL;
        } else if (this.random.nextInt(3) == 0) {
            return this.isTamed() && this.dataTracker.get(HEALTH) < 10.0F ? SoundEvents.ENTITY_WOLF_WHINE : SoundEvents.ENTITY_WOLF_PANT;
        } else {
            return SoundEvents.ENTITY_WOLF_AMBIENT;
        }
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_WOLF_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_WOLF_DEATH;
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume() {
        return 0.4F;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void updateMovement() {
        super.updateMovement();

        if (!this.world.isClient && this.isWet && !this.isShaking && !this.isNavigating() && this.onGround) {
            this.isShaking = true;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
            this.world.summonParticle(this, (byte) 8);
        }

        if (!this.world.isClient && this.getTarget() == null && this.isAngry()) {
            this.setAngry(false);
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void tick() {
        super.tick();
        this.headRotationCourseOld = this.headRotationCourse;

        if (this.isBegging()) {
            this.headRotationCourse += (1.0F - this.headRotationCourse) * 0.4F;
        } else {
            this.headRotationCourse += (0.0F - this.headRotationCourse) * 0.4F;
        }

        if (this.isTouchingWater()) {
            this.isWet = true;
            this.isShaking = false;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
        } else if ((this.isWet || this.isShaking) && this.isShaking) {
            if (this.timeWolfIsShaking == 0.0F) {
                this.playSound(SoundEvents.ENTITY_WOLF_SHAKE, this.getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            }

            this.prevTimeWolfIsShaking = this.timeWolfIsShaking;
            this.timeWolfIsShaking += 0.05F;

            if (this.prevTimeWolfIsShaking >= 2.0F) {
                this.isWet = false;
                this.isShaking = false;
                this.prevTimeWolfIsShaking = 0.0F;
                this.timeWolfIsShaking = 0.0F;
            }

            if (this.timeWolfIsShaking > 0.4F) {
                float float_1 = (float) this.getBoundingBox().minY;
                int int_1 = (int) (MathHelper.sin((this.timeWolfIsShaking - 0.4F) * 3.1415927F) * 7.0F);
                Vec3d vec3d_1 = this.getVelocity();

                for (int j = 0; j < int_1; ++j) {
                    float f1 = (this.random.nextFloat() * 2.0F - 1.0F) * this.getWidth() * 0.5F;
                    float f2 = (this.random.nextFloat() * 2.0F - 1.0F) * this.getWidth() * 0.5F;
                    this.world.addParticle(ParticleTypes.SPLASH, this.x + (double) f1, (double) (float_1 + 0.8F), this.z + (double) f2, vec3d_1.x, vec3d_1.y, vec3d_1.z);
                }
            }
        }
    }

    @Environment(EnvType.CLIENT)
    public boolean isWet() {
        return this.isWet;
    }

    @Environment(EnvType.CLIENT)
    public float method_6707(float float_1) {
        return 0.75F + MathHelper.lerp(float_1, this.timeWolfIsShaking, this.prevTimeWolfIsShaking) / 2.0F * 0.25F;
    }

    @Environment(EnvType.CLIENT)
    public float method_6715(float float_1, float float_2) {
        float float_3 = (MathHelper.lerp(float_1, this.timeWolfIsShaking, this.prevTimeWolfIsShaking) + float_2) / 1.8F;
        if (float_3 < 0.0F) {
            float_3 = 0.0F;
        } else if (float_3 > 1.0F) {
            float_3 = 1.0F;
        }

        return MathHelper.sin(float_3 * 3.1415927F) * MathHelper.sin(float_3 * 3.1415927F * 11.0F) * 0.15F * 3.1415927F;
    }

    @Environment(EnvType.CLIENT)
    public float method_6719(float float_1) {
        return MathHelper.lerp(float_1, this.headRotationCourse, this.headRotationCourseOld) * 0.15F * 3.1415927F;
    }

    protected float getActiveEyeHeight(EntityPose entityPose_1, EntitySize entitySize_1) {
        return entitySize_1.height * 0.8F;
    }

    public int method_5978() {
        return this.isSitting() ? 20 : super.method_5978();
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean damage(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            Entity entity = source.getAttacker();

            if (this.sitGoal != null) {
                this.sitGoal.setEnabledWithOwner(false);
            }

            if (entity != null && !(entity instanceof PlayerEntity) && !(entity instanceof ArrowEntity)) {
                amount = (amount + 1.0F) / 2.0F;
            }

            return super.damage(source, amount);
        }
    }

    public boolean attack(Entity entityIn) {
        boolean flag = entityIn.damage(DamageSource.mob(this), (float) ((int) this.getAttributeContainer().get(EntityAttributes.ATTACK_DAMAGE).getValue()));

        if (flag) {
            this.dealDamage(this, entityIn);
        }

        return flag;
    }

    public void setTamed(boolean tamed) {
        super.setTamed(tamed);

        if (tamed) {
            this.getAttributeContainer().get(EntityAttributes.MAX_HEALTH).setBaseValue(20.0D);
        } else {
            this.getAttributeContainer().get(EntityAttributes.MAX_HEALTH).setBaseValue(8.0D);
        }

        this.getAttributeContainer().get(EntityAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    public boolean interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getStackInHand(hand);
        Item item_1 = itemstack.getItem();

        if (this.isTamed()) {
            if (!itemstack.isEmpty()) {
                if (item_1.isFood()) {
                    if (Objects.requireNonNull(item_1.getFoodSetting()).isWolfFood() && this.dataTracker.get(HEALTH) < 20.0F) {
                        if (!player.abilities.creativeMode) {
                            itemstack.subtractAmount(1);
                        }

                        this.heal((float) Objects.requireNonNull(item_1.getFoodSetting()).getHunger());
                        return true;
                    }
                } else if (itemstack.getItem() instanceof DyeItem) {
                    DyeColor dyeColor_1 = ((DyeItem) itemstack.getItem()).getColor();
                    if (dyeColor_1 != this.getCollarColor()) {
                        this.setCollarColor(dyeColor_1);
                        if (!player.abilities.creativeMode) {
                            itemstack.subtractAmount(1);
                        }

                        return true;
                    }
                }
            }

            if (this.isOwner(player) && !this.world.isClient && !this.isBreedingItem(itemstack)) {
                this.sitGoal.setEnabledWithOwner(!this.isSitting());
                this.field_6282 = false;
                this.navigation.stop();
                this.setTarget(null);
            }
        } else if (itemstack.getItem() == Items.BONE && !this.isAngry()) {
            if (!player.abilities.creativeMode) {
                itemstack.subtractAmount(1);
            }

            if (!this.world.isClient) {
                if (this.random.nextInt(3) == 0) {
                    this.method_6170(player);
                    this.navigation.stop();
                    this.setTarget(null);
                    this.sitGoal.setEnabledWithOwner(true);
                    this.setHealth(20.0F);
                    this.method_6180(true);
                    this.world.summonParticle(this, (byte) 7);
                } else {
                    this.method_6180(false);
                    this.world.summonParticle(this, (byte) 6);
                }
            }

            return true;
        }

        return super.interactMob(player, hand);
    }

    @Environment(EnvType.CLIENT)
    public void method_5711(byte byte_1) {
        if (byte_1 == 8) {
            this.isShaking = true;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
        } else {
            super.method_5711(byte_1);
        }

    }

    @Environment(EnvType.CLIENT)
    public float method_6714() {
        if (this.isAngry()) {
            return 1.5393804F;
        } else {
            return this.isTamed() ? (0.55F - (this.getHealthMaximum() - this.dataTracker.get(HEALTH)) * 0.02F) * 3.1415927F : 0.62831855F;
        }
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    public boolean isBreedingItem(ItemStack stack) {
        Item item_1 = stack.getItem();
        return item_1.isFood() && Objects.requireNonNull(item_1.getFoodSetting()).isWolfFood();
    }

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk() {
        return 8;
    }

    /**
     * Determines whether this wolf is angry or not.
     */
    public boolean isAngry() {
        return (this.dataTracker.get(TAMEABLE_FLAGS) & 2) != 0;
    }

    /**
     * Sets whether this wolf is angry or not.
     */
    public void setAngry(boolean angry) {
        byte b0 = this.dataTracker.get(TAMEABLE_FLAGS);

        if (angry) {
            this.dataTracker.set(TAMEABLE_FLAGS, (byte) (b0 | 2));
        } else {
            this.dataTracker.set(TAMEABLE_FLAGS, (byte) (b0 & -3));
        }
    }

    public DyeColor getCollarColor() {
        return DyeColor.byId(this.dataTracker.get(COLLAR_COLOR) & 15);
    }

    public void setCollarColor(DyeColor collarcolor) {
        this.dataTracker.set(COLLAR_COLOR, collarcolor.getId());
    }

    public ArcticWolfEntity createChild(PassiveEntity ageable) {
        ArcticWolfEntity wolfEntity_1 = NEntityTypes.ARCTIC_WOLF.create(this.world);
        UUID uUID_1 = this.method_6139();
        if (uUID_1 != null) {
            Objects.requireNonNull(wolfEntity_1).setOwnerUuid(uUID_1);
            wolfEntity_1.setTamed(true);
        }

        return wolfEntity_1;
    }

    /**
     * Returns true if the mob is currently able to mate with the specified mob.
     */
    public boolean canBreedWith(AnimalEntity otherAnimal) {
        if (otherAnimal == this) {
            return false;
        } else if (!this.isTamed()) {
            return false;
        } else if (!(otherAnimal instanceof ArcticWolfEntity)) {
            return false;
        } else {
            ArcticWolfEntity entitywolf = (ArcticWolfEntity) otherAnimal;

            if (!entitywolf.isTamed()) {
                return false;
            } else if (entitywolf.isSitting()) {
                return false;
            } else {
                return this.isInLove() && entitywolf.isInLove();
            }
        }
    }

    public boolean isBegging() {
        return this.dataTracker.get(BEGGING);
    }

    public void setBegging(boolean beg) {
        this.dataTracker.set(BEGGING, beg);
    }

    public boolean method_6178(LivingEntity target, LivingEntity owner) {
        if (!(target instanceof CreeperEntity) && !(target instanceof GhastEntity)) {
            if (target instanceof ArcticWolfEntity) {
                ArcticWolfEntity entitywolf = (ArcticWolfEntity) target;

                if (entitywolf.isTamed() && entitywolf.getOwner() == owner) {
                    return false;
                }
            }

            if (target instanceof PlayerEntity && owner instanceof PlayerEntity && !((PlayerEntity) owner).shouldDamagePlayer((PlayerEntity) target)) {
                return false;
            } else {
                return !(target instanceof HorseBaseEntity) || !((HorseBaseEntity) target).isTame();
            }
        } else {
            return false;
        }
    }

    public boolean canBeLeashedBy(PlayerEntity playerEntity_1) {
        return !this.isAngry() && super.canBeLeashedBy(playerEntity_1);
    }

    class AIAvoidEntity<T extends LivingEntity> extends FleeEntityGoal<T> {
        private final ArcticWolfEntity wolf;

        public AIAvoidEntity(ArcticWolfEntity wolfIn, Class<T> p_i47251_3_, float p_i47251_4_, double p_i47251_5_, double p_i47251_7_) {
            super(wolfIn, p_i47251_3_, p_i47251_4_, p_i47251_5_, p_i47251_7_);
            this.wolf = wolfIn;
        }

        public boolean canStart() {
            if (super.canStart() && this.field_6390 instanceof LlamaEntity) {
                return !this.wolf.isTamed() && this.method_6720((LlamaEntity) this.field_6390);
            } else {
                return false;
            }
        }

        private boolean method_6720(LlamaEntity llamaEntity_1) {
            return llamaEntity_1.getStrength() >= ArcticWolfEntity.this.random.nextInt(5);
        }

        public void start() {
            ArcticWolfEntity.this.setTarget(null);
            super.start();
        }

        public void tick() {
            ArcticWolfEntity.this.setTarget(null);
            super.tick();
        }
    }
}