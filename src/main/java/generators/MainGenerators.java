package generators;

import net.minecraft.util.Identifier;
import team.hollow.neutronia.enums.SoulStoneVariants;

public class MainGenerators {

    private static final String NEUTRONIA_DOMAIN = "neutronia";
    private static final String MINECRAFT_DOMAIN = "minecraft";

    public static void main(String[] args) {
//        for (CarvedFaceTypes faceTypes : CarvedFaceTypes.values()) {
//            ModelGenerator.genOrientedBlock(new Identifier(MINECRAFT_DOMAIN, "carved_" + faceTypes.toString() + "_pumpkin"), new Identifier(MINECRAFT_DOMAIN, "pumpkin_top"), new Identifier(NEUTRONIA_DOMAIN, "pumpkins/carved_pumpkin_" + faceTypes.toString()), new Identifier(MINECRAFT_DOMAIN, "pumpkin_side"));
//            ModelGenerator.genOrientedBlock(new Identifier(MINECRAFT_DOMAIN, "carved_" + faceTypes.toString() + "_jack_o_lantern"), new Identifier(MINECRAFT_DOMAIN, "pumpkin_top"), new Identifier(NEUTRONIA_DOMAIN, "jack_o_lanterns/jack_o_lantern_" + faceTypes.toString()), new Identifier(MINECRAFT_DOMAIN, "pumpkin_side"));
//            ModelGenerator.genOrientedBlock(new Identifier(NEUTRONIA_DOMAIN, "carved_" + faceTypes.toString() + "_melon"), new Identifier(MINECRAFT_DOMAIN, "melon_top"), new Identifier(NEUTRONIA_DOMAIN, "melons/carved_pumpkin_" + faceTypes.toString()), new Identifier(MINECRAFT_DOMAIN, "melon_side"));
//            ModelGenerator.genOrientedBlock(new Identifier(NEUTRONIA_DOMAIN, "carved_" + faceTypes.toString() + "_mel_o_lantern"), new Identifier(MINECRAFT_DOMAIN, "melon_top"), new Identifier(NEUTRONIA_DOMAIN, "mel_o_lanterns/jack_o_lantern_" + faceTypes.toString()), new Identifier(MINECRAFT_DOMAIN, "melon_side"));
//        }
        for (SoulStoneVariants variants : SoulStoneVariants.values()) {
            ModelGenerator.genStair(new Identifier(NEUTRONIA_DOMAIN, String.format("%s_stairs", variants.toString())), new Identifier(NEUTRONIA_DOMAIN, "soulstone_bottom"), new Identifier(NEUTRONIA_DOMAIN, "soulstone_top"), new Identifier(NEUTRONIA_DOMAIN, variants.toString()));
            ModelGenerator.genSlab(new Identifier(NEUTRONIA_DOMAIN, String.format("%s_slab", variants.toString())), new Identifier(NEUTRONIA_DOMAIN, variants.toString()), new Identifier(NEUTRONIA_DOMAIN, "soulstone_top"), new Identifier(NEUTRONIA_DOMAIN, variants.toString()), new Identifier(NEUTRONIA_DOMAIN, "soulstone_bottom"));
        }
    }

}