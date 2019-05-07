package team.hollow.neutronia.mixin.client.render;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.ChestBlockEntityRenderer;
import net.minecraft.client.render.entity.model.ChestEntityModel;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.hollow.neutronia.blocks.NeutroniaSimpleChestBlock;
import team.hollow.neutronia.client.renderer.ClientRenderUtils;

@Mixin(ChestBlockEntityRenderer.class)
public class ChestBlockEntityRendererMixin extends BlockEntityRenderer {
    @Inject(method = "method_3562", at = @At("RETURN"))
    private void bindMatchingTexture(BlockEntity blockEntity, int int_1, boolean doubleChest, CallbackInfoReturnable<ChestEntityModel> callbackInfoReturnable) {
        if(!blockEntity.hasWorld()) {
            if(ClientRenderUtils.lastChestRenderWoodType == null) return;
            bindTexture(getSimpleChestTexture(ClientRenderUtils.lastChestRenderWoodType, doubleChest));
            return;
        }
        Block block = blockEntity.getWorld().getBlockState(blockEntity.getPos()).getBlock();
        if(block instanceof NeutroniaSimpleChestBlock) {
            bindTexture(getSimpleChestTexture(((NeutroniaSimpleChestBlock) block).type, doubleChest));
        }
    }
    
    private static Identifier getSimpleChestTexture(Identifier identifier, boolean doubleChest) {
        return new Identifier(identifier.getNamespace(), "textures/entity/chest/" + identifier.getPath() + (doubleChest ? "_double.png" : ".png"));
    }
}
