package team.abnormals.neutronia.mixin;

import net.minecraft.block.ComposterBlock;
import net.minecraft.item.ItemProvider;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ComposterBlock.class)
public abstract class MixinComposter {

    @Shadow
    protected static void registerCompostableItem(float float_1, ItemProvider itemProvider_1) {

    }

    @Inject(method = "registerDefaultCompostableItems()V", at = @At("RETURN"))
    private static void mixinRegisterDefaultCompostableItems(CallbackInfo info) {
        registerCompostableItem(0.5F, Items.ROTTEN_FLESH);
        registerCompostableItem(0.5F, Items.CHICKEN);
        registerCompostableItem(0.5F, Items.COOKED_CHICKEN);
    }

}