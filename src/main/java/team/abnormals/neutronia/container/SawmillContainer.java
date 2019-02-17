//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.abnormals.neutronia.container;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.container.BlockContext;
import net.minecraft.container.Container;
import net.minecraft.container.Property;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import team.abnormals.neutronia.init.NBlocks;
import team.abnormals.neutronia.init.NRecipeType;
import team.abnormals.neutronia.recipe.SawmillingRecipe;

import java.util.List;

public class SawmillContainer extends Container {
    static final ImmutableList<Item> field_17626;

    static {
        field_17626 = ImmutableList.of(Items.field_8118, Items.field_8113, Items.field_8191, Items.field_8842, Items.field_8651, Items.field_8404, Items.field_8583, Items.field_8684, Items.field_8170, Items.field_8125, Items.field_8820, Items.field_8652, Items.field_8415, Items.field_8624, Items.field_8767, Items.field_8334, Items.field_8072, Items.field_8808, Items.field_8248, Items.field_8362, Items.field_8472, Items.field_8785, Items.field_8284, Items.field_8219, Items.field_8888, Items.field_8210, Items.field_8201, Items.field_8439, Items.field_8587, Items.field_8458);
    }

    public final Inventory inventory;
    public final PlayerInventory playerInventory;
    final Slot inputSlot;
    final Slot outputSlot;
    private final BlockContext blockContext_1;
    private final Property selectedRecipe;
    private final World world;
    private List<SawmillingRecipe> availableRecipes;
    private ItemStack inputStack;
    private long lastTakeTime;
    private Runnable contentsChangedListener;

    public SawmillContainer(int int_1, PlayerInventory playerInventory_1) {
        this(int_1, playerInventory_1, BlockContext.field_17304);
    }

    public SawmillContainer(int int_1, PlayerInventory playerInventory_1, final BlockContext blockContext_1) {
        super(null, int_1);
        this.selectedRecipe = Property.create();
        this.availableRecipes = Lists.newArrayList();
        this.inputStack = ItemStack.EMPTY;
        this.contentsChangedListener = () -> {
        };
        this.inventory = new BasicInventory(2) {
            public void markDirty() {
                super.markDirty();
                SawmillContainer.this.onContentChanged(this);
                SawmillContainer.this.contentsChangedListener.run();
            }
        };
        this.blockContext_1 = blockContext_1;
        this.world = playerInventory_1.player.world;
        this.playerInventory = playerInventory_1;
        this.inputSlot = this.addSlot(new Slot(this.inventory, 0, 20, 33));
        this.outputSlot = this.addSlot(new Slot(this.inventory, 1, 143, 33) {
            public boolean canInsert(ItemStack itemStack_1) {
                return false;
            }

            public ItemStack onTakeItem(PlayerEntity playerEntity_1, ItemStack itemStack_1) {
                ItemStack itemStack_2 = SawmillContainer.this.inputSlot.takeStack(1);
                if (!itemStack_2.isEmpty()) {
                    SawmillContainer.this.populateResult();
                }

                itemStack_1.getItem().onCrafted(itemStack_1, playerEntity_1.world, playerEntity_1);
                blockContext_1.run((world_1, blockPos_1) -> {
                    long long_1 = world_1.getTime();
                    if (SawmillContainer.this.lastTakeTime != long_1) {
                        world_1.playSound(null, blockPos_1, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundCategory.BLOCK, 1.0F, 1.0F);
                        SawmillContainer.this.lastTakeTime = long_1;
                    }

                });
                return super.onTakeItem(playerEntity_1, itemStack_1);
            }
        });

        int int_4;
        for (int_4 = 0; int_4 < 3; ++int_4) {
            for (int int_3 = 0; int_3 < 9; ++int_3) {
                this.addSlot(new Slot(playerInventory_1, int_3 + int_4 * 9 + 9, 8 + int_3 * 18, 84 + int_4 * 18));
            }
        }

        for (int_4 = 0; int_4 < 9; ++int_4) {
            this.addSlot(new Slot(playerInventory_1, int_4, 8 + int_4 * 18, 142));
        }

        this.addProperty(this.selectedRecipe);
    }

    @Environment(EnvType.CLIENT)
    public int method_17862() {
        return this.selectedRecipe.get();
    }

    @Environment(EnvType.CLIENT)
    public List<SawmillingRecipe> getAvailableRecipes() {
        return this.availableRecipes;
    }

    @Environment(EnvType.CLIENT)
    public int getAvailableRecipeCount() {
        return this.availableRecipes.size();
    }

    @Environment(EnvType.CLIENT)
    public boolean canCraft() {
        return this.inputSlot.hasStack() && !this.availableRecipes.isEmpty();
    }

    public boolean canUse(PlayerEntity playerEntity_1) {
        return canUse(this.blockContext_1, playerEntity_1, NBlocks.SAWMILL);
    }

    public boolean onButtonClick(PlayerEntity playerEntity_1, int int_1) {
        if (int_1 >= 0 && int_1 < this.availableRecipes.size()) {
            this.selectedRecipe.set(int_1);
            this.populateResult();
        }

        return true;
    }

    public void onContentChanged(Inventory inventory_1) {
        ItemStack itemStack_1 = this.inputSlot.getStack();
        if (itemStack_1.getItem() != this.inputStack.getItem()) {
            this.inputStack = itemStack_1.copy();
            this.updateInput(inventory_1, itemStack_1);
        }

    }

    private void updateInput(Inventory inventory_1, ItemStack itemStack_1) {
        this.availableRecipes.clear();
        this.selectedRecipe.set(-1);
        this.outputSlot.setStack(ItemStack.EMPTY);
        if (!itemStack_1.isEmpty()) {
            this.availableRecipes = this.world.getRecipeManager().method_17877(NRecipeType.SAWMILLING, inventory_1, this.world);
        }

    }

    private void populateResult() {
        if (!this.availableRecipes.isEmpty()) {
            SawmillingRecipe stonecuttingRecipe_1 = this.availableRecipes.get(this.selectedRecipe.get());
            this.outputSlot.setStack(stonecuttingRecipe_1.craft(this.inventory));
        } else {
            this.outputSlot.setStack(ItemStack.EMPTY);
        }

        this.sendContentUpdates();
    }

    @Environment(EnvType.CLIENT)
    public void setContentsChangedListener(Runnable runnable_1) {
        this.contentsChangedListener = runnable_1;
    }

    public ItemStack transferSlot(PlayerEntity playerEntity_1, int int_1) {
        ItemStack itemStack_1 = ItemStack.EMPTY;
        Slot slot_1 = this.slotList.get(int_1);
        if (slot_1 != null && slot_1.hasStack()) {
            ItemStack itemStack_2 = slot_1.getStack();
            Item item_1 = itemStack_2.getItem();
            itemStack_1 = itemStack_2.copy();
            if (int_1 == 1) {
                item_1.onCrafted(itemStack_2, playerEntity_1.world, playerEntity_1);
                if (!this.insertItem(itemStack_2, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }

                slot_1.onStackChanged(itemStack_2, itemStack_1);
            } else if (int_1 == 0) {
                if (!this.insertItem(itemStack_2, 2, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (field_17626.contains(item_1)) {
                if (!this.insertItem(itemStack_2, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (int_1 < 29) {
                if (!this.insertItem(itemStack_2, 29, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (int_1 < 38 && !this.insertItem(itemStack_2, 2, 29, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack_2.isEmpty()) {
                slot_1.setStack(ItemStack.EMPTY);
            }

            slot_1.markDirty();
            if (itemStack_2.getAmount() == itemStack_1.getAmount()) {
                return ItemStack.EMPTY;
            }

            slot_1.onTakeItem(playerEntity_1, itemStack_2);
            this.sendContentUpdates();
        }

        return itemStack_1;
    }

    public void close(PlayerEntity playerEntity_1) {
        super.close(playerEntity_1);
        this.inventory.removeInvStack(1);
        this.blockContext_1.run((world_1, blockPos_1) -> {
            this.dropInventory(playerEntity_1, playerEntity_1.world, this.inventory);
        });
    }
}
