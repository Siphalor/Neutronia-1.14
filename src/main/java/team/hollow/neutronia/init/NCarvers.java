package team.hollow.neutronia.init;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.carver.Carver;
import team.hollow.neutronia.world.gen.carver.CaveCarver;

public class NCarvers {

    public static final Carver<ProbabilityConfig> CAVE = Registry.register(Registry.CARVER, new Identifier("neutronia:cave_mod"), new CaveCarver(ProbabilityConfig::deserialize, 256));

}
