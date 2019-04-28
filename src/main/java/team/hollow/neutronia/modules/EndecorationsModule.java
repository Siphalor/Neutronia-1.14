package team.hollow.neutronia.modules;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import team.hollow.module_api.api.Module;
import team.hollow.module_api.api.features.woodtype.WoodTypeBlockFeature;

import java.util.Collections;

public class EndecorationsModule extends Module {
	WoodTypeBlockFeature lanterns;

	public EndecorationsModule() {
		super("endecorations", "This module contains special decoration blocks by u/Endergy");

		lanterns = register(new WoodTypeBlockFeature("paper_lantern", "Adds full block paper lanterns", Collections.emptySet(),
			(woodType) -> new Block(FabricBlockSettings.of(Material.WOOD).breakByHand(true).ticksRandomly().lightLevel(10).build())
		));

		setBackgroundTexture(new Identifier("minecraft", "textures/block/end_stone_bricks.png"));
	}
}
