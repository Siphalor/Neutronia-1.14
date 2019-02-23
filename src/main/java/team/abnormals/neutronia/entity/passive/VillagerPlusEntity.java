package team.abnormals.neutronia.entity.passive;

import net.minecraft.class_1358;
import net.minecraft.class_1365;
import net.minecraft.class_1370;
import net.minecraft.class_1394;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;
import team.abnormals.neutronia.entity.ai.goal.FindDiamondBlockGoal;
import team.abnormals.neutronia.init.NEntityTypes;

public class VillagerPlusEntity extends PassiveEntity {

    public VillagerPlusEntity(World world_1) {
        this("nitwit", world_1);
    }

    public VillagerPlusEntity(String villagerName, World world_1) {
        super(NEntityTypes.VILLAGER_PLUS, world_1);
        this.setCanPickUpLoot(true);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, ZombieEntity.class, 8.0F, 0.6D, 0.6D));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, EvokerEntity.class, 12.0F, 0.8D, 0.8D));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, VindicatorEntity.class, 8.0F, 0.8D, 0.8D));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, VexEntity.class, 8.0F, 0.6D, 0.6D));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, PillagerEntity.class, 15.0F, 0.6D, 0.6D));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, IllusionerEntity.class, 12.0F, 0.6D, 0.6D));
        this.goalSelector.add(2, new class_1365(this));
        this.goalSelector.add(3, new StayInsideGoal(this));
        this.goalSelector.add(3, new FindDiamondBlockGoal(this, 1.0));
        this.goalSelector.add(4, new OpenDoorGoal(this, true));
        this.goalSelector.add(5, new class_1370(this, 0.6D));
        this.goalSelector.add(9, new class_1358(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.add(9, new class_1394(this, 0.6D));
        this.goalSelector.add(10, new LookAtEntityGoal(this, MobEntity.class, 8.0F));
    }

    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.5F);
    }

    @Override
    public PassiveEntity createChild(PassiveEntity passiveEntity) {
        return new VillagerPlusEntity(world);
    }

    @Override
    public void writeCustomDataToTag(CompoundTag compoundTag_1) {
        super.writeCustomDataToTag(compoundTag_1);
        compoundTag_1.putString("Gender", "Male");
        compoundTag_1.putString("Profession", "Nitwit");
    }

    @Override
    public void readCustomDataFromTag(CompoundTag compoundTag_1) {
        super.readCustomDataFromTag(compoundTag_1);
    }

}