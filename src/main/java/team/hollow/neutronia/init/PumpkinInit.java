package team.hollow.neutronia.init;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.BlockChiseler;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.blocks.NeutroniaCarvedPumpkinBlock;
import team.hollow.neutronia.enums.CarvedFaceTypes;
import team.hollow.neutronia.utils.NeutroniaTags;
import team.hollow.neutronia.utils.registry.RegistryUtils;

import java.util.ArrayList;
import java.util.List;

import static team.hollow.neutronia.Neutronia.MOD_ID;

class PumpkinInit {

    static void init() {
        List<Block> carvedPumpkins = new ArrayList<>(CarvedFaceTypes.values().length + 1);
        carvedPumpkins.add(Blocks.CARVED_PUMPKIN);
        List<Block> jackOLanterns = new ArrayList<>(CarvedFaceTypes.values().length + 1);
        jackOLanterns.add(Blocks.JACK_O_LANTERN);
        for(CarvedFaceTypes carvedFaceType : CarvedFaceTypes.values()) {
            final String name = carvedFaceType.asString();
            Block pumpkin = RegistryUtils.register(new NeutroniaCarvedPumpkinBlock(false), new Identifier(MOD_ID, "carved_" + name + "_pumpkin"));
            Block lantern = RegistryUtils.register(new NeutroniaCarvedPumpkinBlock(true), new Identifier(MOD_ID,  name + "_jack_o_lantern"));
            carvedPumpkins.add(pumpkin);
            jackOLanterns.add(lantern);
        }
        BlockChiseler.create(new Identifier(Neutronia.MOD_ID, "carved_pumpkins"), NeutroniaTags.SHEARS, carvedPumpkins);
        BlockChiseler.create(new Identifier(Neutronia.MOD_ID, "jack_o_lanterns"), NeutroniaTags.SHEARS, jackOLanterns);
    }

}
