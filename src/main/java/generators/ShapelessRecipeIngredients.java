package generators;

import net.minecraft.item.ItemStack;

public class ShapelessRecipeIngredients {

    private ItemStack stack;

    public ShapelessRecipeIngredients(ItemStack stack) {
        this.stack = stack;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ItemStack getStack() {
        return stack;
    }

    public static class Builder {

        private ItemStack stack;

        Builder withIngredient(ItemStack stack) {
            this.stack = stack;
            return this;
        }

        public ShapelessRecipeIngredients build() {
            return new ShapelessRecipeIngredients(stack);
        }

    }

}