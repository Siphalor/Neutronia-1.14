package team.hollow.neutronia.mixin.client;

import net.minecraft.client.render.entity.model.VillagerResemblingModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(VillagerResemblingModel.class)
public class VillagerResemblingModelMixin {
    @ModifyConstant(method = "<init>(FII)V", constant = @Constant(intValue = 18, ordinal = 0))
    public int extendRobe(int old) {
        return 20;
    }
}