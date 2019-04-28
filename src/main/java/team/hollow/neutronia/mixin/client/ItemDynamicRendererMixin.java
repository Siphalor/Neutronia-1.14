package team.hollow.neutronia.mixin.client;

import net.minecraft.block.Block;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.item.ItemDynamicRenderer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.hollow.neutronia.blocks.ChestBaseBlock;
import team.hollow.neutronia.blocks.entity.ChestBaseBlockEntity;
import team.hollow.neutronia.blocks.entity.CustomChestBlockEntity;
import team.hollow.neutronia.enums.CustomChestTypes;
import team.hollow.neutronia.init.NBlocks;

@Mixin(ItemDynamicRenderer.class)
public class ItemDynamicRendererMixin {

    private final CustomChestBlockEntity renderAcaciaChest = new CustomChestBlockEntity(CustomChestTypes.ACACIA);
    private final CustomChestBlockEntity renderBirchChest = new CustomChestBlockEntity(CustomChestTypes.BIRCH);
    private final CustomChestBlockEntity renderDarkOakChest = new CustomChestBlockEntity(CustomChestTypes.DARK_OAK);
    private final CustomChestBlockEntity renderJungleChest = new CustomChestBlockEntity(CustomChestTypes.JUNGLE);
    private final CustomChestBlockEntity renderSpruceChest = new CustomChestBlockEntity(CustomChestTypes.SPRUCE);

    @Inject(at = @At("TAIL"), method = "render")
    private void render(ItemStack stack, CallbackInfo info) {
        Item item = stack.getItem();
        if (item instanceof BlockItem) {
            Block block = ((BlockItem) item).getBlock();
            if(block instanceof ChestBaseBlock) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(new ChestBaseBlockEntity((ChestBaseBlock) block));
            }
            if (block == NBlocks.WOODEN_CHESTS[0]) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderAcaciaChest);
            } else if (block == NBlocks.WOODEN_CHESTS[1]) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderBirchChest);
            } else if (block == NBlocks.WOODEN_CHESTS[2]) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderDarkOakChest);
            } else if (block == NBlocks.WOODEN_CHESTS[3]) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderJungleChest);
            } else if (block == NBlocks.WOODEN_CHESTS[4]) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderSpruceChest);
            }
        }
    }
}