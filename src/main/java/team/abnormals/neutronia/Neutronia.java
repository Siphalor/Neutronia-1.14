package team.abnormals.neutronia;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;
import net.minecraft.world.level.LevelGeneratorType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.abnormals.neutronia.api.RegionCore;
import team.abnormals.neutronia.commands.Locate2Command;
import team.abnormals.neutronia.init.*;
import team.abnormals.neutronia.world.ImprovedOverworldChunkGenerator;
import team.abnormals.neutronia.world.ImprovedOverworldChunkGeneratorConfig;
import team.abnormals.neutronia.world.OverworldChunkGenerator;
import team.abnormals.neutronia.world.OverworldChunkGeneratorConfig;
import team.abnormals.neutronia.world.elements.RandomOptions;
import team.abnormals.neutronia.world.elements.Shallows;
import team.abnormals.neutronia.world.elements.Spires;
import team.abnormals.neutronia.world.gen.ImprovedOverworldLevelType;
import team.abnormals.neutronia.world.gen.ImprovedOverworldTwoLevelType;
import team.abnormals.neutronia.world.gen.features.OreGeneration;
import therealfarfetchd.minicfg.MiniCfg;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.function.Supplier;

public class Neutronia implements ModInitializer {

    public static final String MODID = "neutronia";
    public static final MiniCfg CONFIG = new LoadingSpiceConfig();

    public static LevelGeneratorType IMPROVED_OVERWORLD_LEVEL_TYPE = null;
    public static ChunkGeneratorType<OverworldChunkGeneratorConfig, OverworldChunkGenerator> IMPROVED_OVERWORLD;

    public static LevelGeneratorType IMPROVED_OVERWORLD_TWO_LEVEL_TYPE = null;
    public static ChunkGeneratorType<ImprovedOverworldChunkGeneratorConfig, ImprovedOverworldChunkGenerator> IMPROVED_OVERWORLD_TWO;

    @Override
    public void onInitialize() {

        //create the improved overworld level type
        IMPROVED_OVERWORLD_LEVEL_TYPE = ImprovedOverworldLevelType.getType();

        //register the improved overworld generator type
        ImprovedOverworldGeneratorCreator factory = new ImprovedOverworldGeneratorCreator();

        IMPROVED_OVERWORLD = factory.getChunkGeneratorType(OverworldChunkGeneratorConfig::new);

        Registry.register(Registry.CHUNK_GENERATOR_TYPE, "neutronia:improved_overworld", IMPROVED_OVERWORLD);

        //create the improved overworld level type
        IMPROVED_OVERWORLD_TWO_LEVEL_TYPE = ImprovedOverworldTwoLevelType.getType();

        //register the improved overworld generator type
        ImprovedOverworldTwoGeneratorCreator factory2 = new ImprovedOverworldTwoGeneratorCreator();

        IMPROVED_OVERWORLD_TWO = factory2.getChunkGeneratorType(ImprovedOverworldChunkGeneratorConfig::new);

        Registry.register(Registry.CHUNK_GENERATOR_TYPE, "neutronia:improved_overworld_two", IMPROVED_OVERWORLD_TWO);

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

        new Spires();
        new RandomOptions();
        new Shallows();

        RegionCore.registerPreset(new Identifier("neutronia:presets/list.txt"));
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
    private class ImprovedOverworldGeneratorCreator implements InvocationHandler
    {
        private Object factoryProxy;
        private Class factoryClass;

        ImprovedOverworldGeneratorCreator(){
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

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) {
            if(args.length == 3 &&
                    args[0] instanceof World &&
                    args[1] instanceof BiomeSource &&
                    args[2] instanceof OverworldChunkGeneratorConfig
            ){

                return createProxy((World)args[0],
                        (BiomeSource)args[1],
                        (OverworldChunkGeneratorConfig)args[2]);
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
    private class ImprovedOverworldTwoGeneratorCreator implements InvocationHandler
    {
        private Object factoryProxy;
        private Class factoryClass;

        ImprovedOverworldTwoGeneratorCreator(){
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

        public ImprovedOverworldChunkGenerator createProxy(World w, BiomeSource biomesource, ImprovedOverworldChunkGeneratorConfig gensettings) {
            return new ImprovedOverworldChunkGenerator(w,biomesource,gensettings);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) {
            if(args.length == 3 &&
                    args[0] instanceof World &&
                    args[1] instanceof BiomeSource &&
                    args[2] instanceof ImprovedOverworldChunkGeneratorConfig
            ){

                return createProxy((World)args[0],
                        (BiomeSource)args[1],
                        (ImprovedOverworldChunkGeneratorConfig)args[2]);
            }
            throw(new UnsupportedOperationException("Unknown Method: " + method.toString()));
        }

        public ChunkGeneratorType<ImprovedOverworldChunkGeneratorConfig, ImprovedOverworldChunkGenerator> getChunkGeneratorType(Supplier<ImprovedOverworldChunkGeneratorConfig> supplier){
            Constructor<?>[] initlst = ChunkGeneratorType.class.getDeclaredConstructors();
            final Logger log = LogManager.getLogger("ChunkGenErr");

            for(Constructor<?> init : initlst){
                init.setAccessible(true);
                if(init.getParameterCount() != 3){
                    continue; //skip
                }
                //lets try it
                try {
                    return (ChunkGeneratorType<ImprovedOverworldChunkGeneratorConfig, ImprovedOverworldChunkGenerator>) init.newInstance(factoryProxy, true, supplier);
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