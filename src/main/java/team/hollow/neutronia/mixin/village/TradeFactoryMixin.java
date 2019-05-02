package team.hollow.neutronia.mixin.village;

import net.minecraft.entity.passive.AbstractTraderEntity;
import net.minecraft.village.TradeOffers;
import org.spongepowered.asm.mixin.Mixin;
import team.hollow.neutronia.village.ConditionalTradeFactory;

import java.util.Random;

@Mixin(TradeOffers.Factory.class)
public interface TradeFactoryMixin extends ConditionalTradeFactory {

    @Override
    default boolean neutronia$isApplicable(AbstractTraderEntity entity, Random random) {
        return true;
    }

}
