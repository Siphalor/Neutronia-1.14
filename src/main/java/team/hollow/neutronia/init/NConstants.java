package team.hollow.neutronia.init;

import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;

public class NConstants {

    public static final Identifier NOTEBOOK_TEXTURE = new Identifier(Neutronia.MOD_ID, "textures/gui/notebook.png");

    // Dimensions of the notebook texture with and without the bottom icons
    public static final int NOTEBOOK_WIDTH = 272;
    public static final int NOTEBOOK_HEIGHT = 180;
    public static final int NOTEBOOK_TEX_HEIGHT = 256;

    public static final String NOTEBOOK_SECTION_KEY = "NotebookSection";
    public static final String NOTEBOOK_PAGE_KEY = "NotebookPage";

}