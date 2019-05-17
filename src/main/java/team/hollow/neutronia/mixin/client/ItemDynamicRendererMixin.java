package team.hollow.neutronia.mixin.client;

import net.minecraft.block.Block;
import net.minecraft.client.render.item.ItemDynamicRenderer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.hollow.abnormalib.blocks.SimpleChestBaseBlock;
import team.hollow.neutronia.client.renderer.ClientRenderUtils;

@Mixin(ItemDynamicRenderer.class)
public class ItemDynamicRendererMixin {

    @Inject(at = @At("HEAD"), method = "render")
    private void render(ItemStack stack, CallbackInfo info) {
    	Block block = Block.getBlockFromItem(stack.getItem());
    	if(block instanceof SimpleChestBaseBlock) {
			ClientRenderUtils.lastChestRenderWoodType = ((SimpleChestBaseBlock) block).type;
		} else {
    		ClientRenderUtils.lastChestRenderWoodType = null;
		}
    }
}