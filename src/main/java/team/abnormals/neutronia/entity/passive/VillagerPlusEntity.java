package team.abnormals.neutronia.entity.passive;

import net.minecraft.class_1376;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.world.World;
import team.abnormals.neutronia.entity.ai.goal.FindDiamondBlockGoal;
import team.abnormals.neutronia.init.NEntityTypes;

public class VillagerPlusEntity extends PassiveEntity {

    public VillagerPlusEntity(World world_1) {
        this("nitwit", world_1);
    }

    public VillagerPlusEntity(String villagerName, World world_1) {
        super(NEntityTypes.VILLAGER, world_1);
        this.setSize(0.6F, 1.95F);
        this.setCanPickUpLoot(true);
    }

    @Override
    protected void method_5959() {
        this.goalSelector.add(1, new FleeEntityGoal<>(this, ZombieEntity.class, 8.0F, 0.6D, 0.6D));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, EvokerEntity.class, 12.0F, 0.8D, 0.8D));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, VindicatorEntity.class, 8.0F, 0.8D, 0.8D));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, VexEntity.class, 8.0F, 0.6D, 0.6D));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, PillagerEntity.class, 15.0F, 0.6D, 0.6D));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, IllusionerEntity.class, 12.0F, 0.6D, 0.6D));
        this.goalSelector.add(1, new FindDiamondBlockGoal(this, 0.6D));
        this.goalSelector.add(6, new class_1376(this));
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

}