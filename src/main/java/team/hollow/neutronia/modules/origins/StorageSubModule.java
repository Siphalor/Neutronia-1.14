package team.hollow.neutronia.modules.origins;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
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

        stickBundle = register(new CompressionFeature(new PillarBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(1.0F, 2.5F).build()), "stick_bundle", Items.STICK, "sticks"));
        chorusBundle = register(new CompressionFeature(new PillarBlock(FabricBlockSettings.of(Material.ORGANIC).sounds(BlockSoundGroup.CROP).strength(1.0F, 2.5F).build()), "chorus_bundle", Items.CHORUS_FRUIT, "chorus fruits"));
        sugarCaneBundle = register(new CompressionFeature(new PillarBlock(FabricBlockSettings.of(Material.ORGANIC).sounds(BlockSoundGroup.CROP).strength(1.0F, 2.5F).build()), "sugar_cane_bundle", Items.SUGAR_CANE, "sugar cane"));
        bambooBundle = register(new CompressionFeature(new PillarBlock(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO).strength(1.0F, 2.5F).build()), "bamboo_bundle", Items.BAMBOO, "bamboo"));
        cactusBundle = register(new CompressionFeature(new CactusBundleBlock(), "cactus_bundle", Items.CACTUS, "cacti"));

        netherWartSack = register(new CompressionFeature(new NeutroniaBottomTopBlock(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOL).strength(1.0F, 2.5F)), "nether_wart_sack", Items.NETHER_WART, "nether warts"));
        cocoaBeanSack = register(new CompressionFeature(new NeutroniaBottomTopBlock(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOL).strength(1.0F, 2.5F)), "cocoa_bean_sack", Items.COCOA_BEANS, "cocoa beans"));
        gunpowderSack = register(new CompressionFeature(new NeutroniaBottomTopBlock(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOL).strength(1.0F, 2.5F)), "gunpowder_sack", Items.GUNPOWDER, "gunpowder"));

        eggCrate = register(new CompressionFeature(new NeutroniaBottomTopBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(0.7F, 1.8F)), "egg_crate", Items.EGG, "eggs"));
        beetrootCrate = register(new CompressionFeature(new NeutroniaBottomTopBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(1.0F, 2.5F)), "beetroot_crate", Items.BEETROOT, "beetroots"));
        potatoCrate = register(new CompressionFeature(new NeutroniaBottomTopBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(1.0F, 2.5F)), "potato_crate", Items.POTATO, "potatoes"));
        carrotCrate = register(new CompressionFeature(new NeutroniaBottomTopBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(1.0F, 2.5F)), "carrot_crate", Items.CARROT, "carrots"));
        appleCrate = register(new CompressionFeature(new NeutroniaBottomTopBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(1.0F, 2.5F)), "apple_crate", Items.APPLE, "apples"));
        goldenAppleCrate = register(new CompressionFeature(new NeutroniaBottomTopBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(1.0F, 2.5F)), "golden_apple_crate", Items.GOLDEN_APPLE, "golden apples"));
    }

}
