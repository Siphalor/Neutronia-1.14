package team.abnormals.neutronia.entity.passive;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.class_1399;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntityWithAi;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import team.abnormals.neutronia.init.NEntityTypes;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class BrownBearEntity extends AnimalEntity {
   private static final TrackedData<Boolean> field_6840;
   private float field_6838;
   private float field_6837;
   private int field_6839;

   public BrownBearEntity(World world_1) {
      super(NEntityTypes.BROWN_BEAR, world_1);
   }

   public PassiveEntity createChild(PassiveEntity passiveEntity_1) {
      return new BrownBearEntity(this.world);
   }

   public boolean isBreedingItem(ItemStack itemStack_1) {
      return false;
   }

   protected void initGoals() {
      super.initGoals();
      this.goalSelector.add(0, new SwimGoal(this));
      this.goalSelector.add(1, new BrownBearEntity.class_1460());
      this.goalSelector.add(1, new BrownBearEntity.class_1461());
      this.goalSelector.add(4, new FollowParentGoal(this, 1.25D));
      this.goalSelector.add(5, new WanderAroundGoal(this, 1.0D));
      this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
      this.goalSelector.add(7, new LookAroundGoal(this));
      this.targetSelector.add(1, new BrownBearEntity.class_1459());
      this.targetSelector.add(2, new BrownBearEntity.class_1457());
      this.targetSelector.add(3, new FollowTargetGoal<>(this, FoxEntity.class, 10, true, true, null));
   }

   protected void initAttributes() {
      super.initAttributes();
      this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(30.0D);
      this.getAttributeInstance(EntityAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
      this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
      this.getAttributeContainer().register(EntityAttributes.ATTACK_DAMAGE);
      this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
   }

   public boolean canSpawn(IWorld iWorld_1, SpawnType spawnType_1) {
      int int_1 = MathHelper.floor(this.x);
      int int_2 = MathHelper.floor(this.getBoundingBox().minY);
      int int_3 = MathHelper.floor(this.z);
      BlockPos blockPos_1 = new BlockPos(int_1, int_2, int_3);
      Biome biome_1 = iWorld_1.getBiome(blockPos_1);
      return biome_1.equals(Biomes.TAIGA);
   }

   protected SoundEvent getAmbientSound() {
      return this.isChild() ? SoundEvents.ENTITY_POLAR_BEAR_AMBIENT_BABY : SoundEvents.ENTITY_POLAR_BEAR_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource damageSource_1) {
      return SoundEvents.ENTITY_POLAR_BEAR_HURT;
   }

   protected SoundEvent getDeathSound() {
      return SoundEvents.ENTITY_POLAR_BEAR_DEATH;
   }

   protected void playStepSound(BlockPos blockPos_1, BlockState blockState_1) {
      this.playSound(SoundEvents.ENTITY_POLAR_BEAR_STEP, 0.15F, 1.0F);
   }

   protected void method_6602() {
      if (this.field_6839 <= 0) {
         this.playSound(SoundEvents.ENTITY_POLAR_BEAR_WARNING, 1.0F, 1.0F);
         this.field_6839 = 40;
      }

   }

   protected void initDataTracker() {
      super.initDataTracker();
      this.dataTracker.startTracking(field_6840, false);
   }

   public void update() {
      super.update();
      if (this.world.isClient) {
         this.field_6838 = this.field_6837;
         if (this.method_6600()) {
            this.field_6837 = MathHelper.clamp(this.field_6837 + 1.0F, 0.0F, 6.0F);
         } else {
            this.field_6837 = MathHelper.clamp(this.field_6837 - 1.0F, 0.0F, 6.0F);
         }
      }

      if (this.field_6839 > 0) {
         --this.field_6839;
      }

   }

   public boolean attack(Entity entity_1) {
      boolean boolean_1 = entity_1.damage(DamageSource.mob(this), (float)((int)this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).getValue()));
      if (boolean_1) {
         this.dealDamage(this, entity_1);
      }

      return boolean_1;
   }

   public boolean method_6600() {
      return (Boolean)this.dataTracker.get(field_6840);
   }

   public void method_6603(boolean boolean_1) {
      this.dataTracker.set(field_6840, boolean_1);
   }

   @Environment(EnvType.CLIENT)
   public float method_6601(float float_1) {
      return MathHelper.lerp(float_1, this.field_6838, this.field_6837) / 6.0F;
   }

   protected float method_6120() {
      return 0.98F;
   }

   public EntityData prepareEntityData(IWorld iWorld_1, LocalDifficulty localDifficulty_1, SpawnType spawnType_1, EntityData entityData_1, CompoundTag compoundTag_1) {
      if (entityData_1 instanceof BrownBearEntity.class_1458) {
         this.setBreedingAge(-24000);
      } else {
         entityData_1 = new BrownBearEntity.class_1458();
      }

      return (EntityData)entityData_1;
   }

   static {
      field_6840 = DataTracker.registerData(BrownBearEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
   }

   class class_1461 extends EscapeDangerGoal {
      public class_1461() {
         super(BrownBearEntity.this, 2.0D);
      }

      public boolean canStart() {
         return !BrownBearEntity.this.isChild() && !BrownBearEntity.this.isOnFire() ? false : super.canStart();
      }
   }

   class class_1460 extends MeleeAttackGoal {
      public class_1460() {
         super(BrownBearEntity.this, 1.25D, true);
      }

      protected void method_6288(LivingEntity livingEntity_1, double double_1) {
         double double_2 = this.method_6289(livingEntity_1);
         if (double_1 <= double_2 && this.field_6505 <= 0) {
            this.field_6505 = 20;
            this.entity.attack(livingEntity_1);
            BrownBearEntity.this.method_6603(false);
         } else if (double_1 <= double_2 * 2.0D) {
            if (this.field_6505 <= 0) {
               BrownBearEntity.this.method_6603(false);
               this.field_6505 = 20;
            }

            if (this.field_6505 <= 10) {
               BrownBearEntity.this.method_6603(true);
               BrownBearEntity.this.method_6602();
            }
         } else {
            this.field_6505 = 20;
            BrownBearEntity.this.method_6603(false);
         }

      }

      public void onRemove() {
         BrownBearEntity.this.method_6603(false);
         super.onRemove();
      }

      protected double method_6289(LivingEntity livingEntity_1) {
         return (double)(4.0F + livingEntity_1.getWidth());
      }
   }

   class class_1457 extends FollowTargetGoal<PlayerEntity> {
      public class_1457() {
         super(BrownBearEntity.this, PlayerEntity.class, 20, true, true, (Predicate)null);
      }

      public boolean canStart() {
         if (BrownBearEntity.this.isChild()) {
            return false;
         } else {
            if (super.canStart()) {
               List<BrownBearEntity> list_1 = BrownBearEntity.this.world.method_18467(BrownBearEntity.class, BrownBearEntity.this.getBoundingBox().expand(8.0D, 4.0D, 8.0D));
               Iterator var2 = list_1.iterator();

               while(var2.hasNext()) {
                  BrownBearEntity brownBearEntity_1 = (BrownBearEntity)var2.next();
                  if (brownBearEntity_1.isChild()) {
                     return true;
                  }
               }
            }

            BrownBearEntity.this.setTarget((LivingEntity)null);
            return false;
         }
      }

      protected double getFollowRange() {
         return super.getFollowRange() * 0.5D;
      }
   }

   class class_1459 extends class_1399 {
      public class_1459() {
         super(BrownBearEntity.this);
      }

      public void start() {
         super.start();
         if (BrownBearEntity.this.isChild()) {
            this.method_6317();
            this.onRemove();
         }

      }

      protected void method_6319(MobEntityWithAi mobEntityWithAi_1, LivingEntity livingEntity_1) {
         if (mobEntityWithAi_1 instanceof BrownBearEntity && !mobEntityWithAi_1.isChild()) {
            super.method_6319(mobEntityWithAi_1, livingEntity_1);
         }

      }
   }

   static class class_1458 implements EntityData {
      private class_1458() {
      }

      // $FF: synthetic method
      class_1458(Object polarBearEntity$1_1) {
         this();
      }
   }
}
