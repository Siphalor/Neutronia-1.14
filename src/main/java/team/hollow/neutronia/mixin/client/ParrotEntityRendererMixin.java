package team.hollow.neutronia.mixin.client;

import net.minecraft.client.render.entity.ParrotEntityRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ParrotEntityRenderer.class)
public class ParrotEntityRendererMixin {

    @Shadow @Final public static Identifier[] SKINS = new Identifier[] {
            new Identifier("textures/entity/parrot/parrot_red_blue.png"),
            new Identifier("textures/entity/parrot/parrot_blue.png"),
            new Identifier("textures/entity/parrot/parrot_green.png"),
            new Identifier("textures/entity/parrot/parrot_yellow_blue.png"),
            new Identifier("textures/entity/parrot/parrot_grey.png"),
            new Identifier("textures/entity/parrot/parrot_black.png"),
            new Identifier("textures/entity/parrot/parrot_brown.png"),
            new Identifier("textures/entity/parrot/parrot_budgie.png"),
            new Identifier("textures/entity/parrot/parrot_grey.png"),
            new Identifier("textures/entity/parrot/parrot_jeb.png"),
            new Identifier("textures/entity/parrot/parrot_light_blue.png"),
            new Identifier("textures/entity/parrot/parrot_pink.png"),
            new Identifier("textures/entity/parrot/parrot_purple.png"),
            new Identifier("textures/entity/parrot/parrot_rainbow.png"),
            new Identifier("textures/entity/parrot/parrot_white.png"),
            new Identifier("textures/entity/parrot/parrot_white_bellied_caique.png"),
            new Identifier("textures/entity/parrot/parrot_yellow.png")
    };

}
