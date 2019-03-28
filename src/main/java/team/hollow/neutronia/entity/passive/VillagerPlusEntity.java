package team.hollow.neutronia.entity.passive;

import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import team.hollow.neutronia.entity.ai.goal.FindDiamondBlockGoal;
import team.hollow.neutronia.init.NEntityTypes;

public class VillagerPlusEntity extends PassiveEntity {

    public VillagerPlusEntity(World world_1) {
        super(NEntityTypes.VILLAGER_PLUS, world_1);
        this.setCanPickUpLoot(true);
        ((MobNavigation) this.getNavigation()).setCanPathThroughDoors(true);
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
        this.goalSelector.add(3, new FindDiamondBlockGoal(this, 1.0));
        this.goalSelector.add(5, new GoToWalkTargetGoal(this, 0.6D));
        this.goalSelector.add(9, new GoToEntityGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.add(9, new WanderAroundFarGoal(this, 0.6D));
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

}