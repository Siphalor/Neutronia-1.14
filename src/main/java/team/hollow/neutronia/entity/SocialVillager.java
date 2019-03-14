package team.hollow.neutronia.entity;

import net.minecraft.class_1358;
import net.minecraft.class_1370;
import net.minecraft.class_1394;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.pathing.EntityMobNavigation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.StringTextComponent;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import team.hollow.neutronia.client.gui.SocialScreen;
import team.hollow.neutronia.init.NEntityTypes;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class SocialVillager extends PassiveEntity {
    public static TrackedData<String> hairColorUnified = DataTracker.registerData(SocialVillager.class, TrackedDataHandlerRegistry.STRING);
    public static TrackedData<String> eyeColorUnified = DataTracker.registerData(SocialVillager.class, TrackedDataHandlerRegistry.STRING);
    public static TrackedData<String> skinColorUnified = DataTracker.registerData(SocialVillager.class, TrackedDataHandlerRegistry.STRING);
    public static TrackedData<Integer> hairStyleUnified = DataTracker.registerData(SocialVillager.class, TrackedDataHandlerRegistry.INTEGER);
    public static TrackedData<String> orientationUnified = DataTracker.registerData(SocialVillager.class, TrackedDataHandlerRegistry.STRING);
    public static TrackedData<String> serverUUID = DataTracker.registerData(SocialVillager.class, TrackedDataHandlerRegistry.STRING);
    public static TrackedData<String> sexUnified = DataTracker.registerData(SocialVillager.class, TrackedDataHandlerRegistry.STRING);
    public String firstName;
    public String lastName;
    private HashMap<UUID, Integer> opinions = new HashMap<>();
    private String hairColor;
    private String eyeColor;
    private String skinColor;
    private String sexuality;
    private int hairStyle = 0;
    private int friendliness = 0;
    private int bravery = 0;
    private int generosity = 0;
    private boolean apologized = false;
    private boolean charmed = false;
    private String sex;

    public SocialVillager(World world) {
        this(NEntityTypes.SOCIAL_VILLAGER, world);
    }

    public SocialVillager(EntityType<? extends net.minecraft.entity.passive.PassiveEntity> type, World world) {
        super(type, world);
        ((EntityMobNavigation) this.getNavigation()).setCanPathThroughDoors(true);
        this.setCanPickUpLoot(true);
        Random r = new Random();
        if (hairColor == null || hairColor.equals("")) {

            unifiedSetup();
            this.dataTracker.set(hairColorUnified, hairColor);
            this.dataTracker.set(eyeColorUnified, eyeColor);
            this.dataTracker.set(skinColorUnified, skinColor);
            this.dataTracker.set(hairStyleUnified, hairStyle);
            this.dataTracker.set(orientationUnified, sexuality);
            this.dataTracker.set(serverUUID, this.getUuidAsString());
            this.setBreedingAge(0);

            this.dataTracker.set(sexUnified, sex);
        }

        List<String> sexs = new ArrayList<>();
        sexs.add(0, "Male");
        sexs.add(1, "Female");
        sexs.add(2, "Genderless");

        try {
            this.firstName = generateFirstName(sexs.get(r.nextInt(3)));
            this.lastName = generateLastName();
            this.setCustomName(new StringTextComponent(firstName + " " + lastName));

        } catch (IOException e) {
            e.printStackTrace();
        }
        ((EntityMobNavigation)this.getNavigation()).setCanPathThroughDoors(true);
        this.setCanPickUpLoot(true);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, ZombieEntity.class, 8.0F, 0.6D, 0.6D));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, EvokerEntity.class, 12.0F, 0.8D, 0.8D));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, VindicatorEntity.class, 8.0F, 0.8D, 0.8D));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, VexEntity.class, 8.0F, 0.6D, 0.6D));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, PillagerEntity.class, 15.0F, 0.6D, 0.6D));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, IllusionerEntity.class, 12.0F, 0.6D, 0.6D));
        this.goalSelector.add(5, new class_1370(this, 0.6D));
        this.goalSelector.add(9, new class_1358(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.add(9, new class_1394(this, 0.6D));
        this.goalSelector.add(10, new LookAtEntityGoal(this, MobEntity.class, 8.0F));
    }

    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(hairColorUnified, hairColor);
        this.dataTracker.startTracking(eyeColorUnified, eyeColor);
        this.dataTracker.startTracking(skinColorUnified, skinColor);
        this.dataTracker.startTracking(hairStyleUnified, hairStyle);
        this.dataTracker.startTracking(orientationUnified, sexuality);
        this.dataTracker.startTracking(serverUUID, this.getUuidAsString());
        this.dataTracker.startTracking(sexUnified, sex);
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setOpinion(UUID uuid, int newValue) {
        this.opinions.put(uuid, newValue);
    }

    public int getOpinion(UUID uuid) {
        return opinions.get(uuid);
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public boolean getApologized() {
        return apologized;
    }

    public void setApologized() {
        this.apologized = true;
    }

    public void formOpinion(Entity person) {
        Random rand = new Random();
        if (!opinions.containsKey(person.getUuid())) {
            opinions.put(person.getUuid(), rand.nextInt(50) - 25);
        }
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public boolean interactMob(PlayerEntity player, Hand hand) {
        if (!opinions.containsKey(player.getUuid())) {
            formOpinion(player);
        }
        MinecraftClient.getInstance().openScreen(new SocialScreen(this, player));
        return true;

    }

    public String getSkinColor() {
        return skinColor;
    }

    public int getHairStyle() {
        return hairStyle;
    }

    public void setupHair() {
        Random rand = new Random();
        String[] hairList = {"Red", "Brown", "Black", "Blonde"};
        int[] styleList = {1, 2, 3, 4};
        this.hairStyle = styleList[rand.nextInt(styleList.length)];
        this.hairColor = hairList[rand.nextInt(hairList.length)];
    }

    public void setupEyes() {
        Random rand = new Random();
        String[] eyeList = {"Black", "Blue", "Brown", "Green", "Lime", "Pink", "Yellow"};
        this.eyeColor = eyeList[rand.nextInt(eyeList.length)];
    }

    public void setupSkin() {
        Random rand = new Random();
        String[] skinList = {"Light", "Medium", "Dark"};
        this.skinColor = skinList[rand.nextInt(skinList.length)];
    }

    public void setupGender() {
        Random random = new Random();
        String[] genderList = {"Male", "Female", "Genderless"};
        this.sex = genderList[random.nextInt(genderList.length)];
    }

    public void setupOrientation() {
        Random rand = new Random();
        int orientationInt = rand.nextInt(10);
        if (orientationInt == 9) {
            boolean orientationBool = rand.nextBoolean();
            if (orientationBool) {
                this.sexuality = "Bisexual";
            } else {
                this.sexuality = "Gay";
            }
        } else {
            this.sexuality = "Straight";
        }
    }

    @Override
    public void writeCustomDataToTag(CompoundTag tag) {
        super.writeCustomDataToTag(tag);
        tag.putString("Hair Color", hairColor);
        tag.putString("Eye Color", eyeColor);
        tag.putString("Skin Color", skinColor);
        tag.putString("Sexuality", sexuality);
        tag.putInt("Hair Style", hairStyle);
        tag.putInt("Friendliness", friendliness);
        tag.putInt("Bravery", bravery);
        tag.putInt("Generosity", generosity);
        tag.putBoolean("Apologized", apologized);
        tag.putBoolean("Charmed", charmed);
        tag.putString("First Name", firstName);
        tag.putString("Last Name", lastName);
        tag.putInt("Age", this.getBreedingAge());
        tag.putString("Gender", sex);
        if (opinions.keySet().size() > 13) {
            for (UUID key : opinions.keySet()) {
                CompoundTag opinionTag = new CompoundTag();
                opinionTag.putUuid("Holder", key);
                opinionTag.putInt("Opinion", opinions.get(key));
                tag.put(key.toString(), opinionTag);
            }
        }

    }

    public void unifiedSetup() {
        this.setupEyes();
        this.setupHair();
        this.setupSkin();
        this.setupGender();
        this.setupOrientation();
    }

    @Override
    public void readCustomDataFromTag(CompoundTag tag) {
        super.readCustomDataFromTag(tag);
        this.hairColor = tag.getString("Hair Color");
        this.eyeColor = tag.getString("Eye Color");
        this.skinColor = tag.getString("Skin Color");
        this.sexuality = tag.getString("Sexuality");
        this.hairStyle = tag.getInt("Hair Style");
        this.friendliness = tag.getInt("Friendliness");
        this.bravery = tag.getInt("Bravery");
        this.generosity = tag.getInt("Generosity");
        this.apologized = tag.getBoolean("Apologized");
        this.charmed = tag.getBoolean("Charmed");
        this.firstName = tag.getString("First Name");
        this.lastName = tag.getString("Last Name");
        this.sex = tag.getString("Gender");
        for (String key : tag.getKeys()) {
            if (tag.hasUuid(key)) {
                this.opinions.put(tag.getCompound(key).getUuid("Holder"), tag.getInt("Opinion"));
            }
        }
        if (hairColor == null || hairColor == "") {
            unifiedSetup();
        }
        this.setBreedingAge(tag.getInt("Age"));
        this.dataTracker.set(hairColorUnified, hairColor);
        this.dataTracker.set(eyeColorUnified, eyeColor);
        this.dataTracker.set(skinColorUnified, skinColor);
        this.dataTracker.set(hairStyleUnified, hairStyle);
        this.dataTracker.set(orientationUnified, sexuality);
        this.dataTracker.set(serverUUID, this.getUuidAsString());
        this.dataTracker.set(sexUnified, sex);
    }

    public int getFriendliness() {
        return this.friendliness;
    }

    public void setCharmed() {
        this.charmed = true;
    }

    public boolean getCharmed() {
        return this.charmed;
    }

    @Override
    public PassiveEntity createChild(PassiveEntity var1) {
        // TODO Auto-generated method stub
        return null;
    }

    private String generateFirstName(String gender) throws IOException {
        String firstNameOut;
        Random rand = new Random();
        Identifier malenames = new Identifier("neutronia:names/male.txt");
        Identifier neutralnames = new Identifier("neutronia:names/genderless.txt");
        Identifier femalenames = new Identifier("neutronia:names/female.txt");
        InputStream stream = MinecraftClient.getInstance().getResourceManager().getResource(malenames).getInputStream();
        InputStream stream2 = MinecraftClient.getInstance().getResourceManager().getResource(neutralnames).getInputStream();
        InputStream stream3 = MinecraftClient.getInstance().getResourceManager().getResource(femalenames).getInputStream();
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
        } else if (gender.equals("Female")) {
            Scanner scanner2 = new Scanner(stream3);
            StringBuilder builder2 = new StringBuilder();
            while (scanner2.hasNextLine()) {
                builder2.append(scanner2.nextLine());
                builder2.append(",");
            }
            String[] strings2 = builder2.toString().split(",");
            firstNameOut = strings2[rand.nextInt(strings2.length)];
            scanner2.close();
        } else {
            Scanner scanner3 = new Scanner(stream2);
            StringBuilder builder3 = new StringBuilder();
            while (scanner3.hasNextLine()) {
                builder3.append(scanner3.nextLine());
                builder3.append(",");
            }
            String[] strings3 = builder3.toString().split(",");
            firstNameOut = strings3[rand.nextInt(strings3.length)];
            scanner3.close();
        }
        stream.close();
        stream2.close();
        stream3.close();
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
}