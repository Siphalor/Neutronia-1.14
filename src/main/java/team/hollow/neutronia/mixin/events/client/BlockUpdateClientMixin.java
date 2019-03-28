package team.hollow.neutronia.mixin.events.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.hollow.neutronia.event_system.EventCore;

@Mixin(value = {ClientWorld.class})
@Environment(EnvType.CLIENT)
public class BlockUpdateClientMixin {

    //public void updateListeners(BlockPos blockPos_1, BlockState blockState_1, BlockState blockState_2, int int_1)
    @Inject(at = @At("RETURN"), method = "updateListeners")
    private void on_updateListeners(BlockPos blockPos_1, BlockState blockState_1, BlockState blockState_2, int int_1, CallbackInfo info) {
        World _this = (World) (Object) this;
        boolean client = true;
        EventCore.instance.onBlockUpdate(_this, blockPos_1, blockState_1, blockState_2, int_1, client);
    }

}
