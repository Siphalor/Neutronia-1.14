package team.abnormals.neutronia.client.entity.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import team.abnormals.neutronia.entity.SocialVillager;
import team.abnormals.neutronia.utils.TextureAssembler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Environment(value=EnvType.CLIENT)
public class SocialVillagerRenderer extends MobEntityRenderer{

	public SocialVillagerRenderer(EntityRenderDispatcher dispatcher) {
		super(dispatcher, new PlayerEntityModel<>(0.0F, true), 0.5F);
	}

	@Override
	protected Identifier getTexture(Entity entity) {
		if (this.getRenderManager().textureManager.getTexture(new Identifier("minecraft:dynamic/" + entity.getDataTracker().get(SocialVillager.serverUUID) + "_1")) != null) {
			return new Identifier("minecraft:dynamic/" + entity.getDataTracker().get(SocialVillager.serverUUID) + "_1");
		}
        SocialVillager entityIn = (SocialVillager) entity;
        System.out.printf("Gender: %s", entityIn.getSex());
        boolean female = entityIn.getSex().equals("Female");
        this.model = new PlayerEntityModel<>(0.0F, female);
		String hairColor = entityIn.getDataTracker().get(SocialVillager.hairColorUnified);
		String eyeColor = entityIn.getDataTracker().get(SocialVillager.eyeColorUnified);
		String skinColor = entityIn.getDataTracker().get(SocialVillager.skinColorUnified);
		int hairStyle = entityIn.getDataTracker().get(SocialVillager.hairStyleUnified);
		BufferedImage imageBase = new TextureAssembler(eyeColor, hairColor, skinColor, hairStyle, entityIn.getSex()).createTexture();
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