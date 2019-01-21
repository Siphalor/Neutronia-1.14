package team.abnormals.neutronia.init;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import team.abnormals.neutronia.blocks.BlockCactusBundle;
import team.abnormals.neutronia.blocks.BlockNeutroniaPillar;

public class StorageBlocks {

    public static Block STICK_BUNDLE, CHORUS_BUNDLE, SUGAR_CANE_BUNDLE, BAMBOO_BUNDLE, NETHER_WART_SACK, COCOA_BEAN_SACK, GUNPOWDER_SACK,
            EGG_CRATE, BEETROOT_CRATE, POTATO_CRATE, CARROT_CRATE, APPLE_CRATE, GOLDEN_APPLE_CRATE, CACTUS_BUNDLE;

    public static void init() {
        STICK_BUNDLE = new BlockNeutroniaPillar(Material.WOOD, "stick_bundle");
        CHORUS_BUNDLE = new BlockNeutroniaPillar(Material.PLANT, "chorus_bundle");
        SUGAR_CANE_BUNDLE = new BlockNeutroniaPillar(Material.PLANT, "sugar_cane_bundle");
        BAMBOO_BUNDLE = new BlockNeutroniaPillar(Material.PLANT, "bamboo_bundle");
        CACTUS_BUNDLE = new BlockCactusBundle();

        NETHER_WART_SACK = new BlockNeutroniaPillar(Material.WOOL, "nether_wart_sack");
        COCOA_BEAN_SACK = new BlockNeutroniaPillar(Material.WOOL, "cocoa_bean_sack");
        GUNPOWDER_SACK = new BlockNeutroniaPillar(Material.WOOL, "gunpowder_sack");

        EGG_CRATE = new BlockNeutroniaPillar(Material.WOOD, "egg_crate");
        BEETROOT_CRATE = new BlockNeutroniaPillar(Material.WOOD, "beetroot_crate");
        POTATO_CRATE = new BlockNeutroniaPillar(Material.WOOD, "potato_crate");
        CARROT_CRATE = new BlockNeutroniaPillar(Material.WOOD, "carrot_crate");
        APPLE_CRATE = new BlockNeutroniaPillar(Material.WOOD, "apple_crate");
        GOLDEN_APPLE_CRATE = new BlockNeutroniaPillar(Material.WOOD, "golden_apple_crate");
    }

}
