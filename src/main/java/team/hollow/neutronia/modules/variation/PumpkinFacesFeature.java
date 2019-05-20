package team.hollow.neutronia.modules.variation;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import team.hollow.abnormalib.modules.api.features.OptionalFeature;
import team.hollow.abnormalib.utils.ContentBuilder;
import team.hollow.abnormalib.utils.registry.BlockChiseler;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.blocks.vanillish.NeutroniaCarvedPumpkinBlock;
import team.hollow.neutronia.enums.CarvedFaceType;
import team.hollow.neutronia.utils.NeutroniaTags;

import java.util.ArrayList;
import java.util.List;

public class PumpkinFacesFeature extends OptionalFeature {
	public PumpkinFacesFeature() {
		super("pumpkin-faces", "Adds more pumpkin faces");
	}

	@Override
	protected void applyEnabled() {
		ContentBuilder contentBuilder = Neutronia.getContentBuilder();
		List<Block> carvedPumpkins = new ArrayList<>(CarvedFaceType.values().length + 1);
		carvedPumpkins.add(Blocks.CARVED_PUMPKIN);
		List<Block> jackOLanterns = new ArrayList<>(CarvedFaceType.values().length + 1);
		jackOLanterns.add(Blocks.JACK_O_LANTERN);
		for(CarvedFaceType carvedFaceType : CarvedFaceType.values()) {
			final String name = carvedFaceType.asString();
			Block pumpkin = contentBuilder.newBlock("carved_pumpkin_" + name, new NeutroniaCarvedPumpkinBlock(false), "pumpkins/carved_pumpkin_" + name);
			Block lantern = contentBuilder.newBlock("jack_o_lantern_" + name, new NeutroniaCarvedPumpkinBlock(true), "jack_o_lanterns/jack_o_lantern_" + name);
            carvedPumpkins.add(pumpkin);
            jackOLanterns.add(lantern);
		}

		contentBuilder.runGameTask(() -> {
			BlockChiseler.create(new Identifier(Neutronia.MOD_ID, "carved_pumpkins"), NeutroniaTags.SHEARS, carvedPumpkins);
			BlockChiseler.create(new Identifier(Neutronia.MOD_ID, "jack_o_lanterns"), NeutroniaTags.SHEARS, jackOLanterns);
		});
	}
}
