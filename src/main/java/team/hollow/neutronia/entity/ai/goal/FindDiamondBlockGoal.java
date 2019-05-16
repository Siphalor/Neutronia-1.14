package team.hollow.neutronia.entity.ai.goal;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;
import team.hollow.neutronia.entity.passive.VillagerPlusEntity;

public class FindDiamondBlockGoal extends MoveToTargetPosGoal {

    private final VillagerPlusEntity owner;
    private int timer;

    public FindDiamondBlockGoal(VillagerPlusEntity villagerEntity_1, double double_1) {
        super(villagerEntity_1, double_1, 16);
        this.owner = villagerEntity_1;
    }

    public double getDesiredSquaredDistanceToTarget() {
        return 4.0D;
    }

    public boolean shouldResetPath() {
        return this.tryingTime % 100 == 0;
    }

    protected boolean isTargetPos(ViewableWorld viewableWorld_1, BlockPos blockPos_1) {
        BlockState blockState_1 = viewableWorld_1.getBlockState(blockPos_1);
        return blockState_1.getBlock() == Blocks.DIAMOND_BLOCK || blockState_1.getBlock() == Blocks.DIAMOND_ORE;
    }

    public void tick() {
        if (this.hasReached()) {
            if (this.timer >= 40) {
                this.eatSweetBerry();
            } else {
                ++this.timer;
            }
        } else if (!this.hasReached() && owner.getRand().nextFloat() < 0.05F) {
            owner.playSound(SoundEvents.ENTITY_FOX_SNIFF, 1.0F, 1.0F);
        }

        super.tick();
    }

    protected void eatSweetBerry() {
        World world_1 = this.owner.world;
        BlockState blockState_1 = world_1.getBlockState(this.targetPos);
        if (blockState_1.getBlock() == Blocks.DIAMOND_BLOCK || blockState_1.getBlock() == Blocks.DIAMOND_ORE) {
            ItemStack itemStack_1 = owner.getEquippedStack(EquipmentSlot.MAINHAND);
            if (itemStack_1.isEmpty()) {
                owner.setEquippedStack(EquipmentSlot.MAINHAND, new ItemStack(Items.SWEET_BERRIES));
            }
            owner.playSound(SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, 1.0F, 1.0F);
            world_1.setBlockState(this.targetPos, Blocks.AIR.getDefaultState(), 2);
        }
    }

    public boolean canStart() {
        return super.canStart();
    }

    public void start() {
        this.timer = 0;
        super.start();
    }

}