package team.hollow.neutronia.mixin.entity;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnType;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.VillagerData;
import net.minecraft.world.IWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.hollow.neutronia.village.VillagerTypeRegistry;

@Mixin(ZombieVillagerEntity.class)
abstract class ZombieVillagerEntityMixin extends ZombieEntity {
    public ZombieVillagerEntityMixin(EntityType<? extends ZombieEntity> entityType_1, World world_1) {
        super(entityType_1, world_1);
    }

    @Shadow
    public abstract VillagerData getVillagerData();

    @Shadow
    public abstract void setVillagerData(VillagerData villagerData_1);

    @Inject(method = "initialize", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/ZombieVillagerEntity;setVillagerData(Lnet/minecraft/village/VillagerData;)V", ordinal = 0, shift = At.Shift.AFTER))
    public void onInitialize(IWorld world, LocalDifficulty localDifficulty, SpawnType spawnType, EntityData entityData, CompoundTag compoundTag, CallbackInfoReturnable<EntityData> callbackInfoReturnable) {
        setVillagerData(getVillagerData().withType(VillagerTypeRegistry.getVillagerTypeForBiome(world.getBiome(new BlockPos(this)))));
    }
}
