package team.hollow.neutronia.mixin.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.class_4178;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import team.hollow.neutronia.items.SeedsItemAccessor;

import java.util.Map;
import java.util.Objects;

@Mixin(FlowerPotBlock.class)
public class FlowerPotBlockMixin {
    private static final ThreadLocal<ItemStack> NEUTRONIA$HELD_ITEM = new ThreadLocal<>();

    @Shadow
    @Final
    private static Map<Block, Block> CONTENT_TO_POTTED;

    private FlowerPotBlockMixin() {
    }

    @Inject(method = "activate", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void setHeldItem(
            final BlockState state,
            final World world,
            final BlockPos pos,
            final PlayerEntity player,
            final Hand hand,
            final BlockHitResult hit,
            final CallbackInfoReturnable<Boolean> cir,
            /* Captured */ final ItemStack heldItem
    ) {
        NEUTRONIA$HELD_ITEM.set(heldItem);
    }

    @Inject(method = "activate", at = @At("RETURN"))
    private void clearHeldItem(final BlockState state, final World world, final BlockPos pos, final PlayerEntity player, final Hand hand, final BlockHitResult hit,
                               final CallbackInfoReturnable<Boolean> cir) {
        NEUTRONIA$HELD_ITEM.remove();
    }

    @ModifyVariable(method = "activate", at = @At(value = "JUMP", opcode = Opcodes.IFEQ, shift = Shift.BEFORE))
    private Block checkForCrops(final Block block) {
        if (Blocks.AIR == block) {
            final Item item = Objects.requireNonNull(NEUTRONIA$HELD_ITEM.get(), "held item").getItem();
            if (item instanceof class_4178) {
                return CONTENT_TO_POTTED.getOrDefault(((SeedsItemAccessor) item).getBlock(), Blocks.AIR);
            }
        }
        return block;
    }
}