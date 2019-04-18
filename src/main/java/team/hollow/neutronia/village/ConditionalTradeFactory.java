package team.hollow.neutronia.village;

import net.minecraft.entity.passive.AbstractTraderEntity;

import java.util.Random;

public interface ConditionalTradeFactory {
	boolean neutronia$isApplicable(AbstractTraderEntity entity, Random random);
}
