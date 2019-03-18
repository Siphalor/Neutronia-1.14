package team.hollow.neutronia.mixin.client;

import net.minecraft.block.Block;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.item.ItemDynamicRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.block.BlockItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.hollow.neutronia.blocks.entity.StoneChestBlockEntity;
import team.hollow.neutronia.enums.CustomChestTypes;
import team.hollow.neutronia.init.NBlocks;

@Mixin(ItemDynamicRenderer.class)
public class ItemDynamicRendererMixin {

    private final StoneChestBlockEntity renderAcaciaChest = new StoneChestBlockEntity(CustomChestTypes.ACACIA);
    private final StoneChestBlockEntity renderBirchChest = new StoneChestBlockEntity(CustomChestTypes.BIRCH);
    private final StoneChestBlockEntity renderDarkOakChest = new StoneChestBlockEntity(CustomChestTypes.DARK_OAK);
    private final StoneChestBlockEntity renderJungleChest = new StoneChestBlockEntity(CustomChestTypes.JUNGLE);
    private final StoneChestBlockEntity renderSpruceChest = new StoneChestBlockEntity(CustomChestTypes.SPRUCE);
    private final StoneChestBlockEntity renderBambooChest = new StoneChestBlockEntity(CustomChestTypes.BAMBOO);
    private final StoneChestBlockEntity renderWoodenDungeonChest = new StoneChestBlockEntity(CustomChestTypes.WOODEN_DUNGEON);
    private final StoneChestBlockEntity renderDungeonChest = new StoneChestBlockEntity(CustomChestTypes.DUNGEON);
    private final StoneChestBlockEntity renderStoneChest = new StoneChestBlockEntity(CustomChestTypes.STONE);
    private final StoneChestBlockEntity renderStoneBrickChest = new StoneChestBlockEntity(CustomChestTypes.STONE_BRICK);
    private final StoneChestBlockEntity renderCobblestoneChest = new StoneChestBlockEntity(CustomChestTypes.COBBLESTONE);
    private final StoneChestBlockEntity renderMossyCobblestoneChest = new StoneChestBlockEntity(CustomChestTypes.MOSSY_COBBLESTONE);
    private final StoneChestBlockEntity renderMangroveChest = new StoneChestBlockEntity(CustomChestTypes.MANGROVE);
    private final StoneChestBlockEntity renderRedMangroveChest = new StoneChestBlockEntity(CustomChestTypes.RED_MANGROVE);
    private final StoneChestBlockEntity renderBaobabChest = new StoneChestBlockEntity(CustomChestTypes.BAOBAB);

    @Inject(at = @At("TAIL"), method = "render")
    private void method_3166(ItemStack stack, CallbackInfo info) {
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
            }
        }
    }
}