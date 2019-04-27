package team.hollow.neutronia.unsure;

import generators.RecipeGenerator;
import generators.ShapedRecipeIngredients;
import generators.ShapelessRecipeIngredients;
import net.minecraft.block.Block;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.blocks.*;
import team.hollow.neutronia.utils.registry.RegistryUtils;

public class ContentRegistryBuilder extends ContentBuilder {
    public Block baseBlock;
    public ItemProvider baseItemProvider;
    private ItemProvider secondaryItemProvider;
    public Identifier baseIdentifier;

    @Override
    public Item newItem(String name, Item item) {
        return RegistryUtils.registerItem(item, name);
    }

    @Override
    public Block newBlock(String name, Block block) {
        return RegistryUtils.register(block, new Identifier(Neutronia.MOD_ID, name));
    }

    @Override
    public Block newBlock(String name, Block block, String textureName) {
        return newBlock(name, block);
    }

    @Override
    public Block newCompressedBlock(String name, Block block) {
        newBlock(name, block);

        RecipeGenerator.getInstance(Neutronia.MOD_ID)
            .addShaped(new ItemStack(block),
                new Identifier(Neutronia.MOD_ID, "compress_to_" + name),
                "compression",
               new String[]{
                   "XXX", "XXX", "XXX"
               }, new ShapedRecipeIngredients("X", new ItemStack(baseItemProvider)))
            .addShapeless(new ItemStack(baseItemProvider, 9),
                new Identifier(Neutronia.MOD_ID, "decompress_"),
                "decompression",
                new ShapelessRecipeIngredients(new ItemStack(block)));

        return block;
    }

    @Override
    public void setBaseName(String name) {
        baseIdentifier = new Identifier(Neutronia.MOD_ID, name);
    }

    @Override
    public void setBaseBlock(Block baseBlock) {
        this.baseBlock = baseBlock;
    }

    @Override
    public void setBaseItem(ItemProvider itemProvider) {
        baseItemProvider = itemProvider;
    }

    @Override
    public void setSecondaryItem(ItemProvider itemProvider) {
        secondaryItemProvider = itemProvider;
    }

    @Override
    public Block slab() {
        Block slab = new NeutroniaSlabBlock();
        RegistryUtils.register(slab, extendIdentifier(baseIdentifier, "_slab"), ItemGroup.BUILDING_BLOCKS);

        RecipeGenerator.getInstance(Neutronia.MOD_ID)
            .addShaped(new ItemStack(slab), extendIdentifier(baseIdentifier, "_slab"), "slabs", new String[]{
                "XXX"
            }, new ShapedRecipeIngredients("X", new ItemStack(baseItemProvider)));

        return slab;
    }

    @Override
    public Block stairs() {
        Block stairs = new NeutroniaStairBlock(baseBlock.getDefaultState());
        RegistryUtils.register(stairs, extendIdentifier(baseIdentifier, "_stairs"), ItemGroup.BUILDING_BLOCKS);

        RecipeGenerator.getInstance(Neutronia.MOD_ID)
            .addShaped(new ItemStack(stairs), extendIdentifier(baseIdentifier, "_stairs"), "stairs", new String[]{
                "X  ",
                "XX ",
                "XXX"
            }, new ShapedRecipeIngredients("X", new ItemStack(baseItemProvider)));

        return stairs;
    }

    @Override
    public Block fence() {
        Block fence = new NeutroniaFenceBlock(baseBlock);
        RegistryUtils.register(fence, extendIdentifier(baseIdentifier, "_fence"));

        RecipeGenerator.getInstance(Neutronia.MOD_ID)
            .addShaped(
                new ItemStack(fence),
                extendIdentifier(baseIdentifier, "_fence"),
                "fences",
                new String[]{
                    "O/O",
                    "O/O"
                },
                new ShapedRecipeIngredients("O", new ItemStack(baseItemProvider)),
                new ShapedRecipeIngredients("/", new ItemStack(secondaryItemProvider))
            );
        return fence;
    }

    @Override
    public Block fenceGate() {
        Block fenceGate = new NeutroniaFenceGateBlock();
        RegistryUtils.register(fenceGate, extendIdentifier(baseIdentifier, "_fence_gate"), ItemGroup.REDSTONE);

        RecipeGenerator.getInstance(Neutronia.MOD_ID)
            .addShaped(
                new ItemStack(fenceGate),
                extendIdentifier(baseIdentifier, "_fence_gate"),
                "fence_gates",
                new String[]{
                    "/O/",
                    "/O/"
                },
                new ShapedRecipeIngredients("O", new ItemStack(baseItemProvider)),
                new ShapedRecipeIngredients("/", new ItemStack(secondaryItemProvider))
            );

        return fenceGate;
    }

    @Override
    public Block wall() {
        Block wall = new NeutroniaWallBlock(baseBlock.getDefaultState());
        RegistryUtils.register(wall, extendIdentifier(baseIdentifier, "_wall"), ItemGroup.DECORATIONS);

        RecipeGenerator.getInstance(Neutronia.MOD_ID)
            .addShaped(
                new ItemStack(wall),
                extendIdentifier(baseIdentifier, "_wall"),
                "walls",
                new String[]{
                    "OOO",
                    "OOO"
                },
                new ShapedRecipeIngredients("O", new ItemStack(baseItemProvider))
            );

        return wall;
    }

    @Override
    public Block button(boolean wooden) {
        Block button = new NeutroniaButtonBlock(wooden);
        RegistryUtils.register(button, extendIdentifier(baseIdentifier, "_button"), ItemGroup.REDSTONE);

        RecipeGenerator.getInstance(Neutronia.MOD_ID)
            .addShapeless(
                new ItemStack(button),
                extendIdentifier(baseIdentifier, "_button"),
                "buttons",
                new ShapelessRecipeIngredients(new ItemStack(baseItemProvider))
            );

        return button;
    }

    @Override
    public Block pressurePlate(PressurePlateBlock.Type type) {
        Block pressurePlate = new NeutroniaPressurePlateBlock(baseBlock.getMaterial(baseBlock.getDefaultState()), type);
        RegistryUtils.register(pressurePlate, extendIdentifier(baseIdentifier, "_pressure_plate"), ItemGroup.REDSTONE);

        RecipeGenerator.getInstance(Neutronia.MOD_ID)
            .addShaped(new ItemStack(pressurePlate, 6), extendIdentifier(baseIdentifier, "_pressure_plate"), "pressure_plates", new String[]{
                "XX"
            }, new ShapedRecipeIngredients("X", new ItemStack(baseItemProvider)));

        return pressurePlate;
    }

    @Override
    public Block corner() {
        Block corner = new NeutroniaCornerBlock(baseBlock.getDefaultState(),Block.Settings.copy(baseBlock));
        RegistryUtils.register(corner, extendIdentifier(baseIdentifier, "_corner"));
        return corner;
    }

    @Override
    public Block post() {
        Block post = new NeutroniaPostBlock(Block.Settings.copy(baseBlock));
        RegistryUtils.register(post, extendIdentifier(baseIdentifier, "_post"));
        return post;
    }

    @Override
    public Block siding() {
        Block siding = new NeutroniaSidingBlock(Block.Settings.copy(baseBlock));
        RegistryUtils.register(siding, extendIdentifier(baseIdentifier, "_siding"));

        RecipeGenerator.getInstance(Neutronia.MOD_ID)
            .addShaped(new ItemStack(siding, 6), extendIdentifier(baseIdentifier, "_siding"), "sidings", new String[]{
                "X",
                "X",
                "X"
            }, new ShapedRecipeIngredients("X", new ItemStack(baseItemProvider)));
        RecipeGenerator.getInstance(Neutronia.MOD_ID)
            .addShapeless(
                new ItemStack(siding, 6),
                extendIdentifier(baseIdentifier, "_siding_to_slab"),
                "sidings_to_slabs",
                new ShapelessRecipeIngredients(new ItemStack(siding))
            );
        RecipeGenerator.getInstance(Neutronia.MOD_ID)
            .addShapeless(
                new ItemStack(siding, 6),
                extendIdentifier(baseIdentifier, "siding_to_block"),
                "sidings_to_blocks",
                new ShapelessRecipeIngredients(new ItemStack(siding)),
                new ShapelessRecipeIngredients(new ItemStack(siding))
            );
        RecipeGenerator.getInstance(Neutronia.MOD_ID)
            .addStonecutting(
                new ItemStack(siding, 6),
                new Identifier(baseIdentifier.getNamespace(), baseIdentifier.getPath() + "_siding_from_" + baseIdentifier.getPath() + "_stonecutting"),
                "siding_from_block_stonecutting",
                new ShapelessRecipeIngredients(new ItemStack(siding))
            );

        return siding;
    }
}
