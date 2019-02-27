package team.hollow.neutronia.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;
import team.hollow.neutronia.villagers.VillagerPlusProfession;

import java.util.Random;

public abstract class VillagerPlusBase extends PassiveEntity {
    public static TrackedData<String> genderUnified = DataTracker.registerData(VillagerPlusBase.class, TrackedDataHandlerRegistry.STRING);
    public static TrackedData<String> professionUnified = DataTracker.registerData(VillagerPlusBase.class, TrackedDataHandlerRegistry.STRING);
    public String firstName;
    public String lastName;
    public String gender;
    public VillagerPlusProfession proffesion;

    protected VillagerPlusBase(EntityType<? extends net.minecraft.entity.passive.PassiveEntity> type, World world) {
        super(type, world);
        unifiedSetup();
        this.dataTracker.set(genderUnified, gender);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(genderUnified, gender);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setupGender() {
        Random random = new Random();
        String[] genderList = {"Male", "Female", "Genderless"};
        this.gender = genderList[random.nextInt(genderList.length)];
    }

    @Override
    public void writeCustomDataToTag(CompoundTag tag) {
        super.writeCustomDataToTag(tag);
        tag.putString("first_name", firstName);
        tag.putString("last_name", lastName);
        tag.putString("gender", gender);
    }

    public void unifiedSetup() {
        this.setupGender();
    }

    @Override
    public void readCustomDataFromTag(CompoundTag tag) {
        super.readCustomDataFromTag(tag);
        this.firstName = tag.getString("first_name");
        this.lastName = tag.getString("last_name");
        this.gender = tag.getString("gender");
        this.dataTracker.set(genderUnified, gender);
    }

}