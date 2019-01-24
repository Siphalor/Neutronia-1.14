package team.abnormals.neutronia.mixin;

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
import team.abnormals.neutronia.blocks.entity.TileStoneChest;
import team.abnormals.neutronia.enums.CustomChestTypes;
import team.abnormals.neutronia.init.NBlocks;

@Mixin(ItemDynamicRenderer.class)
public class ItemDynamicRendererMixin {

	private final TileStoneChest renderAcaciaChest = new TileStoneChest(CustomChestTypes.ACACIA);
    private final TileStoneChest renderBirchChest = new TileStoneChest(CustomChestTypes.BIRCH);
    private final TileStoneChest renderDarkOakChest = new TileStoneChest(CustomChestTypes.DARK_OAK);
    private final TileStoneChest renderJungleChest = new TileStoneChest(CustomChestTypes.JUNGLE);
    private final TileStoneChest renderSpruceChest = new TileStoneChest(CustomChestTypes.SPRUCE);
    private final TileStoneChest renderBambooChest = new TileStoneChest(CustomChestTypes.BAMBOO);
    private final TileStoneChest renderWoodenDungeonChest = new TileStoneChest(CustomChestTypes.WOODEN_DUNGEON);
    private final TileStoneChest renderDungeonChest = new TileStoneChest(CustomChestTypes.DUNGEON);
    private final TileStoneChest renderStoneChest = new TileStoneChest(CustomChestTypes.STONE);
    private final TileStoneChest renderStoneBrickChest = new TileStoneChest(CustomChestTypes.STONE_BRICK);
    private final TileStoneChest renderCobblestoneChest = new TileStoneChest(CustomChestTypes.COBBLESTONE);
    private final TileStoneChest renderMossyCobblestoneChest = new TileStoneChest(CustomChestTypes.MOSSY_COBBLESTONE);

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
            }
        }
	}
}