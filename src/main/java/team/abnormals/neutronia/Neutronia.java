package team.abnormals.neutronia;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.JsonOps;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.datafixers.NbtOps;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.source.*;
import net.minecraft.world.gen.carver.Carver;
import net.minecraft.world.gen.carver.CarverConfig;
import net.minecraft.world.gen.chunk.*;
import net.minecraft.world.level.LevelGeneratorType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.abnormals.neutronia.commands.Locate2Command;
import team.abnormals.neutronia.init.*;
import team.abnormals.neutronia.world.OverworldChunkGenerator;
import team.abnormals.neutronia.world.OverworldChunkGeneratorConfig;
import team.abnormals.neutronia.world.gen.ImprovedOverworldLevelType;
import team.abnormals.neutronia.world.gen.features.OreGeneration;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.function.Supplier;

public class Neutronia implements ModInitializer {

    public static final String MODID = "neutronia";

    public static LevelGeneratorType IMPROVED_OVERWORLD_LEVEL_TYPE = null;
    public static ChunkGeneratorType<OverworldChunkGeneratorConfig, OverworldChunkGenerator> IMPROVED_OVERWORLD;

    @Override
    public void onInitialize() {
        IMPROVED_OVERWORLD_LEVEL_TYPE = ImprovedOverworldLevelType.getType();
        OverworldGeneratorCreator factory = new OverworldGeneratorCreator();
        IMPROVED_OVERWORLD = factory.getChunkGeneratorType(OverworldChunkGeneratorConfig::new);
        Registry.register(Registry.CHUNK_GENERATOR_TYPE, "neutronia:improved_overworld", IMPROVED_OVERWORLD);

        new NBlocks();
        NItems.init();
        NBlockEntities.init();
        CommandRegistry.INSTANCE.register(false, (Locate2Command::register));
        ModVillagers.init();
        NEntityTypes.init();
        NRecipeType.init();
        NRecipeSerializers.init();
        OreGeneration.registerOres();
        NRecipes.init();
    }

    private static <C extends CarverConfig, F extends Carver<C>> F register(String string_1, F carver_1) {
        return Registry.register(Registry.CARVER, string_1, carver_1);
    }

    /**
     * This is a bit hacky,
     * The short of it is we want to register the wastelands as CHUNK_GENERATOR_TYPE
     *
     * However  ChunkGeneratorType requires a factory interface ChunkGeneratorFactory
     * that is package private.  (thus we can't use the interface directly)
     *
     * The folowing class uses reflection to become an instance of "ChunkGeneratorFactory"
     * as well as reflection to create the ChunkGeneratorType object to pass in the
     * interface object
     */
    private class OverworldGeneratorCreator implements InvocationHandler
    {
        private Object factoryProxy;
        private Class factoryClass;

        OverworldGeneratorCreator(){
            //reflection hack, dev = mapped in dev enviroment, prod = intermediate value
            String dev_name = "net.minecraft.world.gen.chunk.ChunkGeneratorFactory";
            String prod_name = "net.minecraft.class_2801";

            try {
                factoryClass = Class.forName(dev_name);
            } catch (ClassNotFoundException e1){
                try {
                    factoryClass = Class.forName(prod_name);
                }catch (ClassNotFoundException e2){
                    throw(new RuntimeException("Unable to find " + dev_name));
                }
            }
            factoryProxy = Proxy.newProxyInstance(factoryClass.getClassLoader(),
                    new Class[] {factoryClass},
                    this);
        }

        public OverworldChunkGenerator createProxy(World w, BiomeSource biomesource, OverworldChunkGeneratorConfig gensettings) {
            return new OverworldChunkGenerator(w,biomesource,gensettings);
        }

        public ChunkGenerator<? extends ChunkGeneratorConfig> createGenerator(World world) {
            ChunkGeneratorType<CavesChunkGeneratorConfig, CavesChunkGenerator> chunkGeneratorType_3 = ChunkGeneratorType.CAVES;
            ChunkGeneratorType<FloatingIslandsChunkGeneratorConfig, FloatingIslandsChunkGenerator> chunkGeneratorType_4 = ChunkGeneratorType.FLOATING_ISLANDS;
            ChunkGeneratorType<OverworldChunkGeneratorConfig, OverworldChunkGenerator> chunkGeneratorType_5 = getChunkGeneratorType(OverworldChunkGeneratorConfig::new);
            BiomeSourceType<FixedBiomeSourceConfig, FixedBiomeSource> biomeSourceType_1 = BiomeSourceType.FIXED;
            BiomeSourceType<VanillaLayeredBiomeSourceConfig, VanillaLayeredBiomeSource> biomeSourceType_2 = BiomeSourceType.VANILLA_LAYERED;
            BiomeSourceType<CheckerboardBiomeSourceConfig, CheckerboardBiomeSource> biomeSourceType_3 = BiomeSourceType.CHECKERBOARD;

            BiomeSource biomeSource_1 = null;
            JsonElement jsonElement_1 = Dynamic.convert(NbtOps.INSTANCE, JsonOps.INSTANCE, world.getLevelProperties().getGeneratorOptions());
            JsonObject jsonObject_1 = jsonElement_1.getAsJsonObject();
            if (jsonObject_1.has("biome_source") && jsonObject_1.getAsJsonObject("biome_source").has("type") && jsonObject_1.getAsJsonObject("biome_source").has("options")) {
                BiomeSourceType<?, ?> biomeSourceType_4 = Registry.BIOME_SOURCE_TYPE.get(new Identifier(jsonObject_1.getAsJsonObject("biome_source").getAsJsonPrimitive("type").getAsString()));
                JsonObject jsonObject_2 = jsonObject_1.getAsJsonObject("biome_source").getAsJsonObject("options");
                Biome[] biomes_1 = new Biome[]{Biomes.OCEAN};
                if (jsonObject_2.has("biomes")) {
                    JsonArray jsonArray_1 = jsonObject_2.getAsJsonArray("biomes");
                    biomes_1 = jsonArray_1.size() > 0 ? new Biome[jsonArray_1.size()] : new Biome[]{Biomes.OCEAN};

                    for(int int_1 = 0; int_1 < jsonArray_1.size(); ++int_1) {
                        biomes_1[int_1] = Registry.BIOME.getOptional(new Identifier(jsonArray_1.get(int_1).getAsString())).orElse(Biomes.OCEAN);
                    }
                }

                if (BiomeSourceType.FIXED == biomeSourceType_4) {
                    FixedBiomeSourceConfig fixedBiomeSourceConfig_3 = biomeSourceType_1.getConfig().setBiome(biomes_1[0]);
                    biomeSource_1 = biomeSourceType_1.applyConfig(fixedBiomeSourceConfig_3);
                }

                if (BiomeSourceType.CHECKERBOARD == biomeSourceType_4) {
                    int int_2 = jsonObject_2.has("size") ? jsonObject_2.getAsJsonPrimitive("size").getAsInt() : 2;
                    CheckerboardBiomeSourceConfig checkerboardBiomeSourceConfig_1 = biomeSourceType_3.getConfig().method_8777(biomes_1).method_8780(int_2);
                    biomeSource_1 = biomeSourceType_3.applyConfig(checkerboardBiomeSourceConfig_1);
                }

                if (BiomeSourceType.VANILLA_LAYERED == biomeSourceType_4) {
                    VanillaLayeredBiomeSourceConfig vanillaLayeredBiomeSourceConfig_1 = biomeSourceType_2.getConfig().setGeneratorSettings(new net.minecraft.world.gen.chunk.OverworldChunkGeneratorConfig()).setLevelProperties(world.getLevelProperties());
                    biomeSource_1 = biomeSourceType_2.applyConfig(vanillaLayeredBiomeSourceConfig_1);
                }
            }

            if (biomeSource_1 == null) {
                biomeSource_1 = biomeSourceType_1.applyConfig(biomeSourceType_1.getConfig().setBiome(Biomes.OCEAN));
            }

            BlockState blockState_1 = Blocks.STONE.getDefaultState();
            BlockState blockState_2 = Blocks.WATER.getDefaultState();
            if (jsonObject_1.has("chunk_generator") && jsonObject_1.getAsJsonObject("chunk_generator").has("options")) {
                String string_2;
                if (jsonObject_1.getAsJsonObject("chunk_generator").getAsJsonObject("options").has("default_block")) {
                    string_2 = jsonObject_1.getAsJsonObject("chunk_generator").getAsJsonObject("options").getAsJsonPrimitive("default_block").getAsString();
                    blockState_1 = Registry.BLOCK.get(new Identifier(string_2)).getDefaultState();
                }

                if (jsonObject_1.getAsJsonObject("chunk_generator").getAsJsonObject("options").has("default_fluid")) {
                    string_2 = jsonObject_1.getAsJsonObject("chunk_generator").getAsJsonObject("options").getAsJsonPrimitive("default_fluid").getAsString();
                    blockState_2 = Registry.BLOCK.get(new Identifier(string_2)).getDefaultState();
                }
            }

            if (jsonObject_1.has("chunk_generator") && jsonObject_1.getAsJsonObject("chunk_generator").has("type")) {
                ChunkGeneratorType<?, ?> chunkGeneratorType_6 = Registry.CHUNK_GENERATOR_TYPE.get(new Identifier(jsonObject_1.getAsJsonObject("chunk_generator").getAsJsonPrimitive("type").getAsString()));
                if (ChunkGeneratorType.CAVES == chunkGeneratorType_6) {
                    CavesChunkGeneratorConfig cavesChunkGeneratorConfig_1 = chunkGeneratorType_3.createSettings();
                    cavesChunkGeneratorConfig_1.setDefaultBlock(blockState_1);
                    cavesChunkGeneratorConfig_1.setDefaultFluid(blockState_2);
                    return chunkGeneratorType_3.create(world, biomeSource_1, cavesChunkGeneratorConfig_1);
                }

                if (ChunkGeneratorType.FLOATING_ISLANDS == chunkGeneratorType_6) {
                    FloatingIslandsChunkGeneratorConfig floatingIslandsChunkGeneratorConfig_1 = chunkGeneratorType_4.createSettings();
                    floatingIslandsChunkGeneratorConfig_1.withCenter(new BlockPos(0, 64, 0));
                    floatingIslandsChunkGeneratorConfig_1.setDefaultBlock(blockState_1);
                    floatingIslandsChunkGeneratorConfig_1.setDefaultFluid(blockState_2);
                    return chunkGeneratorType_4.create(world, biomeSource_1, floatingIslandsChunkGeneratorConfig_1);
                }
            }

            OverworldChunkGeneratorConfig overworldChunkGeneratorConfig_1 = chunkGeneratorType_5.createSettings();
            overworldChunkGeneratorConfig_1.setDefaultBlock(blockState_1);
            overworldChunkGeneratorConfig_1.setDefaultFluid(blockState_2);
            return chunkGeneratorType_5.create(world, biomeSource_1, overworldChunkGeneratorConfig_1);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) {
            if(args.length == 3 &&
                    args[0] instanceof World &&
                    args[1] instanceof BiomeSource &&
                    args[2] instanceof OverworldChunkGeneratorConfig
            ){

                return createGenerator((World)args[0]);
            }
            throw(new UnsupportedOperationException("Unknown Method: " + method.toString()));
        }

        public ChunkGeneratorType<OverworldChunkGeneratorConfig, OverworldChunkGenerator> getChunkGeneratorType(Supplier<OverworldChunkGeneratorConfig> supplier){
            Constructor<?>[] initlst = ChunkGeneratorType.class.getDeclaredConstructors();
            final Logger log = LogManager.getLogger("ChunkGenErr");

            for(Constructor<?> init : initlst){
                init.setAccessible(true);
                if(init.getParameterCount() != 3){
                    continue; //skip
                }
                //lets try it
                try {
                    return (ChunkGeneratorType<OverworldChunkGeneratorConfig, OverworldChunkGenerator>) init.newInstance(factoryProxy, true, supplier);
                }
                catch (Exception e){
                    log.error("Error in calling Chunk Generator Type", e);
                }
            }
            log.error("Unable to find constructor for ChunkGeneratorType");
            return null;
        }
    }

}