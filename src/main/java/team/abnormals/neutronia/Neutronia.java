package team.abnormals.neutronia;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.carver.Carver;
import net.minecraft.world.gen.carver.CarverConfig;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;
import net.minecraft.world.level.LevelGeneratorType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.abnormals.neutronia.commands.Locate2Command;
import team.abnormals.neutronia.init.*;
import team.abnormals.neutronia.world.OverworldChunkGenerator;
import team.abnormals.neutronia.world.OverworldChunkGeneratorConfig;
import team.abnormals.neutronia.world.gen.ImprovedOverworldLevelType;
import team.abnormals.neutronia.world.gen.features.OreGeneration;

import java.lang.reflect.*;
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

        final Field fieldA;
        try {
            fieldA = Carver.class.getDeclaredField( "CAVE" );
            fieldA.setAccessible(true);

            Field modifiersField = Field.class.getDeclaredField( "modifiers" );
            modifiersField.setAccessible( true );
            modifiersField.setInt( fieldA, fieldA.getModifiers() & ~Modifier.FINAL );

            fieldA.set(null, register("cave", new team.abnormals.neutronia.world.gen.carver.CaveCarver(ProbabilityConfig::deserialize, 256)));
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
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

}