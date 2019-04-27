package team.hollow.neutronia.modules.origins;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import team.hollow.module_api.api.Module;
import team.hollow.module_api.api.features.SingleBlockFeature;
import team.hollow.neutronia.blocks.CactusBundleBlock;
import team.hollow.neutronia.blocks.NeutroniaBottomTopBlock;
import team.hollow.neutronia.modules.origins.storage.CompressionFeature;

public class StorageSubModule extends Module {

    public static SingleBlockFeature stickBundle, chorusBundle, sugarCaneBundle, bambooBundle, netherWartSack, cocoaBeanSack, gunpowderSack,
        eggCrate, beetrootCrate, potatoCrate, carrotCrate, appleCrate, goldenAppleCrate, cactusBundle;

    public StorageSubModule() {
        super("storage", "This module adds a lot of new storage blocks");

        stickBundle = register(new CompressionFeature(new PillarBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).build()), "stick_bundle", Items.STICK, "sticks"));
        chorusBundle = register(new CompressionFeature(new PillarBlock(FabricBlockSettings.of(Material.ORGANIC).sounds(BlockSoundGroup.CROP).build()), "chorus_fruit_bundle", Items.CHORUS_FRUIT, "chorus fruits"));
        sugarCaneBundle = register(new CompressionFeature(new PillarBlock(FabricBlockSettings.of(Material.ORGANIC).sounds(BlockSoundGroup.CROP).build()), "sugar_cane_bundle", Items.SUGAR_CANE, "sugar cane"));
        bambooBundle = register(new CompressionFeature(new PillarBlock(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO).build()), "bamboo_bundle", Items.BAMBOO, "bamboo"));
        cactusBundle = register(new CompressionFeature(new CactusBundleBlock(), "cactus_bundle", Items.CACTUS, "cacti"));

        netherWartSack = register(new CompressionFeature(new NeutroniaBottomTopBlock(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOL)), "nether_wart_sack", Items.NETHER_WART, "nether warts"));
        cocoaBeanSack = register(new CompressionFeature(new NeutroniaBottomTopBlock(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOL)), "cocoa_beans_sack", Items.COCOA_BEANS, "cocoa beans"));
        gunpowderSack = register(new CompressionFeature(new NeutroniaBottomTopBlock(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOL)), "gunpowder_sack", Items.GUNPOWDER, "gunpowder"));

        eggCrate = register(new CompressionFeature(new NeutroniaBottomTopBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD)), "egg_crate", Items.EGG, "eggs"));
        beetrootCrate = register(new CompressionFeature(new NeutroniaBottomTopBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD)), "beetroot_crate", Items.BEETROOT, "beetroots"));
        potatoCrate = register(new CompressionFeature(new NeutroniaBottomTopBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD)), "potato_crate", Items.POTATO, "potatoes"));
        carrotCrate = register(new CompressionFeature(new NeutroniaBottomTopBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD)), "carrot_crate", Items.CARROT, "carrots"));
        appleCrate = register(new CompressionFeature(new NeutroniaBottomTopBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD)), "apple_crate", Items.APPLE, "apples"));
        goldenAppleCrate = register(new CompressionFeature(new NeutroniaBottomTopBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD)), "golden_apple_crate", Items.GOLDEN_APPLE, "golden apples"));
    }

}
