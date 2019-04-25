package team.hollow.neutronia.modules.origins;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.sound.BlockSoundGroup;
import team.hollow.module_api.api.Module;
import team.hollow.module_api.api.features.SingleBlockFeature;
import team.hollow.neutronia.blocks.CactusBundleBlock;

public class StorageSubModule extends Module {

    public static SingleBlockFeature stickBundle, chorusBundle, sugarCaneBundle, bambooBundle, netherWartSack, cocoaBeanSack, gunpowderSack,
        eggCrate, beetrootCrate, potatoCrate, carrotCrate, appleCrate, goldenAppleCrate, cactusBundle;

    public StorageSubModule() {
        super("storage", "This module adds a lot of new storage blocks");

        stickBundle = register(new SingleBlockFeature(new PillarBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).build()), "stick_bundle"));
        chorusBundle = register(new SingleBlockFeature(new PillarBlock(FabricBlockSettings.of(Material.ORGANIC).sounds(BlockSoundGroup.CROP).build()), "chorus_fruit_bundle"));
        sugarCaneBundle = register(new SingleBlockFeature(new PillarBlock(FabricBlockSettings.of(Material.ORGANIC).sounds(BlockSoundGroup.CROP).build()), "sugar_cane_bundle"));
        bambooBundle = register(new SingleBlockFeature(new PillarBlock(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.BAMBOO).build()), "bamboo_bundle"));
        cactusBundle = register(new SingleBlockFeature(new CactusBundleBlock(), "cactus_bundle"));

        netherWartSack = register(new SingleBlockFeature(new Block(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOL).build()), "nether_wart_sack"));
        cocoaBeanSack = register(new SingleBlockFeature(new Block(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOL).build()), "cocoa_beans_sack"));
        gunpowderSack = register(new SingleBlockFeature(new Block(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOL).build()), "gunpowder_sack"));

        eggCrate = register(new SingleBlockFeature(new Block(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).build()), "egg_crate"));
        beetrootCrate = register(new SingleBlockFeature(new Block(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).build()), "beetroot_crate"));
        potatoCrate = register(new SingleBlockFeature(new Block(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).build()), "potato_crate"));
        carrotCrate = register(new SingleBlockFeature(new Block(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).build()), "carrot_crate"));
        appleCrate = register(new SingleBlockFeature(new Block(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).build()), "apple_crate"));
        goldenAppleCrate = register(new SingleBlockFeature(new Block(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).build()), "golden_apple_crate"));
    }

}
