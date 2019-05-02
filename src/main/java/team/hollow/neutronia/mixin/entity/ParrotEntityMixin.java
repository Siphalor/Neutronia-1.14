package team.hollow.neutronia.mixin.entity;

import net.minecraft.entity.Bird;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnType;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.TameableShoulderEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ParrotEntity.class)
public abstract class ParrotEntityMixin extends TameableShoulderEntity implements Bird {

    @Shadow @Final private static TrackedData<Integer> ATTR_VARIANT;

    protected ParrotEntityMixin(EntityType<? extends TameableShoulderEntity> entityType_1, World world_1) {
        super(entityType_1, world_1);
    }

    @Shadow public abstract void setVariant(int int_1);

    /**
     * @author OliviaTheVampire
     */
    @javax.annotation.Nullable
    @Nullable
    @Overwrite
    public EntityData initialize(IWorld iWorld_1, LocalDifficulty localDifficulty_1, SpawnType spawnType_1, EntityData entityData_1, CompoundTag compoundTag_1) {
        setVariant(this.random.nextInt(17));
        return super.initialize(iWorld_1, localDifficulty_1, spawnType_1, entityData_1, compoundTag_1);
    }

    /**
     * @author OliviaTheVampire
     */
    @Overwrite
    public int getVariant() {
        return MathHelper.clamp(this.dataTracker.get(ATTR_VARIANT), 0, 16);
    }

}
