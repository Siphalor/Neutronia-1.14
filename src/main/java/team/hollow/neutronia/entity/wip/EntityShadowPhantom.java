package team.abnormals.neutronia.entity.wip;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import team.abnormals.neutronia.init.NEntityTypes;
import team.hdt.neutronia.base.util.handlers.LootTableHandler;

import javax.annotation.Nullable;

public class EntityShadowPhantom extends PhantomEntity {

    public EntityShadowPhantom(World worldIn) {
        super(NEntityTypes.SHADOW_PHANTOM, worldIn);
    }

}