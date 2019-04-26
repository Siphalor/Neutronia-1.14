package team.hollow.neutronia.utils.registry;

import generators.RecipeGenerator;
import generators.ShapedRecipeIngredients;
import generators.ShapelessRecipeIngredients;
import net.minecraft.block.Block;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.blocks.*;

public class BlockRegistryBuilder {

    public static final BlockRegistryBuilder INSTANCE = new BlockRegistryBuilder();
    private static Block baseBlock;
    private static String name;
    private static String modId;

    public static BlockRegistryBuilder getInstance(String nameIn, Block blockIn) {
        modId = Neutronia.MOD_ID;
        name = nameIn;
        baseBlock = blockIn;
        return INSTANCE;
    }

    public static BlockRegistryBuilder getInstance(String modIdIn, String nameIn, Block blockIn) {
        modId = modIdIn;
        name = nameIn;
        baseBlock = blockIn;
        return INSTANCE;
    }

    public BlockRegistryBuilder slab() {
        Block slab = new NeutroniaSlabBlock();
        RegistryUtils.register(modId, slab, name + "_slab", ItemGroup.BUILDING_BLOCKS);
        RecipeGenerator.getInstance(Neutronia.MOD_ID)
                .addShaped(new ItemStack(slab, 6), new Identifier(modId, name + "_slab"), "slabs", new String[]{
                        "XXX"
                }, new ShapedRecipeIngredients("X", new ItemStack(baseBlock)));
        return this;
    }

    public BlockRegistryBuilder stair() {
        Block stair = new NeutroniaStairBlock(baseBlock.getDefaultState());
        RegistryUtils.register(modId, stair, name + "_stairs", ItemGroup.BUILDING_BLOCKS);
        RecipeGenerator.getInstance(Neutronia.MOD_ID)
                .addShaped(new ItemStack(stair), new Identifier(modId, name + "_stairs"), "stairs", new String[]{
                        "X  ",
                        "XX ",
                        "XXX"
                }, new ShapedRecipeIngredients("X", new ItemStack(baseBlock)));
        return this;
    }

    public BlockRegistryBuilder fence() {
        Block fence = new NeutroniaFenceBlock(baseBlock);
        RegistryUtils.register(modId, fence, name + "_fence");
        RecipeGenerator.getInstance(Neutronia.MOD_ID)
                .addShaped(
                        new ItemStack(fence),
                        new Identifier(modId, name + "_fence"),
                        "fences",
                        new String[]{
                                "O/O",
                                "O/O"
                        },
                        new ShapedRecipeIngredients("O", new ItemStack(baseBlock)),
                        new ShapedRecipeIngredients("/", new ItemStack(Items.STICK))
                );
        return this;
    }

    public BlockRegistryBuilder fenceGate() {
        Block fenceGate = new NeutroniaFenceGateBlock();
        RegistryUtils.register(modId, fenceGate, name + "_fence_gate", ItemGroup.REDSTONE);
        RecipeGenerator.getInstance(Neutronia.MOD_ID)
                .addShaped(
                        new ItemStack(fenceGate),
                        new Identifier(modId, name + "_fence_gate"),
                        "fence_gates",
                        new String[]{
                            "/O/",
                            "/O/"
                        },
                        new ShapedRecipeIngredients("O", new ItemStack(baseBlock)),
                        new ShapedRecipeIngredients("/", new ItemStack(Items.STICK))
                );
        return this;
    }

    public BlockRegistryBuilder wall() {
        Block wall = new NeutroniaWallBlock(baseBlock.getDefaultState());
        RegistryUtils.register(modId, wall, name + "_wall", ItemGroup.DECORATIONS);
        RecipeGenerator.getInstance(Neutronia.MOD_ID)
                .addShaped(
                        new ItemStack(wall),
                        new Identifier(modId, name + "_wall"),
                        "walls",
                        new String[]{
                                "OOO",
                                "OOO"
                        },
                        new ShapedRecipeIngredients("O", new ItemStack(baseBlock))
                );
        return this;
    }

    public BlockRegistryBuilder button(boolean wooden) {
        Block button = new NeutroniaButtonBlock(wooden);
        RegistryUtils.register(modId, button, name + "_button", ItemGroup.REDSTONE);
        RecipeGenerator.getInstance(Neutronia.MOD_ID)
                .addShapeless(
                        new ItemStack(button),
                        new Identifier(modId, name + "_button"),
                        "buttons",
                        new ShapelessRecipeIngredients(new ItemStack(baseBlock))
                );
        return this;
    }

    public BlockRegistryBuilder pressurePlate(PressurePlateBlock.Type type) {
        Block pressurePlate = new NeutroniaPressurePlateBlock(baseBlock.getMaterial(baseBlock.getDefaultState()), type);
        RegistryUtils.register(modId, pressurePlate, name + "_pressure_plate", ItemGroup.REDSTONE);
        RecipeGenerator.getInstance(Neutronia.MOD_ID)
                .addShaped(new ItemStack(pressurePlate, 6), new Identifier(modId, name + "_pressure_plate"), "pressure_plates", new String[]{
                        "XX"
                }, new ShapedRecipeIngredients("X", new ItemStack(baseBlock)));
        return this;
    }

    public BlockRegistryBuilder corner() {
        Block corner = new NeutroniaCornerBlock(baseBlock.getDefaultState(),Block.Settings.copy(baseBlock));
        RegistryUtils.register(modId, corner, name + "_corner");
        return this;
    }

    public BlockRegistryBuilder post() {
        Block post = new NeutroniaPostBlock(Block.Settings.copy(baseBlock));
        RegistryUtils.register(modId, post, name + "_post");
        return this;
    }

    public BlockRegistryBuilder siding() {
        Block siding = new NeutroniaSidingBlock(Block.Settings.copy(baseBlock));
        RegistryUtils.register(modId, siding, name + "_siding");
        RecipeGenerator.getInstance(Neutronia.MOD_ID)
                .addShaped(new ItemStack(siding, 6), new Identifier(modId, name + "_siding"), "sidings", new String[]{
                        "X",
                        "X",
                        "X"
                }, new ShapedRecipeIngredients("X", new ItemStack(baseBlock)));
        RecipeGenerator.getInstance(Neutronia.MOD_ID)
                .addShapeless(
                        new ItemStack(siding, 6),
                        new Identifier(modId, name + "_siding_to_slab"),
                        "sidings_to_slabs",
                        new ShapelessRecipeIngredients(new ItemStack(siding))
                );
        RecipeGenerator.getInstance(Neutronia.MOD_ID)
                .addShapeless(
                        new ItemStack(siding, 6),
                        new Identifier(modId, name + "_siding_to_block"),
                        "sidings_to_blocks",
                        new ShapelessRecipeIngredients(new ItemStack(siding)),
                        new ShapelessRecipeIngredients(new ItemStack(siding))
                );
        RecipeGenerator.getInstance(Neutronia.MOD_ID)
                .addStonecutting(
                        new ItemStack(siding, 6),
                        new Identifier(modId, String.format("%s_siding_from_%s_stonecutting", name, Registry.BLOCK.getId(baseBlock).getPath())),
                        "siding_from_block_stonecutting",
                        new ShapelessRecipeIngredients(new ItemStack(siding))
                );
        return this;
    }

}
