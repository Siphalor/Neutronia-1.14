package team.hollow.neutronia.entity;/*
package team.team.hollow.neutronia.entity;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.datafixers.NbtOps;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.brain.*;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.AbstractTraderEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.scoreboard.AbstractScoreboardTeam;
import net.minecraft.scoreboard.ScoreboardTeam;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sortme.DebugRendererInfoManager;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.StringStringTextComponent;
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.TranslatableStringTextComponent;
import net.minecraft.util.GlobalPos;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BoundingBox;
import net.minecraft.util.math.MathHelper;
import net.minecraft.village.*;
import net.minecraft.world.IWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import team.team.hollow.neutronia.entity.ai.brain.NMemoryModuleType;
import team.team.hollow.neutronia.entity.ai.brain.task.VillagerTaskListProvider;
import team.team.hollow.neutronia.init.NTrackedDataHandlerRegistry;
import team.team.hollow.neutronia.utils.registry.NRegistries;
import team.team.hollow.neutronia.village.PointOfInterestType;
import team.team.hollow.neutronia.village.VillagerPlusData;
import team.team.hollow.neutronia.village.VillagerPlusProfession;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class VillagerPlusBase extends AbstractTraderEntity implements InteractionObserver {
    private static final TrackedData<VillagerPlusData> VILLAGER_PLUS_DATA;
    public static final Map<Item, Integer> ITEM_FOOD_VALUES;
    private static final Set<Item> GATHERABLE_ITEMS;
    public static TrackedData<String> genderUnified = DataTracker.registerData(VillagerPlusBase.class, TrackedDataHandlerRegistry.STRING);
    public static TrackedData<String> socialVillagerDataUnified = DataTracker.registerData(VillagerPlusBase.class, TrackedDataHandlerRegistry.STRING);
    public String firstName;
    public String lastName;
    public String gender;
    public VillagerPlusProfession profession;
    private static final ImmutableList<MemoryModuleType<?>> MEMORY_MODULES;
    private static final ImmutableList<SensorType<? extends Sensor<? super VillagerPlusBase>>> SENSORS;
    public static final Map<MemoryModuleType<GlobalPos>, BiPredicate<VillagerPlusBase, PointOfInterestType>> field_18851;
    private int levelUpTimer;
    private boolean levellingUp;
    private PlayerEntity lastCustomer;
    private UUID buddyGolemId;
    private long field_18532;
    private byte foodLevel;
    private final VillagerGossips gossip;
    private long gossipStartTime;
    private int experience;
    private long lastRestock;

    public VillagerPlusBase(EntityType<? extends AbstractTraderEntity> entityType_1, World world_1) {
        this(entityType_1, world_1, VillagerTypeRegistry.PLAINS);
    }

    public VillagerPlusBase(EntityType<? extends AbstractTraderEntity> type, World world, VillagerTypeRegistry villagerType) {
        super(type, world);
        this.field_18532 = Long.MIN_VALUE;
        this.gossip = new VillagerGossips();
        this.lastRestock = 0L;
        ((MobNavigation)this.getNavigation()).setCanPathThroughDoors(true);
        this.setCanPickUpLoot(true);
        setupGender();
        this.dataTracker.set(genderUnified, gender);
        this.dataTracker.set(socialVillagerDataUnified, this.getVillagerData().getSocialVillagerData().toString());

        List<String> sexs = new ArrayList<>();
        sexs.add(0, "Male");
        sexs.add(1, "Female");

        try {
            this.firstName = generateFirstName(sexs.get(getRand().nextInt(sexs.size())));
            this.lastName = generateLastName();
            this.setCustomName(new StringStringTextComponent(firstName + " " + lastName));

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setVillagerData(this.getVillagerData().withProfession(VillagerPlusProfession.NONE));
        this.brain = this.createBrain(new Dynamic<>(NbtOps.INSTANCE, new CompoundTag()));
    }


    public Brain<VillagerPlusBase> getBrain() {
        return (Brain<VillagerPlusBase>) super.getBrain();
    }

    protected Brain<?> createBrain(Dynamic<?> dynamic_1) {
        Brain<VillagerPlusBase> brain_1 = new Brain<>(MEMORY_MODULES, SENSORS, dynamic_1);
        this.initBrain(brain_1);
        return brain_1;
    }

    public void reinitializeBrain(ServerWorld serverWorld_1) {
        Brain<VillagerPlusBase> brain_1 = this.getBrain();
        brain_1.stopAllTasks(serverWorld_1, this);
        this.brain = brain_1.copy();
        this.initBrain(this.getBrain());
    }

    private void initBrain(Brain<VillagerPlusBase> brain_1) {
        VillagerPlusProfession villagerProfession_1 = this.getVillagerData().getSocialVillagerData();
        float float_1 = (float)this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).getValue();
        if (this.isChild()) {
            brain_1.setSchedule(Schedule.VILLAGER_BABY);
            brain_1.setTaskList(Activity.PLAY, VillagerTaskListProvider.method_19612());
        } else {
            brain_1.setSchedule(Schedule.VILLAGER_DEFAULT);
            brain_1.setTaskList(Activity.WORK, VillagerTaskListProvider.getWorkTasks(villagerProfession_1, float_1), ImmutableSet.of(Pair.of(MemoryModuleType.JOB_SITE, MemoryModuleState.VALUE_PRESENT)));
        }

        brain_1.setTaskList(Activity.CORE, VillagerTaskListProvider.getCoreTasks(villagerProfession_1, float_1));
        brain_1.setTaskList(Activity.MEET, VillagerTaskListProvider.getMeetTasks(villagerProfession_1, float_1), ImmutableSet.of(Pair.of(MemoryModuleType.MEETING_POINT, MemoryModuleState.VALUE_PRESENT)));
        brain_1.setTaskList(Activity.REST, VillagerTaskListProvider.getRestTasks(villagerProfession_1, float_1), ImmutableSet.of(Pair.of(MemoryModuleType.HOME, MemoryModuleState.VALUE_PRESENT)));
        brain_1.setTaskList(Activity.IDLE, VillagerTaskListProvider.getIdleTasks(villagerProfession_1, float_1));
        brain_1.setTaskList(Activity.PANIC, VillagerTaskListProvider.getPanicTasks(villagerProfession_1, float_1));
        brain_1.method_18890(ImmutableSet.of(Activity.CORE));
        brain_1.setActivity(Activity.IDLE);
        brain_1.doActivity(this.world.getTimeOfDay(), this.world.getTime());
    }

    protected void method_5619() {
        super.method_5619();
        if (this.world instanceof ServerWorld) {
            this.reinitializeBrain((ServerWorld)this.world);
        }

    }

    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
        this.getAttributeInstance(EntityAttributes.FOLLOW_RANGE).setBaseValue(48.0D);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setupGender() {
        Random random = new Random();
        String[] genderList = {"Male", "Female"};
        this.gender = genderList[random.nextInt(genderList.length)];
    }

    protected void mobTick() {
        this.world.getProfiler().push("brain");
        this.getBrain().method_19542((ServerWorld)this.world, this);
        this.world.getProfiler().pop();
        if (!this.hasCustomer() && this.levelUpTimer > 0) {
            --this.levelUpTimer;
            if (this.levelUpTimer <= 0) {
                if (this.levellingUp) {
                    this.levelUp();
                    this.levellingUp = false;
                }

                this.addPotionEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 0));
            }
        }

        if (this.lastCustomer != null && this.world instanceof ServerWorld) {
            ((ServerWorld)this.world).handleInteraction(EntityInteraction.TRADE, this.lastCustomer, this);
            this.lastCustomer = null;
        }

        super.mobTick();
    }

    public void resetCustomer() {
        this.setCurrentCustomer(null);
        this.clearCurrentBonus();
    }

    public boolean interactMob(PlayerEntity playerEntity_1, Hand hand_1) {
        ItemStack itemStack_1 = playerEntity_1.getStackInHand(hand_1);
        boolean boolean_1 = itemStack_1.getItem() == Items.NAME_TAG;
        if (boolean_1) {
            itemStack_1.interactWithEntity(playerEntity_1, this, hand_1);
            return true;
        } else if (itemStack_1.getItem() != Items.VILLAGER_SPAWN_EGG && this.isValid() && !this.hasCustomer() && !this.isChild()) {
            if (hand_1 == Hand.MAIN) {
                playerEntity_1.increaseStat(Stats.TALKED_TO_VILLAGER);
            }

            if (this.getRecipes().isEmpty()) {
                return super.interactMob(playerEntity_1, hand_1);
            } else {
                if (this.world instanceof ServerWorld && !this.recipes.isEmpty()) {
                    if (((ServerWorld)this.world).hasRaidAt(new BlockPos(this))) {
                        this.world.summonParticle(this, (byte)42);
                    } else {
                        this.beginTradeWith(playerEntity_1);
                    }
                }

                return true;
            }
        } else {
            return super.interactMob(playerEntity_1, hand_1);
        }
    }

    private void beginTradeWith(PlayerEntity playerEntity_1) {
        this.prepareRecipesFor(playerEntity_1);
        this.setCurrentCustomer(playerEntity_1);
        this.sendRecipes(playerEntity_1, this.getDisplayName(), this.getVillagerData().getLevel());
    }

    public void restock() {
        for (TraderRecipe traderRecipe_1 : this.getRecipes()) {
            traderRecipe_1.updatePriceOnDemand();
            traderRecipe_1.resetUses();
        }

        this.lastRestock = this.world.getTimeOfDay() % 24000L;
    }

    private void prepareRecipesFor(PlayerEntity playerEntity_1) {
        int int_1 = this.gossip.getReputationFor(playerEntity_1.getUuid(), (villageGossipType_1) -> villageGossipType_1 != VillageGossipType.GOLEM);
        if (int_1 != 0) {

            for (TraderRecipe traderRecipe_1 : this.getRecipes()) {
                traderRecipe_1.increaseTax(-MathHelper.floor((float) int_1 * traderRecipe_1.getPriceMultiplier()));
            }
        }

    }

    private void clearCurrentBonus() {
        for (TraderRecipe traderRecipe_1 : this.getRecipes()) {
            traderRecipe_1.clearTax();
        }
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(genderUnified, gender);
        this.dataTracker.startTracking(VILLAGER_PLUS_DATA, new VillagerPlusData(VillagerTypeRegistry.PLAINS, VillagerPlusProfession.NONE, 1, "Male"));
    }

    @Override
    public void writeCustomDataToTag(CompoundTag tag) {
        super.writeCustomDataToTag(tag);
        tag.putString("first_name", firstName);
        tag.putString("last_name", lastName);
        tag.putString("gender", gender);
        tag.putString("profession", profession.toString());
        tag.put("SocialVillagerData", this.getVillagerData().serialize(NbtOps.INSTANCE));
        tag.putByte("FoodLevel", this.foodLevel);
        tag.put("Gossips", this.gossip.serialize(NbtOps.INSTANCE).getValue());
        tag.putInt("Xp", this.experience);
        tag.putLong("LastRestock", this.lastRestock);
        if (this.buddyGolemId != null) {
            tag.putUuid("BuddyGolem", this.buddyGolemId);
        }
    }

    @Override
    public void readCustomDataFromTag(CompoundTag tag) {
        super.readCustomDataFromTag(tag);
        this.firstName = tag.getString("first_name");
        this.lastName = tag.getString("last_name");
        this.gender = tag.getString("gender");
        if (tag.containsKey("SocialVillagerData", 10)) {
            this.setVillagerData(new VillagerPlusData(new Dynamic<>(NbtOps.INSTANCE, tag.getTag("SocialVillagerData"))));
        }

        if (tag.containsKey("Offers", 10)) {
            this.recipes = new TraderRecipeList(tag.getCompound("Offers"));
        }

        if (tag.containsKey("FoodLevel", 1)) {
            this.foodLevel = tag.getByte("FoodLevel");
        }

        ListTag listTag_1 = tag.getList("Gossips", 10);
        this.gossip.deserialize(new Dynamic<>(NbtOps.INSTANCE, listTag_1));
        if (tag.containsKey("Xp", 3)) {
            this.experience = tag.getInt("Xp");
        } else {
            int int_1 = this.getVillagerData().getLevel();
            if (SocialVillagerData.isLevelValid(int_1)) {
                this.experience = SocialVillagerData.getLowerLevelExperience(int_1);
            }
        }

        this.lastRestock = tag.getLong("LastRestock");
        if (tag.hasUuid("BuddyGolem")) {
            this.buddyGolemId = tag.getUuid("BuddyGolem");
        }

        this.setCanPickUpLoot(true);
        this.dataTracker.set(genderUnified, gender);
    }

    public boolean canImmediatelyDespawn(double double_1) {
        return false;
    }

    protected SoundEvent getAmbientSound() {
        if (this.isSleeping()) {
            return null;
        } else {
            return this.hasCustomer() ? SoundEvents.ENTITY_VILLAGER_TRADE : SoundEvents.ENTITY_VILLAGER_AMBIENT;
        }
    }

    protected SoundEvent getHurtSound(DamageSource damageSource_1) {
        return SoundEvents.ENTITY_VILLAGER_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_VILLAGER_DEATH;
    }

    public void playWorkSound() {
        SoundEvent soundEvent_1 = this.getVillagerData().getSocialVillagerData().getWorkStation().getSound();
        if (soundEvent_1 != null) {
            this.playSound(soundEvent_1, this.getSoundVolume(), this.getSoundPitch());
        }

    }

    public void setVillagerData(VillagerPlusData villagerData_1) {
        VillagerPlusData villagerData_2 = this.getVillagerData();
        if (villagerData_2.getSocialVillagerData() != villagerData_1.getSocialVillagerData()) {
            this.recipes = null;
        }

        this.dataTracker.set(VILLAGER_PLUS_DATA, villagerData_1);
    }

    public VillagerPlusData getVillagerData() {
        return this.dataTracker.get(VILLAGER_PLUS_DATA);
    }

    protected void afterUsing(TraderRecipe traderRecipe_1) {
        int int_1 = 3 + this.random.nextInt(4);
        this.experience += traderRecipe_1.getRewardedExp();
        this.lastCustomer = this.getCurrentCustomer();
        if (this.canLevelUp()) {
            this.levelUpTimer = 40;
            this.levellingUp = true;
            int_1 += 5;
        }

        if (traderRecipe_1.shouldRewardExp()) {
            this.world.spawnEntity(new ExperienceOrbEntity(this.world, this.x, this.y + 0.5D, this.z, int_1));
        }

    }

    public void setAttacker(LivingEntity livingEntity_1) {
        if (livingEntity_1 != null && this.world instanceof ServerWorld) {
            ((ServerWorld)this.world).handleInteraction(EntityInteraction.VILLAGER_HURT, livingEntity_1, this);
        }

        super.setAttacker(livingEntity_1);
    }

    public void onDeath(DamageSource damageSource_1) {
        this.releaseTicketFor(MemoryModuleType.HOME);
        this.releaseTicketFor(MemoryModuleType.JOB_SITE);
        this.releaseTicketFor(MemoryModuleType.MEETING_POINT);
        super.onDeath(damageSource_1);
    }

    public void releaseTicketFor(MemoryModuleType<GlobalPos> memoryModuleType_1) {
        if (this.world instanceof ServerWorld) {
            MinecraftServer minecraftServer_1 = ((ServerWorld)this.world).getServer();
            this.brain.getMemory(memoryModuleType_1).ifPresent((globalPos_1) -> {
                ServerWorld serverWorld_1 = minecraftServer_1.getWorld(globalPos_1.getDimension());
                PointOfInterestStorage pointOfInterestStorage_1 = serverWorld_1.getPointOfInterestStorage();
                Optional<net.minecraft.village.PointOfInterestType> optional_1 = pointOfInterestStorage_1.getType(globalPos_1.getPos());
                BiPredicate<VillagerPlusBase, net.minecraft.village.PointOfInterestType> biPredicate_1 = (BiPredicate)field_18851.get(memoryModuleType_1);
                if (optional_1.isPresent() && biPredicate_1.test(this, optional_1.get())) {
                    pointOfInterestStorage_1.releaseTicket(globalPos_1.getPos());
                    DebugRendererInfoManager.method_19778(serverWorld_1, globalPos_1.getPos());
                }

            });
        }
    }

    public boolean isReadyToBreed() {
        return this.foodLevel + this.getAvailableFood() >= 12 && this.getBreedingAge() == 0;
    }

    public void consumeAvailableFood() {
        if (this.foodLevel < 12 && this.getAvailableFood() != 0) {
            Set<Item> set_1 = ITEM_FOOD_VALUES.keySet();

            for(int int_1 = 0; int_1 < this.getInventory().getInvSize(); ++int_1) {
                ItemStack itemStack_1 = this.getInventory().getInvStack(int_1);
                Item item_1 = itemStack_1.getItem();
                if (!itemStack_1.isEmpty() || set_1.contains(item_1)) {
                    int int_2 = itemStack_1.getAmount();

                    for(int int_3 = int_2; int_3 > 0; --int_3) {
                        this.foodLevel = (byte)(this.foodLevel + ITEM_FOOD_VALUES.get(item_1));
                        this.getInventory().takeInvStack(int_1, 1);
                        if (this.foodLevel >= 12) {
                            return;
                        }
                    }
                }
            }

        }
    }

    public void depleteFood(int int_1) {
        this.foodLevel = (byte)(this.foodLevel - int_1);
    }

    public void setRecipes(TraderRecipeList traderRecipeList_1) {
        this.recipes = traderRecipeList_1;
    }

    private boolean canLevelUp() {
        int int_1 = this.getVillagerData().getLevel();
        return SocialVillagerData.isLevelValid(int_1) && this.experience >= SocialVillagerData.getUpperLevelExperience(int_1);
    }

    private void levelUp() {
        this.setVillagerData(this.getVillagerData().withLevel(this.getVillagerData().getLevel() + 1));
        this.fillRecipes();
    }

    public StringTextComponent getDisplayName() {
        AbstractScoreboardTeam abstractScoreboardTeam_1 = this.getScoreboardTeam();
        StringTextComponent textComponent_1 = this.getCustomName();
        if (textComponent_1 != null) {
            return ScoreboardTeam.modifyText(abstractScoreboardTeam_1, textComponent_1).modifyStyle((style_1) -> {
                style_1.setHoverEvent(this.getComponentHoverEvent()).setInsertion(this.getUuidAsString());
            });
        } else {
            VillagerPlusProfession villagerProfession_1 = this.getVillagerData().getSocialVillagerData();
            StringTextComponent textComponent_2 = (new TranslatableStringTextComponent(this.getType().getTranslationKey() + '.' + NRegistries.VILLAGER_PROFESSION.getId(villagerProfession_1).getPath())).modifyStyle((style_1) -> {
                style_1.setHoverEvent(this.getComponentHoverEvent()).setInsertion(this.getUuidAsString());
            });
            if (abstractScoreboardTeam_1 != null) {
                textComponent_2.applyFormat(abstractScoreboardTeam_1.getColor());
            }

            return textComponent_2;
        }
    }

    @Environment(EnvType.CLIENT)
    public void method_5711(byte byte_1) {
        if (byte_1 == 12) {
            this.method_18007(ParticleTypes.HEART);
        } else if (byte_1 == 13) {
            this.method_18007(ParticleTypes.ANGRY_VILLAGER);
        } else if (byte_1 == 14) {
            this.method_18007(ParticleTypes.HAPPY_VILLAGER);
        } else if (byte_1 == 42) {
            this.method_18007(ParticleTypes.SPLASH);
        } else {
            super.method_5711(byte_1);
        }

    }

    public EntityData prepareEntityData(IWorld iWorld_1, LocalDifficulty localDifficulty_1, SpawnType spawnType_1,EntityData entityData_1, CompoundTag compoundTag_1) {
        if (spawnType_1 == SpawnType.BREEDING) {
            this.setVillagerData(this.getVillagerData().withProfession(VillagerPlusProfession.NONE));
        }

        if (spawnType_1 == SpawnType.COMMAND || spawnType_1 == SpawnType.SPAWN_EGG || spawnType_1 == SpawnType.SPAWNER) {
            this.setVillagerData(this.getVillagerData().withType(VillagerTypeRegistry.forBiome(iWorld_1.getBiome(new BlockPos(this)))));
        }

        if(spawnType_1 == SpawnType.NATURAL) {
            this.setVillagerData(this.getVillagerData().withType(VillagerTypeRegistry.forBiome(iWorld_1.getBiome(new BlockPos(this)))));
        }

        return super.prepareEntityData(iWorld_1, localDifficulty_1, spawnType_1, entityData_1, compoundTag_1);
    }

    public VillagerPlusBase createChild(PassiveEntity passiveEntity_1) {
        double double_1 = this.random.nextDouble();
        VillagerTypeRegistry villagerType_3;
        if (double_1 < 0.5D) {
            villagerType_3 = VillagerTypeRegistry.forBiome(this.world.getBiome(new BlockPos(this)));
        } else if (double_1 < 0.75D) {
            villagerType_3 = this.getVillagerData().getType();
        } else {
            villagerType_3 = ((VillagerPlusBase)passiveEntity_1).getVillagerData().getType();
        }

        VillagerPlusBase villagerEntity_1 = new VillagerPlusBase(EntityType.VILLAGER, this.world, villagerType_3);
        villagerEntity_1.prepareEntityData(this.world, this.world.getLocalDifficulty(new BlockPos(villagerEntity_1)), SpawnType.BREEDING, null, null);
        return villagerEntity_1;
    }

    public void onStruckByLightning(LightningEntity lightningEntity_1) {
        WitchEntity witchEntity_1 = EntityType.WITCH.create(this.world);
        witchEntity_1.setPositionAndAngles(this.x, this.y, this.z, this.yaw, this.pitch);
        witchEntity_1.prepareEntityData(this.world, this.world.getLocalDifficulty(new BlockPos(witchEntity_1)), SpawnType.CONVERSION, null, null);
        witchEntity_1.setAiDisabled(this.isAiDisabled());
        if (this.hasCustomName()) {
            witchEntity_1.setCustomName(this.getCustomName());
            witchEntity_1.setCustomNameVisible(this.isCustomNameVisible());
        }

        this.world.spawnEntity(witchEntity_1);
        this.invalidate();
    }

    protected void pickupItem(ItemEntity itemEntity_1) {
        ItemStack itemStack_1 = itemEntity_1.getStack();
        Item item_1 = itemStack_1.getItem();
        VillagerPlusProfession villagerProfession_1 = this.getVillagerData().getSocialVillagerData();
        if (GATHERABLE_ITEMS.contains(item_1) || villagerProfession_1.getGatherableItems().contains(item_1)) {
            if (villagerProfession_1 == VillagerPlusProfession.FARMER && item_1 == Items.WHEAT) {
                int int_1 = itemStack_1.getAmount() / 3;
                if (int_1 > 0) {
                    ItemStack itemStack_2 = this.getInventory().add(new ItemStack(Items.BREAD, int_1));
                    itemStack_1.subtractAmount(int_1 * 3);
                    if (!itemStack_2.isEmpty()) {
                        this.dropStack(itemStack_2, 0.5F);
                    }
                }
            }

            ItemStack itemStack_3 = this.getInventory().add(itemStack_1);
            if (itemStack_3.isEmpty()) {
                itemEntity_1.invalidate();
            } else {
                itemStack_1.setAmount(itemStack_3.getAmount());
            }
        }

    }

    public boolean wantsToStartBreeding() {
        return this.getAvailableFood() >= 24;
    }

    public boolean canBreed() {
        boolean boolean_1 = this.getVillagerData().getSocialVillagerData() == VillagerPlusProfession.FARMER;
        int int_1 = this.getAvailableFood();
        return boolean_1 ? int_1 < 60 : int_1 < 12;
    }

    private int getAvailableFood() {
        BasicInventory basicInventory_1 = this.getInventory();
        return ITEM_FOOD_VALUES.entrySet().stream().mapToInt((map$Entry_1) -> basicInventory_1.getInvAmountOf(map$Entry_1.getKey()) * map$Entry_1.getValue()).sum();
    }

    public boolean method_19623() {
        BasicInventory basicInventory_1 = this.getInventory();
        return basicInventory_1.method_18862(ImmutableSet.of(Items.WHEAT_SEEDS, Items.POTATO, Items.CARROT, Items.BEETROOT_SEEDS));
    }

    protected void fillRecipes() {
        VillagerPlusData villagerData_1 = this.getVillagerData();
        Int2ObjectMap<Trades.Factory[]> int2ObjectMap_1 = Trades.PROFESSION_TO_LEVELED_TRADE.get(villagerData_1.getSocialVillagerData());
        if (int2ObjectMap_1 != null && !int2ObjectMap_1.isEmpty()) {
            Trades.Factory[] trades$Factorys_1 = int2ObjectMap_1.get(villagerData_1.getLevel());
            if (trades$Factorys_1 != null) {
                TraderRecipeList traderRecipeList_1 = this.getRecipes();
                this.fillRecipesFromPool(traderRecipeList_1, trades$Factorys_1, 2);
            }
        }
    }

    public void talkWithVillager(VillagerPlusBase villagerEntity_1, long long_1) {
        if ((long_1 < this.gossipStartTime || long_1 >= this.gossipStartTime + 1200L) && (long_1 < villagerEntity_1.gossipStartTime || long_1 >= villagerEntity_1.gossipStartTime + 1200L)) {
            boolean boolean_1 = this.isLackingBuddyGolem(long_1);
            if (this.wantsGolem(this) || boolean_1) {
                this.gossip.startGossip(this.getUuid(), VillageGossipType.GOLEM, VillageGossipType.GOLEM.maxReputation);
            }

            this.gossip.shareGossipFrom(villagerEntity_1.gossip, this.random, 10);
            this.gossipStartTime = long_1;
            villagerEntity_1.gossipStartTime = long_1;
            if (boolean_1) {
                this.trySpawnGolem();
            }

        }
    }

    private void trySpawnGolem() {
        VillagerPlusData villagerData_1 = this.getVillagerData();
        if (villagerData_1.getSocialVillagerData() != VillagerPlusProfession.NONE && villagerData_1.getSocialVillagerData() != VillagerPlusProfession.NITWIT) {
            Optional<VillagerPlusBase.GolemSpawnCondition> optional_1 = this.getBrain().getMemory(NMemoryModuleType.GOLEM_SPAWN_CONDITIONS);
            if (optional_1.isPresent()) {
                if (optional_1.get().canSpawn(this.world.getTime())) {
                    LivingEntity livingEntity_1 = this.getAttacker();
                    boolean boolean_1 = livingEntity_1 instanceof ZombieEntity && this.age - this.getLastAttackedTime() <= 1200;
                    boolean boolean_2 = this.gossip.getGossipCount(VillageGossipType.GOLEM, (double_1) -> double_1 > 30.0D) >= 5L;
                    if (boolean_1 || boolean_2) {
                        BoundingBox boundingBox_1 = this.getBoundingBox().stretch(80.0D, 80.0D, 80.0D);
                        List<VillagerPlusBase> list_1 = this.world.getEntities(VillagerPlusBase.class, boundingBox_1, this::wantsGolem).stream().limit(5L).collect(Collectors.toList());
                        boolean boolean_3 = list_1.size() >= 5;
                        if (boolean_1 || boolean_3) {
                            IronGolemEntity ironGolemEntity_1 = this.spawnIronGolem();
                            if (ironGolemEntity_1 != null) {
                                UUID uUID_1 = ironGolemEntity_1.getUuid();

                                VillagerPlusBase villagerEntity_1;
                                for(Iterator var11 = list_1.iterator(); var11.hasNext(); villagerEntity_1.buddyGolemId = uUID_1) {
                                    villagerEntity_1 = (VillagerPlusBase)var11.next();
                                    for (VillagerPlusBase villagerEntity_2 : list_1) {
                                        villagerEntity_1.gossip.startGossip(villagerEntity_2.getUuid(), VillageGossipType.GOLEM, -VillageGossipType.GOLEM.maxReputation);
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
    }

    private boolean wantsGolem(Entity entity_1) {
        return this.gossip.getReputationFor(entity_1.getUuid(), (villageGossipType_1) -> villageGossipType_1 == VillageGossipType.GOLEM) > 30;
    }

    private boolean isLackingBuddyGolem(long long_1) {
        if (this.buddyGolemId == null) {
            return true;
        } else {
            if (this.field_18532 < long_1 + 1200L) {
                this.field_18532 = long_1 + 1200L;
                Entity entity_1 = ((ServerWorld)this.world).getEntity(this.buddyGolemId);
                if (entity_1 == null || !entity_1.isValid() || this.squaredDistanceTo(entity_1) > 80.0D) {
                    this.buddyGolemId = null;
                    return true;
                }
            }

            return false;
        }
    }

    private IronGolemEntity spawnIronGolem() {
        BlockPos blockPos_1 = new BlockPos(this);

        for(int int_1 = 0; int_1 < 10; ++int_1) {
            BlockPos blockPos_2 = blockPos_1.add(this.world.random.nextInt(16) - 8, this.world.random.nextInt(6) - 3, this.world.random.nextInt(16) - 8);
            IronGolemEntity ironGolemEntity_1 = EntityType.IRON_GOLEM.create(this.world, null, null, null, blockPos_2, SpawnType.MOB_SUMMONED, false, false);
            if (ironGolemEntity_1 != null) {
                if (ironGolemEntity_1.canSpawn(this.world, SpawnType.MOB_SUMMONED) && ironGolemEntity_1.method_5957(this.world)) {
                    this.world.spawnEntity(ironGolemEntity_1);
                    return ironGolemEntity_1;
                }

                ironGolemEntity_1.invalidate();
            }
        }

        return null;
    }

    public void onInteractionWith(EntityInteraction entityInteraction_1, Entity entity_1) {
        if (entityInteraction_1 == EntityInteraction.ZOMBIE_VILLAGER_CURED) {
            this.gossip.startGossip(entity_1.getUuid(), VillageGossipType.MAJOR_POSITIVE, 25);
        } else if (entityInteraction_1 == EntityInteraction.TRADE) {
            this.gossip.startGossip(entity_1.getUuid(), VillageGossipType.TRADING, 2);
        } else if (entityInteraction_1 == EntityInteraction.VILLAGER_HURT) {
            this.gossip.startGossip(entity_1.getUuid(), VillageGossipType.MINOR_NEGATIVE, 25);
        } else if (entityInteraction_1 == EntityInteraction.VILLAGER_KILLED) {
            this.gossip.startGossip(entity_1.getUuid(), VillageGossipType.MAJOR_NEGATIVE, 25);
        }

    }

    public int getExperience() {
        return this.experience;
    }

    public void method_19625(int int_1) {
        this.experience = int_1;
    }

    public long getLastRestock() {
        return this.lastRestock;
    }

    protected void method_18409() {
        super.method_18409();
        DebugRendererInfoManager.method_19774(this);
    }

    public void sleep(BlockPos blockPos_1) {
        super.sleep(blockPos_1);
        VillagerPlusBase.GolemSpawnCondition villagerEntity$GolemSpawnCondition_1 = this.getBrain().getMemory(NMemoryModuleType.GOLEM_SPAWN_CONDITIONS).orElseGet(VillagerPlusBase.GolemSpawnCondition::new);
        villagerEntity$GolemSpawnCondition_1.setLastSlept(this.world.getTime());
        this.brain.putMemory(NMemoryModuleType.GOLEM_SPAWN_CONDITIONS, villagerEntity$GolemSpawnCondition_1);
    }

    private String generateFirstName(String gender) throws IOException {
        String firstNameOut;
        Random rand = new Random();
        Identifier male = new Identifier("neutronia:names/male.txt");
        Identifier female = new Identifier("neutronia:names/female.txt");
        InputStream stream = MinecraftClient.getInstance().getResourceManager().getResource(male).getInputStream();
        InputStream stream2 = MinecraftClient.getInstance().getResourceManager().getResource(female).getInputStream();
        if (gender.equals("Male")) {
            Scanner scanner = new Scanner(stream);
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
                builder.append(",");
            }
            String[] strings = builder.toString().split(",");
            firstNameOut = strings[rand.nextInt(strings.length)];
            scanner.close();
        } else {
            Scanner scanner2 = new Scanner(stream2);
            StringBuilder builder2 = new StringBuilder();
            while (scanner2.hasNextLine()) {
                builder2.append(scanner2.nextLine());
                builder2.append(",");
            }
            String[] strings2 = builder2.toString().split(",");
            firstNameOut = strings2[rand.nextInt(strings2.length)];
            scanner2.close();
        }
        stream.close();
        stream2.close();
        return firstNameOut;
    }

    private String generateLastName() throws IOException {
        String lastNameOut;
        Random rand = new Random();
        Identifier surnames = new Identifier("neutronia:names/surnames.txt");
        InputStream stream = MinecraftClient.getInstance().getResourceManager().getResource(surnames).getInputStream();
        Scanner scanner = new Scanner(stream);
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine());
            builder.append(",");
        }
        String[] strings = builder.toString().split(",");
        lastNameOut = strings[rand.nextInt(strings.length)];
        stream.close();
        scanner.close();
        return lastNameOut;
    }

    static {
        VILLAGER_PLUS_DATA = DataTracker.registerData(VillagerPlusBase.class, NTrackedDataHandlerRegistry.VILLAGER_DATA);
        ITEM_FOOD_VALUES = ImmutableMap.of(Items.BREAD, 4, Items.POTATO, 1, Items.CARROT, 1, Items.BEETROOT, 1);
        GATHERABLE_ITEMS = ImmutableSet.of(Items.BREAD, Items.POTATO, Items.CARROT, Items.WHEAT, Items.WHEAT_SEEDS, Items.BEETROOT, Items.BEETROOT_SEEDS);
        MEMORY_MODULES = ImmutableList.of(MemoryModuleType.HOME, MemoryModuleType.JOB_SITE, MemoryModuleType.MEETING_POINT, MemoryModuleType.MOBS, MemoryModuleType.VISIBLE_MOBS, MemoryModuleType.NEAREST_PLAYERS, MemoryModuleType.NEAREST_VISIBLE_PLAYER, MemoryModuleType.WALK_TARGET, MemoryModuleType.LOOK_TARGET, MemoryModuleType.INTERACTION_TARGET, MemoryModuleType.BREED_TARGET, MemoryModuleType.PATH, MemoryModuleType.INTERACTABLE_DOORS, MemoryModuleType.HURT_BY, MemoryModuleType.HURT_BY_ENTITY, MemoryModuleType.NEAREST_HOSTILE, MemoryModuleType.SECONDARY_JOB_SITE, MemoryModuleType.GOLEM_SPAWN_CONDITIONS);
        SENSORS = (ImmutableList<SensorType<? extends Sensor<? super VillagerPlusBase>>>) ImmutableList.of(SensorType.NEAREST_LIVING_ENTITIES, SensorType.NEAREST_PLAYERS, SensorType.INTERACTABLE_DOORS, SensorType.HURT_BY, SensorType.VILLAGER_HOSTILES, SensorType.SECONDARY_POIS);
        field_18851 = ImmutableMap.of(MemoryModuleType.HOME, (villagerEntity_1, pointOfInterestType_1) -> pointOfInterestType_1 == PointOfInterestType.HOME,
                MemoryModuleType.JOB_SITE, (villagerEntity_1, pointOfInterestType_1) -> villagerEntity_1.getVillagerData().getSocialVillagerData().getWorkStation() == pointOfInterestType_1,
                MemoryModuleType.MEETING_POINT, (villagerEntity_1, pointOfInterestType_1) -> pointOfInterestType_1 == PointOfInterestType.MEETING);
    }

    public static final class GolemSpawnCondition {
        private long lastWorked;
        private long lastSlept;

        public void setLastWorked(long long_1) {
            this.lastWorked = long_1;
        }

        public void setLastSlept(long long_1) {
            this.lastSlept = long_1;
        }

        private boolean canSpawn(long long_1) {
            return long_1 - this.lastSlept < 24000L && long_1 - this.lastWorked < 36000L;
        }
    }

}*/
