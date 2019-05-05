package team.hollow.neutronia.modules.villagesandvillagers;

import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.sound.SoundEvent;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import team.hollow.module_api.api.features.OptionalFeature;
import team.hollow.neutronia.village.PointOfInterestRegistry;
import team.hollow.neutronia.village.PointOfInterestTypeCustom;
import team.hollow.neutronia.village.VillagerProfessionRegistry;

import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class VillagerProfessionFeature extends OptionalFeature {
	protected String name;
	protected Set<BlockState> pointsOfInterest;
	protected SoundEvent soundEvent;
	private final Supplier<Map<Integer, TradeOffers.Factory[]>> tradeOfferSupplier;
	public PointOfInterestType pointOfInterest;
	public VillagerProfession profession;

	public VillagerProfessionFeature(String name, String enablesDescription, SoundEvent soundEvent, Set<BlockState> pointsOfInterest, Supplier<Map<Integer, TradeOffers.Factory[]>> tradeOfferSupplier) {
		super(name, enablesDescription);
		this.name = name;
		this.pointsOfInterest = pointsOfInterest;
		this.soundEvent = soundEvent;
		this.tradeOfferSupplier = tradeOfferSupplier;
	}

	@Override
	protected void applyEnabled() {
		pointOfInterest = PointOfInterestRegistry.register(new PointOfInterestTypeCustom(name + "_poi", pointsOfInterest, 1, soundEvent));
		profession = VillagerProfessionRegistry.register(name, pointOfInterest, ImmutableSet.of(), ImmutableSet.of());
		TradeOffers.PROFESSION_TO_LEVELED_TRADE.put(profession, new Int2ObjectOpenHashMap<>(tradeOfferSupplier.get()));
	}
}
