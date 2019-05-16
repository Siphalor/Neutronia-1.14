package team.hollow.neutronia.mixin.block;

import net.minecraft.block.BarrelBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BarrelBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BarrelBlockEntity.class)
public abstract class BarrelBlockEntityMixin extends LootableContainerBlockEntity {
	@Shadow protected abstract void playSound(BlockState blockState_1, SoundEvent soundEvent_1);

	@Shadow protected abstract void setOpen(BlockState blockState_1, boolean boolean_1);

	protected BarrelBlockEntityMixin(BlockEntityType<?> blockEntityType_1) {
		super(blockEntityType_1);
	}

	@Inject(method = "tick", at = @At(value = "INVOKE", target = "net/minecraft/block/entity/BarrelBlockEntity.invalidate()V"), cancellable = true)
	public void onTick(CallbackInfo callbackInfo) {
		BlockState blockState = getCachedState();
		if(blockState.getBlock() instanceof BarrelBlock) {
			boolean open = blockState.get(BarrelBlock.OPEN);
			if(open) {
				playSound(blockState, SoundEvents.BLOCK_BARREL_CLOSE);
				setOpen(blockState, false);
			}
			callbackInfo.cancel();
		}
	}
}
