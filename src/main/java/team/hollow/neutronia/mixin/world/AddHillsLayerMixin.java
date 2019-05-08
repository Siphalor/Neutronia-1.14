package team.hollow.neutronia.mixin.world;

import net.minecraft.world.biome.layer.AddHillsLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import team.hollow.neutronia.Neutronia;

import java.util.Random;

@Mixin(AddHillsLayer.class)
public class AddHillsLayerMixin {

    @ModifyConstant(method = "sample", constant = @Constant(ordinal = 0))
    private int sample(int int_3) {
        return Neutronia.doMiniBiomes ? new Random().nextInt(10000) : int_3;
    }

}
