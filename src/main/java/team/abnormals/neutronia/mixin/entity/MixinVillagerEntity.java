package team.abnormals.neutronia.mixin.entity;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnType;
import net.minecraft.entity.passive.AbstractTraderEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.VillagerData;
import net.minecraft.village.VillagerType;
import net.minecraft.world.IWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@SuppressWarnings("InvalidInjectorMethodSignature")
@Mixin(VillagerEntity.class)
public abstract class MixinVillagerEntity extends AbstractTraderEntity {

    public MixinVillagerEntity(EntityType<?> entityType_1, World world_1) {
        super((EntityType<? extends AbstractTraderEntity>) entityType_1, world_1);
    }

    @Shadow
    public abstract VillagerData getVillagerData();

    @Shadow
    public abstract void setVillagerData(VillagerData villagerData_1);

    @Inject(
            method = "prepareEntityData(Lnet/minecraft/world/IWorld;Lnet/minecraft/world/LocalDifficulty;Lnet/minecraft/entity/SpawnType;Lnet/minecraft/entity/EntityData;Lnet/minecraft/nbt/CompoundTag;)Lnet/minecraft/entity/EntityData;",
            at = @At(value = "RETURN", ordinal = 2),
            locals = LocalCapture.CAPTURE_FAILHARD,
            allow = 1,
            cancellable = true
    )
    private EntityData prepareEntityData(CallbackInfoReturnable info, IWorld iWorld_1, LocalDifficulty localDifficulty_1, SpawnType spawnType_1, EntityData entityData_1, CompoundTag compoundTag_1) {
        if (spawnType_1 == SpawnType.BREEDING) {
            System.out.println("This is a test");
            this.setVillagerData(this.getVillagerData().withType(VillagerType.forBiome(iWorld_1.getBiome(new BlockPos(this)))).withProfession(Registry.VILLAGER_PROFESSION.getRandom(iWorld_1.getRandom())));
        }

        if (spawnType_1 == SpawnType.COMMAND || spawnType_1 == SpawnType.SPAWN_EGG || spawnType_1 == SpawnType.SPAWNER) {
            this.setVillagerData(this.getVillagerData().withType(VillagerType.forBiome(iWorld_1.getBiome(new BlockPos(this)))).withProfession(Registry.VILLAGER_PROFESSION.getRandom(iWorld_1.getRandom())));
        }
        return super.prepareEntityData(iWorld_1, localDifficulty_1, spawnType_1, entityData_1, compoundTag_1);
    }
}