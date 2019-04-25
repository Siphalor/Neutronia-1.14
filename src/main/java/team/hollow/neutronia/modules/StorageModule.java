package team.hollow.neutronia.modules;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.StringTextComponent;
import team.hollow.module_api.api.Module;
import team.hollow.neutronia.blocks.CactusBundleBlock;

public class StorageModule extends Module {

    public static SingleBlockFeature STICK_BUNDLE, CHORUS_BUNDLE, SUGAR_CANE_BUNDLE, BAMBOO_BUNDLE, NETHER_WART_SACK, COCOA_BEAN_SACK, GUNPOWDER_SACK,
            EGG_CRATE, BEETROOT_CRATE, POTATO_CRATE, CARROT_CRATE, APPLE_CRATE, GOLDEN_APPLE_CRATE, CACTUS_BUNDLE;

    public StorageModule() {
        super("storage_module", new ItemStack(Items.BONE_BLOCK), new StringTextComponent("This module adds a lot of new storage blocks"));

        STICK_BUNDLE = register(new SingleBlockFeature(new PillarBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).build()), "stick_bundle"));
        CHORUS_BUNDLE = register(new SingleBlockFeature(new PillarBlock(FabricBlockSettings.of(Material.ORGANIC).sounds(BlockSoundGroup.CROP).build()), "chorus_fruit_bundle"));
        SUGAR_CANE_BUNDLE = register(new SingleBlockFeature(new PillarBlock(FabricBlockSettings.of(Material.ORGANIC).sounds(BlockSoundGroup.CROP).build()), "sugar_cane_bundle"));
        BAMBOO_BUNDLE = register(new SingleBlockFeature(new PillarBlock(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO).build()), "bamboo_bundle"));
        CACTUS_BUNDLE = register(new SingleBlockFeature(new CactusBundleBlock(), "cactus_bundle"));

        NETHER_WART_SACK = register(new SingleBlockFeature(new Block(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOL).build()), "nether_wart_sack"));
        COCOA_BEAN_SACK = register(new SingleBlockFeature(new Block(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOL).build()), "cocoa_beans_sack"));
        GUNPOWDER_SACK = register(new SingleBlockFeature(new Block(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOL).build()), "gunpowder_sack"));

        EGG_CRATE = register(new SingleBlockFeature(new Block(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).build()), "egg_crate"));
        BEETROOT_CRATE = register(new SingleBlockFeature(new Block(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).build()), "beetroot_crate"));
        POTATO_CRATE = register(new SingleBlockFeature(new Block(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).build()), "potato_crate"));
        CARROT_CRATE = register(new SingleBlockFeature(new Block(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).build()), "carrot_crate"));
        APPLE_CRATE = register(new SingleBlockFeature(new Block(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).build()), "apple_crate"));
        GOLDEN_APPLE_CRATE = register(new SingleBlockFeature(new Block(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).build()), "golden_apple_crate"));
    }

}
