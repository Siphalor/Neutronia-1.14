package team.hollow.neutronia.event_system.loot;

import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import io.netty.util.concurrent.ImmediateExecutor;
import net.fabricmc.fabric.impl.resources.ModResourcePackUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.WorldGenerationProgressTracker;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.DefaultResourcePack;
import net.minecraft.resource.ReloadableResourceManager;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourceType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldGenerationProgressListenerFactory;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.UserCache;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import net.minecraft.world.WorldSaveHandler;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.level.LevelGeneratorType;
import net.minecraft.world.level.LevelInfo;
import net.minecraft.world.level.LevelProperties;
import net.minecraft.world.level.storage.LevelStorage;
import net.minecraft.world.loot.context.LootContext;
import net.minecraft.world.loot.context.LootContextParameters;
import net.minecraft.world.loot.context.LootContextTypes;

import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ServerWorldRedirectManager {
    static ServerWorldRedirectManager instance = null;
    public World that = null;
    Path tempDir = null;
    IntegratedServer reServer = null;
    ServerWorldRedirect reWorld = null;

    public ServerWorldRedirectManager() {
        tempDir = null;
        try {
            tempDir = Files.createTempDirectory("autosteve_fakeserver");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        System.out.println("using scratchdir " + tempDir.toString());

        LevelStorage storage = new LevelStorage(tempDir, tempDir, MinecraftClient.getInstance().getDataFixer());

        WorldSaveHandler worldSaveHandler_1 = storage.method_242("save", null);

        LevelInfo levelInfo_1 = new LevelInfo(0, GameMode.CREATIVE, false, false, LevelGeneratorType.FLAT);
        LevelProperties levelProperties_1 = new LevelProperties(levelInfo_1, "save");

        YggdrasilAuthenticationService yggdrasilAuthenticationService_1 = new YggdrasilAuthenticationService(Proxy.NO_PROXY, UUID.randomUUID().toString());
        MinecraftSessionService minecraftSessionService_1 = yggdrasilAuthenticationService_1.createMinecraftSessionService();
        GameProfileRepository gameProfileRepository_1 = yggdrasilAuthenticationService_1.createProfileRepository();
        UserCache userCache_1 = new UserCache(gameProfileRepository_1, new File(tempDir.toString(), MinecraftServer.USER_CACHE_FILE.getName()));
        UserCache.setUseRemote(false);

        WorldGenerationProgressListenerFactory x = (intx) -> {
            return null;
        };
        IntegratedServer server = new IntegratedServer(MinecraftClient.getInstance(),
                tempDir.toString() + "/save",
                "save",
                levelInfo_1,
                yggdrasilAuthenticationService_1,
                minecraftSessionService_1,
                gameProfileRepository_1,
                userCache_1,
                x);

        WorldGenerationProgressTracker worldGenerationProgressTracker_1 = new WorldGenerationProgressTracker(1 + 0);
        ServerWorldRedirect serverWorld_1 = new ServerWorldRedirect(server, ImmediateExecutor.INSTANCE, worldSaveHandler_1, levelProperties_1, DimensionType.OVERWORLD, null, worldGenerationProgressTracker_1);


        serverWorld_1.that = MinecraftClient.getInstance().world;
        reServer = server;
        reWorld = serverWorld_1;

        ReloadableResourceManager dataManager = server.getDataManager();

        List<ResourcePack> packs = new ArrayList<>();
        DefaultResourcePack pack = new DefaultResourcePack("minecraft");
        packs.add(pack);
        ModResourcePackUtil.appendModResourcePacks(packs, ResourceType.DATA);

        for (int i = 0; i < packs.size(); i++) {
            dataManager.addPack(packs.get(i));
        }
        reWorld.getServer().getLootManager().apply(dataManager);

        System.out.println("a-ok!");
        //ServerWorldRedirectGenerator.generate();
    }

    public static ServerWorldRedirectManager getInstance() {
        if (instance == null) instance = new ServerWorldRedirectManager();
        return instance;
    }

    public LootContext killContext(PlayerEntity player, Entity victim) {
        LootContext.Builder builder = new LootContext.Builder(reWorld);
        builder.put(LootContextParameters.LAST_DAMAGE_PLAYER, player);
        builder.put(LootContextParameters.DAMAGE_SOURCE, DamageSource.player(player));
        builder.put(LootContextParameters.DIRECT_KILLER_ENTITY, player);
        builder.put(LootContextParameters.KILLER_ENTITY, player);
        builder.put(LootContextParameters.THIS_ENTITY, victim);
        builder.put(LootContextParameters.POSITION, new BlockPos(MinecraftClient.getInstance().player.getPos()));

        return builder.build(LootContextTypes.ENTITY);
    }

    public void lootTest() {
        EntityType<?> type = EntityType.WITHER_SKELETON;
        List<ItemStack> list_1 = LootLookup.getLootFor(type, MinecraftClient.getInstance().world, 100);
        System.out.println(type.getTranslationKey() + " drops " + list_1.size());
        for (int i = 0; i < list_1.size(); i++) {
            ItemStack x = list_1.get(i);
            System.out.println(x.getTranslationKey() + " x " + x.getAmount());
        }

		/*EntityType<?> type = EntityType.ENDERMAN;

		int raw_id = Registry.ENTITY_TYPE.getRawId(type);
		Entity entity = EntityType.createInstanceFromId(raw_id,  MinecraftClient.getInstance().world);
		PlayerEntity player = MinecraftClient.getInstance().player;

		LootContext ctx = killContext(player, entity);

		LootSupplier lootSupplier_1 = reServer.getLootManager().getSupplier(type.getLootTableId());
        List<ItemStack> list_1 = lootSupplier_1.getDrops(ctx);
        System.out.println(type.getTranslationKey()+" drops "+list_1.size());
        for(int i =0; i < list_1.size(); i++)
        {
        	System.out.println(list_1.get(i).getTranslationKey());
        }*/

        //Blocks.IRON_ORE.getDroppedStacks(blockState_1, lootContext$Builder_1)
    }

    public void lootFloorTest() {
        Entity e = MinecraftClient.getInstance().player;
        BlockPos plpos = new BlockPos(e.x, e.y - 0.5, e.z);
        //BlockPos start = AutoSteveMain.mgr.getValidAt(plpos);
        BlockPos start = plpos;//start.down().down();
        World w = MinecraftClient.getInstance().world;

        BlockState iblockstate = w.getBlockState(start);
        Block block = iblockstate.getBlock();
        System.out.println("block is " + block.getTranslationKey());

        //iblockstate.getDroppedStacks(lootContext$Builder_1)

        BlockEntity blockEntity_1 = w.getBlockEntity(start);
        //iblockstate.getDroppedStacks(lootContext$Builder_1)
        List<ItemStack> list_1 = Block.getDroppedStacks(iblockstate, reWorld, start, blockEntity_1);

        System.out.println(block.getTranslationKey() + " drops " + list_1.size());
        for (int i = 0; i < list_1.size(); i++) {
            System.out.println(list_1.get(i).getTranslationKey());
        }
    }

    public void shutdown() {
        if (tempDir != null) {
            try {
                Files.walkFileTree(tempDir, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Files.delete(file);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        Files.delete(dir);
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            tempDir = null;
        }
    }


}
