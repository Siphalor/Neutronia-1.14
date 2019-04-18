package team.hollow.neutronia.mixin.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AbstractTraderEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.TraderOfferList;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import team.hollow.neutronia.village.ConditionalTradeFactory;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Mixin(AbstractTraderEntity.class)
public abstract class AbstractTraderEntityMixin extends PassiveEntity {
	protected AbstractTraderEntityMixin(EntityType<? extends PassiveEntity> entityType_1, World world_1) {
		super(entityType_1, world_1);
	}

	/*@Inject(method = "fillRecipesFromPool", at = @At("HEAD"))
	public void onFillRecipesFromPool(TraderOfferList tradeOffers, TradeOffers.Factory[] factories, int amount, CallbackInfo callbackInfo) {
		factories = Arrays.stream(factories).filter(factory -> ((ConditionalTradeFactory) factory).neutronia$isApplicable((AbstractTraderEntity) (Object) this, random)).toArray(TradeOffers.Factory[]::new);
	}*/

	@Inject(method = "fillRecipesFromPool", at = @At(value = "INVOKE", target = "Ljava/util/Set;iterator()Ljava/util/Iterator;"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
	public void onTradesReady(TraderOfferList tradeOffers, TradeOffers.Factory[] factories, int amount, CallbackInfo callbackInfo, Set<Integer> tradeIds) {
		ArrayList<Integer> applicableTrades = new ArrayList<>();
		for (int i = 0; i < factories.length; i++) {
			if (((ConditionalTradeFactory) factories[i]).neutronia$isApplicable((AbstractTraderEntity) (Object) this, random)) {
				applicableTrades.add(i);
			}
		}
		Set<Integer> disallowedTrades = tradeIds.stream().filter(id -> !applicableTrades.contains(id)).collect(Collectors.toSet());
		for (int id : disallowedTrades) {
			tradeIds.remove(id);
			disallowedTrades.remove(id);
			if (applicableTrades.size() > tradeIds.size() - disallowedTrades.size())
				while (tradeIds.size() < amount) {
					tradeIds.add(applicableTrades.get(random.nextInt(applicableTrades.size())));
				}
		}
	}
}
