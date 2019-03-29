package team.hollow.neutronia.items;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import team.hollow.neutronia.client.gui.NotebookScreen;
import team.hollow.neutronia.init.NConstants;
import team.hollow.neutronia.notebook.NotebookSectionRegistry;

public class NotebookItem extends Item {

    private static final String TAG_BOOK = "neutronia:notebook";

    public NotebookItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public void onEntityTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient) {
            CompoundTag tag = stack.getOrCreateTag();
            if (!tag.containsKey(NConstants.NOTEBOOK_SECTION_KEY)) {
                tag.putString(NConstants.NOTEBOOK_SECTION_KEY, NotebookSectionRegistry.CONTENTS.getID().toString());
                tag.putInt(NConstants.NOTEBOOK_PAGE_KEY, 0);
            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (!player.isSneaking()) {
            if (world.isClient) {
                player.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, 1, 1);
                openGUI(stack);
            }
            return new TypedActionResult<>(ActionResult.SUCCESS, stack);
        }
        return new TypedActionResult<>(ActionResult.PASS, stack);
    }

    @Environment(EnvType.CLIENT)
    private void openGUI(ItemStack stack) {
        MinecraftClient.getInstance().openScreen(new NotebookScreen(stack));
    }
}