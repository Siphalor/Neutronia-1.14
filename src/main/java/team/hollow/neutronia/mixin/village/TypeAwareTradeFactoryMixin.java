package team.hollow.neutronia.mixin.village;

import net.minecraft.entity.passive.AbstractTraderEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Item;
import net.minecraft.village.VillagerType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.hollow.neutronia.village.ConditionalTradeFactory;

import java.util.Map;
import java.util.Random;

@Mixin(targets = "net/minecraft/village/TradeOffers$TypeAwareBuyForOneEmeraldFactory")
public class TypeAwareTradeFactoryMixin implements ConditionalTradeFactory {
	@Shadow @Final private Map<VillagerType, Item> map;

	@SuppressWarnings("UnresolvedMixinReference")
	@Inject(method = "method_19201(Lnet/minecraft/village/VillagerType;)V", at = @At("HEAD"), cancellable = true)
	private static void onCheckVillagerTypes(VillagerType villagerType, CallbackInfo callbackInfo) {
        callbackInfo.cancel();
	}

	@Override
	public boolean neutronia$isApplicable(AbstractTraderEntity entity, Random random) {
        if(entity instanceof VillagerEntity) {
        	return map.containsKey(((VillagerEntity) entity).getVillagerData().getType());
		}
        return false;
	}
}
