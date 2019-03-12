package team.hollow.neutronia.init;

import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;

public class ArcaneMagicConstants {
    public static final String PARCHMENT_TYPE_KEY = "key.arcanemagic.parchment_type";

    public static final Identifier GLOW_PARTICLE_TEXTURE = new Identifier(Neutronia.MOD_ID, "particle/glow_particle");
    public static final Identifier SMOKE_PARTICLE_TEXTURE = new Identifier(Neutronia.MOD_ID, "particle/smoke_particle");
    public static final Identifier NOTEBOOK_TEXTURE = new Identifier(Neutronia.MOD_ID, "textures/gui/notebook.png");

    // Dimensions of the notebook texture with and without the bottom icons
    public static final int NOTEBOOK_WIDTH = 272;
    public static final int NOTEBOOK_HEIGHT = 180;
    public static final int NOTEBOOK_TEX_HEIGHT = 256;

    public static final String NOTEBOOK_SECTION_KEY = "NotebookSection";
    public static final String NOTEBOOK_PAGE_KEY = "NotebookPage";

    public static final String UUID_KEY = "CrystalItemIdentifier";
    public static final String PASSIVE_CRYSTAL_KEY = "PassiveCrystal";
    public static final String ACTIVE_CRYSTAL_KEY = "ActiveCrystal";
    public static final String ACTIVE_TIMER_KEY = "ActiveTimer";

    // For the Discovery parchment
    public static final String DROWNED_KILLS_KEY = "DrownedKills"; // How many Drowned Zombies the player has killed, capped at 5
    public static final String GATHER_QUEST_INDEXES_KEY = "GatherQuestIndexes"; // List of indexes in DiscoveryParchment.GATHER_QUEST_OPTIONS of new 1.13 & 1.14 items that need to be collected
    public static final String GATHER_QUEST_FINISHED_KEY = "FinishedGatherQuest"; // If the player has collected all four of the new 1.13 and 1.14 items requested
    public static final String GATHER_QUEST_ANALYZED_INDEXES_KEY = "GatherAnalysisFinishedIndexes"; // List of indexes DiscoveryParchment.GATHER_QUEST_OPTIONS of the new 1.13 & 1.14 items that have been analyzed
    public static final String PLACED_ANALYZER_KEY = "PlacedAnalyzer"; // If the player has placed an Analyzer block
    public static final String ANALYSIS_QUEST_INDEXES_KEY = "AnalysisQuestIndexes"; // List of indexes in DiscoveryParchment.ANALYSIS_QUEST_OPTIONS of items that need to be analyzed
    public static final String ANALYSIS_QUEST_ANALYZED_INDEXES_KEY = "AnalysisQuestAnalyzedIndexesKey"; // List of indexes in DiscoveryParchment.ANALYSIS_QUEST_OPTION of items which have been analyzed
    public static final String ANALYZED_STICK_KEY = "AnalyzedStick"; // If the player has placed a stick in the analyzer
    public static final String CRAFTED_SCEPTER_KEY = "CraftedScepter"; // If the player has crafted a Golden Scepter

    // For notebook progression
    public static final String ANALYZED_CRAFTING_TABLE_KEY = "AnalyzedCraftingTable";
    public static final String PLACED_TRANSFIGURATION_TABLE_KEY = "PlacedTransfigurationTable";
    public static final String CRAFTED_GOLD_CRYSTAL_KEY = "CraftedGoldCrystal";
}