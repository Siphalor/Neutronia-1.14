package team.hollow.neutronia.mixin.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.hollow.neutronia.registry.WoodType;
import team.hollow.neutronia.registry.WoodTypeRegistry;

@Mixin(SignBlockEntityRenderer.class)
public class SignBlockEntityRendererMixin {
	@Inject(method = "getModelTexture", at = @At("HEAD"), cancellable = true)
	private void getModelTexture(Block block, CallbackInfoReturnable<Identifier> callbackInfoReturnable) {
		for(WoodType woodType : WoodTypeRegistry.woodTypes) {
			Identifier base = woodType.getIdentifier();
            String baseId = woodType.getIdentifier().toString();
            String blockId = Registry.BLOCK.getId(block).toString();
            if(blockId.equals(baseId + "_sign") || blockId.equals(baseId + "_wall_sign")) {
            	callbackInfoReturnable.setReturnValue(new Identifier(base.getNamespace(), "textures/entity/signs/" + base.getPath() + ".png"));
            	return;
			}
		}
	}
}
