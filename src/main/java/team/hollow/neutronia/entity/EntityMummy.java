/*
package team.abnormals.neutronia.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xenoscape.worldsretold.WorldsRetold;
import xenoscape.worldsretold.hailstorm.init.HailstormSounds;
import xenoscape.worldsretold.heatwave.entity.IDesertCreature;

public class EntityMummy extends ZombieEntity implements IDesertCreature
{
    protected static final TrackedData<Byte> HIDDEN = DataTracker.registerData(EntityMummy.class, TrackedDataHandlerRegistry.BYTE);
    public float risingTime;
    public float prevRisingTime;

    public EntityMummy(World worldIn)
    {
        super(worldIn);
        this.experiencePoints = 10;
        this.setSize(0.5F, 1.8F);
    }

    protected void initAttributes()
    {
        super.initAttributes();
        this.getAttributeContainer().register(EntityAttributes.MAX_HEALTH).setBaseValue(40D);
        this.getAttributeContainer().register(EntityAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
        this.getAttributeContainer().register(EntityAttributes.ARMOR).setBaseValue(4.0D);
        this.getAttributeContainer().register(SPAWN_REINFORCEMENTS).setBaseValue(0D);
    }

    protected void initGoals()
    {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, OcelotEntity.class, 8.0F, 1.2D, 1.5D));
        this.goalSelector.add(3, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.add(5, new MoveIntoWaterGoal(this, 1.0D));
        this.goalSelector.add(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.initGoals();
    }


    protected void initDataTracker()
    {
        super.initDataTracker();
        this.dataTracker.set(HIDDEN, (byte) 0);
    }

    public void update()
    {
    	super.update();

        if (this.risingTime % 60 == 0)
        {
            this.heal(1.0F);
        }

    	this.getAttributeContainer().register(EntityAttributes.KNOCKBACK_RESISTANCE).setBaseValue(this.isHidden() ? 1D : 0.05D);

    	if (this.isHidden())
    	{
    		this.x *= 0.25D;
    		this.z *= 0.25D;
    		if (this.y > 0D)
        		this.y = 0D;
    	}

    	if (!this.world.isRemote && !this.isHidden() && this.getAttackTarget() == null && this.world.getBlockState(this.getPosition().down()).getMaterial().isSolid() && rand.nextInt(this.world.isDaytime() && this.world.canSeeSky(new BlockPos(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ)) ? 5 : 500) == 0 && this.ticksExisted % 20 == 0)
    		this.setHidden(true);

    	if (!this.world.isRemote && this.isHidden() && (this.getAttackTarget() != null || this.getRevengeTarget() != null || this.hurtResistantTime > 0 || !this.world.getBlockState(this.getPosition().down()).getMaterial().isSolid()))
    	{
    		this.setHidden(false);
    		this.playSound(HailstormSounds.ENTITY_MUMMY_INFECT, 3F, this.isChild() ? 1.5F : 1F);
    	}

        this.height = (1.95F - ((this.prevRisingTime + (this.risingTime - this.prevRisingTime)) * 1.75F)) * (this.isChild() ? 0.5F : 1F);
        double d0 = (double)width / 2.0D;
        this.setEntityBoundingBox(new AxisAlignedBB(this.posX - d0, this.posY, this.posZ - d0, this.posX + d0, this.posY + (double)this.height, this.posZ + d0));

        this.prevRisingTime = this.risingTime;

        if (this.isHidden())
            this.risingTime = MathHelper.clamp(this.risingTime + 0.025F, 0F, 1F);
        else
            this.risingTime = MathHelper.clamp(this.risingTime - 0.025F, 0F, 1F);

        if (this.risingTime > 0.01F && this.risingTime < 0.99F)
        {
        	++this.limbSwingAmount;
        	this.rotationPitch = (this.risingTime * 60F);
        }

        if (this.risingTime > 0.25F && this.risingTime < 0.99F)
        	this.world.playEvent(2001, this.getPosition().down(), Block.getStateId(this.world.getBlockState(this.getPosition().down())));

        this.setInvisible(this.risingTime == 1F);
    }

    public float getRisingRot(float p_189795_1_)
    {
        return (this.prevRisingTime + (this.risingTime - this.prevRisingTime) * p_189795_1_);
    }

    */
/**
 * (abstract) Protected helper method to write subclass entity data to NBT.
 * <p>
 * (abstract) Protected helper method to read subclass entity data from NBT.
 *//*

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Hidden", this.isHidden());
    }

    */
/**
 * (abstract) Protected helper method to read subclass entity data from NBT.
 *//*

    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setHidden(compound.getBoolean("Hidden"));
    }

    public boolean isHidden()
    {
        return ((Byte) this.dataManager.get(HIDDEN)).byteValue() == 1;
    }

    public void setHidden(boolean value)
    {
        int i = ((Byte) this.dataManager.get(HIDDEN)).byteValue();

        if (value)
        {
            i = i | 1;
        } else
        {
            i = i & ~1;
        }

        this.dataManager.set(HIDDEN, Byte.valueOf((byte) (i & 255)));
    }

    protected boolean shouldBurnInDay()
    {
        return false;
    }

    protected SoundEvent getAmbientSound()
    {
        return this.isHidden() ? null : HailstormSounds.ENTITY_MUMMY_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return HailstormSounds.ENTITY_MUMMY_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return HailstormSounds.ENTITY_MUMMY_DEATH;
    }

    protected SoundEvent getStepSound()
    {
        return this.isHidden() ? null : SoundEvents.BLOCK_CLOTH_BREAK;
    }

	protected ResourceLocation getLootTable()
	{
		return new ResourceLocation(WorldsRetold.MODID, "entity/mummy");
	}

    public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = super.attackEntityAsMob(entityIn);

        if (flag && this.getHeldItemMainhand().isEmpty() && entityIn instanceof EntityLivingBase)
        {
            float f = this.world.getDifficultyForLocation(new BlockPos(this)).getAdditionalDifficulty();
            ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.HUNGER, 240 * (int)f));
            if (f >= 1F)
            	((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 160 * (int)f));
            if (f >= 1.5F)
            	((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 80 * (int)f));
            if (f >= 2F)
                ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.WITHER, 80 * (int)f));
        }

        return flag;
    }

    protected ItemStack getSkullDrop()
    {
        return ItemStack.EMPTY;
    }

    public boolean getCanSpawnHere() {
        return this.world.provider.getDimension() == 0
                && super.getCanSpawnHere();
    }

    public float getEyeHeight()
    {
        float f = 1.725F - ((this.prevRisingTime + (this.risingTime - this.prevRisingTime)) * 1.5F);

        if (this.isChild())
        {
            f = (float)((double)f - 0.81D);
        }

        if (f <= 0.15F)
        	f = 0.15F;

        return f;
    }

	public void setSwingingArms(boolean swingingArms) {}
}*/
