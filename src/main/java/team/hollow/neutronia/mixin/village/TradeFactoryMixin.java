package team.hollow.neutronia.mixin.village;

import net.minecraft.entity.passive.AbstractTraderEntity;
import org.spongepowered.asm.mixin.Mixin;
import team.hollow.neutronia.village.ConditionalTradeFactory;

import java.util.Random;

@Mixin(targets = "net.minecraft.village.TradeOffers$Factory")
public interface TradeFactoryMixin extends ConditionalTradeFactory {
	@Override
	default boolean neutronia$isApplicable(AbstractTraderEntity entity, Random random) {
		return true;
	}
}
