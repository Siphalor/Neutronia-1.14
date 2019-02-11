package generators;

import net.minecraft.item.ItemStack;

public class ShapedRecipeIngredients {

    private String pattern;
    private ItemStack stack;

    public static Builder builder() {
        return new Builder();
    }

    private ShapedRecipeIngredients(String pattern, ItemStack stack) {
        this.pattern = pattern;
        this.stack = stack;
    }

    public String getPattern() {
        return pattern;
    }

    public ItemStack getStack() {
        return stack;
    }

    public static class Builder {

        private String pattern;
        private ItemStack stack;

        public Builder withIngredient(String pattern, ItemStack stack) {
            this.pattern = pattern;
            this.stack = stack;
            return this;
        }

        public ShapedRecipeIngredients build() {
            return new ShapedRecipeIngredients(pattern, stack);
        }

    }

}