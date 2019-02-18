package team.abnormals.neutronia.client.entity.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import team.abnormals.neutronia.entity.SocialVillagerBase;
import team.abnormals.neutronia.entity.SocialVillagerMale;
import team.abnormals.neutronia.utils.TextureAssembler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Environment(value=EnvType.CLIENT)
public class SocialVillagerMaleRenderer extends MobEntityRenderer{

	public SocialVillagerMaleRenderer(EntityRenderDispatcher dispatcher)
	{
		super(dispatcher, new PlayerEntityModel(0.0F, true), 0.5F);
	}
	public SocialVillagerMaleRenderer(EntityRenderDispatcher entityRenderDispatcher_1, EntityModel entityModel_1,
                                      float float_1) {
		super(entityRenderDispatcher_1, entityModel_1, float_1);
	}

	@Override
	protected Identifier getTexture(Entity entity) {
		if (this.getRenderManager().textureManager.getTexture(new Identifier("minecraft:/" + entity.getDataTracker().get(SocialVillagerBase.serverUUID) + "_1")) != null)
		{
			System.out.println("Dynamic texture acquired from prior building");
			return new Identifier("minecraft:/" + entity.getDataTracker().get(SocialVillagerBase.serverUUID) + "_1");
		}
		SocialVillagerMale entityIn = (SocialVillagerMale) entity;
		String hairColor = entityIn.getDataTracker().get(SocialVillagerBase.hairColorUnified);
		String eyeColor = entityIn.getDataTracker().get(SocialVillagerBase.eyeColorUnified);
		String skinColor = entityIn.getDataTracker().get(SocialVillagerBase.skinColorUnified);
		int hairStyle = entityIn.getDataTracker().get(SocialVillagerBase.hairStyleUnified);
		BufferedImage imageBase = new TextureAssembler(eyeColor,hairColor,skinColor, hairStyle, "Male").createTexture();
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
		return this.getRenderManager().textureManager.registerDynamicTexture(entity.getDataTracker().get(SocialVillagerBase.serverUUID), texture);
	}



}