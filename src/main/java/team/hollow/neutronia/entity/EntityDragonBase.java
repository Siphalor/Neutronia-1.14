package team.hollow.neutronia.entity;/*
package team.team.hollow.neutronia.entity;

import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.api.FoodUtils;
import com.github.alexthe666.iceandfire.client.model.IFChainBuffer;
import com.github.alexthe666.iceandfire.client.model.util.LegSolverQuadruped;
import com.github.alexthe666.iceandfire.core.ModItems;
import com.github.alexthe666.iceandfire.core.ModKeys;
import com.github.alexthe666.iceandfire.core.ModSounds;
import com.github.alexthe666.iceandfire.entity.ai.PathNavigateExperimentalGround;
import com.github.alexthe666.iceandfire.enums.EnumDragonEgg;
import com.github.alexthe666.iceandfire.message.MessageDragonArmor;
import com.github.alexthe666.iceandfire.message.MessageDragonControl;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.FabricLoader;
import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.ilexiconn.llibrary.server.entity.EntityPropertiesHandler;
import net.ilexiconn.llibrary.server.entity.multipart.IMultipartEntity;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.SpawnType;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.ContainerHorseChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.PotionEffect;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public abstract class EntityDragonBase extends TameableEntity implements IMultipartEntity, IAnimatedEntity, IDragonFlute, IDeadMob, IVillagerFear, IAnimalFear, IDropArmor {

    private static final int FLIGHT_CHANCE_PER_TICK = 1500;
    private static final TrackedData<Integer> HUNGER = DataTracker.registerData(EntityDragonBase.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> AGE_TICKS = DataTracker.registerData(EntityDragonBase.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> GENDER = DataTracker.registerData(EntityDragonBase.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Integer> VARIANT = DataTracker.registerData(EntityDragonBase.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> SLEEPING = DataTracker.registerData(EntityDragonBase.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> FIREBREATHING = DataTracker.registerData(EntityDragonBase.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> HOVERING = DataTracker.registerData(EntityDragonBase.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> FLYING = DataTracker.registerData(EntityDragonBase.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Integer> HEAD_ARMOR = DataTracker.registerData(EntityDragonBase.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> NECK_ARMOR = DataTracker.registerData(EntityDragonBase.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> BODY_ARMOR = DataTracker.registerData(EntityDragonBase.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> TAIL_ARMOR = DataTracker.registerData(EntityDragonBase.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> MODEL_DEAD = DataTracker.registerData(EntityDragonBase.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Integer> DEATH_STAGE = DataTracker.registerData(EntityDragonBase.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Byte> CONTROL_STATE = DataTracker.registerData(EntityDragonBase.class, TrackedDataHandlerRegistry.BYTE);
    private static final TrackedData<Boolean> TACKLE = DataTracker.registerData(EntityDragonBase.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> AGINGDISABLED = DataTracker.registerData(EntityDragonBase.class, TrackedDataHandlerRegistry.BOOLEAN);
    public static Animation ANIMATION_EAT;
    public static Animation ANIMATION_SPEAK;
    public static Animation ANIMATION_BITE;
    public static Animation ANIMATION_SHAKEPREY;
    public static Animation ANIMATION_WINGBLAST;
    public static Animation ANIMATION_ROAR;
    public static Animation ANIMATION_TAILWHACK;
    public double minimumDamage;
    public double maximumDamage;
    public double minimumHealth;
    public double maximumHealth;
    public double minimumSpeed;
    public double maximumSpeed;
    public double minimumArmor;
    public double maximumArmor;
    public float sitProgress;
    public float sleepProgress;
    public float hoverProgress;
    public float flyProgress;
    public float fireBreathProgress;
    public int fireStopTicks;
    public int flyTicks;
    public float modelDeadProgress;
    public float ridingProgress;
    public float tackleProgress;
    public ContainerHorseChest dragonInv;
    public boolean isDaytime;
    public boolean attackDecision;
    public int flightCycle;
    public BlockPos airTarget;
    public BlockPos homePos;
    public boolean hasHomePosition = false;
    @Environment(EnvType.CLIENT)
    public IFChainBuffer roll_buffer;
    @Environment(EnvType.CLIENT)
    public ReversedBuffer turn_buffer;
    @Environment(EnvType.CLIENT)
    public ChainBuffer tail_buffer;
    public int spacebarTicks;
    public float[][] growth_stages;
    public boolean isFire = this instanceof EntityFireDragon;
    public LegSolverQuadruped legSolver;
    protected int flyHovering;
    private boolean isSleeping;
    private boolean isSitting;
    private boolean isHovering;
    private boolean isFlying;
    private boolean isBreathingFire;
    private boolean isTackling;
    private int fireTicks;
    private int hoverTicks;
    private boolean isModelDead;
    private int animationTick;
    private Animation currentAnimation;
    private ItemStackHandler itemHandler = null;
    public int walkCycle;
    private int tacklingTicks;
    private int ticksStill;
    private float lastScale;
    //private EntityDragonPart[] parts = new EntityDragonPart[1];//head, neck, left wing, right wing, left leg, right leg, tail1, tail2, tail3, tail4, tail5
    private EntityDragonPart headPart;
    private EntityDragonPart neckPart;
    private EntityDragonPart rightWingUpperPart;
    private EntityDragonPart rightWingLowerPart;
    private EntityDragonPart leftWingUpperPart;
    private EntityDragonPart leftWingLowerPart;
    private EntityDragonPart tail1Part;
    private EntityDragonPart tail2Part;
    private EntityDragonPart tail3Part;
    private EntityDragonPart tail4Part;

    public EntityDragonBase(World world, double minimumDamage, double maximumDamage, double minimumHealth, double maximumHealth, double minimumSpeed, double maximumSpeed) {
        super(world);
        this.minimumDamage = minimumDamage;
        this.maximumDamage = maximumDamage;
        this.minimumHealth = minimumHealth;
        this.maximumHealth = maximumHealth;
        this.minimumSpeed = minimumSpeed;
        this.maximumSpeed = maximumSpeed;
        this.minimumArmor = 1D;
        this.maximumArmor = 20D;
        ANIMATION_EAT = Animation.create(20);
        updateAttributes();
        initDragonInv();
        if (FabricLoader.INSTANCE.) {
            roll_buffer = new IFChainBuffer();
            turn_buffer = new ReversedBuffer();
            tail_buffer = new ChainBuffer();
        }
        legSolver = new LegSolverQuadruped(0.2F, 1.2F, 1.0F);
        resetParts(1);
    }// /entitydata @e {NoAI:1}

    public void resetParts(float scale) {
        removeParts();
        headPart = new EntityDragonPart(this, 1.55F * scale, 0, 0.6F * scale, 0.5F * scale, 0.35F * scale, 1.5F);
        neckPart = new EntityDragonPart(this, 0.85F * scale, 0, 0.7F * scale, 0.5F * scale, 0.2F * scale, 1);
        rightWingUpperPart = new EntityDragonPart(this, 1F * scale, 90, 0.5F * scale, 0.85F * scale, 0.3F * scale, 0.5F);
        rightWingLowerPart = new EntityDragonPart(this, 1.4F * scale, 100, 0.3F * scale, 0.85F * scale, 0.2F * scale, 0.5F);
        leftWingUpperPart = new EntityDragonPart(this, 1F * scale, -90, 0.5F * scale, 0.85F * scale, 0.3F * scale, 0.5F);
        leftWingLowerPart = new EntityDragonPart(this, 1.4F * scale, -100, 0.3F * scale, 0.85F * scale, 0.2F * scale, 0.5F);
        tail1Part = new EntityDragonPart(this, -0.75F * scale, 0, 0.6F * scale, 0.35F * scale, 0.35F * scale, 1);
        tail2Part = new EntityDragonPart(this, -1.15F * scale, 0, 0.45F * scale, 0.35F * scale, 0.35F * scale, 1);
        tail3Part = new EntityDragonPart(this, -1.5F * scale, 0, 0.35F * scale, 0.35F * scale, 0.35F * scale, 1);
        tail4Part = new EntityDragonPart(this, -1.95F * scale, 0, 0.25F * scale, 0.45F * scale, 0.3F * scale, 1.5F);
    }

    public void removeParts() {
        if (headPart != null) {
            world.removeEntityDangerously(headPart);
        }
        if (neckPart != null) {
            world.removeEntityDangerously(neckPart);
        }
        if (rightWingUpperPart != null) {
            world.removeEntityDangerously(rightWingUpperPart);
        }
        if (rightWingLowerPart != null) {
            world.removeEntityDangerously(rightWingLowerPart);
        }
        if (leftWingUpperPart != null) {
            world.removeEntityDangerously(leftWingUpperPart);
        }
        if (leftWingLowerPart != null) {
            world.removeEntityDangerously(leftWingLowerPart);
        }
        if (tail1Part != null) {
            world.removeEntityDangerously(tail1Part);
        }
        if (tail2Part != null) {
            world.removeEntityDangerously(tail2Part);
        }
        if (tail3Part != null) {
            world.removeEntityDangerously(tail3Part);
        }
        if (tail4Part != null) {
            world.removeEntityDangerously(tail4Part);
        }
    }

    public void updateParts() {
        headPart.onUpdate();
        neckPart.onUpdate();
        rightWingUpperPart.onUpdate();
        rightWingLowerPart.onUpdate();
        leftWingUpperPart.onUpdate();
        leftWingLowerPart.onUpdate();
        tail1Part.onUpdate();
        tail2Part.onUpdate();
        tail3Part.onUpdate();
        tail4Part.onUpdate();
    }

    @Override
    protected EntityNavigation createNavigation(World world_1) {
        return IceAndFire.CONFIG.experimentalPathFinder ? new PathNavigateExperimentalGround(this, worldIn) : super.createNavigation(world_1);
    }

    public boolean canDestroyBlock(BlockPos pos){
        float hardness = world.getBlockState(pos).getBlock().getHardness(world.getBlockState(pos), world, pos);
        return world.getBlockState(pos).getBlock().canEntityDestroy(world.getBlockState(pos), world, pos, this) && hardness >= 0;
    }

    @Override
    public boolean isValid() {
        return !this.isModelDead();
    }

    @Override
    public float getMovementSpeed() {
        return 10 * this.getDragonStage() / 5;
    }

    private void initDragonInv() {
        ContainerHorseChest animalchest = this.dragonInv;
        this.dragonInv = new ContainerHorseChest("dragonInventory", 4);
        this.dragonInv.setCustomName(this.getName());
        if (animalchest != null) {
            int i = Math.min(animalchest.getSizeInventory(), this.dragonInv.getSizeInventory());

            for (int j = 0; j < i; ++j) {
                ItemStack itemstack = animalchest.getStackInSlot(j);
                if (!itemstack.isEmpty()) {
                    this.dragonInv.setInventorySlotContents(j, itemstack.copy());
                }
            }
        }
        //this.updateDragonSlots();
        this.itemHandler = new ItemStackHandler(4) {
            public void onContentsChanged() {
                int dragonArmorHead = EntityDragonBase.this.getArmorInSlot(0);
                int dragonArmorNeck = EntityDragonBase.this.getArmorInSlot(1);
                int dragonArmorBody = EntityDragonBase.this.getArmorInSlot(2);
                int dragonArmorTail = EntityDragonBase.this.getArmorInSlot(3);
                EntityDragonBase.this.updateDragonSlots();
                if (EntityDragonBase.this.ticksStill > 20) {
                    if (dragonArmorHead != EntityDragonBase.this.getIntFromArmor(EntityDragonBase.this.dragonInv.getStackInSlot(0))) {
                        EntityDragonBase.this.playSound(SoundEvents.ENTITY_HORSE_ARMOR, 0.5F, 1.0F);
                    }
                    if (dragonArmorNeck != EntityDragonBase.this.getIntFromArmor(EntityDragonBase.this.dragonInv.getStackInSlot(1))) {
                        EntityDragonBase.this.playSound(SoundEvents.ENTITY_HORSE_ARMOR, 0.5F, 1.0F);
                    }
                    if (dragonArmorBody != EntityDragonBase.this.getIntFromArmor(EntityDragonBase.this.dragonInv.getStackInSlot(2))) {
                        EntityDragonBase.this.playSound(SoundEvents.ENTITY_HORSE_ARMOR, 0.5F, 1.0F);
                    }
                    if (dragonArmorTail != EntityDragonBase.this.getIntFromArmor(EntityDragonBase.this.dragonInv.getStackInSlot(3))) {
                        EntityDragonBase.this.playSound(SoundEvents.ENTITY_HORSE_ARMOR, 0.5F, 1.0F);
                    }
                }
            }
        };
        if (world.isClient) {
            IceAndFire.NETWORK_WRAPPER.sendToServer(new MessageDragonArmor(this.getEntityId(), 0, this.getIntFromArmor(this.dragonInv.getStackInSlot(0))));
            IceAndFire.NETWORK_WRAPPER.sendToServer(new MessageDragonArmor(this.getEntityId(), 1, this.getIntFromArmor(this.dragonInv.getStackInSlot(1))));
            IceAndFire.NETWORK_WRAPPER.sendToServer(new MessageDragonArmor(this.getEntityId(), 2, this.getIntFromArmor(this.dragonInv.getStackInSlot(2))));
            IceAndFire.NETWORK_WRAPPER.sendToServer(new MessageDragonArmor(this.getEntityId(), 3, this.getIntFromArmor(this.dragonInv.getStackInSlot(3))));
        }
    }

    public void updateDragonSlots() {
        this.setArmorInSlot(0, getIntFromArmor(this.dragonInv.getStackInSlot(0)));
        this.setArmorInSlot(1, getIntFromArmor(this.dragonInv.getStackInSlot(1)));
        this.setArmorInSlot(2, getIntFromArmor(this.dragonInv.getStackInSlot(2)));
        this.setArmorInSlot(3, getIntFromArmor(this.dragonInv.getStackInSlot(3)));
        if (world.isClient) {
            IceAndFire.NETWORK_WRAPPER.sendToServer(new MessageDragonArmor(this.getEntityId(), 0, this.getIntFromArmor(this.dragonInv.getStackInSlot(0))));
            IceAndFire.NETWORK_WRAPPER.sendToServer(new MessageDragonArmor(this.getEntityId(), 1, this.getIntFromArmor(this.dragonInv.getStackInSlot(1))));
            IceAndFire.NETWORK_WRAPPER.sendToServer(new MessageDragonArmor(this.getEntityId(), 2, this.getIntFromArmor(this.dragonInv.getStackInSlot(2))));
            IceAndFire.NETWORK_WRAPPER.sendToServer(new MessageDragonArmor(this.getEntityId(), 3, this.getIntFromArmor(this.dragonInv.getStackInSlot(3))));
        }
        double armorStep = (maximumArmor - minimumArmor) / (125);
        double oldValue = minimumArmor + (armorStep * this.getAgeInDays());
        this.getAttributeContainer().register(EntityAttributes.ARMOR).setBaseValue(oldValue + calculateArmorModifier());
    }

    public void openGUI(PlayerEntity playerEntity) {
        if (!this.world.isClient && (!this.hasPassenger(this) || this.canFitPassenger(playerEntity))) {
            playerEntity.openGui(IceAndFire.INSTANCE, 0, this.world, this.getEntityId(), 0, 0);
        }
    }

    public int getTalkInterval() {
        return 90;
    }

    @Override
    protected void updatePostDeath() {
        super.updatePostDeath();
    }

    protected void onDeathUpdate() {
        this.deathCounter = 0;
        if(!this.isModelDead()){
            if (!this.world.isClient && this.recentlyHit > 0) {
                int i = this.getExperiencePoints(this.attackingPlayer);
                i = net.minecraftforge.event.ForgeEventFactory.getExperienceDrop(this, this.attackingPlayer, i);
                while (i > 0) {
                    int j = EntityXPOrb.getXPSplit(i);
                    i -= j;
                    this.world.spawnEntity(new EntityXPOrb(this.world, this.posX, this.posY, this.posZ, j));
                }
            }
        }
        this.setModelDead(true);

        if (this.getDeathStage() >= this.getAgeInDays() / 5) {
            this.setDead();
            for (int k = 0; k < 40; ++k) {
                double d2 = this.rand.nextGaussian() * 0.02D;
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                if (world.isClient) {
                    this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d2, d0, d1, new int[0]);
                }
            }
            for (int k = 0; k < 3; ++k) {
                double d2 = this.rand.nextGaussian() * 0.02D;
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                if (isFire) {
                    if (world.isClient) {
                        this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d2, d0, d1, new int[0]);
                    }
                } else {
                    IceAndFire.PROXY.spawnParticle("snowflake", this.world, this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d2, d0, d1);
                }
            }
        }
    }

    public void setDead() {
        removeParts();
        super.updatePostDeath();
    }

    protected int getExperiencePoints(PlayerEntity player) {
        if(this.isChild()){
            return 15;
        }
        return 15 + this.getDragonStage() * 35;
    }

    public int getIntFromArmor(ItemStack stack) {
        if (!stack.isEmpty() && stack.getItem() != null && stack.getItem() == ModItems.dragon_armor_iron) {
            return 1;
        }
        if (!stack.isEmpty() && stack.getItem() != null && stack.getItem() == ModItems.dragon_armor_gold) {
            return 2;
        }
        if (!stack.isEmpty() && stack.getItem() != null && stack.getItem() == ModItems.dragon_armor_diamond) {
            return 3;
        }
        return 0;
    }

    @Override
    public boolean isAiDisabled() {
        StoneEntityProperties properties = EntityPropertiesHandler.INSTANCE.getProperties(this, StoneEntityProperties.class);
        return this.isModelDead() || properties == null || properties.isStone || super.isAIDisabled();
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.set(HUNGER, Integer.valueOf(0));
        this.dataTracker.set(AGE_TICKS, Integer.valueOf(0));
        this.dataTracker.set(GENDER, Boolean.valueOf(false));
        this.dataTracker.set(VARIANT, Integer.valueOf(0));
        this.dataTracker.set(SLEEPING, Boolean.valueOf(false));
        this.dataTracker.set(FIREBREATHING, Boolean.valueOf(false));
        this.dataTracker.set(HOVERING, Boolean.valueOf(false));
        this.dataTracker.set(FLYING, Boolean.valueOf(false));
        this.dataTracker.set(HEAD_ARMOR, Integer.valueOf(0));
        this.dataTracker.set(NECK_ARMOR, Integer.valueOf(0));
        this.dataTracker.set(BODY_ARMOR, Integer.valueOf(0));
        this.dataTracker.set(TAIL_ARMOR, Integer.valueOf(0));
        this.dataTracker.set(DEATH_STAGE, Integer.valueOf(0));
        this.dataTracker.set(MODEL_DEAD, Boolean.valueOf(false));
        this.dataTracker.set(CONTROL_STATE, Byte.valueOf((byte) 0));
        this.dataTracker.set(TACKLE, Boolean.valueOf(false));
        this.dataTracker.set(AGINGDISABLED, Boolean.valueOf(false));

    }

    public boolean up() {
        return (dataTracker.get(CONTROL_STATE) & 1) == 1;
    }

    public boolean down() {
        return (dataTracker.get(CONTROL_STATE) >> 1 & 1) == 1;
    }

    public boolean attack() {
        return (dataTracker.get(CONTROL_STATE) >> 2 & 1) == 1;
    }

    public boolean strike() {
        return (dataTracker.get(CONTROL_STATE) >> 3 & 1) == 1;
    }

    public boolean dismount() {
        return (dataTracker.get(CONTROL_STATE) >> 4 & 1) == 1;
    }

    public void up(boolean up) {
        setStateField(0, up);
    }

    public void down(boolean down) {
        setStateField(1, down);
    }

    public void attack(boolean attack) {
        setStateField(2, attack);
    }

    public void strike(boolean strike) {
        setStateField(3, strike);
    }

    public void dismount(boolean dismount) {
        setStateField(4, dismount);
    }

    private void setStateField(int i, boolean newState) {
        byte prevState = dataTracker.get(CONTROL_STATE);
        if (newState) {
            dataTracker.set(CONTROL_STATE, (byte) (prevState | (1 << i)));
        } else {
            dataTracker.set(CONTROL_STATE, (byte) (prevState & ~(1 << i)));
        }
    }

    public byte getControlState() {
        return dataTracker.get(CONTROL_STATE);
    }

    public void setControlState(byte state) {
        dataTracker.set(CONTROL_STATE, (byte) state);
    }

    @Override
    public void writeCustomDataToTag(CompoundTag compound) {
        super.writeCustomDataToTag(compound);
        compound.putInt("Hunger", this.getHunger());
        compound.putInt("AgeTicks", this.getAgeInTicks());
        compound.putBoolean("Gender", this.isMale());
        compound.putInt("Variant", this.getVariant());
        compound.putBoolean("Sleeping", this.isSleeping());
        compound.putBoolean("FireBreathing", this.isBreathingFire());
        compound.putBoolean("AttackDecision", attackDecision);
        compound.putBoolean("Hovering", this.isHovering());
        compound.putBoolean("Flying", this.isFlying());
        compound.putInt("ArmorHead", this.getArmorInSlot(0));
        compound.putInt("ArmorNeck", this.getArmorInSlot(1));
        compound.putInt("ArmorBody", this.getArmorInSlot(2));
        compound.putInt("ArmorTail", this.getArmorInSlot(3));
        compound.putInt("DeathStage", this.getDeathStage());
        compound.putBoolean("ModelDead", this.isModelDead());
        compound.putFloat("DeadProg", this.modelDeadProgress);
        compound.putBoolean("Tackle", this.isTackling());
        if (dragonInv != null) {
            ListTag nbttaglist = new ListTag();
            for (int i = 0; i < this.dragonInv.getSizeInventory(); ++i) {
                ItemStack itemstack = this.dragonInv.getStackInSlot(i);
                if (!itemstack.isEmpty()) {
                    NBTTagCompound nbttagcompound = new NBTTagCompound();
                    nbttagcompound.setByte("Slot", (byte) i);
                    itemstack.writeToNBT(nbttagcompound);
                    nbttaglist.appendTag(nbttagcompound);
                }
            }
            compound.setTag("Items", nbttaglist);
        }
        if (this.getCustomName() != null && !this.getCustomName().getFormattedText().isEmpty()) {
            compound.putString("CustomName", this.getCustomName().getFormattedText());
        }
        compound.putBoolean("HasHomePosition", this.hasHomePosition);
        if (homePos != null && this.hasHomePosition) {
            compound.putInt("HomeAreaX", homePos.getX());
            compound.putInt("HomeAreaY", homePos.getY());
            compound.putInt("HomeAreaZ", homePos.getZ());
        }
        compound.putBoolean("AgingDisabled", this.isAgingDisabled());
    }

    @Override
    public void readCustomDataFromTag(CompoundTag compound) {
        super.readCustomDataFromTag(compound);
        this.setHunger(compound.getInt("Hunger"));
        this.setAgeInTicks(compound.getInt("AgeTicks"));
        this.setGender(compound.getBoolean("Gender"));
        this.setVariant(compound.getInt("Variant"));
        this.setSleeping(compound.getBoolean("Sleeping"));
        this.setBreathingFire(compound.getBoolean("FireBreathing"));
        this.attackDecision = compound.getBoolean("AttackDecision");
        this.setHovering(compound.getBoolean("Hovering"));
        this.setFlying(compound.getBoolean("Flying"));
        this.setArmorInSlot(0, compound.getInt("ArmorHead"));
        this.setArmorInSlot(1, compound.getInt("ArmorNeck"));
        this.setArmorInSlot(2, compound.getInt("ArmorBody"));
        this.setArmorInSlot(3, compound.getInt("ArmorTail"));
        if (dragonInv != null) {
            ListTag nbttaglist = compound.getList("Items", 10);
            this.initDragonInv();
            for (int i = 0; i < nbttaglist.size(); ++i) {
                CompoundTag nbttagcompound = nbttaglist.getCompoundTag(i);
                int j = nbttagcompound.getByte("Slot") & 255;
                if (j <= 4) {
                    this.dragonInv.setInventorySlotContents(j, new ItemStack(nbttagcompound));
                }
            }
        } else {
            NBTTagList nbttaglist = compound.getTagList("Items", 10);
            this.initDragonInv();
            for (int i = 0; i < nbttaglist.tagCount(); ++i) {
                NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
                int j = nbttagcompound.getByte("Slot") & 255;
                this.initDragonInv();
                this.dragonInv.setInventorySlotContents(j, new ItemStack(nbttagcompound));
                this.setArmorInSlot(j, this.getIntFromArmor(new ItemStack(nbttagcompound)));

                if (world.isClient) {
                    IceAndFire.NETWORK_WRAPPER.sendToServer(new MessageDragonArmor(this.getEntityId(), 0, this.getIntFromArmor(new ItemStack(nbttagcompound))));
                    IceAndFire.NETWORK_WRAPPER.sendToServer(new MessageDragonArmor(this.getEntityId(), 1, this.getIntFromArmor(new ItemStack(nbttagcompound))));
                    IceAndFire.NETWORK_WRAPPER.sendToServer(new MessageDragonArmor(this.getEntityId(), 2, this.getIntFromArmor(new ItemStack(nbttagcompound))));
                    IceAndFire.NETWORK_WRAPPER.sendToServer(new MessageDragonArmor(this.getEntityId(), 3, this.getIntFromArmor(new ItemStack(nbttagcompound))));
                }
            }
        }

        this.setDeathStage(compound.getInt("DeathStage"));
        this.setModelDead(compound.getBoolean("ModelDead"));
        this.modelDeadProgress = compound.getFloat("DeadProg");
        if (!compound.getString("CustomName").isEmpty()) {
            this.setCustomName(new StringTextComponent(compound.getString("CustomName")));
        }
        this.hasHomePosition = compound.getBoolean("HasHomePosition");
        if (hasHomePosition && compound.getInt("HomeAreaX") != 0 && compound.getInt("HomeAreaY") != 0 && compound.getInt("HomeAreaZ") != 0) {
            homePos = new BlockPos(compound.getInt("HomeAreaX"), compound.getInt("HomeAreaY"), compound.getInt("HomeAreaZ"));
        }
        this.setTackling(compound.getBoolean("Tackle"));
        this.setAgingDisabled(compound.getBoolean("AgingDisabled"));

    }

    public Entity getControllingPassenger() {
        for (Entity passenger : this.getPassengerList()) {
            if (passenger instanceof PlayerEntity && this.getAttacker() != passenger) {
                PlayerEntity player = (PlayerEntity) passenger;
                if (this.isLeashed() && this.getOwnerId() != null && this.getOwnerId().equals(player.getUuidAsString())) {
                    return player;
                }
            }
        }
        return null;
    }

    public boolean isRidingPlayer(PlayerEntity player) {
        return this.getControllingPassenger() != null && this.getControllingPassenger() instanceof PlayerEntity && this.getControllingPassenger().getUuidAsString().equals(player.getUuidAsString());
    }

    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeContainer().register(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
        this.getAttributeContainer().register(EntityAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getAttributeContainer().register(EntityAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
        this.getAttributeContainer().register(EntityAttributes.FOLLOW_RANGE).setBaseValue(Math.min(2048, IceAndFire.CONFIG.dragonTargetSearchLength));
        this.getAttributeContainer().register(EntityAttributes.ARMOR).setBaseValue(4.0D);

    }

    private void updateAttributes() {
        double healthStep = (maximumHealth - minimumHealth) / (125);
        double attackStep = (maximumDamage - minimumDamage) / (125);
        double speedStep = (maximumSpeed - minimumSpeed) / (125);
        double armorStep = (maximumArmor - minimumArmor) / (125);
        if (this.getAgeInDays() <= 125) {
            this.getAttributeContainer().register(EntityAttributes.MAX_HEALTH).setBaseValue(Math.round(minimumHealth + (healthStep * this.getAgeInDays())));
            this.getAttributeContainer().register(EntityAttributes.ATTACK_DAMAGE).setBaseValue(Math.round(minimumDamage + (attackStep * this.getAgeInDays())));
            this.getAttributeContainer().register(EntityAttributes.MOVEMENT_SPEED).setBaseValue(minimumSpeed + (speedStep * this.getAgeInDays()));
            double oldValue = minimumArmor + (armorStep * this.getAgeInDays());
            this.getAttributeContainer().register(EntityAttributes.ARMOR).setBaseValue(oldValue + calculateArmorModifier());
        }
    }

    public int getHunger() {
        return this.dataTracker.get(HUNGER);
    }

    public void setHunger(int hunger) {
        this.dataTracker.set(HUNGER, Math.min(100, hunger));
    }

    public int getVariant() {
        return this.dataTracker.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.dataTracker.set(VARIANT, variant);
    }

    public int getAgeInDays() {
        return this.dataTracker.get(AGE_TICKS) / 24000;
    }

    public void setAgeInDays(int age) {
        this.dataTracker.set(AGE_TICKS, age * 24000);
    }

    public int getAgeInTicks() {
        return this.dataTracker.get(AGE_TICKS);
    }

    public void setAgeInTicks(int age) {
        this.dataTracker.set(AGE_TICKS, age);
    }

    public int getDeathStage() {
        return this.dataTracker.get(DEATH_STAGE);
    }

    public void setDeathStage(int stage) {
        this.dataTracker.set(DEATH_STAGE, stage);
    }

    public boolean isMale() {
        return this.dataTracker.get(GENDER);
    }

    public boolean isModelDead() {
        if (world.isClient) {
            return this.isModelDead = this.dataTracker.get(MODEL_DEAD);
        }
        return isModelDead;
    }

    public void setModelDead(boolean modeldead) {
        this.dataTracker.set(MODEL_DEAD, modeldead);
        if (!world.isClient) {
            this.isModelDead = modeldead;
        }
    }

    public boolean isHovering() {
        if (world.isClient) {
            return this.isHovering = this.dataTracker.get(HOVERING);
        }
        return isHovering;
    }

    public void setHovering(boolean hovering) {
        this.dataTracker.set(HOVERING, hovering);
        if (!world.isClient) {
            this.isHovering = hovering;
        }
    }

    public boolean isFlying() {
        if (world.isClient) {
            return this.isFlying = this.dataTracker.get(FLYING).booleanValue();
        }
        return isFlying;
    }

    public void setFlying(boolean flying) {
        this.dataTracker.set(FLYING, flying);
        if (!world.isClient) {
            this.isFlying = flying;
        }
    }

    public void setGender(boolean male) {
        this.dataTracker.set(GENDER, male);
    }

    public boolean isSleeping() {
        if (world.isClient) {
            boolean isSleeping = this.dataTracker.get(SLEEPING);
            this.isSleeping = isSleeping;
            return isSleeping;
        }
        return isSleeping;
    }

    public void setSleeping(boolean sleeping) {
        this.dataTracker.set(SLEEPING, sleeping);
        if (!world.isClient) {
            this.isSleeping = sleeping;
        }
    }

    public boolean isBlinking() {
        return this.getAgeInTicks() % 50 > 43;
    }

    public boolean isBreathingFire() {
        if (world.isClient) {
            boolean breathing = this.dataTracker.get(FIREBREATHING);
            this.isBreathingFire = breathing;
            return breathing;
        }
        return isBreathingFire;
    }

    public void setBreathingFire(boolean breathing) {
        this.dataTracker.set(FIREBREATHING, breathing);
        if (!world.isClient) {
            this.isBreathingFire = breathing;
        }
    }

    public void setTackling(boolean tackling) {
        this.dataTracker.set(TACKLE, tackling);
        if (!world.isClient) {
            this.isTackling = tackling;
        }
    }

    public void setAgingDisabled(boolean isAgingDisabled) {
        this.dataTracker.set(AGINGDISABLED, isAgingDisabled);
    }

    protected boolean canFitPassenger(Entity passenger) {
        return this.getPassengerList().size() < 2;
    }

    @Override
    public boolean isSitting() {
        if (world.isClient) {
            boolean isSitting = (this.dataTracker.get(TAMEABLE_FLAGS) & 1) != 0;
            this.isSitting = isSitting;
            return isSitting;
        }
        return isSitting;
    }

    @Override
    public void setSitting(boolean sitting) {
        super.setSitting(sitting);
        if (!world.isClient) {
            this.isSitting = sitting;
        }
    }

    public int getArmorInSlot(int i) {
        switch (i) {
            default:
                return this.dataTracker.get(HEAD_ARMOR);
            case 1:
                return this.dataTracker.get(NECK_ARMOR);
            case 2:
                return this.dataTracker.get(BODY_ARMOR);
            case 3:
                return this.dataTracker.get(TAIL_ARMOR);
        }
    }

    public void riderShootFire(Entity controller) {
    }

    @Override
    public void kill() {
        this.setHunger(this.getHunger());
    }

    @Override
    public void onKillEntity(EntityLivingBase entity) {
        super.onKillEntity(entity);
        this.setHunger(this.getHunger() + FoodUtils.getFoodPoints(entity));
    }

    public void setArmorInSlot(int i, int armorType) {
        switch (i) {
            case 0:
                this.dataTracker.set(HEAD_ARMOR, armorType);
                break;
            case 1:
                this.dataTracker.set(NECK_ARMOR, armorType);
                break;
            case 2:
                this.dataTracker.set(BODY_ARMOR, armorType);
                break;
            case 3:
                this.dataTracker.set(TAIL_ARMOR, armorType);
                break;
        }
        if (world.isClient) {
            IceAndFire.NETWORK_WRAPPER.sendToServer(new MessageDragonArmor(this.getEntityId(), i, armorType));
        }
        double armorStep = (maximumArmor - minimumArmor) / (125);
        double oldValue = minimumArmor + (armorStep * this.getAgeInDays());
        this.getAttributeContainer().register(EntityAttributes.ARMOR).setBaseValue(oldValue + calculateArmorModifier());
    }

    private double calculateArmorModifier() {
        double val = 1D;
        for (int i = 0; i < 4; i++) {
            switch (getArmorInSlot(i)) {
                case 1:
                    val += 2D;
                    break;
                case 2:
                    val += 3D;
                    break;
                case 3:
                    val += 5D;
                    break;
            }
        }
        return val;
    }

    public boolean canMove() {
        StoneEntityProperties properties = EntityPropertiesHandler.INSTANCE.getProperties(this, StoneEntityProperties.class);
        if(properties != null && properties.isStone){
            return false;
        }
        return !this.isSitting() && !this.isSleeping() && this.getControllingPassenger() == null && !this.isModelDead() && sleepProgress == 0 && this.getAnimation() != ANIMATION_SHAKEPREY;
    }

    @Override
    public boolean interactMob(PlayerEntity playerEntity_1, Hand hand_1) {
        ItemStack stack = playerEntity_1.getStackInHand(hand_1);
        int lastDeathStage = this.getAgeInDays() / 5;
        if (this.isModelDead() && this.getDeathStage() < lastDeathStage && playerEntity_1.abilities.allowModifyWorld) {
            //player.addStat(ModAchievements.dragonHarvest, 1);
            if (!world.isClient && !stack.isEmpty() && stack.getItem() != null && stack.getItem() == Items.GLASS_BOTTLE && this.getDeathStage() < lastDeathStage / 2 && IceAndFire.CONFIG.dragonDropBlood) {
                if (!playerEntity_1.abilities.creativeMode) {
                    stack.subtractAmount(1);
                }
                this.setDeathStage(this.getDeathStage() + 1);
                playerEntity_1.inventory.addPickBlock(new ItemStack(this instanceof EntityFireDragon ? ModItems.fire_dragon_blood : ModItems.ice_dragon_blood, 1));
                return true;
            } else if (!world.isClient && stack.isEmpty() && IceAndFire.CONFIG.dragonDropSkull) {
                if (this.getDeathStage() == lastDeathStage - 1) {
                    ItemStack skull = new ItemStack(ModItems.dragon_skull, 1, this.isFire ? 0 : 1);
                    skull.setTag(new CompoundTag());
                    skull.getTag().putInt("Stage", this.getDragonStage());
                    skull.getTag().putInt("DragonType", 0);
                    skull.getTag().putInt("DragonAge", this.getAgeInDays());
                    this.setDeathStage(this.getDeathStage() + 1);
                    if (!world.isClient) {
                        this.dropStack(skull, 1);
                    }
                    this.setDead();
                } else if (this.getDeathStage() == (lastDeathStage / 2) - 1 && IceAndFire.CONFIG.dragonDropHeart) {
                    ItemStack heart = new ItemStack(this instanceof EntityFireDragon ? ModItems.fire_dragon_heart : ModItems.ice_dragon_heart, 1);
                    ItemStack egg = new ItemStack(this.getVariantEgg(this.rand.nextInt(4)), 1);
                    if (!world.isClient) {
                        this.dropStack(heart, 1);
                        if (!this.isMale() && this.getDragonStage() > 3) {
                            this.dropStack(egg, 1);
                        }
                    }
                    this.setDeathStage(this.getDeathStage() + 1);
                } else {
                    this.setDeathStage(this.getDeathStage() + 1);
                    ItemStack drop = getRandomDrop();
                    if (!drop.isEmpty() && !world.isClient) {
                        this.dropStack(drop, 1);
                    }
                }
            }
            return true;
        } else if (!this.isModelDead()) {
            if (this.isOwner(playerEntity_1)) {
                if (!stack.isEmpty()) {
                    if (this.isBreedingItem(stack) && this.isAdult()) {
                        this.setBreedingAge(0);
//                        this.item(playerEntity_1, stack);
                        this.setOwnerUuid(playerEntity_1.getUuid());
                        return true;
                    }
                    if (stack.getItem() != null) {
                        int itemFoodAmount = FoodUtils.getFoodPoints(stack, true, !isFire);
                        if (itemFoodAmount > 0 && (this.getHunger() < 100 || this.getHealth() < this.getHealthMaximum())) {
                            //this.growDragon(1);
                            this.setHunger(this.getHunger() + itemFoodAmount);
                            this.setHealth(Math.min(this.getHealthMaximum(), (int) (this.getHealth() + (itemFoodAmount / 10))));
                            this.playSound(SoundEvents.ENTITY_GENERIC_EAT, this.getSoundVolume(), this.getSoundPitch());
                            this.spawnItemCrackParticles(stack.getItem());
                            this.eatFoodBonus(stack);
                            if (!playerEntity_1.isCreative()) {
                                stack.subtractAmount(1);
                            }
                            return true;
                        }
                        if (stack.getItem() == ModItems.dragon_meal) {
                            this.growDragon(1);
                            this.setHunger(this.getHunger() + 20);
                            this.heal(Math.min(this.getHealth(), (int) (this.getHealthMaximum() / 2)));
                            this.playSound(SoundEvents.ENTITY_GENERIC_EAT, this.getSoundVolume(), this.getSoundPitch());
                            this.spawnItemCrackParticles(stack.getItem());
                            this.spawnItemCrackParticles(Items.BONE);
                            this.spawnItemCrackParticles(Items.WHITE_DYE);
                            this.eatFoodBonus(stack);
                            if (!playerEntity_1.isCreative()) {
                                stack.subtractAmount(1);
                            }
                            return true;
                        }
                        if (stack.getItem() == ModItems.sickly_dragon_meal && !this.isAgingDisabled()) {
                            this.setHunger(this.getHunger() + 20);
                            this.heal(this.getHealthMaximum());
                            this.playSound(SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE, this.getSoundVolume(), this.getSoundPitch());
                            this.spawnItemCrackParticles(stack.getItem());
                            this.spawnItemCrackParticles(Items.BONE);
                            //TODO: Add rest of dyes
                            this.spawnItemCrackParticles(Items.WHITE_DYE);
                            this.spawnItemCrackParticles(Items.POISONOUS_POTATO);
                            this.spawnItemCrackParticles(Items.POISONOUS_POTATO);
                            this.setAgingDisabled(true);
                            this.eatFoodBonus(stack);
                            if (!playerEntity_1.isCreative()) {
                                stack.subtractAmount(1);
                            }
                            return true;
                        }
                        if (stack.getItem() == ModItems.dragon_stick) {
                            if (playerEntity_1.isSneaking()) {
                                BlockPos pos = new BlockPos(this);
                                this.homePos = pos;
                                this.hasHomePosition = true;
                                playerEntity_1.addChatMessage(new TranslatableTextComponent("dragon.command.new_home", homePos.getX(), homePos.getY(), homePos.getZ()), true);
                                return true;
                            } else {
                                this.playSound(SoundEvents.ENTITY_ZOMBIE_INFECT, this.getSoundVolume(), this.getSoundPitch());
                                this.setSitting(!this.isSitting());
                                playerEntity_1.addChatMessage(new TranslatableTextComponent("dragon.command." + (this.isSitting() ? "sit" : "stand")), true);
                                return true;
                            }

                        }
                        StoneEntityProperties properties = EntityPropertiesHandler.INSTANCE.getProperties(this, StoneEntityProperties.class);
                        if (stack.getItem() == ModItems.dragon_horn && !world.isClient && (properties == null || !properties.isStone)) {
                            this.playSound(SoundEvents.ENTITY_ZOMBIE_VILLAGER_CONVERTED, 3, 1.25F);
                            ItemStack stack1 = new ItemStack(this.isFire ? ModItems.dragon_horn_fire : ModItems.dragon_horn_ice);
                            stack1.setTag(new CompoundTag());
                            this.writeCustomDataToTag(stack1.getTag());
                            player.setHeldItem(hand, stack1);
                            this.setDead();
                            return true;
                        }
                    }
                } else {
                    if (stack.isEmpty() && !playerEntity_1.isSneaking() && !this.isValid()) {
                        if (this.getDragonStage() < 2) {
                            this.startRiding(playerEntity_1, true);
                        }
                        if (this.getDragonStage() > 2 && !playerEntity_1.isPartOf(this)) {
                            playerEntity_1.setSneaking(false);
                            playerEntity_1.startRiding(this, true);
                            this.setSleeping(false);
                        }

                        if (this.getDragonStage() < 2) {
                            this.startRiding(playerEntity_1, true);
                        }
                        return true;
                    } else if (stack.isEmpty() && playerEntity_1.isSneaking()) {
                        this.openGUI(playerEntity_1);
                        return true;
                    }
                }
            }
        }
        return super.interactMob(playerEntity_1, hand_1);
    }

    private ItemStack getRandomDrop() {
        ItemStack stack = getItemFromLootTable();
        if(stack.getItem() == ModItems.dragonbone){
            this.playSound(SoundEvents.ENTITY_SKELETON_AMBIENT, 1, 1);
        }else{
            this.playSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
        }
        return stack;
    }

    public ItemStack getItemFromLootTable() {
        return ItemStack.EMPTY;
    }

    public void eatFoodBonus(ItemStack stack) {

    }

    protected void destroy() {
        if (!IceAndFire.CONFIG.canDragonsDespawn) {
            super.destroy();
        }
    }

    public void growDragon(int ageInDays) {
        if (this.isAgingDisabled()) {
            return;
        }
        this.setAgeInDays(this.getAgeInDays() + ageInDays);
        this.setScaleForAge(false);
        this.clearSleepingPosition();
        if (this.getAgeInDays() % 25 == 0) {
            for (int i = 0; i < this.getRenderSize() * 4; i++) {
                double motionX = getRand().nextGaussian() * 0.07D;
                double motionY = getRand().nextGaussian() * 0.07D;
                double motionZ = getRand().nextGaussian() * 0.07D;
                float f = (float) (getRand().nextFloat() * (this.getBoundingBox().maxX - this.getBoundingBox().minX) + this.getBoundingBox().minX);
                float f1 = (float) (getRand().nextFloat() * (this.getBoundingBox().maxY - this.getBoundingBox().minY) + this.getBoundingBox().minY);
                float f2 = (float) (getRand().nextFloat() * (this.getBoundingBox().maxZ - this.getBoundingBox().minZ) + this.getBoundingBox().minZ);
                if (world.isClient) {
                    this.world.addParticle(ParticleTypes.HAPPY_VILLAGER, f, f1, f2, motionX, motionY, motionZ);
                }
            }
        }
        this.updateAttributes();
    }

    public void spawnItemCrackParticles(Item item) {
        for (int i = 0; i < 15; i++) {
            double motionX = getRand().nextGaussian() * 0.07D;
            double motionY = getRand().nextGaussian() * 0.07D;
            double motionZ = getRand().nextGaussian() * 0.07D;
            float headPosX = (float) (x + 1.9F * getRenderSize() * 0.3F * Math.cos((yaw + 90) * Math.PI / 180));
            float headPosZ = (float) (z + 1.9F * getRenderSize() * 0.3F * Math.sin((yaw + 90) * Math.PI / 180));
            float headPosY = (float) (y + (getRenderSize() * 0.2F));
            if (world.isClient) {
                //TODO: Find correct particle
                this.world.addParticle(ParticleTypes.POOF, headPosX, headPosY, headPosZ, motionX, motionY, motionZ);
            }
        }
    }

    public boolean isDaytime() {
        return this.world.isDaytime();
    }

    private boolean isStuck() {
        return !this.isTamed() && (!this.getNavigator().noPath() && (this.getNavigator().getPath() == null || this.getNavigator().getPath().getFinalPathPoint() != null && this.getDistanceSq(new BlockPos(this.getNavigator().getPath().getFinalPathPoint().x, this.getNavigator().getPath().getFinalPathPoint().y, this.getNavigator().getPath().getFinalPathPoint().z)) > 15) || this.airTarget != null) && ticksStill > 80 && !this.isHovering() && canMove();
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!world.isClient) {
            if(this.isInLove()){
                this.world.setEntityState(this, (byte)18);
            }
            if ((int) this.prevPosX == (int) this.posX && (int) this.prevPosZ == (int) this.posZ) {
                this.ticksStill++;
            } else {
                ticksStill = 0;
            }
            if (this.getDragonStage() >= 3 && isStuck() && this.world.getGameRules().getBoolean("mobGriefing") && IceAndFire.CONFIG.dragonGriefing != 2) {
                if (this.getAnimation() == NO_ANIMATION && this.ticksExisted % 5 == 0) {
                    this.setAnimation(ANIMATION_TAILWHACK);
                }
                if (this.getAnimation() == ANIMATION_TAILWHACK && this.getAnimationTick() == 10) {
                    IBlockState state = world.getBlockState(new BlockPos(this));
                    BlockBreakExplosion explosion = new BlockBreakExplosion(world, this, this.posX, this.posY, this.posZ, (4) * this.getDragonStage() - 2);
                    explosion.doExplosionA();
                    explosion.doExplosionB(true);
                    this.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1, 1);

                }
            }
        }

        if (this.isTackling() && !this.isFlying() && this.onGround) {
            tacklingTicks++;
            if (tacklingTicks == 40) {
                tacklingTicks = 0;
                this.setTackling(false);
                this.setFlying(false);
            }
        }
        if (this.walkCycle < 39) {
            this.walkCycle++;
        } else {
            this.walkCycle = 0;
        }
        if (!world.isClient && this.getRand().nextInt(500) == 0 && !this.isModelDead() && !this.isSleeping()) {
            this.roar();
        }
        if (!world.isClient && this.onGround && this.getNavigator().noPath() && this.getAttackTarget() != null && this.getAttackTarget().posY - 3 > this.posY && this.getRand().nextInt(15) == 0 && this.canMove() && !this.isHovering() && !this.isFlying() && !this.isChild()) {
            this.setHovering(true);
            this.setSleeping(false);
            this.setSitting(false);
            this.flyHovering = 0;
            this.hoverTicks = 0;
            this.flyTicks = 0;
        }
        if (this.getAnimation() == ANIMATION_WINGBLAST && (this.getAnimationTick() == 17 || this.getAnimationTick() == 22 || this.getAnimationTick() == 28)) {
            this.spawnGroundEffects();
            if (this.getAttackTarget() != null) {
                boolean flag = this.getAttackTarget().attackEntityFrom(DamageSource.causeMobDamage(this), ((int) this.getAttributeContainer().register(EntityAttributes.ATTACK_DAMAGE).getAttributeValue()) / 4);
                this.getAttackTarget().knockBack(this.getAttackTarget(), this.getDragonStage() * 0.6F, 1, 1);
                this.attackDecision = this.getRand().nextBoolean();
            }
        }
        if (!world.isClient && this.isFlying() && this.getAttackTarget() != null && this.attackDecision && this.isDirectPathBetweenPoints(this.getPositionVector(), this.getAttackTarget().getPositionVector())) {
            this.setTackling(true);
        }
        if (!world.isClient && this.isFlying() && this.getAttackTarget() != null && this.isTackling() && this.getBoundingBox().expand(2.0D, 2.0D, 2.0D).intersects(this.getAttackTarget().getBoundingBox())) {
            this.attackDecision = true;
            this.getAttackTarget().attackEntityFrom(DamageSource.causeMobDamage(this), this.getDragonStage() * 3);
            this.spawnGroundEffects();
            this.setFlying(false);
            this.setHovering(false);
        }
        if (!world.isClient && this.isTackling() && this.getAttackTarget() == null) {
            this.setTackling(false);
        }
        StoneEntityProperties properties = EntityPropertiesHandler.INSTANCE.getProperties(this, StoneEntityProperties.class);
        if (properties != null && properties.isStone) {
            this.setFlying(false);
            this.setHovering(false);
            return;
        }
        if (this.isFlying() && this.ticksExisted % 40 == 0 || this.isFlying() && this.isSleeping()) {
            this.setFlying(false);
            this.setFlying(true);
            this.setSleeping(false);
        }
        if (!this.canMove() && this.getAttackTarget() != null) {
            this.setAttackTarget(null);
        }
        if (!this.canMove()) {
            this.getNavigator().clearPath();

        }
        if (this.getControllingPassenger() != null) {
            if (motionY > 0.5) {
                this.motionY = 0.5;
            }
            if (motionY < -0.5) {
                this.motionY = -0.5;
            }
        } else {
            if (motionY > 0.5) {
                this.motionY = 0.5;
            }
            if (motionY < -0.5) {
                this.motionY = -0.5;
            }
            if (motionY > 1) {
                this.motionY = 0;
            }
        }
        this.updateCheckPlayer();
        AnimationHandler.INSTANCE.updateAnimations(this);
        this.legSolver.update(this);
        if ((this.isFlying() || this.isHovering()) && !this.isModelDead()) {
            if (flightCycle < 58) {
                flightCycle += 2;
            } else {
                flightCycle = 0;
            }
            if (flightCycle == 2) {
                this.playSound(ModSounds.DRAGON_FLIGHT, this.getSoundVolume() * IceAndFire.CONFIG.dragonFlapNoiseDistance, getSoundPitch());
            }
            if (flightCycle > 10 && flightCycle < 12) {
                this.spawnGroundEffects();
            }
            if (this.isModelDead() && flightCycle != 0) {
                flightCycle = 0;
            }
        }
        if (this.isModelDead() && (this.isFlying() || this.isHovering())) {
            this.setFlying(false);
            this.setHovering(false);
        }

        boolean sitting = isSitting() && !isModelDead() && !isSleeping() && !isHovering() && !isFlying();
        if (sitting && sitProgress < 20.0F) {
            sitProgress += 0.5F;
        } else if (!sitting && sitProgress > 0.0F) {
            sitProgress -= 0.5F;
        }
        boolean sleeping = isSleeping() && !isHovering() && !isFlying();
        if (sleeping && sleepProgress < 20.0F) {
            sleepProgress += 0.5F;
        } else if (!sleeping && sleepProgress > 0.0F) {
            sleepProgress -= 0.5F;
        }
        boolean fireBreathing = isBreathingFire();
        if (fireBreathing && fireBreathProgress < 5.0F) {
            fireBreathProgress += 0.5F;
        } else if (!fireBreathing && fireBreathProgress > 0.0F) {
            fireBreathProgress -= 0.5F;
        }
        boolean hovering = isHovering();
        if (hovering && hoverProgress < 20.0F) {
            hoverProgress += 0.5F;
        } else if (!hovering && hoverProgress > 0.0F) {
            hoverProgress -= 0.5F;
        }
        boolean tackling = isTackling();
        if (tackling && tackleProgress < 5F) {
            tackleProgress += 0.5F;
        } else if (!tackling && tackleProgress > 0.0F) {
            tackleProgress -= 1.5F;
        }
        boolean flying = !tackling && this.isFlying() || !this.onGround && !this.isHovering() && this.airTarget != null;
        if (flying && flyProgress < 20.0F) {
            flyProgress += 0.5F;
        } else if (!flying && flyProgress > 0.0F) {
            flyProgress -= 0.5F;
        }
        boolean modeldead = isModelDead();
        if (modeldead && modelDeadProgress < 20.0F) {
            modelDeadProgress += 0.5F;
        } else if (!modeldead && modelDeadProgress > 0.0F) {
            modelDeadProgress -= 0.5F;
        }
        boolean riding = isRiding() && this.getRidingEntity() != null && this.getRidingEntity() instanceof EntityPlayer;
        if (riding && ridingProgress < 20.0F) {
            ridingProgress += 0.5F;
        } else if (!riding && ridingProgress > 0.0F) {
            ridingProgress -= 0.5F;
        }

        if (this.isModelDead()) {
            return;
        }
        if (!this.world.isClient && this.onGround && this.doesWantToLand() && (this.isFlying() || this.isHovering())) {
            this.setFlying(false);
            this.setHovering(false);
        }
        if (this.getControllingPassenger() != null && !this.onGround && (this.isFlying() || this.isHovering())) {
            this.motionY *= 0D;
        }
        if (this.isHovering()) {
            if (this.isSleeping()) {
                this.setHovering(false);
            }
            this.hoverTicks++;
            if (this.doesWantToLand() && !this.onGround) {
                this.motionY -= 0.25D;
            } else {
                if (this.getControllingPassenger() == null) {
                    this.motionY += 0.08;
                }
                if (this.hoverTicks > 40) {
                    if (!this.isChild()) {
                        this.setFlying(true);
                    }
                    this.setHovering(false);
                    this.flyHovering = 0;
                    this.hoverTicks = 0;
                    this.flyTicks = 0;
                }
            }

            if (flyHovering == 0) {
                // move upwards
            }
            if (flyHovering == 1) {
                // move down
            }
            if (flyHovering == 2) {
                // stay still
            }
        }
        if (this.isSleeping()) {
            this.getNavigator().clearPath();
        }
        if (!this.isFlying() && !this.isHovering() && this.airTarget != null && this.onGround) {
            this.airTarget = null;
        }
        if (this.isFlying() && this.airTarget == null && this.onGround && this.getControllingPassenger() == null) {
            this.setFlying(false);
        }

        if (this.isFlying() && getAttackTarget() == null) {
            flyAround();
        } else if (getAttackTarget() != null) {
            flyTowardsTarget();
        }
        if (this.onGround && flyTicks != 0) {
            flyTicks = 0;
        }
        if (this.isFlying() && this.doesWantToLand()) {
            this.setFlying(false);
            this.setHovering(!this.onGround);
            if (this.onGround) {
                flyTicks = 0;
                this.setFlying(false);
            }
            //this.motionY -= 0.26D;
        }
        if (this.isFlying()) {
            this.flyTicks++;
        }
        if ((this.isHovering() || this.isFlying()) && this.isSleeping()) {
            this.setFlying(false);
            this.setHovering(false);
        }
        if ((properties == null || properties != null && !properties.isStone) && (!world.isClient && this.getRand().nextInt(FLIGHT_CHANCE_PER_TICK) == 0 && !this.isSitting() && !this.isFlying() && this.getPassengers().isEmpty() && !this.isChild() && !this.isHovering() && !this.isSleeping() && this.canMove() && this.onGround || this.posY < -1)) {
            this.setHovering(true);
            this.setSleeping(false);
            this.setSitting(false);
            this.flyHovering = 0;
            this.hoverTicks = 0;
            this.flyTicks = 0;
        }
        if (this.getAttackTarget() != null && this.getAttackTarget().posY + 5 > this.posY && (properties == null || properties != null && !properties.isStone) && (!world.isClient  && !this.isSitting() && !this.isFlying() && this.getPassengers().isEmpty() && !this.isChild() && !this.isHovering() && !this.isSleeping() && this.canMove() && this.onGround)) {
            this.setHovering(true);
            this.setSleeping(false);
            this.setSitting(false);
            this.flyHovering = 0;
            this.hoverTicks = 0;
            this.flyTicks = 0;
        }
        if (getAttackTarget() != null && !this.getPassengers().isEmpty() && this.getOwner() != null && this.getPassengers().contains(this.getOwner())) {
            this.setAttackTarget(null);
        }
        if (!this.isAgingDisabled()) {
            this.setAgeInTicks(this.getAgeInTicks() + 1);
            if (this.getAgeInTicks() % 24000 == 0) {
                this.updateAttributes();
                this.growDragon(0);
            }
        }
        if (this.getAgeInTicks() % IceAndFire.CONFIG.dragonHungerTickRate == 0) {
            if (this.getHunger() > 0) {
                this.setHunger(this.getHunger() - 1);
            }
        }
        if(this.attackDecision && this.getAttackTarget() != null && this.getDistance(this.getAttackTarget()) > Math.min(this.getBoundingBox().getAverageEdgeLength() * 5, 25) && !this.isChild()){
            this.attackDecision = false;
        }
        if ((!this.attackDecision || this.getRand().nextInt(750) == 0) && this.getDragonStage() < 2) {
            this.attackDecision = this.getRand().nextBoolean();
            for (int i = 0; i < 5; i++) {
                float radiusAdd = i * 0.15F;
                float headPosX = (float) (posX + 1.8F * getRenderSize() * (0.3F + radiusAdd) * Math.cos((rotationYaw + 90) * Math.PI / 180));
                float headPosZ = (float) (posZ + 1.8F * getRenderSize() * (0.3F + radiusAdd) * Math.sin((rotationYaw + 90) * Math.PI / 180));
                float headPosY = (float) (posY + 0.5 * getRenderSize() * 0.3F);
                if (this.isFire && world.isClient) {
                    this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, headPosX, headPosY, headPosZ, 0, 0, 0);
                } else if (world.isClient) {
                    IceAndFire.PROXY.spawnParticle("dragonice", this.world, headPosX, headPosY, headPosZ, 0, 0, 0);
                }
            }
            if (this.isFire) {
                this.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 1, 1);
            } else {
                this.playSound(SoundEvents.ITEM_BOTTLE_FILL_DRAGONBREATH, 1, 1);
            }
        }
        if (this.isBreathingFire()) {
            this.fireTicks++;
            if (fireTicks > this.getDragonStage() * 12 || this.getOwner() != null && this.getPassengers().contains(this.getOwner()) && this.fireStopTicks <= 0) {
                this.setBreathingFire(false);
                this.attackDecision = this.getRand().nextBoolean();
                fireTicks = 0;
            }
            if (fireStopTicks > 0 && this.getOwner() != null && this.getPassengers().contains(this.getOwner())) {
                fireStopTicks--;
            }
        }
        if (this.isFlying() && this.getAttackTarget() != null && this.getBoundingBox().expand(3.0F, 3.0F, 3.0F).intersects(this.getAttackTarget().getBoundingBox())) {
            this.attackEntityAsMob(this.getAttackTarget());
        }
        this.breakBlock();
        if(this.posY > IceAndFire.CONFIG.maxDragonFlight){
            this.setPosition(this.posX, IceAndFire.CONFIG.maxDragonFlight, this.posZ);
        }
    }

    public void breakBlock() {
        if (IceAndFire.CONFIG.dragonGriefing != 2 || this.isTamed() && !IceAndFire.CONFIG.tamedDragonGriefing) {
            float hardness = IceAndFire.CONFIG.dragonGriefing == 1 || this.getDragonStage() <= 3 ? 1.6F : 5F;
            if (!isModelDead() && this.getDragonStage() >= 3 && this.canMove()) {
                for (int a = (int) Math.round(this.getBoundingBox().minX) - 1; a <= (int) Math.round(this.getBoundingBox().maxX) + 1; a++) {
                    for (int b = (int) Math.round(this.getBoundingBox().minY) + 1; (b <= (int) Math.round(this.getBoundingBox().maxY) + 2) && (b <= 127); b++) {
                        for (int c = (int) Math.round(this.getBoundingBox().minZ) - 1; c <= (int) Math.round(this.getBoundingBox().maxZ) + 1; c++) {
                            IBlockState state = world.getBlockState(new BlockPos(a, b, c));
                            Block block = state.getBlock();
                            if (state.getMaterial() != Material.AIR && !(block instanceof BlockBush) && !(block instanceof BlockLiquid) && block != Blocks.BEDROCK && state.getBlockHardness(world, new BlockPos(a, b, c)) < hardness && DragonUtils.canDragonBreak(state.getBlock()) && this.canDestroyBlock(new BlockPos(a, b, c))) {
                                this.motionX *= 0.6D;
                                this.motionZ *= 0.6D;
                                if (block != Blocks.AIR) {
                                    if (!world.isClient) {
                                        world.destroyBlock(new BlockPos(a, b, c), true);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void spawnGroundEffects() {
        for (int i = 0; i < this.getRenderSize(); i++) {
            for (int i1 = 0; i1 < 20; i1++) {
                double motionX = getRand().nextGaussian() * 0.07D;
                double motionY = getRand().nextGaussian() * 0.07D;
                double motionZ = getRand().nextGaussian() * 0.07D;
                float radius = 0.75F * (0.7F * getRenderSize() / 3) * -3;
                float angle = (0.01745329251F * this.headYaw) + i1 * 1F;
                double extraX = (double) (radius * MathHelper.sin((float) (Math.PI + angle)));
                double extraY = 0.8F;
                double extraZ = (double) (radius * MathHelper.cos(angle));

                BlockState iblockstate = this.world.getBlockState(new BlockPos(MathHelper.floor(this.x + extraX), MathHelper.floor(this.y + extraY) - 1, MathHelper.floor(this.z + extraZ)));
                if (iblockstate.getMaterial() != Material.AIR) {
                    if (world.isClient) {
                        world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, true, this.posX + extraX, this.posY + extraY, this.posZ + extraZ, motionX, motionY, motionZ, new int[]{Block.getStateId(iblockstate)});
                    }
                }
            }
        }
    }

    public void fall(float distance, float damageMultiplier) {

    }

    public boolean isActuallyBreathingFire() {
        return this.fireTicks > 20 && this.isBreathingFire();
    }

    public boolean doesWantToLand() {
        StoneEntityProperties properties = EntityPropertiesHandler.INSTANCE.getProperties(this, StoneEntityProperties.class);
        return this.flyTicks > 6000 || down() || flyTicks > 40 && this.flyProgress == 0 || properties != null && properties.isStone;
    }

    public abstract String getVariantName(int variant);

    public void updatePassenger(Entity passenger) {
        super.updatePassenger(passenger);
        if (this.isPassenger(passenger)) {
            if (this.getControllingPassenger() == null || this.getControllingPassenger().getEntityId() != passenger.getEntityId()) {
                updatePreyInMouth(passenger);
            } else {
                if (this.isModelDead()) {
                    passenger.dismountRidingEntity();
                }
                float speed_walk = 0.2F;
                float speed_idle = 0.05F;
                float speed_fly = 0.2F;
                float degree_walk = 0.5F;
                float degree_idle = 0.5F;
                float degree_fly = 0.5F;
                //this.walk(BodyLower, speed_fly, (float) (degree_fly * 0.15), false, 0, 0, entity.ticksExisted, 1);
                //this.walk(BodyUpper, speed_fly, (float) (degree_fly * -0.15), false, 0, 0, entity.ticksExisted, 1);
                renderYawOffset = rotationYaw;
                this.rotationYaw = passenger.rotationYaw;
                float hoverAddition = hoverProgress * -0.001F;
                float flyAddition = flyProgress * -0.0001F;
                float flyBody = Math.max(flyProgress, hoverProgress) * 0.0065F;
                float radius = 0.75F * ((0.3F - flyBody) * getRenderSize()) + ((this.getRenderSize() / 3) * flyAddition * 0.0065F);
                float angle = (0.01745329251F * this.renderYawOffset);
                double extraX = (double) (radius * MathHelper.sin((float) (Math.PI + angle)));
                double extraZ = (double) (radius * MathHelper.cos(angle));
                float bob0 = this.isFlying() || this.isHovering() ? (hoverProgress > 0 || flyProgress > 0 ? this.bob(-speed_fly, degree_fly * 5, false, this.ticksExisted, -0.0625F) : 0) : 0;
                float bob1 = this.bob(speed_walk * 2, degree_walk * 1.7F, false, this.limbSwing, this.limbSwingAmount * -0.0625F);
                float bob2 = this.bob(speed_idle, degree_idle * 1.3F, false, this.ticksExisted, -0.0625F);

                double extraY_pre = 0.8F;
                double extraY = ((extraY_pre - (hoverAddition) + (flyAddition)) * (this.getRenderSize() / 3)) - (0.35D * (1 - (this.getRenderSize() / 30))) + bob0 + bob1 + bob2;

                passenger.setPosition(this.posX + extraX, this.posY + extraY, this.posZ + extraZ);

                this.stepHeight = 1;
            }
        }
    }

    private float bob(float speed, float degree, boolean bounce, float f, float f1) {
        float bob = (float) (Math.sin(f * speed) * f1 * degree - f1 * degree);
        if (bounce) {
            bob = (float) -Math.abs((Math.sin(f * speed) * f1 * degree));
        }
        return bob * this.getRenderSize() / 3;
    }

    private void updatePreyInMouth(Entity prey) {
        this.setAnimation(ANIMATION_SHAKEPREY);
        if (this.getAnimation() == ANIMATION_SHAKEPREY && this.getAnimationTick() > 55 && prey != null) {
            prey.damage(DamageSource.mob(this), prey instanceof PlayerEntity ? 17F : (float) this.getAttributeContainer().register(EntityAttributes.ATTACK_DAMAGE).getValue() * 4);
            prey.stopRiding();
        }
        headYaw = yaw;
        float modTick_0 = this.getAnimationTick() - 25;
        float modTick_1 = this.getAnimationTick() > 25 && this.getAnimationTick() < 55 ? 8 * MathHelper.clamp(MathHelper.sin((float) (Math.PI + modTick_0 * 0.25)), -0.8F, 0.8F) : 0;
        float modTick_2 = this.getAnimationTick() > 30 ? 10 : Math.max(0, this.getAnimationTick() - 20);
        float radius = 0.75F * (0.6F * getRenderSize() / 3) * -3;
        float angle = (0.01745329251F * this.headYaw) + 3.15F + (modTick_1 * 2F) * 0.015F;
        double extraX = (double) (radius * MathHelper.sin((float) (Math.PI + angle)));
        double extraZ = (double) (radius * MathHelper.cos(angle));
        double extraY = modTick_2 == 0 ? 0 : 0.035F * ((getRenderSize() / 3) + (modTick_2 * 0.5 * (getRenderSize() / 3)));
        prey.setPosition(this.x + extraX, this.y + extraY, this.z + extraZ);
    }

    public int getDragonStage() {
        int age = this.getAgeInDays();
        if (age >= 100) {
            return 5;
        } else if (age >= 75) {
            return 4;
        } else if (age >= 50) {
            return 3;
        } else if (age >= 25) {
            return 2;
        } else {
            return 1;
        }
    }

    public boolean isTeen() {
        return getDragonStage() < 4 && getDragonStage() > 2;
    }

    public boolean isAdult() {
        return getDragonStage() >= 4;
    }

    public boolean isChild() {
        return getDragonStage() < 2;
    }

    @Override
    public EntityData prepareEntityData(IWorld iWorld_1, LocalDifficulty localDifficulty_1, SpawnType spawnType_1, EntityData entityData_1, CompoundTag compoundTag_1) {
        entityData_1 = super.prepareEntityData(iWorld_1, localDifficulty_1, spawnType_1, entityData_1, compoundTag_1);
        this.setGender(this.getRand().nextBoolean());
        int age = this.getRand().nextInt(80) + 1;
        this.growDragon(age);
        this.setVariant(new Random().nextInt(4));
        this.setSleeping(false);
        this.updateAttributes();
        double healthStep = (maximumHealth - minimumHealth) / (125);
        this.heal((Math.round(minimumHealth + (healthStep * age))));
        this.attackDecision = true;
        this.setHunger(50);
        return entityData_1;
    }

    @Override
    public boolean damage(DamageSource dmg, float i) {
        if (this.isModelDead()) {
            return false;
        }
        if (this.isBeingRidden() && dmg.getTrueSource() != null && this.getControllingPassenger() != null && dmg.getTrueSource() == this.getControllingPassenger()) {
            return false;
        }

        if ((dmg.damageType.contains("arrow") || getRidingEntity() != null && dmg.getTrueSource() != null && dmg.getTrueSource().isEntityEqual(this.getRidingEntity())) && this.isRiding()) {
            return false;
        }

        if (dmg == DamageSource.IN_WALL || dmg == DamageSource.FALLING_BLOCK) {
            return false;
        }

        if (!world.isClient && dmg.getTrueSource() != null && this.getRand().nextInt(4) == 0) {
            this.roar();
        }
        return super.damage(dmg, i);

    }

    @Override
    public void update() {
        super.update();
        updateParts();
        this.setScaleForAge(true);
        if (world.isClient) {
            this.updateClientControls();
        }
        if (this.isModelDead()) {
            return;
        }
        if (this.up()) {
            if (!this.isFlying() && !this.isHovering()) {
                this.spacebarTicks += 2;
            }
            if (this.isFlying() || this.isHovering()) {
                this.motionY += 0.4D;
            }
        } else if (this.dismount()) {
            if (this.isFlying() || this.isHovering()) {
                this.motionY -= 0.4D;
                if (this.onGround) {
                    this.setFlying(false);
                    this.setHovering(false);
                }
            }
        }
        if (!this.dismount() && (this.isFlying() || this.isHovering())) {
            this.motionY += 0.01D;
        }
        if (this.attack() && this.getControllingPassenger() != null && this.getDragonStage() > 1) {
            this.setBreathingFire(true);
            this.riderShootFire(this.getControllingPassenger());
            this.fireStopTicks = 10;
        }
        if (this.strike() && this.getControllingPassenger() != null && this.getControllingPassenger() instanceof EntityPlayer) {
            EntityLivingBase target = DragonUtils.riderLookingAtEntity(this, (EntityPlayer) this.getControllingPassenger(), this.getDragonStage() + (this.getBoundingBox().maxX - this.getBoundingBox().minX));
            if (this.getAnimation() != ANIMATION_BITE) {
                this.setAnimation(ANIMATION_BITE);
            }
            if (target != null && !DragonUtils.hasSameOwner(this, target)) {
                target.attackEntityFrom(DamageSource.causeMobDamage(this), ((int) this.getAttributeContainer().register(EntityAttributes.ATTACK_DAMAGE).getAttributeValue()));
            }
        }
        if (this.getControllingPassenger() != null && this.getControllingPassenger().isSneaking()) {
            MiscPlayerProperties properties = EntityPropertiesHandler.INSTANCE.getProperties(this.getControllingPassenger(), MiscPlayerProperties.class);
            if(properties != null) {
                properties.hasDismountedDragon = true;
            }
            this.getControllingPassenger().dismountRidingEntity();
        }
        if (this.isFlying() && !this.isHovering() && this.getControllingPassenger() != null && !this.onGround && Math.max(Math.abs(motionZ), Math.abs(motionX)) < 0.1F) {
            this.setHovering(true);
            this.setFlying(false);
        }
        if ((this.isFlying() || this.isHovering()) && this.isInWater()) {
            //this.motionY += 0.2;
        }
        if (this.isHovering() && !this.isFlying() && this.getControllingPassenger() != null && !this.onGround && Math.max(Math.abs(motionZ), Math.abs(motionX)) > 0.1F) {
            this.setFlying(true);
            this.setHovering(false);
        }
        if (this.spacebarTicks > 0) {
            this.spacebarTicks--;
        }
        if (this.spacebarTicks > 20 && this.getOwner() != null && this.getPassengers().contains(this.getOwner()) && !this.isFlying() && !this.isHovering()) {
            this.setHovering(true);
        }
        if (world.isClient && !this.isModelDead()) {
            roll_buffer.calculateChainFlapBuffer(50, 10, 4, this);
            turn_buffer.calculateChainSwingBuffer(50, 0, 4, this);
            tail_buffer.calculateChainSwingBuffer(90, 10, 2.5F, this);

        }
        if (this.getAttackTarget() != null && this.getRidingEntity() == null && this.getAttackTarget().isDead || this.getAttackTarget() != null && this.getAttackTarget() instanceof EntityDragonBase && ((EntityDragonBase) this.getAttackTarget()).isDead) {
            this.setAttackTarget(null);
        }
        if (!world.isClient && !this.isInWater() && !this.isSleeping() && this.onGround && !this.isFlying() && !this.isHovering() && this.getAttackTarget() == null && !this.isDaytime() && this.getRand().nextInt(250) == 0 && this.getAttackTarget() == null && this.getPassengers().isEmpty()) {
            this.setSleeping(true);
        }

        if (!world.isClient && this.isSleeping() && (this.isFlying() || this.isHovering() || this.isInWater() || (this.world.canBlockSeeSky(new BlockPos(this)) && this.isDaytime() && !this.isTamed() || this.isDaytime() && this.isTamed()) || this.getAttackTarget() != null || !this.getPassengers().isEmpty())) {
            this.setSleeping(false);
        }

        if (this.isSitting() && this.getControllingPassenger() != null) {
            this.setSitting(false);
        }
    }

    @Override
    public void setScaleForAge(boolean par1) {
        float scale = Math.min(this.getRenderSize() * 0.35F, 7F);
        this.setScale(scale);
        if (scale != lastScale) {
            resetParts(this.getRenderSize() / 3);
        }
        lastScale = scale;
    }

    public float getRenderSize() {
        float step = (growth_stages[this.getDragonStage() - 1][1] - growth_stages[this.getDragonStage() - 1][0]) / 25;
        if (this.getAgeInDays() > 125) {
            return growth_stages[this.getDragonStage() - 1][0] + ((step * 25));
        }
        return growth_stages[this.getDragonStage() - 1][0] + ((step * this.getAgeFactor()));
    }

    private int getAgeFactor() {
        return (this.getDragonStage() > 1 ? this.getAgeInDays() - (25 * (this.getDragonStage() - 1)) : this.getAgeInDays());
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (this.isTackling()) {
            return false;
        }
        if (this.isModelDead()) {
            return false;
        }
        boolean flag = entityIn.damage(DamageSource.mob(this), ((int) this.getAttributeContainer().register(EntityAttributes.ATTACK_DAMAGE).getValue()));

        if (flag) {
            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }

    public void updateRidden() {
        Entity entity = this.getRidingEntity();
        if (this.isRiding() && entity.isDead) {
            this.dismountRidingEntity();
        } else {
            this.motionX = 0.0D;
            this.motionY = 0.0D;
            this.motionZ = 0.0D;
            this.onUpdate();
            if (this.isRiding()) {
                this.updateRiding(entity);
            }
        }
    }

    public void updateRiding(Entity riding) {
        if (riding != null && riding.isPassenger(this) && riding instanceof EntityPlayer) {
            int i = riding.getPassengers().indexOf(this);
            float radius = (i == 2 ? 0F : 0.4F) + (((EntityPlayer) riding).isElytraFlying() ? 2 : 0);
            float angle = (0.01745329251F * ((EntityPlayer) riding).renderYawOffset) + (i == 1 ? -90 : i == 0 ? 90 : 0);
            double extraX = (double) (radius * MathHelper.sin((float) (Math.PI + angle)));
            double extraZ = (double) (radius * MathHelper.cos(angle));
            double extraY = (riding.isSneaking() ? 1.2D : 1.4D) + (i == 2 ? 0.4D : 0D);
            this.rotationYaw = ((EntityPlayer) riding).rotationYawHead;
            this.rotationYawHead = ((EntityPlayer) riding).rotationYawHead;
            this.prevRotationYaw = ((EntityPlayer) riding).rotationYawHead;
            this.setPosition(riding.posX + extraX, riding.posY + extraY, riding.posZ + extraZ);
            if (this.getControlState() == 1 << 4 || ((EntityPlayer) riding).isElytraFlying()) {
                this.dismountRidingEntity();
            }

        }
    }

    @Override
    public int getAnimationTick() {
        return animationTick;
    }

    @Override
    public void setAnimationTick(int tick) {
        animationTick = tick;
    }

    @Override
    public Animation getAnimation() {
        if (this.isModelDead()) {
            return this.NO_ANIMATION;
        }
        return currentAnimation;
    }

    @Override
    public void setAnimation(Animation animation) {
        if (this.isModelDead()) {
            return;
        }
        currentAnimation = animation;
    }

    public void playLivingSound() {
        if (!this.isSleeping() && !this.isModelDead() && !this.world.isClient) {
            if (this.getAnimation() == this.NO_ANIMATION) {
                this.setAnimation(ANIMATION_SPEAK);
            }
            super.playLivingSound();
        }
    }

    protected void playHurtSound(DamageSource source) {
        if (!this.isModelDead()) {
            if (this.getAnimation() == this.NO_ANIMATION && !this.world.isClient) {
                this.setAnimation(ANIMATION_SPEAK);
            }
            super.playHurtSound(source);
        }
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[]{IAnimatedEntity.NO_ANIMATION, EntityDragonBase.ANIMATION_EAT};
    }

    @Override
    public PassiveEntity createChild(PassiveEntity var1) {
        return null;
    }

    @Override
    public boolean canBreedWith(AnimalEntity otherAnimal) {
        if (otherAnimal instanceof EntityDragonBase && otherAnimal != this && otherAnimal.getClass() == this.getClass()) {
            EntityDragonBase dragon = (EntityDragonBase) otherAnimal;
            return this.isMale() && !dragon.isMale() || !this.isMale() && dragon.isMale();
        }
        return false;
    }

    public EntityDragonEgg createEgg(EntityDragonBase ageable) {
        int i = MathHelper.floor(this.x);
        int j = MathHelper.floor(this.y);
        int k = MathHelper.floor(this.z);
        BlockPos pos = new BlockPos(i, j, k);
        EntityDragonEgg dragon = new EntityDragonEgg(this.world);
        dragon.setType(EnumDragonEgg.byMetadata(new Random().nextInt(3) + (this.isFire ? 0 : 4)));
        dragon.setPosition(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
        return dragon;
    }

    public void flyAround() {
        if (airTarget != null) {
            if (!isTargetInAir() || flyTicks > 6000 || !this.isFlying()) {
                airTarget = null;
            }
            flyTowardsTarget();
        }
    }

    public boolean isTargetBlocked(Vec3d target) {
        if (target != null) {
            RayTraceResult rayTrace = world.rayTraceBlocks(new Vec3d(this.getPosition()), target, false);
            if (rayTrace != null && rayTrace.hitVec != null) {
                BlockPos pos = new BlockPos(rayTrace.hitVec);
                if (!world.isAirBlock(pos) || world.getBlockState(pos).getMaterial() == Material.WATER && !isFire) {
                    return true;
                }
                return rayTrace != null && rayTrace.typeOfHit != RayTraceResult.Type.BLOCK;
            }
        }
        return false;
    }

    public void flyTowardsTarget() {
        if(airTarget != null && airTarget.getY() > IceAndFire.CONFIG.maxDragonFlight){
            airTarget = new BlockPos(airTarget.getX(), IceAndFire.CONFIG.maxDragonFlight, airTarget.getZ());
        }
        if (airTarget != null && isTargetInAir() && this.isFlying() && this.getDistanceSquared(new Vec3d(airTarget.getX(), this.posY, airTarget.getZ())) > 3) {
            double y = this.attackDecision ? airTarget.getY() : this.posY;

            double targetX = airTarget.getX() + 0.5D - posX;
            double targetY = Math.min(y, 256) + 1D - posY;
            double targetZ = airTarget.getZ() + 0.5D - posZ;
            motionX += (Math.signum(targetX) * 0.5D - motionX) * 0.100000000372529 * getFlySpeed();
            motionY += (Math.signum(targetY) * 0.5D - motionY) * 0.100000000372529 * getFlySpeed();
            motionZ += (Math.signum(targetZ) * 0.5D - motionZ) * 0.100000000372529 * getFlySpeed();
            float angle = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
            moveForward = 0.5F;
            double d0 = airTarget.getX() + 0.5D - this.posX;
            double d2 = airTarget.getZ() + 0.5D - this.posZ;
            double d1 = y + 0.5D - this.posY;
            double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
            float f = (float) (MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
            float f1 = (float) (-(MathHelper.atan2(d1, d3) * (180D / Math.PI)));
            this.rotationPitch = this.updateRotation(this.rotationPitch, f1, 30F);
            this.rotationYaw = this.updateRotation(this.rotationYaw, f, 30F);

            if (!this.isFlying()) {
                this.setFlying(true);
            }
        } else {
            this.airTarget = null;
        }
        if (airTarget != null && isTargetInAir() && this.isFlying() && this.getDistanceSquared(new Vec3d(airTarget.getX(), this.posY, airTarget.getZ())) < 3 && this.doesWantToLand()) {
            this.setFlying(false);
            this.setHovering(true);
            this.flyHovering = 1;
        }
    }

    private double getFlySpeed() {
        return (2 + (this.getAgeInDays() / 125) * 2) * (this.isTackling() ? 2 : 1);
    }

    private boolean isTackling() {
        if (world.isClient) {
            boolean tackling = this.dataTracker.get(TACKLE).booleanValue();
            this.isTackling = tackling;
            return tackling;
        }
        return isTackling;
    }

    private boolean isAgingDisabled() {
        return this.dataTracker.get(AGINGDISABLED).booleanValue();
    }

    protected boolean isTargetInAir() {
        return airTarget != null && ((world.getBlockState(airTarget).getMaterial() == Material.AIR) || (this instanceof EntityIceDragon && (world.getBlockState(airTarget).getMaterial() == Material.WATER || world.getBlockState(airTarget).getMaterial() == Material.AIR)));
    }

    private float updateRotation(float angle, float targetAngle, float maxIncrease) {
        float f = MathHelper.wrapDegrees(targetAngle - angle);

        if (f > maxIncrease) {
            f = maxIncrease;
        }

        if (f < -maxIncrease) {
            f = -maxIncrease;
        }

        return angle + f;
    }

    public float getDistanceSquared(Vec3d vec3d) {
        float f = (float) (this.posX - vec3d.x);
        float f1 = (float) (this.posY - vec3d.y);
        float f2 = (float) (this.posZ - vec3d.z);
        return f * f + f1 * f1 + f2 * f2;
    }


    public boolean replaceItemInInventory(int inventorySlot, @Nullable ItemStack itemStackIn) {
        int j = inventorySlot - 500 + 2;
        if (j >= 0 && j < this.dragonInv.getSizeInventory()) {
            this.dragonInv.setInventorySlotContents(j, itemStackIn);
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, net.minecraft.util.EnumFacing facing) {
        if (capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return (T) itemHandler;
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(net.minecraftforge.common.capabilities.Capability<?> capability, net.minecraft.util.EnumFacing facing) {
        return capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    public abstract Item getVariantScale(int variant);

    public abstract Item getVariantEgg(int variant);

    @Environment(EnvType.CLIENT)
    protected void updateClientControls() {
        Minecraft mc = Minecraft.getMinecraft();
        if (this.isRidingPlayer(mc.player)) {
            byte previousState = getControlState();
            up(mc.gameSettings.keyBindJump.isKeyDown());
            down(mc.gameSettings.keyBindSneak.isKeyDown());
            attack(ModKeys.dragon_fireAttack.isKeyDown());
            strike(ModKeys.dragon_strike.isKeyDown());
            dismount(ModKeys.dragon_down.isKeyDown());
            byte controlState = getControlState();
            if (controlState != previousState) {
                IceAndFire.NETWORK_WRAPPER.sendToServer(new MessageDragonControl(this.getEntityId(), controlState, posX, posY, posZ));
            }
        }
        if (this.getRidingEntity() != null && this.getRidingEntity() == mc.player) {
            byte previousState = getControlState();
            dismount(ModKeys.dragon_down.isKeyDown());
            byte controlState = getControlState();
            if (controlState != previousState) {
                IceAndFire.NETWORK_WRAPPER.sendToServer(new MessageDragonControl(this.getEntityId(), controlState, posX, posY, posZ));
            }
        }
    }

    public boolean canBeSteered() {
        return true;
    }

    @Override
    public void travel(float strafe, float forward, float vertical) {
        if (this.getAnimation() == ANIMATION_SHAKEPREY || !this.canMove() && !this.isBeingRidden()) {
            strafe = 0;
            forward = 0;
            vertical = 0;
            super.travel(strafe, forward, vertical);
            return;
        }
        if (this.isBeingRidden() && this.canBeSteered()) {
            EntityLivingBase controller = (EntityLivingBase) this.getControllingPassenger();
            if (controller != null && controller != this.getAttackTarget()) {
                this.rotationYaw = controller.rotationYaw;
                this.prevRotationYaw = this.rotationYaw;
                this.rotationPitch = controller.rotationPitch * 0.5F;
                this.setRotation(this.rotationYaw, this.rotationPitch);
                this.renderYawOffset = this.rotationYaw;
                this.rotationYawHead = this.renderYawOffset;
                strafe = controller.moveStrafing * 0.5F;
                forward = controller.moveForward;
                if (forward <= 0.0F) {
                    forward *= 0.25F;
                }
                if (this.isFlying() || this.isHovering()) {
                    motionX *= 1.06;
                    motionZ *= 1.06;
                }
                jumpMovementFactor = 0.05F;
                this.setAIMoveSpeed(onGround ? (float) this.getAttributeContainer().register(EntityAttributes.MOVEMENT_SPEED).getAttributeValue() : (float) getFlySpeed());
                super.travel(strafe, vertical = 0, forward);
                return;
            }
        }
        super.travel(strafe, forward, vertical);
    }

    public void updateCheckPlayer() {
        double checklength = this.getBoundingBox().getAverageEdgeLength() * 3;
        EntityPlayer player = world.getClosestPlayerToEntity(this, checklength);
        if (!this.isTamed() && this.isSleeping()) {
            if (player != null && !this.isOwner(player) && !player.capabilities.isCreativeMode) {
                this.setSleeping(false);
                this.setSitting(false);
                this.setAttackTarget(player);
            }
        }
        EntityPlayer player1 = world.getClosestPlayerToEntity(this, (this.getRenderSize() / 2) + 15);
        //if (player1 != null) {
        //	player1.addStat(ModAchievements.dragonEncounter, 1);
        //}
    }

    public boolean shouldDismountInWater(Entity rider) {
        return false;
    }

    public boolean isDirectPathBetweenPoints(Vec3d vec1, Vec3d vec2) {
        RayTraceResult movingobjectposition = this.world.rayTraceBlocks(vec1, new Vec3d(vec2.x, vec2.y + (double) this.height * 0.5D, vec2.z), false, true, false);
        return movingobjectposition == null || movingobjectposition.typeOfHit != RayTraceResult.Type.BLOCK;
    }

    public void onDeath(DamageSource cause) {
        if (cause.getTrueSource() != null) {
            //if (cause.getTrueSource() instanceof EntityPlayer) {
            //	((EntityPlayer) cause.getTrueSource()).addStat(ModAchievements.dragonSlayer, 1);
            //}
        }
        super.onDeath(cause);
        if (dragonInv != null && !this.world.isClient) {
            for (int i = 0; i < dragonInv.getSizeInventory(); ++i) {
                ItemStack itemstack = dragonInv.getStackInSlot(i);
                if (!itemstack.isEmpty()) {
                    this.entityDropItem(itemstack, 0.0F);
                }
            }
        }
    }

    @Override
    public void onHearFlute(EntityPlayer player) {
        if (this.isTamed() && this.isOwner(player)) {
            if (this.isFlying() || this.isHovering()) {
                this.setFlying(false);
                this.setHovering(false);
            }
        }
    }

    public abstract SoundEvent getRoarSound();

    public void roar() {
        if (EntityGorgon.isStoneMob(this)) {
            return;
        }
        if (this.getAnimation() != ANIMATION_ROAR) {
            this.setAnimation(ANIMATION_ROAR);
            this.playSound(this.getRoarSound(), this.getSoundVolume() + 2 + Math.max(0, this.getDragonStage() - 3), this.getSoundPitch());
        }
        if (this.getDragonStage() > 3) {
            int size = (this.getDragonStage() - 3) * 30;
            List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(this, this.getBoundingBox().expand(size, size, size));
            for (Entity entity : entities) {
                boolean isStrongerDragon = entity instanceof EntityDragonBase && ((EntityDragonBase) entity).getDragonStage() >= this.getDragonStage();
                if (entity instanceof EntityLivingBase && !isStrongerDragon) {
                    EntityLivingBase living = (EntityLivingBase) entity;
                    if (this.isOwner(living) || this.isOwnersPet(living)) {
                        living.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 30 * size));
                    } else {
                        living.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 30 * size));
                    }
                }
            }
        }
    }

    private boolean isOwnersPet(EntityLivingBase living) {
        return this.isTamed() && this.getOwner() != null && living instanceof EntityTameable && ((EntityTameable) living).getOwner() != null && this.getOwner().isEntityEqual(((EntityTameable) living).getOwner());
    }

    public boolean isDirectPathBetweenPoints(Entity entity, Vec3d vec1, Vec3d vec2) {
        RayTraceResult movingobjectposition = entity.world.rayTraceBlocks(vec1, new Vec3d(vec2.x, vec2.y + (double) entity.height * 0.5D, vec2.z), false, true, false);
        return movingobjectposition == null || movingobjectposition.typeOfHit != RayTraceResult.Type.BLOCK;
    }

    public void processArrows() {
        List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, this.getBoundingBox());
        for (Entity entity : entities) {
            if (entity instanceof EntityArrow) {

            }
        }
    }

    public boolean shouldRenderEyes() {
        return !this.isSleeping() && !this.isModelDead() && !this.isBlinking();
    }

    @Override
    public boolean shouldAnimalsFear(Entity entity) {
        return DragonUtils.canTameDragonAttack(this, entity);
    }

    public void dropArmor(){
        if (dragonInv != null && !this.world.isClient) {
            for (int i = 0; i < dragonInv.getSizeInventory(); ++i) {
                ItemStack itemstack = dragonInv.getStackInSlot(i);
                if (!itemstack.isEmpty()) {
                    this.entityDropItem(itemstack, 0.0F);
                }
            }
        }
    }
}*/