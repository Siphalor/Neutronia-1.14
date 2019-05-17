package team.hollow.neutronia.modules;

import net.minecraft.util.Identifier;
import team.hollow.abnormalib.modules.api.MainModule;
import team.hollow.neutronia.modules.endecorations.PaperLanternsFeature;

public class EndecorationsModule extends MainModule {
    public static PaperLanternsFeature paperLanterns;

	public EndecorationsModule() {
		super("endecorations", "This module contains special decoration blocks by u/Endergy");

		paperLanterns = register(new PaperLanternsFeature());

		setBackgroundTexture(new Identifier("minecraft", "textures/block/end_stone_bricks.png"));
	}
}
