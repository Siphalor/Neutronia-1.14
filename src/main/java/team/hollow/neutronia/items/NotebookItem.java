package team.hollow.neutronia.items;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import team.hollow.neutronia.client.gui.NotebookScreen;
import team.hollow.neutronia.init.ArcaneMagicConstants;
import team.hollow.neutronia.notebook.NotebookSectionRegistry;

public class NotebookItem extends Item {
    public NotebookItem() {
        super(new Item.Settings().stackSize(1).itemGroup(ItemGroup.MISC));
    }

    @Override
    public void onEntityTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient) {
            CompoundTag tag = stack.getOrCreateTag();
            if (!tag.containsKey(ArcaneMagicConstants.NOTEBOOK_SECTION_KEY)) {
                tag.putString(ArcaneMagicConstants.NOTEBOOK_SECTION_KEY, NotebookSectionRegistry.CONTENTS.getID().toString());
                tag.putInt(ArcaneMagicConstants.NOTEBOOK_PAGE_KEY, 0);
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