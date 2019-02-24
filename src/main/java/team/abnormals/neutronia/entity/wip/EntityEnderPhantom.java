package team.abnormals.neutronia.entity.wip;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import team.hdt.neutronia.base.util.handlers.LootTableHandler;

import javax.annotation.Nullable;

public class EntityEnderPhantom extends EntityPhantomBase {

    public EntityEnderPhantom(World worldIn) {
        super(worldIn);
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootTableHandler.ENDER_PHANTOM;
    }

}