package team.hollow.neutronia.client.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.hollow.neutronia.entity.FlyingLanternEntity;

import java.util.Random;

public class FlyingLanternEntityRenderer extends EntityRenderer<FlyingLanternEntity> {
	public FlyingLanternEntityRenderer(EntityRenderDispatcher entityRenderDispatcher_1) {
		super(entityRenderDispatcher_1);
	}

	@Override
	public void render(FlyingLanternEntity flyingLanternEntity, double double_1, double double_2, double double_3, float float_1, float float_2) {
		BlockState blockState_1 = flyingLanternEntity.getBlockState();
		if (blockState_1.getRenderType() == BlockRenderType.MODEL) {
			World world_1 = MinecraftClient.getInstance().world;
			if (blockState_1 != world_1.getBlockState(new BlockPos(flyingLanternEntity)) && blockState_1.getRenderType() != BlockRenderType.INVISIBLE) {
				this.bindTexture(SpriteAtlasTexture.BLOCK_ATLAS_TEX);
				GlStateManager.pushMatrix();
				GlStateManager.disableLighting();
				Tessellator tessellator_1 = Tessellator.getInstance();
				BufferBuilder bufferBuilder_1 = tessellator_1.getBufferBuilder();
				if (this.field_4674) {
					GlStateManager.enableColorMaterial();
					GlStateManager.setupSolidRenderingTextureCombine(this.getOutlineColor(flyingLanternEntity));
				}

				bufferBuilder_1.begin(7, VertexFormats.POSITION_COLOR_UV_LMAP);
				BlockPos blockPos_1 = new BlockPos(flyingLanternEntity.x, flyingLanternEntity.getBoundingBox().maxY, flyingLanternEntity.z);
				GlStateManager.translatef((float)(double_1 - (double)blockPos_1.getX() - 0.5D), (float)(double_2 - (double)blockPos_1.getY()), (float)(double_3 - (double)blockPos_1.getZ() - 0.5D));
				BlockRenderManager blockRenderManager_1 = MinecraftClient.getInstance().getBlockRenderManager();
				blockRenderManager_1.getModelRenderer().tesselate(world_1, blockRenderManager_1.getModel(blockState_1), blockState_1, blockPos_1, bufferBuilder_1, false, new Random(), blockState_1.getRenderingSeed(flyingLanternEntity.getDataTracker().get(FlyingLanternEntity.INITIAL_BLOCK_POS)));
				tessellator_1.draw();
				if (this.field_4674) {
					GlStateManager.tearDownSolidRenderingTextureCombine();
					GlStateManager.disableColorMaterial();
				}

				GlStateManager.enableLighting();
				GlStateManager.popMatrix();
				super.render(flyingLanternEntity, double_1, double_2, double_3, float_1, float_2);
			}
		}
	}

	@Override
	protected Identifier getTexture(FlyingLanternEntity var1) {
		return SpriteAtlasTexture.BLOCK_ATLAS_TEX;
	}
}
