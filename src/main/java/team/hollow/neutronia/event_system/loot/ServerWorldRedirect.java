package team.hollow.neutronia.event_system.loot;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.map.MapState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.particle.ParticleParameters;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldGenerationProgressListener;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.tag.TagManager;
import net.minecraft.util.ProgressListener;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.crash.CrashReportSection;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BoundingBox;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkManager;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.Explosion.class_4179;
import net.minecraft.world.gen.Heightmap.Type;
import net.minecraft.world.level.LevelGeneratorType;
import net.minecraft.world.level.LevelInfo;
import net.minecraft.world.level.LevelProperties;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;


public class ServerWorldRedirect extends ServerWorld {

    public World that = null;

    public ServerWorldRedirect(MinecraftServer minecraftServer_1, Executor executor_1,
                               WorldSaveHandler worldSaveHandler_1, LevelProperties levelProperties_1, DimensionType dimensionType_1,
                               Profiler profiler_1, WorldGenerationProgressListener worldGenerationProgressListener_1) {
        super(minecraftServer_1, executor_1, worldSaveHandler_1, levelProperties_1, dimensionType_1, profiler_1,
                worldGenerationProgressListener_1);
        // TODO Auto-generated constructor stub
    }

    public void syncFields() {

    }


    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void init(LevelInfo A) {
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void close() {
        try {
            if (that != null) {
                that.close();
            } else {
                super.close();
            }
        } catch (Exception e) {
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void save(ProgressListener A, boolean B, boolean C) {
        try {
        } catch (Exception e) {
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void handleInteraction(EntityInteraction A, Entity B, InteractionObserver C) {
    }

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Entity getEntity(UUID A){
	//non void return in non-World method}

	*/

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public MinecraftServer getServer(){
	if(that != null)
	{
	return that.getServer();
	}
	else
	{
	return super.getServer();
	}
	}

	*/

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean spawnEntity(Entity A) {
        if (that != null) {
            return that.spawnEntity(A);
        } else {
            return super.spawnEntity(A);
        }
    }

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public StructureManager getStructureManager(){
	//non void return in non-World method}

	*/

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PointOfInterestStorage getPointOfInterestStorage(){
	//non void return in non-World method}

	*/

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public CompletableFuture getChunkSyncIfServerThread(int A, int B, boolean C){
	//non void return in non-World method}

	*/

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean method_18768(Entity A){
	//non void return in non-World method}

	*/

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void setEntityStatus(Entity A, byte B) {
        if (that != null) {
            that.setEntityStatus(A, B);
        } else {
            super.setEntityStatus(A, B);
        }
    }

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ServerChunkManager method_14178(){
	//non void return in non-World method}

	*/

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int method_14199(ParticleParameters A, double B, double C, double D, int E, double F, double G, double H, double I){
	//non void return in non-World method}

	*/

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public TagManager getTagManager() {
        if (that != null) {
            return that.getTagManager();
        } else {
            return super.getTagManager();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public List getPlayers() {
        if (that != null) {
            return that.getPlayers();
        } else {
            return super.getPlayers();
        }
    }

	/*@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Scoreboard getScoreboard(){
	if(that != null)
	{
	return that.getScoreboard();
	}
	else
	{
	return super.getScoreboard();
	}
	}*/

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public ChunkManager getChunkManager() {
        if (that != null) {
            return that.getChunkManager();
        } else {
            return super.getChunkManager();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void playSound(PlayerEntity A, double B, double C, double D, SoundEvent E, SoundCategory F, float G, float H) {
        if (that != null) {
            that.playSound(A, B, C, D, E, F, G, H);
        } else {
            super.playSound(A, B, C, D, E, F, G, H);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void setBlockBreakingProgress(int A, BlockPos B, int C) {
        if (that != null) {
            that.setBlockBreakingProgress(A, B, C);
        } else {
            super.setBlockBreakingProgress(A, B, C);
        }
    }

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PersistentStateManager getPersistentStateManager(){
	//non void return in non-World method}

	*/

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void setDefaultSpawnClient() {
        if (that != null) {
            that.setDefaultSpawnClient();
        } else {
            super.setDefaultSpawnClient();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void playSoundFromEntity(PlayerEntity A, Entity B, SoundEvent C, SoundCategory D, float E, float F) {
        if (that != null) {
            that.playSoundFromEntity(A, B, C, D, E, F);
        } else {
            super.playSoundFromEntity(A, B, C, D, E, F);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean canPlayerModifyAt(PlayerEntity A, BlockPos B) {
        if (that != null) {
            return that.canPlayerModifyAt(A, B);
        } else {
            return super.canPlayerModifyAt(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void updatePlayersSleeping() {
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void method_18769(Entity A) {
    }

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public BlockPos getForcedSpawnPoint(){
	//non void return in non-World method}

	*/

	/*@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public TickScheduler getFluidTickScheduler(){
	if(that != null)
	{
	return that.getFluidTickScheduler();
	}
	else
	{
	return super.getFluidTickScheduler();
	}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public TickScheduler getBlockTickScheduler(){
	if(that != null)
	{
	return that.getBlockTickScheduler();
	}
	else
	{
	return super.getBlockTickScheduler();
	}
	}*/

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void method_18767(Entity A) {
    }

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PortalForcer getPortalForcer(){
	//non void return in non-World method}

	*/

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void resetIdleTimeout() {
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void updateListeners(BlockPos A, BlockState B, BlockState C, int D) {
        if (that != null) {
            that.updateListeners(A, B, C, D);
        } else {
            super.updateListeners(A, B, C, D);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void playEvent(PlayerEntity A, int B, BlockPos C, int D) {
        if (that != null) {
            that.playEvent(A, B, C, D);
        } else {
            super.playEvent(A, B, C, D);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void method_19282(BlockPos A, BlockState B, BlockState C) {
        if (that != null) {
            that.method_19282(A, B, C);
        } else {
            super.method_19282(A, B, C);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Entity getEntityById(int A) {
        if (that != null) {
            return that.getEntityById(A);
        } else {
            return super.getEntityById(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Explosion createExplosion(Entity A, DamageSource B, double C, double D, double E, float F, boolean G, class_4179 H) {
        if (that != null) {
            return that.createExplosion(A, B, C, D, E, F, G, H);
        } else {
            return super.createExplosion(A, B, C, D, E, F, G, H);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void addBlockAction(BlockPos A, Block B, int C, int D) {
        if (that != null) {
            that.addBlockAction(A, B, C, D);
        } else {
            super.addBlockAction(A, B, C, D);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public MapState getMapState(String A) {
        if (that != null) {
            return that.getMapState(A);
        } else {
            return super.getMapState(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public int getNextMapId() {
        if (that != null) {
            return that.getNextMapId();
        } else {
            return super.getNextMapId();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public BlockPos locateStructure(String A, BlockPos B, int C, boolean D) {
        if (that != null) {
            return that.locateStructure(A, B, C, D);
        } else {
            return super.locateStructure(A, B, C, D);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void putMapState(MapState A) {
        if (that != null) {
            that.putMapState(A);
        } else {
            super.putMapState(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public RecipeManager getRecipeManager() {
        if (that != null) {
            return that.getRecipeManager();
        } else {
            return super.getRecipeManager();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void playGlobalEvent(int A, BlockPos B, int C) {
        if (that != null) {
            that.playGlobalEvent(A, B, C);
        } else {
            super.playGlobalEvent(A, B, C);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean isSavingDisabled() {
        if (that != null) {
            return that.isSavingDisabled();
        } else {
            return super.isSavingDisabled();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void tickChunk(WorldChunk A, int B) {
    }

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public BlockPos method_18210(BlockPos A){
	//non void return in non-World method}

	*/

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void method_18763(Entity A, Entity B) {
    }

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public LongSet getForcedChunks(){
	//non void return in non-World method}

	*/

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void placeBonusChest() {
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void unloadEntity(Entity A) {
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void addLightning(LightningEntity A) {
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void checkSessionLock() {
        try {
        } catch (Exception e) {
        }
    }

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean isInsideTick(){
	//non void return in non-World method}

	*/

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ServerScoreboard method_14170(){
	//non void return in non-World method}

	*/

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void saveLevel() {
        try {
        } catch (Exception e) {
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void removeEntity(Entity A) {
    }

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object2IntMap method_18219(){
	//non void return in non-World method}

	*/

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List method_18198(EntityType A, Predicate B){
	//non void return in non-World method}

	*/

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void method_18215(ServerPlayerEntity A) {
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void method_18213(ServerPlayerEntity A) {
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void method_18211(ServerPlayerEntity A) {
    }

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ServerTickScheduler method_14196(){
	//non void return in non-World method}

	*/

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ServerTickScheduler method_14179(){
	//non void return in non-World method}

	*/

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void removePlayer(ServerPlayerEntity A) {
    }

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List method_18766(Predicate A){
	//non void return in non-World method}

	*/

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean method_14166(ServerPlayerEntity A, ParticleParameters B, boolean C, double D, double E, double F, int G, double H, double I, double J, double K){
	//non void return in non-World method}

	*/

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public WorldSaveHandler getSaveHandler(){
	//non void return in non-World method}

	*/

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void unloadEntities(WorldChunk A) {
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void method_18207(ServerPlayerEntity A) {
    }

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ServerPlayerEntity method_18779(){
	//non void return in non-World method}

	*/

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List method_18776(){
	//non void return in non-World method}

	*/

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public long getTime() {
        if (that != null) {
            return that.getTime();
        } else {
            return super.getTime();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void setTime(long A) {
        if (that != null) {
            that.setTime(A);
        } else {
            super.setTime(A);
        }
    }

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean setChunkForced(int A, int B, boolean C){
	//non void return in non-World method}

	*/

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean method_19497(BlockPos A, int B){
	//non void return in non-World method}

	*/

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int method_19498(ChunkSectionPos A){
	//non void return in non-World method}

	*/

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public RaidManager getRaidManager(){
	//non void return in non-World method}

	*/

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Raid getRaidAt(BlockPos A){
	//non void return in non-World method}

	*/

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean method_19500(BlockPos A){
	//non void return in non-World method}

	*/

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean hasRaidAt(BlockPos A){
	//non void return in non-World method}

	*/

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public List getEntities(Class A, BoundingBox B, Predicate C) {
        if (that != null) {
            return that.getEntities(A, B, C);
        } else {
            return super.getEntities(A, B, C);
        }
    }

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	//final/static/abstract/private/protected method
	public boolean isValid(BlockPos A){
	if(that != null)
	{
	return that.isValid(A);
	}
	else
	{
	return super.isValid(A);
	}
	}

	*/

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public List getEntities(Entity A, BoundingBox B, Predicate C) {
        if (that != null) {
            return that.getEntities(A, B, C);
        } else {
            return super.getEntities(A, B, C);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Dimension getDimension() {
        if (that != null) {
            return that.getDimension();
        } else {
            return super.getDimension();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public long getTimeOfDay() {
        if (that != null) {
            return that.getTimeOfDay();
        } else {
            return super.getTimeOfDay();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void setTimeOfDay(long A) {
        if (that != null) {
            that.setTimeOfDay(A);
        } else {
            super.setTimeOfDay(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean isClient() {
        if (that != null) {
            return that.isClient();
        } else {
            return super.isClient();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Profiler getProfiler() {
        if (that != null) {
            return that.getProfiler();
        } else {
            return super.getProfiler();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public WorldBorder getWorldBorder() {
        if (that != null) {
            return that.getWorldBorder();
        } else {
            return super.getWorldBorder();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean setBlockState(BlockPos A, BlockState B) {
        if (that != null) {
            return that.setBlockState(A, B);
        } else {
            return super.setBlockState(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean setBlockState(BlockPos A, BlockState B, int C) {
        if (that != null) {
            return that.setBlockState(A, B, C);
        } else {
            return super.setBlockState(A, B, C);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void sendPacket(Packet A) {
        if (that != null) {
            that.sendPacket(A);
        } else {
            super.sendPacket(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public FluidState getFluidState(BlockPos A) {
        if (that != null) {
            return that.getFluidState(A);
        } else {
            return super.getFluidState(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public int getTop(Type A, int B, int C) {
        if (that != null) {
            return that.getTop(A, B, C);
        } else {
            return super.getTop(A, B, C);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Biome getBiome(BlockPos A) {
        if (that != null) {
            return that.getBiome(A);
        } else {
            return super.getBiome(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public int getSeaLevel() {
        if (that != null) {
            return that.getSeaLevel();
        } else {
            return super.getSeaLevel();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public int getLightLevel(BlockPos A, int B) {
        if (that != null) {
            return that.getLightLevel(A, B);
        } else {
            return super.getLightLevel(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public int getLightLevel(LightType A, BlockPos B) {
        if (that != null) {
            return that.getLightLevel(A, B);
        } else {
            return super.getLightLevel(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Chunk getChunk(int A, int B) {
        if (that != null) {
            return that.getChunk(A, B);
        } else {
            return super.getChunk(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Chunk getChunk(int A, int B, ChunkStatus C, boolean D) {
        if (that != null) {
            return that.getChunk(A, B, C, D);
        } else {
            return super.getChunk(A, B, C, D);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean clearBlockState(BlockPos A, boolean B) {
        if (that != null) {
            return that.clearBlockState(A, B);
        } else {
            return super.clearBlockState(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean breakBlock(BlockPos A, boolean B) {
        if (that != null) {
            return that.breakBlock(A, B);
        } else {
            return super.breakBlock(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Random getRandom() {
        if (that != null) {
            return that.getRandom();
        } else {
            return super.getRandom();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void updateNeighbors(BlockPos A, Block B) {
        if (that != null) {
            that.updateNeighbors(A, B);
        } else {
            super.updateNeighbors(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean testBlockState(BlockPos A, Predicate B) {
        if (that != null) {
            return that.testBlockState(A, B);
        } else {
            return super.testBlockState(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public BlockState getBlockState(BlockPos A) {
        if (that != null) {
            return that.getBlockState(A);
        } else {
            return super.getBlockState(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public BlockState getBlockState(BoundingBox A, Block B) {
        if (that != null) {
            return that.getBlockState(A, B);
        } else {
            return super.getBlockState(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void playSound(double A, double B, double C, SoundEvent D, SoundCategory E, float F, float G, boolean H) {
        if (that != null) {
            that.playSound(A, B, C, D, E, F, G, H);
        } else {
            super.playSound(A, B, C, D, E, F, G, H);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void playSound(PlayerEntity A, BlockPos B, SoundEvent C, SoundCategory D, float E, float F) {
        if (that != null) {
            that.playSound(A, B, C, D, E, F);
        } else {
            super.playSound(A, B, C, D, E, F);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void addParticle(ParticleParameters A, double B, double C, double D, double E, double F, double G) {
        if (that != null) {
            that.addParticle(A, B, C, D, E, F, G);
        } else {
            super.addParticle(A, B, C, D, E, F, G);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void addParticle(ParticleParameters A, boolean B, double C, double D, double E, double F, double G, double H) {
        if (that != null) {
            that.addParticle(A, B, C, D, E, F, G, H);
        } else {
            super.addParticle(A, B, C, D, E, F, G, H);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public GameRules getGameRules() {
        if (that != null) {
            return that.getGameRules();
        } else {
            return super.getGameRules();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public BlockPos getSpawnPos() {
        if (that != null) {
            return that.getSpawnPos();
        } else {
            return super.getSpawnPos();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void setSpawnPos(BlockPos A) {
        if (that != null) {
            that.setSpawnPos(A);
        } else {
            super.setSpawnPos(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean isDaylight() {
        if (that != null) {
            return that.isDaylight();
        } else {
            return super.isDaylight();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public BlockPos getRandomPosInChunk(int A, int B, int C, int D) {
        if (that != null) {
            return that.getRandomPosInChunk(A, B, C, D);
        } else {
            return super.getRandomPosInChunk(A, B, C, D);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public int getTicksSinceLightning() {
        if (that != null) {
            return that.getTicksSinceLightning();
        } else {
            return super.getTicksSinceLightning();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void setTicksSinceLightning(int A) {
        if (that != null) {
            that.setTicksSinceLightning(A);
        } else {
            super.setTicksSinceLightning(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void addFireworkParticle(double A, double B, double C, double D, double E, double F, CompoundTag G) {
        if (that != null) {
            that.addFireworkParticle(A, B, C, D, E, F, G);
        } else {
            super.addFireworkParticle(A, B, C, D, E, F, G);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void updateHorizontalAdjacent(BlockPos A, Block B) {
        if (that != null) {
            that.updateHorizontalAdjacent(A, B);
        } else {
            super.updateHorizontalAdjacent(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean isEmittingRedstonePower(BlockPos A, Direction B) {
        if (that != null) {
            return that.isEmittingRedstonePower(A, B);
        } else {
            return super.isEmittingRedstonePower(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public int getEmittedRedstonePower(BlockPos A, Direction B) {
        if (that != null) {
            return that.getEmittedRedstonePower(A, B);
        } else {
            return super.getEmittedRedstonePower(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public int getReceivedRedstonePower(BlockPos A) {
        if (that != null) {
            return that.getReceivedRedstonePower(A);
        } else {
            return super.getReceivedRedstonePower(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public CrashReportSection addDetailsToCrashReport(CrashReport A) {
        if (that != null) {
            return that.addDetailsToCrashReport(A);
        } else {
            return super.addDetailsToCrashReport(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void removeBlockEntity(BlockPos A) {
        if (that != null) {
            that.removeBlockEntity(A);
        } else {
            super.removeBlockEntity(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public String getChunkProviderStatus() {
        if (that != null) {
            return that.getChunkProviderStatus();
        } else {
            return super.getChunkProviderStatus();
        }
    }

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	//synthesized method
	public void handler$method_18471$zba000(CallbackInfo A){
	if(that != null)
	{
	that.handler$method_18471$zba000(A);
	}
	else
	{
	super.handler$method_18471$zba000(A);
	}
	}

	*/

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void calculateAmbientDarkness() {
        if (that != null) {
            that.calculateAmbientDarkness();
        } else {
            super.calculateAmbientDarkness();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public int getReceivedStrongRedstonePower(BlockPos A) {
        if (that != null) {
            return that.getReceivedStrongRedstonePower(A);
        } else {
            return super.getReceivedStrongRedstonePower(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public float getThunderGradient(float A) {
        if (that != null) {
            return that.getThunderGradient(A);
        } else {
            return super.getThunderGradient(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean doesBlockHaveSolidTopSurface(BlockPos blockPos_1, Entity entity_1) {
        if (that != null) {
            return that.doesBlockHaveSolidTopSurface(blockPos_1, entity_1);
        } else {
            return super.doesBlockHaveSolidTopSurface(blockPos_1, entity_1);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void addImportantParticle(ParticleParameters A, double B, double C, double D, double E, double F, double G) {
        if (that != null) {
            that.addImportantParticle(A, B, C, D, E, F, G);
        } else {
            super.addImportantParticle(A, B, C, D, E, F, G);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void addImportantParticle(ParticleParameters A, boolean B, double C, double D, double E, double F, double G, double H) {
        if (that != null) {
            that.addImportantParticle(A, B, C, D, E, F, G, H);
        } else {
            super.addImportantParticle(A, B, C, D, E, F, G, H);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public float getStarsBrightness(float A) {
        if (that != null) {
            return that.getStarsBrightness(A);
        } else {
            return super.getStarsBrightness(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void scheduleBlockRender(BlockPos A) {
        if (that != null) {
            that.scheduleBlockRender(A);
        } else {
            super.scheduleBlockRender(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void setMobSpawnOptions(boolean A, boolean B) {
        if (that != null) {
            that.setMobSpawnOptions(A, B);
        } else {
            super.setMobSpawnOptions(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean isHeightValidAndBlockLoaded(BlockPos A) {
        if (that != null) {
            return that.isHeightValidAndBlockLoaded(A);
        } else {
            return super.isHeightValidAndBlockLoaded(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void updateNeighborsExcept(BlockPos A, Block B, Direction C) {
        if (that != null) {
            that.updateNeighborsExcept(A, B, C);
        } else {
            super.updateNeighborsExcept(A, B, C);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void updateNeighborsAlways(BlockPos A, Block B) {
        if (that != null) {
            that.updateNeighborsAlways(A, B);
        } else {
            super.updateNeighborsAlways(A, B);
        }
    }

	/*@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void initWeatherGradients(){
	if(that != null)
	{
	that.initWeatherGradients();
	}
	else
	{
	super.initWeatherGradients();
	}
	}*/

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public BlockState getTopNonAirState(BlockPos A) {
        if (that != null) {
            return that.getTopNonAirState(A);
        } else {
            return super.getTopNonAirState(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean isReceivingRedstonePower(BlockPos A) {
        if (that != null) {
            return that.isReceivingRedstonePower(A);
        } else {
            return super.isReceivingRedstonePower(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void setThunderGradient(float A) {
        if (that != null) {
            that.setThunderGradient(A);
        } else {
            super.setThunderGradient(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public int getEffectiveHeight() {
        if (that != null) {
            return that.getEffectiveHeight();
        } else {
            return super.getEffectiveHeight();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean containsBlockWithMaterial(BoundingBox A, Material B) {
        if (that != null) {
            return that.containsBlockWithMaterial(A, B);
        } else {
            return super.containsBlockWithMaterial(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean doesAreaContainFireSource(BoundingBox A) {
        if (that != null) {
            return that.doesAreaContainFireSource(A);
        } else {
            return super.doesAreaContainFireSource(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public LevelProperties getLevelProperties() {
        if (that != null) {
            return that.getLevelProperties();
        } else {
            return super.getLevelProperties();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public int getAmbientDarkness() {
        if (that != null) {
            return that.getAmbientDarkness();
        } else {
            return super.getAmbientDarkness();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public LocalDifficulty getLocalDifficulty(BlockPos A) {
        if (that != null) {
            return that.getLocalDifficulty(A);
        } else {
            return super.getLocalDifficulty(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public int getEmittedStrongRedstonePower(BlockPos A, Direction B) {
        if (that != null) {
            return that.getEmittedStrongRedstonePower(A, B);
        } else {
            return super.getEmittedStrongRedstonePower(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void disconnect() {
        if (that != null) {
            that.disconnect();
        } else {
            super.disconnect();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public long getSeed() {
        if (that != null) {
            return that.getSeed();
        } else {
            return super.getSeed();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean hasRain(BlockPos A) {
        if (that != null) {
            return that.hasRain(A);
        } else {
            return super.hasRain(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public World getWorld() {
        if (that != null) {
            return that.getWorld();
        } else {
            return super.getWorld();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public BlockPos getTopPosition(Type A, BlockPos B) {
        if (that != null) {
            return that.getTopPosition(A, B);
        } else {
            return super.getTopPosition(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public BlockEntity getBlockEntity(BlockPos A) {
        if (that != null) {
            return that.getBlockEntity(A);
        } else {
            return super.getBlockEntity(A);
        }
    }

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	//final/static/abstract/private/protected method
	public boolean isHeightInvalid(BlockPos A){
	if(that != null)
	{
	return that.isHeightInvalid(A);
	}
	else
	{
	return super.isHeightInvalid(A);
	}
	}

	*/

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	//final/static/abstract/private/protected method
	public boolean isHeightInvalid(int A){
	if(that != null)
	{
	return that.isHeightInvalid(A);
	}
	else
	{
	return super.isHeightInvalid(A);
	}
	}

	*/

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public float getRainGradient(float A) {
        if (that != null) {
            return that.getRainGradient(A);
        } else {
            return super.getRainGradient(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Vec3d getSkyColor(BlockPos A, float B) {
        if (that != null) {
            return that.getSkyColor(A, B);
        } else {
            return super.getSkyColor(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public WorldChunk method_8497(int A, int B) {
        if (that != null) {
            return that.method_8497(A, B);
        } else {
            return super.method_8497(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public LevelGeneratorType getGeneratorType() {
        if (that != null) {
            return that.getGeneratorType();
        } else {
            return super.getGeneratorType();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public float getAmbientLight(float A) {
        if (that != null) {
            return that.getAmbientLight(A);
        } else {
            return super.getAmbientLight(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public WorldChunk getWorldChunk(BlockPos A) {
        if (that != null) {
            return that.getWorldChunk(A);
        } else {
            return super.getWorldChunk(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void updateNeighbor(BlockPos A, Block B, BlockPos C) {
        if (that != null) {
            that.updateNeighbor(A, B, C);
        } else {
            super.updateNeighbor(A, B, C);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void playEvent(int A, BlockPos B, int C) {
        if (that != null) {
            that.playEvent(A, B, C);
        } else {
            super.playEvent(A, B, C);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public float method_8442(float A) {
        if (that != null) {
            return that.method_8442(A);
        } else {
            return super.method_8442(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Vec3d getCloudColor(float A) {
        if (that != null) {
            return that.getCloudColor(A);
        } else {
            return super.getCloudColor(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Vec3d getFogColor(float A) {
        if (that != null) {
            return that.getFogColor(A);
        } else {
            return super.getFogColor(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean addBlockEntity(BlockEntity A) {
        if (that != null) {
            return that.addBlockEntity(A);
        } else {
            return super.addBlockEntity(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void addBlockEntities(Collection A) {
        if (that != null) {
            that.addBlockEntities(A);
        } else {
            super.addBlockEntities(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void method_18471() {
        if (that != null) {
            that.method_18471();
        } else {
            super.method_18471();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void tickEntity(Consumer A, Entity B) {
        if (that != null) {
            that.tickEntity(A, B);
        } else {
            super.tickEntity(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Explosion createExplosion(Entity A, double B, double C, double D, float E, class_4179 F) {
        if (that != null) {
            return that.createExplosion(A, B, C, D, E, F);
        } else {
            return super.createExplosion(A, B, C, D, E, F);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Explosion createExplosion(Entity A, double B, double C, double D, float E, boolean F, class_4179 G) {
        if (that != null) {
            return that.createExplosion(A, B, C, D, E, F, G);
        } else {
            return super.createExplosion(A, B, C, D, E, F, G);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void setBlockEntity(BlockPos A, BlockEntity B) {
        if (that != null) {
            that.setBlockEntity(A, B);
        } else {
            super.setBlockEntity(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public List method_18023(EntityType A, BoundingBox B, Predicate C) {
        if (that != null) {
            return that.method_18023(A, B, C);
        } else {
            return super.method_18023(A, B, C);
        }
    }

	/*@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void tickTime(){
	if(that != null)
	{
	that.tickTime();
	}
	else
	{
	super.tickTime();
	}
	}*/

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void setRainGradient(float A) {
        if (that != null) {
            that.setRainGradient(A);
        } else {
            super.setRainGradient(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean method_8506(PlayerEntity A, BlockPos B, Direction C) {
        if (that != null) {
            return that.method_8506(A, B, C);
        } else {
            return super.method_8506(A, B, C);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public double getHorizonHeight() {
        if (that != null) {
            return that.getHorizonHeight();
        } else {
            return super.getHorizonHeight();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean isThundering() {
        if (that != null) {
            return that.isThundering();
        } else {
            return super.isThundering();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void markDirty(BlockPos A, BlockEntity B) {
        if (that != null) {
            that.markDirty(A, B);
        } else {
            super.markDirty(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean isAreaNotEmpty(BoundingBox A) {
        if (that != null) {
            return that.isAreaNotEmpty(A);
        } else {
            return super.isAreaNotEmpty(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean isRaining() {
        if (that != null) {
            return that.isRaining();
        } else {
            return super.isRaining();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean hasHighHumidity(BlockPos A) {
        if (that != null) {
            return that.hasHighHumidity(A);
        } else {
            return super.hasHighHumidity(A);
        }
    }

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	//final/static/abstract/private/protected method
	public void wait(){
	try{
	if(that != null)
	{
	that.wait();
	}
	else
	{
	super.wait();
	}
	}catch(Exception e){}
	}

	*/

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	//final/static/abstract/private/protected method
	public void wait(long A, int B){
	try{
	if(that != null)
	{
	that.wait(A, B);
	}
	else
	{
	super.wait(A, B);
	}
	}catch(Exception e){}
	}

	*/

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	//final/static/abstract/private/protected method
	public void wait(long A){
	try{
	if(that != null)
	{
	that.wait(A);
	}
	else
	{
	super.wait(A);
	}
	}catch(Exception e){}
	}

	*/

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean equals(Object A) {
        if (that != null) {
            return that.equals(A);
        } else {
            return super.equals(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public String toString() {
        if (that != null) {
            return that.toString();
        } else {
            return super.toString();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public int hashCode() {
        if (that != null) {
            return that.hashCode();
        } else {
            return super.hashCode();
        }
    }

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	//final/static/abstract/private/protected method
	public Class getClass(){
	if(that != null)
	{
	return that.getClass();
	}
	else
	{
	return super.getClass();
	}
	}

	*/

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	//final/static/abstract/private/protected method
	public void notify(){
	if(that != null)
	{
	that.notify();
	}
	else
	{
	super.notify();
	}
	}

	*/

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	//final/static/abstract/private/protected method
	public void notifyAll(){
	if(that != null)
	{
	that.notifyAll();
	}
	else
	{
	super.notifyAll();
	}
	}

	*/

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean isSkyVisible(BlockPos A) {
        if (that != null) {
            return that.isSkyVisible(A);
        } else {
            return super.isSkyVisible(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public int method_8312(LightType A, BlockPos B) {
        if (that != null) {
            return that.method_8312(A, B);
        } else {
            return super.method_8312(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public int getLightmapIndex(BlockPos A, int B) {
        if (that != null) {
            return that.getLightmapIndex(A, B);
        } else {
            return super.getLightmapIndex(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public int getHeight() {
        if (that != null) {
            return that.getHeight();
        } else {
            return super.getHeight();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public int getMaxLightLevel() {
        if (that != null) {
            return that.getMaxLightLevel();
        } else {
            return super.getMaxLightLevel();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public int getLuminance(BlockPos A) {
        if (that != null) {
            return that.getLuminance(A);
        } else {
            return super.getLuminance(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public BlockHitResult rayTraceBlock(Vec3d A, Vec3d B, BlockPos C, VoxelShape D, BlockState E) {
        if (that != null) {
            return that.rayTraceBlock(A, B, C, D, E);
        } else {
            return super.rayTraceBlock(A, B, C, D, E);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public BlockHitResult rayTrace(RayTraceContext A) {
        if (that != null) {
            return that.rayTrace(A);
        } else {
            return super.rayTrace(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Stream getCollisionShapes(Entity A, VoxelShape B, Set C) {
        if (that != null) {
            return that.getCollisionShapes(A, B, C);
        } else {
            return super.getCollisionShapes(A, B, C);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public float method_8391() {
        if (that != null) {
            return that.method_8391();
        } else {
            return super.method_8391();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean intersectsEntities(Entity A, VoxelShape B) {
        if (that != null) {
            return that.intersectsEntities(A, B);
        } else {
            return super.intersectsEntities(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public float getSkyAngle(float A) {
        if (that != null) {
            return that.getSkyAngle(A);
        } else {
            return super.getSkyAngle(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public int getMoonPhase() {
        if (that != null) {
            return that.getMoonPhase();
        } else {
            return super.getMoonPhase();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Difficulty getDifficulty() {
        if (that != null) {
            return that.getDifficulty();
        } else {
            return super.getDifficulty();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean isChunkLoaded(int A, int B) {
        if (that != null) {
            return that.isChunkLoaded(A, B);
        } else {
            return super.isChunkLoaded(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public List<Entity> getEntities(Entity entity_1, BoundingBox boundingBox_1) {
        if (that != null) {
            return that.getEntities(entity_1, boundingBox_1);
        } else {
            return super.getEntities(entity_1, boundingBox_1);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public List method_18467(Class A, BoundingBox B) {
        if (that != null) {
            return that.method_18467(A, B);
        } else {
            return super.method_18467(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public PlayerEntity method_18459(double A, double B, double C, double D, boolean E) {
        if (that != null) {
            return that.method_18459(A, B, C, D, E);
        } else {
            return super.method_18459(A, B, C, D, E);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public LivingEntity method_18465(Class A, TargetPredicate B, LivingEntity C, double D, double E, double F, BoundingBox G) {
        if (that != null) {
            return that.method_18465(A, B, C, D, E, F, G);
        } else {
            return super.method_18465(A, B, C, D, E, F, G);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public List method_18466(Class A, TargetPredicate B, LivingEntity C, BoundingBox D) {
        if (that != null) {
            return that.method_18466(A, B, C, D);
        } else {
            return super.method_18466(A, B, C, D);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public PlayerEntity method_18460(Entity A, double B) {
        if (that != null) {
            return that.method_18460(A, B);
        } else {
            return super.method_18460(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public PlayerEntity method_18462(TargetPredicate A, LivingEntity B) {
        if (that != null) {
            return that.method_18462(A, B);
        } else {
            return super.method_18462(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public PlayerEntity method_18463(TargetPredicate A, LivingEntity B, double C, double D, double E) {
        if (that != null) {
            return that.method_18463(A, B, C, D, E);
        } else {
            return super.method_18463(A, B, C, D, E);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean method_18458(double A, double B, double C, double D) {
        if (that != null) {
            return that.method_18458(A, B, C, D);
        } else {
            return super.method_18458(A, B, C, D);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public LivingEntity method_18468(List A, TargetPredicate B, LivingEntity C, double D, double E, double F) {
        if (that != null) {
            return that.method_18468(A, B, C, D, E, F);
        } else {
            return super.method_18468(A, B, C, D, E, F);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public PlayerEntity method_18461(TargetPredicate A, double B, double C, double D) {
        if (that != null) {
            return that.method_18461(A, B, C, D);
        } else {
            return super.method_18461(A, B, C, D);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public PlayerEntity method_8604(double A, double B, double C, double D, Predicate E) {
        if (that != null) {
            return that.method_8604(A, B, C, D, E);
        } else {
            return super.method_8604(A, B, C, D, E);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public PlayerEntity method_18457(double A, double B, double C) {
        if (that != null) {
            return that.method_18457(A, B, C);
        } else {
            return super.method_18457(A, B, C);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public List method_18464(TargetPredicate A, LivingEntity B, BoundingBox C) {
        if (that != null) {
            return that.method_18464(A, B, C);
        } else {
            return super.method_18464(A, B, C);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public PlayerEntity method_18470(UUID A) {
        if (that != null) {
            return that.method_18470(A);
        } else {
            return super.method_18470(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean doesNotCollide(Entity A, BoundingBox B) {
        if (that != null) {
            return that.doesNotCollide(A, B);
        } else {
            return super.doesNotCollide(A, B);
        }
    }



    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean doesNotCollide(Entity A, BoundingBox B, Set C) {
        if (that != null) {
            return that.doesNotCollide(A, B, C);
        } else {
            return super.doesNotCollide(A, B, C);
        }
    }

    @Override
    public Stream<VoxelShape> getCollisionShapes(Entity entity_1, BoundingBox boundingBox_1, Set<Entity> set_1) {
        if(that != null) {
            return that.getCollisionShapes(entity_1, boundingBox_1, set_1);
        } else {
            return super.getCollisionShapes(entity_1, boundingBox_1, set_1);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean isAir(BlockPos A) {
        if (that != null) {
            return that.isAir(A);
        } else {
            return super.isAir(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean isBlockLoaded(BlockPos A) {
        if (that != null) {
            return that.isBlockLoaded(A);
        } else {
            return super.isBlockLoaded(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean method_8626(BlockPos A) {
        if (that != null) {
            return that.method_8626(A);
        } else {
            return super.method_8626(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public int getLightLevel(BlockPos A) {
        if (that != null) {
            return that.getLightLevel(A);
        } else {
            return super.getLightLevel(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Chunk getChunk(BlockPos A) {
        if (that != null) {
            return that.getChunk(A);
        } else {
            return super.getChunk(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Chunk getChunk(int A, int B, ChunkStatus C) {
        if (that != null) {
            return that.getChunk(A, B, C);
        } else {
            return super.getChunk(A, B, C);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean isWaterAt(BlockPos A) {
        if (that != null) {
            return that.isWaterAt(A);
        } else {
            return super.isWaterAt(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean intersectsEntities(Entity A) {
        if (that != null) {
            return that.intersectsEntities(A);
        } else {
            return super.intersectsEntities(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public int method_8603(BlockPos A, int B) {
        if (that != null) {
            return that.method_8603(A, B);
        } else {
            return super.method_8603(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean canPlace(BlockState A, BlockPos B, VerticalEntityPosition C) {
        if (that != null) {
            return that.canPlace(A, B, C);
        } else {
            return super.canPlace(A, B, C);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean doesNotCollide(BoundingBox A) {
        if (that != null) {
            return that.doesNotCollide(A);
        } else {
            return super.doesNotCollide(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean intersectsFluid(BoundingBox A) {
        if (that != null) {
            return that.intersectsFluid(A);
        } else {
            return super.intersectsFluid(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean doesNotCollide(Entity A) {
        if (that != null) {
            return that.doesNotCollide(A);
        } else {
            return super.doesNotCollide(A);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean isAreaLoaded(BlockPos A, BlockPos B) {
        if (that != null) {
            return that.isAreaLoaded(A, B);
        } else {
            return super.isAreaLoaded(A, B);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public boolean isAreaLoaded(int A, int B, int C, int D, int E, int F) {
        if (that != null) {
            return that.isAreaLoaded(A, B, C, D, E, F);
        } else {
            return super.isAreaLoaded(A, B, C, D, E, F);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public float getBrightness(BlockPos A) {
        if (that != null) {
            return that.getBrightness(A);
        } else {
            return super.getBrightness(A);
        }
    }


}
