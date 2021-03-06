package team.hollow.neutronia.init;

import generators.RecipeGenerator;
import generators.ShapedRecipeIngredients;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;

public class NRecipes {

    static {
        String[] pattern = new String[]{
                "###",
                "###",
                "###"
        };

        RecipeGenerator.getInstance(Neutronia.MOD_ID)
                //Bundles
                .addShaped(new ItemStack(NBlocks.STICK_BUNDLE, 4), new Identifier(Neutronia.MOD_ID, "stick_bundle"), "storage_bundles", pattern,
                        ShapedRecipeIngredients.builder()
                                .withIngredient("#", new ItemStack(Items.STICK))
                                .build()
                )
                .addShaped(new ItemStack(NBlocks.CHORUS_BUNDLE), new Identifier(Neutronia.MOD_ID, "chorus_bundle"), "storage_bundles", pattern,
                        ShapedRecipeIngredients.builder()
                                .withIngredient("#", new ItemStack(Items.CHORUS_FRUIT))
                                .build()
                )
                .addShaped(new ItemStack(NBlocks.SUGAR_CANE_BUNDLE), new Identifier(Neutronia.MOD_ID, "sugar_cane_bundle"), "storage_bundles", pattern,
                        ShapedRecipeIngredients.builder()
                                .withIngredient("#", new ItemStack(Blocks.SUGAR_CANE))
                                .build()
                )
                .addShaped(new ItemStack(NBlocks.BAMBOO_BUNDLE), new Identifier(Neutronia.MOD_ID, "bamboo_bundle"), "storage_bundles", pattern,
                        ShapedRecipeIngredients.builder()
                                .withIngredient("#", new ItemStack(Blocks.BAMBOO))
                                .build()
                )
                .addShaped(new ItemStack(NBlocks.CACTUS_BUNDLE), new Identifier(Neutronia.MOD_ID, "cactus_bundle"), "storage_bundles", pattern,
                        ShapedRecipeIngredients.builder()
                                .withIngredient("#", new ItemStack(Blocks.CACTUS))
                                .build()
                )
                //Sacks
                .addShaped(new ItemStack(NBlocks.NETHER_WART_SACK), new Identifier(Neutronia.MOD_ID, "nether_wart_sack"), "storage_sacks", pattern,
                        ShapedRecipeIngredients.builder()
                                .withIngredient("#", new ItemStack(Items.NETHER_WART))
                                .build()
                )
                .addShaped(new ItemStack(NBlocks.COCOA_BEAN_SACK), new Identifier(Neutronia.MOD_ID, "cocoa_bean_sack"), "storage_sacks", pattern,
                        ShapedRecipeIngredients.builder()
                                .withIngredient("#", new ItemStack(Items.COCOA_BEANS))
                                .build()
                )
                .addShaped(new ItemStack(NBlocks.GUNPOWDER_SACK), new Identifier(Neutronia.MOD_ID, "gunpowder_sack"), "storage_sacks", pattern,
                        ShapedRecipeIngredients.builder()
                                .withIngredient("#", new ItemStack(Items.GUNPOWDER))
                                .build()
                )
                //Crates
                .addShaped(new ItemStack(NBlocks.EGG_CRATE), new Identifier(Neutronia.MOD_ID, "egg_crate"), "storage_crates", pattern,
                        ShapedRecipeIngredients.builder()
                                .withIngredient("#", new ItemStack(Items.CHORUS_FRUIT))
                                .build()
                )
                .addShaped(new ItemStack(NBlocks.BEETROOT_CRATE), new Identifier(Neutronia.MOD_ID, "beetroot_crate"), "storage_crates", pattern,
                        ShapedRecipeIngredients.builder()
                                .withIngredient("#", new ItemStack(Items.BEETROOT))
                                .build()
                )
                .addShaped(new ItemStack(NBlocks.POTATO_CRATE), new Identifier(Neutronia.MOD_ID, "potato_crate"), "storage_crates", pattern,
                        ShapedRecipeIngredients.builder()
                                .withIngredient("#", new ItemStack(Items.POTATO))
                                .build()
                )
                .addShaped(new ItemStack(NBlocks.CARROT_CRATE), new Identifier(Neutronia.MOD_ID, "carrot_crate"), "storage_crates", pattern,
                        ShapedRecipeIngredients.builder()
                                .withIngredient("#", new ItemStack(Items.CARROT))
                                .build()
                )
                .addShaped(new ItemStack(NBlocks.APPLE_CRATE), new Identifier(Neutronia.MOD_ID, "apple_crate"), "storage_crates", pattern,
                        ShapedRecipeIngredients.builder()
                                .withIngredient("#", new ItemStack(Items.APPLE))
                                .build()
                )
                .addShaped(new ItemStack(NBlocks.GOLDEN_APPLE_CRATE), new Identifier(Neutronia.MOD_ID, "golden_apple_crate"), "storage_crates", pattern,
                        ShapedRecipeIngredients.builder()
                                .withIngredient("#", new ItemStack(Items.GOLDEN_APPLE))
                                .build()
                );
    }

}
