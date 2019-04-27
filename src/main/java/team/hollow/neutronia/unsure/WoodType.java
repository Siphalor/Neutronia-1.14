package team.hollow.neutronia.unsure;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class WoodType {
	public static final WoodType SPRUCE = WoodTypeRegistry.registerVanilla(new WoodType("spruce", Blocks.SPRUCE_PLANKS));
	public static final WoodType OAK = WoodTypeRegistry.registerVanilla(new WoodType("oak", Blocks.OAK_PLANKS));
	public static final WoodType BIRCH = WoodTypeRegistry.registerVanilla(new WoodType("birch", Blocks.BIRCH_PLANKS));
	public static final WoodType JUNGLE = WoodTypeRegistry.registerVanilla(new WoodType("jungle", Blocks.JUNGLE_PLANKS));
	public static final WoodType ACACIA = WoodTypeRegistry.registerVanilla(new WoodType("acacia", Blocks.ACACIA_PLANKS));
	public static final WoodType DARK_OAK = WoodTypeRegistry.registerVanilla(new WoodType("dark_oak", Blocks.DARK_OAK_PLANKS));
	public static final WoodType[] VANILLA = new WoodType[]{OAK, SPRUCE, BIRCH, JUNGLE, ACACIA, DARK_OAK};

	protected String name;
	protected Block baseBlock;

	public WoodType(String name, Block baseBlock) {
		this.name = name;
		this.baseBlock = baseBlock;
	}

	public String getName() {
		return name;
	}

	public String getBaseBlockName() {
		return name + "_planks";
	}

	public Block getBaseBlock() {
		return baseBlock;
	}
}
