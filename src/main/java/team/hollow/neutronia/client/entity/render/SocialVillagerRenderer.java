package team.hollow.neutronia.client.entity.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.entity.SocialVillager;
import team.hollow.neutronia.utils.TextureAssembler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Environment(value = EnvType.CLIENT)
public class SocialVillagerRenderer extends MobEntityRenderer<SocialVillager, PlayerEntityModel<SocialVillager>> {

    public SocialVillagerRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new PlayerEntityModel<>(0.0F, true), 0.5F);
    }

    @Override
    protected Identifier getTexture(SocialVillager entity) {
        if (this.getRenderManager().textureManager.getTexture(new Identifier("minecraft:dynamic/" + entity.getDataTracker().get(SocialVillager.serverUUID) + "_1")) != null) {
            return new Identifier("minecraft:dynamic/" + entity.getDataTracker().get(SocialVillager.serverUUID) + "_1");
        }
        boolean female = entity.getSex().equals("Female");
        this.model = new PlayerEntityModel<>(0.0F, female);
        String hairColor = entity.getDataTracker().get(SocialVillager.hairColorUnified);
        String eyeColor = entity.getDataTracker().get(SocialVillager.eyeColorUnified);
        String skinColor = entity.getDataTracker().get(SocialVillager.skinColorUnified);
        int hairStyle = entity.getDataTracker().get(SocialVillager.hairStyleUnified);
        BufferedImage imageBase = new TextureAssembler(eyeColor, hairColor, skinColor, hairStyle, entity.getSex()).createTexture();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            ImageIO.write(imageBase, "png", stream);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        InputStream is = new ByteArrayInputStream(stream.toByteArray());
        NativeImage base = null;
        try {
            base = NativeImage.fromInputStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        NativeImageBackedTexture texture = new NativeImageBackedTexture(base);
        return this.getRenderManager().textureManager.registerDynamicTexture(entity.getDataTracker().get(SocialVillager.serverUUID), texture);
    }

}