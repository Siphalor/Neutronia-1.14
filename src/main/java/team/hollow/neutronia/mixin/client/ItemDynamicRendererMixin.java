package team.hollow.neutronia.mixin.client;

import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.item.ItemDynamicRenderer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.hollow.neutronia.blocks.CustomChestBlock;
import team.hollow.neutronia.blocks.entity.CustomChestBlockEntity;
import team.hollow.neutronia.enums.CustomChestType;
import team.hollow.neutronia.init.NBlocks;

@Mixin(ItemDynamicRenderer.class)
public class ItemDynamicRendererMixin {

    private final CustomChestBlockEntity renderAcaciaChest = new CustomChestBlockEntity(CustomChestType.ACACIA);
    private final CustomChestBlockEntity renderBirchChest = new CustomChestBlockEntity(CustomChestType.BIRCH);
    private final CustomChestBlockEntity renderDarkOakChest = new CustomChestBlockEntity(CustomChestType.DARK_OAK);
    private final CustomChestBlockEntity renderJungleChest = new CustomChestBlockEntity(CustomChestType.JUNGLE);
    private final CustomChestBlockEntity renderSpruceChest = new CustomChestBlockEntity(CustomChestType.SPRUCE);
    private final CustomChestBlockEntity renderBambooChest = new CustomChestBlockEntity(CustomChestType.BAMBOO);
    private final CustomChestBlockEntity renderMangroveChest = new CustomChestBlockEntity(CustomChestType.MANGROVE);
    private final CustomChestBlockEntity renderRedMangroveChest = new CustomChestBlockEntity(CustomChestType.RED_MANGROVE);
    private final CustomChestBlockEntity renderBaobabChest = new CustomChestBlockEntity(CustomChestType.BAOBAB);
    private final CustomChestBlockEntity renderWengeChest = new CustomChestBlockEntity(CustomChestType.WENGE);
    private final CustomChestBlockEntity renderPurpleHeartChest = new CustomChestBlockEntity(CustomChestType.PURPLEHEART);
    private final CustomChestBlockEntity renderLacewoodChest = new CustomChestBlockEntity(CustomChestType.LACEWOOD);
    private final CustomChestBlockEntity renderBolivianRosewoodChest = new CustomChestBlockEntity(CustomChestType.BOLIVIAN_ROSEWOOD);
    private final CustomChestBlockEntity renderGabonEbonyChest = new CustomChestBlockEntity(CustomChestType.GABON_EBONY);

    @Inject(at = @At("TAIL"), method = "render")
    private void render(ItemStack stack, CallbackInfo info) {
        Item item = stack.getItem();
        if (item instanceof BlockItem) {
            for(CustomChestBlock block1 : NBlocks.WOODEN_CHESTS) {
                switch(block1.getChestType()) {
                    case ACACIA:
                    case BIRCH:
                        BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderBirchChest);
                        break;
                    case DARK_OAK:
                        BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderDarkOakChest);
                        break;
                    case JUNGLE:
                        BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderJungleChest);
                        break;
                    case SPRUCE:
                        BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderSpruceChest);
                        break;
                    case BAMBOO:
                        BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderBambooChest);
                        break;
                    case MANGROVE:
                        BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderMangroveChest);
                        break;
                    case RED_MANGROVE:
                        BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderRedMangroveChest);
                        break;
                    case BAOBAB:
                        BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderBaobabChest);
                        break;
                    case WENGE:
                        BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderWengeChest);
                        break;
                    case PURPLEHEART:
                        BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderPurpleHeartChest);
                        break;
                    case LACEWOOD:
                        BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderLacewoodChest);
                        break;
                    case BOLIVIAN_ROSEWOOD:
                        BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderBolivianRosewoodChest);
                        break;
                    case GABON_EBONY:
                        BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderGabonEbonyChest);
                        break;
                    default:
                        BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderAcaciaChest);
                        break;
                }
            }
        }
    }
}