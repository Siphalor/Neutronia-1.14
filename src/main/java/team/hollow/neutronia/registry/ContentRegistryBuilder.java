package team.hollow.neutronia.registry;

import net.minecraft.block.Block;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SignBlock;
import net.minecraft.block.WallSignBlock;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.blocks.*;
import team.hollow.neutronia.utils.RecipeGenerator;
import team.hollow.neutronia.utils.ShapedRecipeIngredients;
import team.hollow.neutronia.utils.ShapelessRecipeIngredients;
import team.hollow.neutronia.utils.registry.RegistryUtils;

public class ContentRegistryBuilder extends ContentBuilder {
    public Block baseBlock;
    public ItemProvider baseItemProvider;
    private ItemProvider secondaryItemProvider;
    protected String modId;

    public ContentRegistryBuilder(String modId) {
        this.modId = modId;
    }

    @Override
    public String getModId() {
        return modId;
    }

    @Override
    public void finish() {

    }

    @Override
    public Item newItem(String name, Item item) {
        return RegistryUtils.registerItem(item, name);
    }

    @Override
    public Block newBlock(String name, Block block) {
        if(block instanceof NoBlockItem) {
            return RegistryUtils.registerNoBI(block, new Identifier(getModId(), name));
        }
        return RegistryUtils.register(block, new Identifier(getModId(), name));
    }

    @Override
    public Block newBlock(String name, Block block, String textureName) {
        return newBlock(name, block);
    }

    @Override
    public Block newCompressedBlock(String name, Block block) {
        newBlock(name, block);

        RecipeGenerator.getInstance(getModId())
            .addShaped(new ItemStack(block),
                new Identifier(getModId(), "compress_to_" + name),
                "compression",
               new String[]{
                   "XXX", "XXX", "XXX"
               }, new ShapedRecipeIngredients("X", new ItemStack(baseItemProvider)))
            .addShapeless(new ItemStack(baseItemProvider, 9),
                new Identifier(getModId(), "decompress_"),
                "decompression",
                new ShapelessRecipeIngredients(new ItemStack(block)));

        return block;
    }

    @Override
    public void setBaseTexture(Identifier name) {

    }

    @Override
    public void setBaseName(Identifier name) {
        baseNameIdentifier = name;
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
        Block slab = new NeutroniaSlabBlock(Block.Settings.copy(baseBlock));
        RegistryUtils.register(slab, extendIdentifier("_slab"), ItemGroup.BUILDING_BLOCKS);

        RecipeGenerator.getInstance(getModId())
            .addShaped(new ItemStack(slab), extendIdentifier("_slab"), "slabs", new String[]{
                "XXX"
            }, new ShapedRecipeIngredients("X", new ItemStack(baseItemProvider)));

        return slab;
    }

    @Override
    public Block stairs() {
        Block stairs = new NeutroniaStairBlock(baseBlock.getDefaultState());
        RegistryUtils.register(stairs, extendIdentifier("_stairs"), ItemGroup.BUILDING_BLOCKS);

        RecipeGenerator.getInstance(getModId())
            .addShaped(new ItemStack(stairs), extendIdentifier("_stairs"), "stairs", new String[]{
                "X  ",
                "XX ",
                "XXX"
            }, new ShapedRecipeIngredients("X", new ItemStack(baseItemProvider)));

        return stairs;
    }

    @Override
    public Block fence() {
        Block fence = new NeutroniaFenceBlock(Block.Settings.copy(baseBlock));
        RegistryUtils.register(fence, extendIdentifier("_fence"));

        RecipeGenerator.getInstance(getModId())
            .addShaped(
                new ItemStack(fence),
                extendIdentifier("_fence"),
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
        Block fenceGate = new NeutroniaFenceGateBlock(Block.Settings.copy(baseBlock));
        RegistryUtils.register(fenceGate, extendIdentifier("_fence_gate"), ItemGroup.REDSTONE);

        RecipeGenerator.getInstance(getModId())
            .addShaped(
                new ItemStack(fenceGate),
                extendIdentifier("_fence_gate"),
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
    public Block door() {
        Block door = new NeutroniaDoorBlock(Block.Settings.copy(baseBlock));
        RegistryUtils.register(door, extendIdentifier("_door"));
        return door;
    }

    @Override
    public Block trapDoor() {
        Block trapDoor = new NeutroniaTrapdoorBlock(Block.Settings.copy(baseBlock));
        RegistryUtils.register(trapDoor, extendIdentifier("_trapdoor"));
        return trapDoor;
    }

    @Override
    public Block grate() {
        Block grate = new NeutroniaTrapdoorBlock(Block.Settings.copy(baseBlock));
        RegistryUtils.register(grate, extendIdentifier("_grate"));
        return grate;
    }

    @Override
    public Block wall() {
        Block wall = new NeutroniaWallBlock(Block.Settings.copy(baseBlock));
        RegistryUtils.register(wall, extendIdentifier("_wall"), ItemGroup.DECORATIONS);

        RecipeGenerator.getInstance(getModId())
            .addShaped(
                new ItemStack(wall),
                extendIdentifier("_wall"),
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
        Block button = new NeutroniaButtonBlock(wooden, Block.Settings.copy(baseBlock));
        RegistryUtils.register(button, extendIdentifier("_button"), ItemGroup.REDSTONE);

        RecipeGenerator.getInstance(getModId())
            .addShapeless(
                new ItemStack(button),
                extendIdentifier("_button"),
                "buttons",
                new ShapelessRecipeIngredients(new ItemStack(baseItemProvider))
            );

        return button;
    }

    @Override
    public Block pressurePlate(PressurePlateBlock.Type type) {
        Block pressurePlate = new NeutroniaPressurePlateBlock(Block.Settings.copy(baseBlock), type);
        RegistryUtils.register(pressurePlate, extendIdentifier("_pressure_plate"), ItemGroup.REDSTONE);

        RecipeGenerator.getInstance(getModId())
            .addShaped(new ItemStack(pressurePlate, 6), extendIdentifier("_pressure_plate"), "pressure_plates", new String[]{
                "XX"
            }, new ShapedRecipeIngredients("X", new ItemStack(baseItemProvider)));

        return pressurePlate;
    }

    @Override
    public Block corner() {
        Block corner = new NeutroniaCornerBlock(baseBlock.getDefaultState(),Block.Settings.copy(baseBlock));
        RegistryUtils.register(corner, extendIdentifier("_corner"));
        return corner;
    }

    @Override
    public Block post() {
        Block post = new NeutroniaPostBlock(Block.Settings.copy(baseBlock));
        RegistryUtils.register(post, extendIdentifier("_post"));
        return post;
    }

    @Override
    public Block sign() {
        Block wallSign = new WallSignBlock(Block.Settings.copy(baseBlock));
        RegistryUtils.registerNoBI(wallSign, extendIdentifier("_wall_sign"));
        Block sign = new SignBlock(Block.Settings.copy(baseBlock));
        RegistryUtils.registerNoBI(sign, extendIdentifier("_sign"));
        Item signItem = newItem(baseNameIdentifier.getPath() + "_sign", new SignItem(new Item.Settings().itemGroup(ItemGroup.DECORATIONS), sign, wallSign));

        RecipeGenerator.getInstance(getModId()).addShaped(new ItemStack(sign), extendIdentifier("_sign"), "signs", new String[] {
            "PPP",
            "PPP",
            " R "
        },
            new ShapedRecipeIngredients("P", new ItemStack(baseItemProvider)),
            new ShapedRecipeIngredients("R", new ItemStack(secondaryItemProvider)));
        return sign;
    }

    @Override
    public Block siding() {
        Block siding = new NeutroniaSidingBlock(Block.Settings.copy(baseBlock));
        Identifier identifier = extendIdentifier("_siding");
        RegistryUtils.register(siding, identifier);

        RecipeGenerator.getInstance(getModId())
            .addShaped(new ItemStack(siding, 6), extendIdentifier( "_siding"), "sidings", new String[]{
                "X",
                "X",
                "X"
            }, new ShapedRecipeIngredients("X", new ItemStack(baseItemProvider)));
        RecipeGenerator.getInstance(getModId())
            .addShapeless(
                new ItemStack(siding, 6),
                extendIdentifier( "_siding_to_slab"),
                "sidings_to_slabs",
                new ShapelessRecipeIngredients(new ItemStack(siding))
            );
        RecipeGenerator.getInstance(getModId())
            .addShapeless(
                new ItemStack(siding, 6),
                extendIdentifier( "_siding_to_block"),
                "sidings_to_blocks",
                new ShapelessRecipeIngredients(new ItemStack(siding)),
                new ShapelessRecipeIngredients(new ItemStack(siding))
            );
        RecipeGenerator.getInstance(getModId())
            .addStonecutting(
                new ItemStack(siding, 6),
                new Identifier(baseNameIdentifier.getNamespace(), baseNameIdentifier.getPath() + "_siding_from_" + baseNameIdentifier.getPath() + "_stonecutting"),
                "siding_from_block_stonecutting",
                new ShapelessRecipeIngredients(new ItemStack(siding))
            );

        return siding;
    }

    @Override
    public Block addPotted() {
        return addPotted(null);
    }

    @Override
    public Block addPotted(Identifier plantTexture) {
        return RegistryUtils.registerNoBI(new NeutroniaFlowerPotBlock(baseBlock), new Identifier(getModId(), "potted_" + baseNameIdentifier.getPath()));
    }
}