package team.hollow.neutronia.entity;

import net.minecraft.block.Block;
import net.minecraft.class_1394;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.goal.BreakDoorGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntityWithAi;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.init.*;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.VillagerData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.neutronia.base.util.handlers.LootTableHandler;
import team.hollow.neutronia.entity.ai.EntityAIMummyAttack;

import java.util.Calendar;
import java.util.UUID;

public class EntityMummy extends MobEntityWithAi {

    public static final TrackedData<Boolean> ARMS_RAISED = DataTracker.registerData(EntityMummy.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final UUID BABY_SPEED_BOOST_ID = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
    private static final EntityAttributeModifier BABY_SPEED_BOOST = new EntityAttributeModifier(BABY_SPEED_BOOST_ID, "Mummy Baby Speed Boost", 0.5D, EntityAttributeModifier.Operation.MULTIPLY_BASE);
    private static final TrackedData<Boolean> IS_CHILD = DataTracker.registerData(EntityMummy.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<VillagerData> VILLAGER_TYPE = DataTracker.registerData(EntityMummy.class, TrackedDataHandlerRegistry.VILLAGER_DATA);
    private final BreakDoorGoal breakDoor = new BreakDoorGoal(this);
    private boolean isBreakDoorsTaskSet;

    private double mummyBabyChance = 0.05;
    private float mummyWidth = -1.0F;
    private float mummyHeight;

    public EntityMummy(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.95F);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(2, new EntityAIMummyAttack(this, 1.0D, false));
        this.goalSelector.add(6, new MoveThroughVillageGoal(this, 1.0D, false));
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(5, new class_1394(this, 1.0D));
        this.goalSelector.add(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.goalSelector.add(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.applyEntityAI();
    }

    protected void applyEntityAI() {
        this.targetSelector.add(1, new EntityAIHurtByTarget(this, true));
        this.targetSelector.add(3, new EntityAINearestAttackableTarget<>(this, VillagerEntity.class, true));
        this.targetSelector.add(2, new EntityAINearestAttackableTarget<>(this, PlayerEntity.class, true));
    }

    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(10.0D);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.getDataManager().register(VILLAGER_TYPE, 0);
        this.getDataManager().register(ARMS_RAISED, Boolean.FALSE);
        this.getDataManager().register(IS_CHILD, Boolean.FALSE);
    }

    @SideOnly(Side.CLIENT)
    public boolean isArmsRaised() {
        return this.getDataManager().get(ARMS_RAISED);
    }

    public void setArmsRaised(boolean armsRaised) {
        this.getDataManager().set(ARMS_RAISED, armsRaised);
    }

    public boolean isBreakDoorsTaskSet() {
        return this.isBreakDoorsTaskSet;
    }

    public void setBreakDoorAItask(boolean enabled) {
        if (this.isBreakDoorsTaskSet != enabled) {
            this.isBreakDoorsTaskSet = enabled;
            ((PathNavigateGround) this.getNavigator()).setBreakDoors(enabled);

            if (enabled)
                this.goalSelector.add(1, this.breakDoor);
            else
                this.tasks.removeTask(this.breakDoor);
        }
    }

    public boolean isChild() {
        return this.getDataManager().get(IS_CHILD);
    }

    public void setChild(boolean child) {
        this.getDataManager().set(IS_CHILD, child);
        if (this.world != null && !this.world.isRemote) {
            IAttributeInstance attributeInstance = this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
            attributeInstance.removeModifier(BABY_SPEED_BOOST);

            if (child)
                attributeInstance.applyModifier(BABY_SPEED_BOOST);
        }
        this.setChildSize(child);
    }

    @Override
    protected int getExperiencePoints(EntityPlayer player) {
        if (this.isChild())
            this.experienceValue = (int) ((float) this.experienceValue * 2.5F);
        return super.getExperiencePoints(player);
    }

    @Override
    public void notifyDataManagerChange(DataParameter<?> key) {
        if (IS_CHILD.equals(key))
            this.setChildSize(this.isChild());
        super.notifyDataManagerChange(key);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return super.attackEntityFrom(source, amount);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        boolean flag = super.attackEntityAsMob(entityIn);

        if (flag) {
            float f = this.world.getDifficultyForLocation(new BlockPos(this)).getAdditionalDifficulty();

            if (this.getHeldItemMainhand().isEmpty() && this.isBurning() && this.rand.nextFloat() < f * 0.3F)
                entityIn.setFire(2 * (int) f);
        }

        if (flag && this.getHeldItemMainhand().isEmpty() && entityIn instanceof EntityLivingBase) {
            float f = this.world.getDifficultyForLocation(new BlockPos(this)).getAdditionalDifficulty();
            ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.HUNGER, 140 * (int) f));
            ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 140 * (int) f));

            if (world.getDifficulty() == EnumDifficulty.HARD) {
                ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 140 * (int) f));
            }
        }

        return flag;
    }

    /**
     * TODO: Change Sounds...
     */
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ZOMBIE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_ZOMBIE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ZOMBIE_DEATH;
    }

    // TODO ^

    protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_ZOMBIE_STEP;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    protected ResourceLocation getLootTable() {
        return LootTableHandler.MUMMY;
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        super.setEquipmentBasedOnDifficulty(difficulty);

        if (this.rand.nextFloat() < (this.world.getDifficulty() == EnumDifficulty.HARD ? 0.05F : 0.01F)) {
            this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Item.getItemFromBlock(Blocks.SAND)));
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        if (this.isChild())
            compound.setBoolean("IsBaby", true);
        compound.setBoolean("CanBreakDoors", this.isBreakDoorsTaskSet());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        if (compound.getBoolean("IsBaby"))
            this.setChild(true);
        this.setBreakDoorAItask(compound.getBoolean("CanBreakDoors"));
    }

    @Override
    public void onKillEntity(EntityLivingBase entityLivingIn) {
        super.onKillEntity(entityLivingIn);

        if ((this.world.getDifficulty() == EnumDifficulty.NORMAL || this.world.getDifficulty() == EnumDifficulty.HARD) && entityLivingIn instanceof EntityVillager) {
            if (this.world.getDifficulty() != EnumDifficulty.HARD && this.rand.nextBoolean())
                return;

            EntityVillager entityVillager = (EntityVillager) entityLivingIn;
            EntityMummyVillager entityMummyVillager = new EntityMummyVillager(this.world);
            entityMummyVillager.copyLocationAndAnglesFrom(entityVillager);
            this.world.removeEntity(entityVillager);
            entityMummyVillager.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(entityMummyVillager)), null);
            entityMummyVillager.setProfession(entityVillager.getProfession());
            entityMummyVillager.setNoAI(entityVillager.isAIDisabled());

            if (entityVillager.hasCustomName()) {
                entityMummyVillager.setCustomNameTag(entityVillager.getCustomNameTag());
                entityMummyVillager.setAlwaysRenderNameTag(entityVillager.getAlwaysRenderNameTag());
            }

            this.world.spawnEntity(entityMummyVillager);
            this.world.playEvent(null, 1026, new BlockPos(this), 0);
        }
    }

    @Override
    public float getEyeHeight() {
        float f = 1.74F;
        if (this.isChild())
            f = (float) ((double) f - 0.81D);
        return f;
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && world.getBiome(new BlockPos(this)) == Biomes.DESERT || world.getBiome(new BlockPos(this)) == Biomes.DESERT_HILLS;
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        if (!getCanSpawnHere())
            despawnEntity();
        else {
            livingdata = super.onInitialSpawn(difficulty, livingdata);
            float f = difficulty.getClampedAdditionalDifficulty();
            this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * f);
            if (this.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty()) {
                Calendar calendar = this.world.getCurrentDate();
                if (calendar.get(Calendar.MONTH) + 1 == 10 && calendar.get(Calendar.DATE) == 31 && this.rand.nextFloat() < 0.25F) {
                    this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.LIT_PUMPKIN : Blocks.PUMPKIN));
                    this.inventoryArmorDropChances[EntityEquipmentSlot.HEAD.getIndex()] = 0.0F;
                }
            }
            if (livingdata == null) {
                livingdata = new EntityMummy.GroupData(this.world.rand.nextFloat() < mummyBabyChance);
            }

            if (livingdata instanceof EntityMummy.GroupData) {
                EntityMummy.GroupData entitymummy$groupdata = (EntityMummy.GroupData) livingdata;
                if (entitymummy$groupdata.isChild) {
                    this.setChild(true);
                }
            }
            this.setBreakDoorAItask(this.rand.nextFloat() < f * 0.1F);
            this.setEquipmentBasedOnDifficulty(difficulty);
            this.setEnchantmentBasedOnDifficulty(difficulty);

            this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).applyModifier(new AttributeModifier("Spawn Bonus", this.rand.nextDouble() * 0.05000000074505806D, 0));
            double d0 = this.rand.nextDouble() * 1.5D * (double) f;

            if (d0 > 1.0D)
                this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random mummy-spawn bonus", d0, 2));

            if (this.rand.nextFloat() < f * 0.0F && this.world.getDifficulty() == EnumDifficulty.HARD) {
                this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(new AttributeModifier("Leader mummy bonus", this.rand.nextDouble() * 3.0D + 1.0D, 2));
                this.setBreakDoorAItask(true);
            }
        }
        return livingdata;
    }

    public void setChildSize(boolean isChild) {
        this.multiplySize(isChild ? 0.5F : 1.0F);
    }

    @Override
    protected final void setSize(float width, float height) {
        boolean flag = this.mummyWidth > 0.0F && this.mummyHeight > 0.0F;
        this.mummyWidth = width;
        this.mummyHeight = height;

        if (!flag)
            this.multiplySize(1.0f);
    }

    protected final void multiplySize(float size) {
        super.setSize(this.mummyWidth * size, this.mummyHeight * size);
    }

    public double getYOffset() {
        return this.isChild() ? 0.0D : -0.45D;
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);

        if (cause.getTrueSource() instanceof EntityCreeper) {
            EntityCreeper entityCreeper = (EntityCreeper) cause.getTrueSource();

            if (entityCreeper.getPowered() && entityCreeper.ableToCauseSkullDrop()) {
                entityCreeper.incrementDroppedSkulls();
                ItemStack itemStack = this.getSkullDrop();

                if (!itemStack.isEmpty())
                    this.entityDropItem(itemStack, 0.0F);
            }
        }
    }

    protected ItemStack getSkullDrop() {
        return new ItemStack(Items.SKULL, 1, 2);
    }

    class GroupData implements IEntityLivingData {
        public boolean isChild;

        private GroupData(boolean isChild) {
            this.isChild = isChild;
        }
    }

}