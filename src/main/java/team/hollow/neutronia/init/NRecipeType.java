package team.hollow.neutronia.init;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.hollow.neutronia.recipe.SawmillingRecipe;

public class NRecipeType {

    public static RecipeType<SawmillingRecipe> SAWMILLING;

    public static void init() {
        SAWMILLING = register("sawmilling");
    }

    private static <T extends Recipe<?>> RecipeType<T> register(final String string_1) {
        return Registry.register(Registry.RECIPE_TYPE, new Identifier("neutronia", string_1), new RecipeType<T>() {
            public String toString() {
                return string_1;
            }
        });
    }

}
