package team.hollow.neutronia.modules;

import net.minecraft.util.Identifier;
import team.hollow.module_api.api.Module;
import team.hollow.neutronia.modules.endecorations.PaperLanternsFeature;

public class EndecorationsModule extends Module {
    public static PaperLanternsFeature paperLanterns;

	public EndecorationsModule() {
		super("endecorations", "This module contains special decoration blocks by u/Endergy");

		paperLanterns = register(new PaperLanternsFeature());

		setBackgroundTexture(new Identifier("minecraft", "textures/block/end_stone_bricks.png"));
	}
}
