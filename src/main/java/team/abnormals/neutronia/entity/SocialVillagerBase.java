package team.abnormals.neutronia.entity;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import team.abnormals.neutronia.client.gui.SocialScreen;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public abstract class SocialVillagerBase extends PassiveEntity {
    public static TrackedData<String> hairColorUnified = DataTracker.registerData(SocialVillagerBase.class, TrackedDataHandlerRegistry.STRING);
    public static TrackedData<String> eyeColorUnified = DataTracker.registerData(SocialVillagerBase.class, TrackedDataHandlerRegistry.STRING);
    public static TrackedData<String> skinColorUnified = DataTracker.registerData(SocialVillagerBase.class, TrackedDataHandlerRegistry.STRING);
    public static TrackedData<Integer> hairStyleUnified = DataTracker.registerData(SocialVillagerBase.class, TrackedDataHandlerRegistry.INTEGER);
    public static TrackedData<String> genderUnified = DataTracker.registerData(SocialVillagerBase.class, TrackedDataHandlerRegistry.STRING);
    public static TrackedData<String> orientationUnified = DataTracker.registerData(SocialVillagerBase.class, TrackedDataHandlerRegistry.STRING);
    public static TrackedData<String> serverUUID = DataTracker.registerData(SocialVillagerBase.class, TrackedDataHandlerRegistry.STRING);
    public String firstName;
    public String lastName;
    protected String hairColor;
    protected String hairRecessive;
    protected String eyeColor;
    protected String eyeRecessive;
    protected String skinColor;
    protected String skinRecessive;
    protected String sexuality;
    protected String gender;
    protected String genderRecessive;
    protected int hairStyle = 0;
    protected int friendliness = 0;
    protected int bravery = 0;
    protected int generosity = 0;
    protected boolean apologized = false;
    protected boolean charmed = false;
    protected boolean generated = false;
    protected HashMap<UUID, Integer> opinions = new HashMap<UUID, Integer>();

    protected SocialVillagerBase(EntityType<?> type, World world) {
        super(type, world);
        if (hairColor == null) {
            unifiedSetup();
            this.dataTracker.set(hairColorUnified, hairColor);
            this.dataTracker.set(eyeColorUnified, eyeColor);
            this.dataTracker.set(skinColorUnified, skinColor);
            this.dataTracker.set(hairStyleUnified, hairStyle);
            this.dataTracker.set(genderUnified, gender);
            this.dataTracker.set(orientationUnified, sexuality);
            this.dataTracker.set(serverUUID, this.getUuidAsString());

        }
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(hairColorUnified, hairColor);
        this.dataTracker.startTracking(eyeColorUnified, eyeColor);
        this.dataTracker.startTracking(skinColorUnified, skinColor);
        this.dataTracker.startTracking(hairStyleUnified, hairStyle);
        this.dataTracker.startTracking(orientationUnified, sexuality);
        this.dataTracker.startTracking(genderUnified, gender);
        this.dataTracker.startTracking(serverUUID, this.getUuidAsString());
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void formOpinion(Entity person) {
        Random rand = new Random();
        if (!opinions.containsKey(person.getUuid())) {
            opinions.put(person.getUuid(), rand.nextInt(50) - 25);
        }

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
        int[] styleList = {1, 2, 3, 4, 5};
        this.hairStyle = styleList[rand.nextInt(styleList.length)];
        this.hairColor = hairList[rand.nextInt(hairList.length)];
        this.hairRecessive = hairColor;
    }

    public void setupEyes() {
        Random rand = new Random();
        String[] eyeList = {"Black", "Blue", "Brown", "Green", "Lime", "Pink", "Yellow"};
        this.eyeColor = eyeList[rand.nextInt(eyeList.length)];
        this.eyeRecessive = eyeColor;
    }

    public void setupSkin() {
        Random rand = new Random();
        String[] skinList = {"Light", "Medium", "Dark"};
        this.skinColor = skinList[rand.nextInt(skinList.length)];
        this.skinRecessive = skinColor;
    }

    public void setupGender() {
        Random random = new Random();
        String[] genderList = {"Male", "Female", "Genderless"};
        this.gender = genderList[random.nextInt(genderList.length)];
        this.genderRecessive = gender;
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
        tag.putString("hair_color", hairColor);
        System.out.println("Hair Color: " + hairColor);
        tag.putString("hair_color_recessive", hairRecessive);
        tag.putString("eye_color", eyeColor);
        tag.putString("eye_color_recessive", eyeRecessive);
        tag.putString("skin_color", skinColor);
        tag.putString("skin_color_recessive", skinRecessive);
        tag.putString("sexuality", sexuality);
        tag.putInt("hair_style", hairStyle);
        tag.putInt("friendliness", friendliness);
        tag.putInt("bravery", bravery);
        tag.putInt("generosity", generosity);
        tag.putBoolean("apologized", apologized);
        tag.putBoolean("charmed", charmed);
        tag.putString("first_name", firstName);
        tag.putString("last_name", lastName);
        tag.putString("gender", gender);
        tag.putString("gender_recessive", genderRecessive);
        if (opinions.keySet().size() > 13) {
            for (UUID key : opinions.keySet()) {
                CompoundTag opinionTag = new CompoundTag();
                opinionTag.putUuid("holder", key);
                opinionTag.putInt("opinion", opinions.get(key));
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
        this.hairColor = tag.getString("hair_color");
        this.hairRecessive = tag.getString("hair_color_recessive");
        this.eyeColor = tag.getString("eye_color");
        this.eyeRecessive = tag.getString("eye_color_recessive");
        this.skinColor = tag.getString("skin_color");
        this.skinRecessive = tag.getString("skin_color_recessive");
        this.sexuality = tag.getString("sexuality");
        this.hairStyle = tag.getInt("hair_style");
        this.friendliness = tag.getInt("friendliness");
        this.bravery = tag.getInt("bravery");
        this.generosity = tag.getInt("generosity");
        this.apologized = tag.getBoolean("apologized");
        this.charmed = tag.getBoolean("charmed");
        this.firstName = tag.getString("first_name");
        this.lastName = tag.getString("last_name");
        this.gender = tag.getString("gender");
        this.genderRecessive = tag.getString("gender_recessive");
        for (String key : tag.getKeys()) {
            if (tag.hasUuid(key)) {
                this.opinions.put(tag.getCompound(key).getUuid("holder"), tag.getInt("opinion"));
            }
        }
        this.dataTracker.set(hairColorUnified, hairColor);
        this.dataTracker.set(eyeColorUnified, eyeColor);
        this.dataTracker.set(skinColorUnified, skinColor);
        this.dataTracker.set(hairStyleUnified, hairStyle);
        this.dataTracker.set(orientationUnified, sexuality);
        this.dataTracker.set(serverUUID, this.getUuidAsString());

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

}