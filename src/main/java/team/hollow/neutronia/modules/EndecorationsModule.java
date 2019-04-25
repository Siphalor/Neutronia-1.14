package team.hollow.neutronia.modules;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import team.hollow.module_api.api.Module;
import team.hollow.module_api.api.features.woodtype.WoodTypeBlockFeature;

import java.util.Collections;

public class EndecorationsModule extends Module {
	WoodTypeBlockFeature lanterns;

	public EndecorationsModule() {
		super("endecorations", "This module contains special decoration blocks by u/endergy");

		lanterns = register(new WoodTypeBlockFeature("paper_lantern", "Adds full block paper lanterns", Collections.emptySet(),
			() -> new Block(FabricBlockSettings.of(Material.WOOD).breakByHand(true).ticksRandomly().lightLevel(10).build())
		));
	}
}
