package generators;

import de.siphalor.tweed.config.ConfigEnvironment;
import de.siphalor.tweed.config.ConfigScope;
import team.hollow.module_api.ModuleManager;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.modules.EndecorationsModule;
import team.hollow.neutronia.modules.VariationModule;
import team.hollow.neutronia.registry.ContentBuilder;

public class MainGenerators {

    private static final String NEUTRONIA_DOMAIN = "neutronia";
    private static final String MINECRAFT_DOMAIN = "minecraft";

    public static void main(String[] args) {
        ContentBuilder.setInstance(new ContentResourceBuilder(Neutronia.MOD_ID));
        ModuleManager.registerModule(new EndecorationsModule(), new VariationModule());
        ModuleManager.apply(ConfigEnvironment.UNIVERSAL, ConfigScope.GAME);
//        for (CarvedFaceTypes faceTypes : CarvedFaceTypes.values()) {
//            ModelGenerator.genOrientedBlock(new Identifier(MINECRAFT_DOMAIN, "carved_" + faceTypes.asString() + "_pumpkin"), new Identifier(MINECRAFT_DOMAIN, "pumpkin_top"), new Identifier(NEUTRONIA_DOMAIN, "pumpkins/carved_pumpkin_" + faceTypes.asString()), new Identifier(MINECRAFT_DOMAIN, "pumpkin_side"));
//            ModelGenerator.genOrientedBlock(new Identifier(MINECRAFT_DOMAIN, "carved_" + faceTypes.asString() + "_jack_o_lantern"), new Identifier(MINECRAFT_DOMAIN, "pumpkin_top"), new Identifier(NEUTRONIA_DOMAIN, "jack_o_lanterns/jack_o_lantern_" + faceTypes.asString()), new Identifier(MINECRAFT_DOMAIN, "pumpkin_side"));
//            ModelGenerator.genOrientedBlock(new Identifier(NEUTRONIA_DOMAIN, "carved_" + faceTypes.asString() + "_melon"), new Identifier(MINECRAFT_DOMAIN, "melon_top"), new Identifier(NEUTRONIA_DOMAIN, "melons/carved_pumpkin_" + faceTypes.asString()), new Identifier(MINECRAFT_DOMAIN, "melon_side"));
//            ModelGenerator.genOrientedBlock(new Identifier(NEUTRONIA_DOMAIN, "carved_" + faceTypes.asString() + "_mel_o_lantern"), new Identifier(MINECRAFT_DOMAIN, "melon_top"), new Identifier(NEUTRONIA_DOMAIN, "mel_o_lanterns/jack_o_lantern_" + faceTypes.asString()), new Identifier(MINECRAFT_DOMAIN, "melon_side"));
//        }
        /*for (SoulStoneVariants variants : SoulStoneVariants.values()) {
            ModelGenerator.genStair(new Identifier(NEUTRONIA_DOMAIN, String.format("%s_stairs", variants.asString())), new Identifier(NEUTRONIA_DOMAIN, "soulstone_bottom"), new Identifier(NEUTRONIA_DOMAIN, "soulstone_top"), new Identifier(NEUTRONIA_DOMAIN, variants.asString()));
            ModelGenerator.genSlab(new Identifier(NEUTRONIA_DOMAIN, String.format("%s_slab", variants.asString())), new Identifier(NEUTRONIA_DOMAIN, variants.asString()), new Identifier(NEUTRONIA_DOMAIN, "soulstone_top"), new Identifier(NEUTRONIA_DOMAIN, variants.asString()), new Identifier(NEUTRONIA_DOMAIN, "soulstone_bottom"));
        }*/
    }

}