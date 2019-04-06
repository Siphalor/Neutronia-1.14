package team.hollow.neutronia_base;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class NeutroniaBaseBundle {

    public static String getBundleName() {
        return "neutronia_base";
    }

    public static String getBundleVersion() {
        return "0.0.1";
    }

    public static void init() {
        Registry.register(Registry.BLOCK, new Identifier(getBundleName(), "test"), new Block(Block.Settings.of(Material.WOOD)));
    }

}