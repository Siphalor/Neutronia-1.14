package team.abnormals.neutronia.entity.wip;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_4051;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.BodyControl;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;

import java.util.Iterator;
import java.util.List;

public class PhantomEntity extends FlyingEntity implements Monster {
    private static final TrackedData<Integer> SIZE;

    static {
        SIZE = DataTracker.registerData(PhantomEntity.class, TrackedDataHandlerRegistry.INTEGER);
    }

    private Vec3d field_7314;
    private BlockPos field_7312;
    private PhantomEntity.class_1594 field_7315;

    public PhantomEntity(EntityType<? extends PhantomEntity> entityType_1, World world_1) {
        super(entityType_1, world_1);
        this.field_7314 = Vec3d.ZERO;
        this.field_7312 = BlockPos.ORIGIN;
        this.field_7315 = PhantomEntity.class_1594.CIRCLE;
        this.experiencePoints = 5;
        this.moveControl = new PhantomEntity.PhantomMoveControl(this);
        this.lookControl = new PhantomEntity.PhantomLookControl(this);
    }

    protected BodyControl createBodyControl() {
        return new PhantomEntity.class_1597(this);
    }

    protected void initGoals() {
        this.goalSelector.add(1, new PhantomEntity.class_1596());
        this.goalSelector.add(2, new PhantomEntity.class_1602());
        this.goalSelector.add(3, new PhantomEntity.class_1598());
        this.targetSelector.add(1, new PhantomEntity.class_1595());
    }

    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeContainer().register(EntityAttributes.ATTACK_DAMAGE);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SIZE, 0);
    }

    private void method_7097() {
        this.method_18382();
        this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).setBaseValue((double) (6 + this.getSize()));
    }

    public int getSize() {
        return (Integer) this.dataTracker.get(SIZE);
    }

    public void setSize(int int_1) {
        this.dataTracker.set(SIZE, MathHelper.clamp(int_1, 0, 64));
    }

    protected float getActiveEyeHeight(EntityPose entityPose_1, EntitySize entitySize_1) {
        return entitySize_1.height * 0.35F;
    }

    public void onTrackedDataSet(TrackedData<?> trackedData_1) {
        if (SIZE.equals(trackedData_1)) {
            this.method_7097();
        }

        super.onTrackedDataSet(trackedData_1);
    }

    public void update() {
        super.update();
        if (this.world.isClient) {
            float float_1 = MathHelper.cos((float) (this.getEntityId() * 3 + this.age) * 0.13F + 3.1415927F);
            float float_2 = MathHelper.cos((float) (this.getEntityId() * 3 + this.age + 1) * 0.13F + 3.1415927F);
            if (float_1 > 0.0F && float_2 <= 0.0F) {
                this.world.playSound(this.x, this.y, this.z, SoundEvents.ENTITY_PHANTOM_FLAP, this.getSoundCategory(), 0.95F + this.random.nextFloat() * 0.05F, 0.95F + this.random.nextFloat() * 0.05F, false);
            }

            int int_1 = this.getSize();
            float float_3 = MathHelper.cos(this.yaw * 0.017453292F) * (1.3F + 0.21F * (float) int_1);
            float float_4 = MathHelper.sin(this.yaw * 0.017453292F) * (1.3F + 0.21F * (float) int_1);
            float float_5 = (0.3F + float_1 * 0.45F) * ((float) int_1 * 0.2F + 1.0F);
            this.world.addParticle(ParticleTypes.MYCELIUM, this.x + (double) float_3, this.y + (double) float_5, this.z + (double) float_4, 0.0D, 0.0D, 0.0D);
            this.world.addParticle(ParticleTypes.MYCELIUM, this.x - (double) float_3, this.y + (double) float_5, this.z - (double) float_4, 0.0D, 0.0D, 0.0D);
        }

        if (!this.world.isClient && this.world.getDifficulty() == Difficulty.PEACEFUL) {
            this.invalidate();
        }

    }

    public void updateMovement() {
        if (this.method_5972()) {
            this.setOnFireFor(8);
        }

        super.updateMovement();
    }

    protected void mobTick() {
        super.mobTick();
    }

    public EntityData prepareEntityData(IWorld iWorld_1, LocalDifficulty localDifficulty_1, SpawnType spawnType_1, EntityData entityData_1, CompoundTag compoundTag_1) {
        this.field_7312 = (new BlockPos(this)).up(5);
        this.setSize(0);
        return super.prepareEntityData(iWorld_1, localDifficulty_1, spawnType_1, entityData_1, compoundTag_1);
    }

    public void readCustomDataFromTag(CompoundTag compoundTag_1) {
        super.readCustomDataFromTag(compoundTag_1);
        if (compoundTag_1.containsKey("AX")) {
            this.field_7312 = new BlockPos(compoundTag_1.getInt("AX"), compoundTag_1.getInt("AY"), compoundTag_1.getInt("AZ"));
        }

        this.setSize(compoundTag_1.getInt("Size"));
    }

    public void writeCustomDataToTag(CompoundTag compoundTag_1) {
        super.writeCustomDataToTag(compoundTag_1);
        compoundTag_1.putInt("AX", this.field_7312.getX());
        compoundTag_1.putInt("AY", this.field_7312.getY());
        compoundTag_1.putInt("AZ", this.field_7312.getZ());
        compoundTag_1.putInt("Size", this.getSize());
    }

    @Environment(EnvType.CLIENT)
    public boolean shouldRenderAtDistance(double double_1) {
        return true;
    }

    public SoundCategory getSoundCategory() {
        return SoundCategory.HOSTILE;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_PHANTOM_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSource_1) {
        return SoundEvents.ENTITY_PHANTOM_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PHANTOM_DEATH;
    }

    public EntityGroup getGroup() {
        return EntityGroup.UNDEAD;
    }

    protected float getSoundVolume() {
        return 1.0F;
    }

    public boolean method_5973(EntityType<?> entityType_1) {
        return true;
    }

    public EntitySize getSizeForStatus(EntityPose entityPose_1) {
        int int_1 = this.getSize();
        EntitySize entitySize_1 = super.getSizeForStatus(entityPose_1);
        float float_1 = (entitySize_1.width + 0.2F * (float) int_1) / entitySize_1.width;
        return entitySize_1.scaled(float_1);
    }

    static enum class_1594 {
        CIRCLE,
        SWOOP;
    }

    class class_1595 extends Goal {
        private final class_4051 field_18130;
        private int field_7320;

        private class_1595() {
            this.field_18130 = (new class_4051()).method_18418(64.0D);
            this.field_7320 = 20;
        }

        // $FF: synthetic method
        class_1595(Object phantomEntity$1_1) {
            this();
        }

        public boolean canStart() {
            if (this.field_7320 > 0) {
                --this.field_7320;
                return false;
            } else {
                this.field_7320 = 60;
                List<PlayerEntity> list_1 = PhantomEntity.this.world.method_18464(this.field_18130, PhantomEntity.this, PhantomEntity.this.getBoundingBox().expand(16.0D, 64.0D, 16.0D));
                if (!list_1.isEmpty()) {
                    list_1.sort((playerEntity_1x, playerEntity_2) -> {
                        return playerEntity_1x.y > playerEntity_2.y ? -1 : 1;
                    });
                    Iterator var2 = list_1.iterator();

                    while (var2.hasNext()) {
                        PlayerEntity playerEntity_1 = (PlayerEntity) var2.next();
                        if (PhantomEntity.this.method_18391(playerEntity_1, class_4051.field_18092)) {
                            PhantomEntity.this.setTarget(playerEntity_1);
                            return true;
                        }
                    }
                }

                return false;
            }
        }

        public boolean shouldContinue() {
            LivingEntity livingEntity_1 = PhantomEntity.this.getTarget();
            return livingEntity_1 != null ? PhantomEntity.this.method_18391(livingEntity_1, class_4051.field_18092) : false;
        }
    }

    class class_1596 extends Goal {
        private int field_7322;

        private class_1596() {
        }

        // $FF: synthetic method
        class_1596(Object phantomEntity$1_1) {
            this();
        }

        public boolean canStart() {
            LivingEntity livingEntity_1 = PhantomEntity.this.getTarget();
            return livingEntity_1 != null ? PhantomEntity.this.method_18391(PhantomEntity.this.getTarget(), class_4051.field_18092) : false;
        }

        public void start() {
            this.field_7322 = 10;
            PhantomEntity.this.field_7315 = PhantomEntity.class_1594.CIRCLE;
            this.method_7102();
        }

        public void onRemove() {
            PhantomEntity.this.field_7312 = PhantomEntity.this.world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, PhantomEntity.this.field_7312).up(10 + PhantomEntity.this.random.nextInt(20));
        }

        public void tick() {
            if (PhantomEntity.this.field_7315 == PhantomEntity.class_1594.CIRCLE) {
                --this.field_7322;
                if (this.field_7322 <= 0) {
                    PhantomEntity.this.field_7315 = PhantomEntity.class_1594.SWOOP;
                    this.method_7102();
                    this.field_7322 = (8 + PhantomEntity.this.random.nextInt(4)) * 20;
                    PhantomEntity.this.playSound(SoundEvents.ENTITY_PHANTOM_SWOOP, 10.0F, 0.95F + PhantomEntity.this.random.nextFloat() * 0.1F);
                }
            }

        }

        private void method_7102() {
            PhantomEntity.this.field_7312 = (new BlockPos(PhantomEntity.this.getTarget())).up(20 + PhantomEntity.this.random.nextInt(20));
            if (PhantomEntity.this.field_7312.getY() < PhantomEntity.this.world.getSeaLevel()) {
                PhantomEntity.this.field_7312 = new BlockPos(PhantomEntity.this.field_7312.getX(), PhantomEntity.this.world.getSeaLevel() + 1, PhantomEntity.this.field_7312.getZ());
            }

        }
    }

    class class_1602 extends PhantomEntity.class_1601 {
        private class_1602() {
            super();
        }

        // $FF: synthetic method
        class_1602(Object phantomEntity$1_1) {
            this();
        }

        public boolean canStart() {
            return PhantomEntity.this.getTarget() != null && PhantomEntity.this.field_7315 == PhantomEntity.class_1594.SWOOP;
        }

        public boolean shouldContinue() {
            LivingEntity livingEntity_1 = PhantomEntity.this.getTarget();
            if (livingEntity_1 == null) {
                return false;
            } else if (!livingEntity_1.isValid()) {
                return false;
            } else if (livingEntity_1 instanceof PlayerEntity && (((PlayerEntity) livingEntity_1).isSpectator() || ((PlayerEntity) livingEntity_1).isCreative())) {
                return false;
            } else if (!this.canStart()) {
                return false;
            } else {
                if (PhantomEntity.this.age % 20 == 0) {
                    List<CatEntity> list_1 = PhantomEntity.this.world.method_8390(CatEntity.class, PhantomEntity.this.getBoundingBox().expand(16.0D), EntityPredicates.VALID_ENTITY);
                    if (!list_1.isEmpty()) {
                        Iterator var3 = list_1.iterator();

                        while (var3.hasNext()) {
                            CatEntity catEntity_1 = (CatEntity) var3.next();
                            catEntity_1.method_16089();
                        }

                        return false;
                    }
                }

                return true;
            }
        }

        public void start() {
        }

        public void onRemove() {
            PhantomEntity.this.setTarget((LivingEntity) null);
            PhantomEntity.this.field_7315 = PhantomEntity.class_1594.CIRCLE;
        }

        public void tick() {
            LivingEntity livingEntity_1 = PhantomEntity.this.getTarget();
            PhantomEntity.this.field_7314 = new Vec3d(livingEntity_1.x, livingEntity_1.y + (double) livingEntity_1.getHeight() * 0.5D, livingEntity_1.z);
            if (PhantomEntity.this.getBoundingBox().expand(0.20000000298023224D).intersects(livingEntity_1.getBoundingBox())) {
                PhantomEntity.this.attack(livingEntity_1);
                PhantomEntity.this.field_7315 = PhantomEntity.class_1594.CIRCLE;
                PhantomEntity.this.world.playEvent(1039, new BlockPos(PhantomEntity.this), 0);
            } else if (PhantomEntity.this.horizontalCollision || PhantomEntity.this.hurtTime > 0) {
                PhantomEntity.this.field_7315 = PhantomEntity.class_1594.CIRCLE;
            }

        }
    }

    class class_1598 extends PhantomEntity.class_1601 {
        private float field_7328;
        private float field_7327;
        private float field_7326;
        private float field_7324;

        private class_1598() {
            super();
        }

        // $FF: synthetic method
        class_1598(Object phantomEntity$1_1) {
            this();
        }

        public boolean canStart() {
            return PhantomEntity.this.getTarget() == null || PhantomEntity.this.field_7315 == PhantomEntity.class_1594.CIRCLE;
        }

        public void start() {
            this.field_7327 = 5.0F + PhantomEntity.this.random.nextFloat() * 10.0F;
            this.field_7326 = -4.0F + PhantomEntity.this.random.nextFloat() * 9.0F;
            this.field_7324 = PhantomEntity.this.random.nextBoolean() ? 1.0F : -1.0F;
            this.method_7103();
        }

        public void tick() {
            if (PhantomEntity.this.random.nextInt(350) == 0) {
                this.field_7326 = -4.0F + PhantomEntity.this.random.nextFloat() * 9.0F;
            }

            if (PhantomEntity.this.random.nextInt(250) == 0) {
                ++this.field_7327;
                if (this.field_7327 > 15.0F) {
                    this.field_7327 = 5.0F;
                    this.field_7324 = -this.field_7324;
                }
            }

            if (PhantomEntity.this.random.nextInt(450) == 0) {
                this.field_7328 = PhantomEntity.this.random.nextFloat() * 2.0F * 3.1415927F;
                this.method_7103();
            }

            if (this.method_7104()) {
                this.method_7103();
            }

            if (PhantomEntity.this.field_7314.y < PhantomEntity.this.y && !PhantomEntity.this.world.isAir((new BlockPos(PhantomEntity.this)).down(1))) {
                this.field_7326 = Math.max(1.0F, this.field_7326);
                this.method_7103();
            }

            if (PhantomEntity.this.field_7314.y > PhantomEntity.this.y && !PhantomEntity.this.world.isAir((new BlockPos(PhantomEntity.this)).up(1))) {
                this.field_7326 = Math.min(-1.0F, this.field_7326);
                this.method_7103();
            }

        }

        private void method_7103() {
            if (BlockPos.ORIGIN.equals(PhantomEntity.this.field_7312)) {
                PhantomEntity.this.field_7312 = new BlockPos(PhantomEntity.this);
            }

            this.field_7328 += this.field_7324 * 15.0F * 0.017453292F;
            PhantomEntity.this.field_7314 = (new Vec3d(PhantomEntity.this.field_7312)).add((double) (this.field_7327 * MathHelper.cos(this.field_7328)), (double) (-4.0F + this.field_7326), (double) (this.field_7327 * MathHelper.sin(this.field_7328)));
        }
    }

    abstract class class_1601 extends Goal {
        public class_1601() {
            this.setControlBits(1);
        }

        protected boolean method_7104() {
            return PhantomEntity.this.field_7314.squaredDistanceTo(PhantomEntity.this.x, PhantomEntity.this.y, PhantomEntity.this.z) < 4.0D;
        }
    }

    class PhantomLookControl extends LookControl {
        public PhantomLookControl(MobEntity mobEntity_1) {
            super(mobEntity_1);
        }

        public void tick() {
        }
    }

    class class_1597 extends BodyControl {
        public class_1597(LivingEntity livingEntity_1) {
            super(livingEntity_1);
        }

        public void method_6224() {
            PhantomEntity.this.headYaw = PhantomEntity.this.field_6283;
            PhantomEntity.this.field_6283 = PhantomEntity.this.yaw;
        }
    }

    class PhantomMoveControl extends MoveControl {
        private float field_7331 = 0.1F;

        public PhantomMoveControl(MobEntity mobEntity_1) {
            super(mobEntity_1);
        }

        public void tick() {
            if (PhantomEntity.this.horizontalCollision) {
                PhantomEntity var10000 = PhantomEntity.this;
                var10000.yaw += 180.0F;
                this.field_7331 = 0.1F;
            }

            float float_1 = (float) (PhantomEntity.this.field_7314.x - PhantomEntity.this.x);
            float float_2 = (float) (PhantomEntity.this.field_7314.y - PhantomEntity.this.y);
            float float_3 = (float) (PhantomEntity.this.field_7314.z - PhantomEntity.this.z);
            double double_1 = (double) MathHelper.sqrt(float_1 * float_1 + float_3 * float_3);
            double double_2 = 1.0D - (double) MathHelper.abs(float_2 * 0.7F) / double_1;
            float_1 = (float) ((double) float_1 * double_2);
            float_3 = (float) ((double) float_3 * double_2);
            double_1 = (double) MathHelper.sqrt(float_1 * float_1 + float_3 * float_3);
            double double_3 = (double) MathHelper.sqrt(float_1 * float_1 + float_3 * float_3 + float_2 * float_2);
            float float_4 = PhantomEntity.this.yaw;
            float float_5 = (float) MathHelper.atan2((double) float_3, (double) float_1);
            float float_6 = MathHelper.wrapDegrees(PhantomEntity.this.yaw + 90.0F);
            float float_7 = MathHelper.wrapDegrees(float_5 * 57.295776F);
            PhantomEntity.this.yaw = MathHelper.method_15388(float_6, float_7, 4.0F) - 90.0F;
            PhantomEntity.this.field_6283 = PhantomEntity.this.yaw;
            if (MathHelper.method_15356(float_4, PhantomEntity.this.yaw) < 3.0F) {
                this.field_7331 = MathHelper.method_15348(this.field_7331, 1.8F, 0.005F * (1.8F / this.field_7331));
            } else {
                this.field_7331 = MathHelper.method_15348(this.field_7331, 0.2F, 0.025F);
            }

            float float_8 = (float) (-(MathHelper.atan2((double) (-float_2), double_1) * 57.2957763671875D));
            PhantomEntity.this.pitch = float_8;
            float float_9 = PhantomEntity.this.yaw + 90.0F;
            double double_4 = (double) (this.field_7331 * MathHelper.cos(float_9 * 0.017453292F)) * Math.abs((double) float_1 / double_3);
            double double_5 = (double) (this.field_7331 * MathHelper.sin(float_9 * 0.017453292F)) * Math.abs((double) float_3 / double_3);
            double double_6 = (double) (this.field_7331 * MathHelper.sin(float_8 * 0.017453292F)) * Math.abs((double) float_2 / double_3);
            Vec3d vec3d_1 = PhantomEntity.this.getVelocity();
            PhantomEntity.this.setVelocity(vec3d_1.add((new Vec3d(double_4, double_6, double_5)).subtract(vec3d_1).multiply(0.2D)));
        }
    }
}
