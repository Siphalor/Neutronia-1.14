package team.hollow.neutronia;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.impl.registry.CompostingChanceRegistryImpl;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.hollow.neutronia.configNew.ConfigManager;
import team.hollow.neutronia.event_system.EventCore;
import team.hollow.neutronia.init.*;
import team.hollow.neutronia.utils.registry.PiecesSet;

import java.util.HashMap;
import java.util.Map;

public class Neutronia implements ModInitializer {

    public static final String MOD_ID = "neutronia";
    public static final String MOD_NAME = "Neutronia";
    public static final String PREFIX = MOD_ID + ":";
    private static final Logger LOGGER = LogManager.getFormatterLogger(MOD_NAME);

    public static Map<PiecesSet.BlockPiece, ItemGroup> groups = new HashMap<>();

    public static TestConfig testConfig;

    static {
        EventCore.instance = new EventCore();
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    @Override
    public void onInitialize() {
        for(PiecesSet.BlockPiece p : PiecesSet.BlockPiece.values()) {
            groups.put(p, FabricItemGroupBuilder.create(new Identifier(MOD_ID, p.getName())).icon(() -> new ItemStack(Blocks.TERRACOTTA)).build());
        }
        testConfig = ConfigManager.loadConfig(TestConfig.class);
        new NBlocks();
        new NLightBlocks();
        new NItems();
        NBlockEntities.init();
        new NEntityTypes();
        new NPaintingMotives();
        new NBiomes();
        CompostingChanceRegistryImpl.INSTANCE.add(Items.ROTTEN_FLESH, 0.5F);
        CompostingChanceRegistryImpl.INSTANCE.add(Items.CHICKEN, 0.5F);
        CompostingChanceRegistryImpl.INSTANCE.add(Items.COOKED_CHICKEN, 0.5F);
    }

}