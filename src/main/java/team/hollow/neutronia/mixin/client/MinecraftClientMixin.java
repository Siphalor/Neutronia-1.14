package team.hollow.neutronia.mixin.client;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.BlockColorMap;
import net.minecraft.client.render.item.ItemColorMap;
import net.minecraft.item.block.BlockItem;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.hollow.neutronia.init.NBlocks;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {

    @Shadow
    private ItemColorMap itemColorMap;
    @Shadow
    private BlockColorMap blockColorMap;

    @Shadow
    public abstract BlockColorMap getBlockColorMap();

    @Inject(at = @At("RETURN"), method = "init()V")
    public void afterInit(CallbackInfo info) {
        getBlockColorMap().register((var0x, var1, var2, var3) -> {
            if (MinecraftClient.getInstance().player != null && MinecraftClient.getInstance().world != null) {
                return blockColorMap.getRenderColor(var0x, MinecraftClient.getInstance().player.world, new BlockPos(MinecraftClient.getInstance().player), var3);
            } else {
                return blockColorMap.getRenderColor(var0x, null, null, var3);
            }
        }, NBlocks.POTTED_GRASS, NBlocks.POTTED_TALL_GRASS, NBlocks.LACEWOOD_LEAVES, NBlocks.BOLIVIAN_ROSEWOOD_LEAVES/*, NBlocks.MANGROVE_LEAVES, NBlocks.RED_MANGROVE_LEAVES*/);
        itemColorMap.register((stack, tintIndex) -> {
                    if (MinecraftClient.getInstance().player != null && MinecraftClient.getInstance().world != null) {
                        return blockColorMap.getRenderColor(((BlockItem) stack.getItem()).getBlock().getDefaultState(), MinecraftClient.getInstance().player.world, new BlockPos(MinecraftClient.getInstance().player), tintIndex);
                    } else {
                        BlockState blockState_1 = ((BlockItem) stack.getItem()).getBlock().getDefaultState();
                        return blockColorMap.getRenderColor(blockState_1, null, null, tintIndex);
                    }
                }, Blocks.GRASS_BLOCK, Blocks.GRASS, Blocks.FERN, Blocks.VINE, Blocks.OAK_LEAVES, Blocks.SPRUCE_LEAVES,
                Blocks.BIRCH_LEAVES, Blocks.JUNGLE_LEAVES, Blocks.ACACIA_LEAVES, Blocks.DARK_OAK_LEAVES, Blocks.LILY_PAD,
                Blocks.TALL_GRASS, Blocks.LARGE_FERN, NBlocks.POTTED_GRASS, NBlocks.POTTED_TALL_GRASS, NBlocks.LACEWOOD_LEAVES,
                NBlocks.BOLIVIAN_ROSEWOOD_LEAVES/*, NBlocks.MANGROVE_LEAVES, NBlocks.RED_MANGROVE_LEAVES*/);

    }
}