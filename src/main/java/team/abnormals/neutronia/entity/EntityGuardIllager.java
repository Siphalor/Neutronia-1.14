/*
package team.abnormals.neutronia.entity;

import illager.guardillagers.GuardIllagers;
import illager.guardillagers.init.IllagerEntityRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1358;
import net.minecraft.class_1361;
import net.minecraft.class_1370;
import net.minecraft.class_3745;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RangedAttacker;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.OpenDoorGoal;
import net.minecraft.entity.ai.goal.StayInsideGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.monster.AbstractIllager;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class EntityGuardIllager extends IllagerEntity implements CrossbowUser, RangedAttacker {

    private static final TrackedData<Boolean> field_7334 = DataTracker.registerData(PillagerEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private final BasicInventory inventory = new BasicInventory(5);
    private static final UUID MODIFIER_UUID = UUID.fromString("5CD17E52-A79A-43D3-A529-90FDE04B181E");
    private static final EntityAttributeModifier MODIFIER = (new EntityAttributeModifier(MODIFIER_UUID, "Drinking speed penalty", -0.25D, EntityAttributeModifier.Operation.ADDITION)).setSerialize(false);
    private static final TrackedData<Boolean> IS_DRINKING = DataTracker.registerData(EntityGuardIllager.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> IS_STRONG = DataTracker.registerData(EntityGuardIllager.class, TrackedDataHandlerRegistry.BOOLEAN);

    private int potionUseTimer;

    public double prevChasingPosX;
    WitchEntity
*
     * Previous Y position of the illager's cape


    public double prevChasingPosY;
*
     * Previous Z position of the illager's cape


    public double prevChasingPosZ;
*
     * Current X position of the illager's cape


    public double chasingPosX;

    public double chasingPosY;

    public double chasingPosZ;

    public EntityGuardIllager(World world) {
        super(IllagerEntityRegistry.GUARD_ILLAGER, world);
        this.setSize(0.6F, 1.95F);
        this.setEquipmentDropChance(EquipmentSlot.HAND_OFF, 0.4F);
        ((PathNavigateGround) this.getNavigation()).setBreakDoors(true);
    }

    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(2, new StayInsideGoal(this));
        this.goalSelector.add(3, new OpenDoorGoal(this, true));
        this.goalSelector.add(4, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.add(6, new class_1370(this, 0.6D));
        this.goalSelector.add(8, new class_1358(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.add(9, new class_1361(this, LivingEntity.class, 8.0F));
        this.targetSelector.add(1, new EntityAIHurtByTarget(this, true, IllagerEntity.class));
        this.targetSelector.add(2, new EntityAINearestAttackableTarget(this, VillagerEntity.class, true));
        this.targetSelector.add(2, new EntityAINearestAttackableTarget(this, IronGolemEntity.class, true));
    }

    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue((double) 0.348F);
        this.getAttributeInstance(EntityAttributes.FOLLOW_RANGE).setBaseValue(22.0D);
        this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(26.0D);
        this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
        this.getAttributeInstance(EntityAttributes.ARMOR).setBaseValue(6.0D);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.getDataTracker().startTracking(IS_DRINKING, false);
        this.getDataTracker().startTracking(IS_STRONG, false);
        this.dataTracker.startTracking(field_7334, false);
    }

    public void setDrinkingPotion(boolean drinkingPotion) {
        this.getDataTracker().set(IS_DRINKING, drinkingPotion);
    }

    public boolean isDrinkingPotion() {
        return this.getDataTracker().get(IS_DRINKING);
    }

    public boolean isStrong() {
        return this.dataTracker.get(IS_STRONG);
    }

    public void setStrong(boolean strong) {
        this.dataTracker.set(IS_STRONG, strong);
        if (strong) {
            this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(30.0D);
            this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
        } else {
            this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(26.0D);
        }

    }


    public void writeCustomDataToTag(CompoundTag compound) {
        super.writeCustomDataToTag(compound);
        compound.putBoolean("Strong", this.isStrong());
    }

    public void readCustomDataFromTag(CompoundTag compound) {
        super.readCustomDataFromTag(compound);
        ListTag listTag_1 = compound.getList("Inventory", 10);

        for(int int_1 = 0; int_1 < listTag_1.size(); ++int_1) {
            ItemStack itemStack_1 = ItemStack.fromTag(listTag_1.getCompoundTag(int_1));
            if (!itemStack_1.isEmpty()) {
                this.inventory.add(itemStack_1);
            }
        }

        this.setCanPickUpLoot(true);
        this.setStrong(compound.getBoolean("Strong"));
    }

    public void mobTick() {
        if (!this.world.isClient) {
            if (this.isDrinkingPotion()) {
                if (this.potionUseTimer-- <= 0) {
                    this.setDrinkingPotion(false);
                    ItemStack itemstack = this.getMainHandStack();
                    this.setEquippedStack(EquipmentSlot.HAND_OFF, ItemStack.EMPTY);
                    if (itemstack.getItem() == Items.POTION) {
                        List<StatusEffectInstance> list = PotionUtil.getPotionEffects(itemstack);
                        for (StatusEffectInstance statusEffectInstance_1 : list) {
                            this.addPotionEffect(new StatusEffectInstance(statusEffectInstance_1));
                        }
                    }

                    this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).removeModifier(MODIFIER);
                }
            } else {
                Potion potiontype = null;

                if (this.random.nextFloat() < 0.004F && this.getHealth() < this.getHealthMaximum()) {
                    potiontype = Potions.HEALING;
                } else if (this.random.nextFloat() < 0.008F && this.getAttacker() != null && !this.addPotionEffect(new StatusEffectInstance(StatusEffects.SPEED)) && this.getAttacker().distanceTo(this) > 121.0D) {
                    potiontype = Potions.SWIFTNESS;
                }

                if (potiontype != null) {
                    this.setEquippedStack(EquipmentSlot.HAND_OFF, PotionUtil.setPotion(new ItemStack(Items.POTION), potiontype));
                    this.potionUseTimer = this.getOffHandStack().getDurability();
                    this.setDrinkingPotion(true);
                    this.world.playSound(null, this.x, this.y, this.z, SoundEvents.ENTITY_WITCH_DRINK, this.getSoundCategory(), 1.0F, 0.8F + this.random.nextFloat() * 0.4F);
                    EntityAttributeInstance iattributeinstance = this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
                    iattributeinstance.removeModifier(MODIFIER);
                    iattributeinstance.addModifier(MODIFIER);
                }
            }
        }

        super.mobTick();
    }

    public void update() {
        super.update();
        this.updateCape();
    }

    private void updateCape() {
        this.prevChasingPosX = this.chasingPosX;
        this.prevChasingPosY = this.chasingPosY;
        this.prevChasingPosZ = this.chasingPosZ;
        double d0 = this.x - this.chasingPosX;
        double d1 = this.y - this.chasingPosY;
        double d2 = this.z - this.chasingPosZ;
        double d3 = 10.0D;
        if (d0 > 10.0D) {
            this.chasingPosX = this.x;
            this.prevChasingPosX = this.chasingPosX;
        }

        if (d2 > 10.0D) {
            this.chasingPosZ = this.z;
            this.prevChasingPosZ = this.chasingPosZ;
        }

        if (d1 > 10.0D) {
            this.chasingPosY = this.y;
            this.prevChasingPosY = this.chasingPosY;
        }

        if (d0 < -10.0D) {
            this.chasingPosX = this.x;
            this.prevChasingPosX = this.chasingPosX;
        }

        if (d2 < -10.0D) {
            this.chasingPosZ = this.z;
            this.prevChasingPosZ = this.chasingPosZ;
        }

        if (d1 < -10.0D) {
            this.chasingPosY = this.y;
            this.prevChasingPosY = this.chasingPosY;
        }

        this.chasingPosX += d0 * 0.25D;
        this.chasingPosZ += d2 * 0.25D;
        this.chasingPosY += d1 * 0.25D;
    }

    @Override
    public void setAttacker(LivingEntity livingBase) {
        super.setAttacker(livingBase);
        if (livingBase != null) {
            if (livingBase instanceof PlayerEntity) {

                if (!((PlayerEntity) livingBase).isCreative() && this.invalid) {
                    this.world.setEntityState(this, (byte) 13);
                }
            }
        }

    }

    @Override
    protected Identifier getLootTableId() {
        return GuardIllagers.LOOT_TABLE;
    }

    @Environment(EnvType.CLIENT)
    public boolean method_7108() {
        return (Boolean)this.dataTracker.get(field_7334);
    }

    public void method_7110(boolean boolean_1) {
        this.dataTracker.set(field_7334, boolean_1);
    }

    @Environment(EnvType.CLIENT)
    public boolean method_7109() {
        return this.method_6991(1);
    }

    @Override
    public void setArmsRaised(boolean boolean_1) {
        this.method_6992(1, boolean_1);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public IllagerEntity.State method_6990() {
        if (this.method_7109()) {
            return State.ATTACKING;
        } else if (this.method_7108()) {
            return State.CROSSED;
        } else {
            return !this.getMainHandStack().isEmpty() && this.getMainHandStack().getItem() == Items.CROSSBOW ? IllagerEntity.State.CROSSBOW_HOLD : IllagerEntity.State.CROSSED;
        }
    }

    public void setHomePos() {
        this.setAiHome(new BlockPos(this), 26);
    }

    public EntityData prepareEntityData(IWorld iWorld_1, LocalDifficulty localDifficulty_1, SpawnType spawnType_1, EntityData entityData_1, CompoundTag compoundTag_1) {
        EntityData ientitylivingdata = super.prepareEntityData(iWorld_1, localDifficulty_1, spawnType_1, entityData_1, compoundTag_1);
        this.initEquipment(localDifficulty_1);
        this.method_5984(localDifficulty_1);
        return ientitylivingdata;
    }

    protected void initEquipment(LocalDifficulty difficulty) {
        this.setEquippedStack(EquipmentSlot.HAND_MAIN, new ItemStack(Items.IRON_SWORD));
    }

    protected void updateAITasks() {
        super.updateAITasks();
        this.setAggressive(this.getAttackTarget() != null);
    }

    public boolean isTeammate(Entity entityIn) {
        if (super.isTeammate(entityIn)) {
            return true;
        } else if (entityIn instanceof LivingEntity && ((LivingEntity) entityIn).getGroup() == EntityGroup.ILLAGER) {
            return this.getScoreboardTeam() == null && entity_1.getScoreboardTeam() == null;
        } else {
            return false;
        }
    }
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_VINDICATOR_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_VINDICATOR_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_VINDICATOR_HURT;
    }

}
*/
