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
    private final CustomChestBlockEntity renderBambooChest = new CustomChestBlockEntity(CustomChestTypes.BAMBOO);
    private final CustomChestBlockEntity renderWoodenDungeonChest = new CustomChestBlockEntity(CustomChestTypes.WOODEN_DUNGEON);
    private final CustomChestBlockEntity renderDungeonChest = new CustomChestBlockEntity(CustomChestTypes.DUNGEON);
    private final CustomChestBlockEntity renderStoneChest = new CustomChestBlockEntity(CustomChestTypes.STONE);
    private final CustomChestBlockEntity renderStoneBrickChest = new CustomChestBlockEntity(CustomChestTypes.STONE_BRICK);
    private final CustomChestBlockEntity renderCobblestoneChest = new CustomChestBlockEntity(CustomChestTypes.COBBLESTONE);
    private final CustomChestBlockEntity renderMossyCobblestoneChest = new CustomChestBlockEntity(CustomChestTypes.MOSSY_COBBLESTONE);
    private final CustomChestBlockEntity renderMangroveChest = new CustomChestBlockEntity(CustomChestTypes.MANGROVE);
    private final CustomChestBlockEntity renderRedMangroveChest = new CustomChestBlockEntity(CustomChestTypes.RED_MANGROVE);
    private final CustomChestBlockEntity renderBaobabChest = new CustomChestBlockEntity(CustomChestTypes.BAOBAB);
    private final CustomChestBlockEntity renderWengeChest = new CustomChestBlockEntity(CustomChestTypes.WENGE);
    private final CustomChestBlockEntity renderPurpleHeartChest = new CustomChestBlockEntity(CustomChestTypes.PURPLEHEART);
    private final CustomChestBlockEntity renderLacewoodChest = new CustomChestBlockEntity(CustomChestTypes.LACEWOOD);
    private final CustomChestBlockEntity renderBolivianRosewoodChest = new CustomChestBlockEntity(CustomChestTypes.BOLIVIAN_ROSEWOOD);
    private final CustomChestBlockEntity renderGabonEbonyChest = new CustomChestBlockEntity(CustomChestTypes.GABON_EBONY);

    @Inject(at = @At("TAIL"), method = "render")
    private void render(ItemStack stack, CallbackInfo info) {
        Item item = stack.getItem();
        if (item instanceof BlockItem) {
            Block block = ((BlockItem) item).getBlock();
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
            } else if (block == NBlocks.WOODEN_CHESTS[5]) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderBambooChest);
            } else if (block == NBlocks.WOODEN_CHESTS[6]) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderWoodenDungeonChest);
            } else if (block == NBlocks.WOODEN_CHESTS[7]) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderDungeonChest);
            } else if (block == NBlocks.WOODEN_CHESTS[8]) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderStoneChest);
            } else if (block == NBlocks.WOODEN_CHESTS[9]) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderStoneBrickChest);
            } else if (block == NBlocks.WOODEN_CHESTS[10]) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderCobblestoneChest);
            } else if (block == NBlocks.WOODEN_CHESTS[11]) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderMossyCobblestoneChest);
            } else if (block == NBlocks.WOODEN_CHESTS[12]) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderMangroveChest);
            } else if (block == NBlocks.WOODEN_CHESTS[13]) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderRedMangroveChest);
            } else if (block == NBlocks.WOODEN_CHESTS[14]) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderBaobabChest);
            } else if (block == NBlocks.WOODEN_CHESTS[15]) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderWengeChest);
            } else if (block == NBlocks.WOODEN_CHESTS[16]) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderPurpleHeartChest);
            } else if (block == NBlocks.WOODEN_CHESTS[17]) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderLacewoodChest);
            } else if (block == NBlocks.WOODEN_CHESTS[18]) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderBolivianRosewoodChest);
            } else if (block == NBlocks.WOODEN_CHESTS[19]) {
                BlockEntityRenderDispatcher.INSTANCE.renderEntity(renderGabonEbonyChest);
            }
        }
    }
}