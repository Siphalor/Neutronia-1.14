package team.abnormals.neutronia.world.dimension;

import net.minecraft.world.dimension.DimensionType;

public class AetherDimensionType extends DimensionType {

	public AetherDimensionType()
	{
		super(1, "", "", OverworldDimension::new, true);
	}

}