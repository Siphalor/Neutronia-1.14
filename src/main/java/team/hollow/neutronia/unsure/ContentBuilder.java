package team.hollow.neutronia.unsure;

import net.minecraft.block.Block;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemProvider;
import net.minecraft.util.Identifier;

public abstract class ContentBuilder {
	private static ContentBuilder instance;

	public static void setInstance(ContentBuilder instance) {
		ContentBuilder.instance = instance;
	}
	public static ContentBuilder getInstance() {
		return instance;
	}

	protected Identifier baseNameIdentifier;

	public abstract String getModId();

	public abstract void finish();

	public abstract Item newItem(String name, Item item);

	public abstract Block newBlock(String name, Block block);
	public abstract Block newBlock(String name, Block block, String textureName);
	public Block newBaseBlock(String name, Block block) {
		newBlock(name, block);
        asBaseBlock(block, new Identifier(getModId(), name));
		return block;
	}

	public void asBaseBlock(Block block, Identifier name) {
		setBaseTexture(name);
		setBaseName(name);
		setBaseBlock(block);
		setBaseItem(block);
	}

	public Block newCompressedBlock(String name, Block block, ItemProvider baseItem) {
		setBaseItem(baseItem);
		return newCompressedBlock(name, block);
	}
	public abstract Block newCompressedBlock(String name, Block block);

	public abstract void setBaseTexture(Identifier name);
	public void setBaseName(Identifier name) {
		baseNameIdentifier = name;
	}
	public abstract void setBaseBlock(Block block);
	public abstract void setBaseItem(ItemProvider itemProvider);
	public abstract void setSecondaryItem(ItemProvider itemProvider);

	public abstract Block slab();
	public abstract Block stairs();
	public abstract Block fence();
	public abstract Block fenceGate();
	public abstract Block door();
	public abstract Block trapDoor();
	public abstract Block wall();
	public abstract Block button(boolean wooden);
	public abstract Block pressurePlate(PressurePlateBlock.Type type);
	public abstract Block corner();
	public abstract Block post();
	public abstract Block siding();
	public abstract Block addPotted();
	public abstract Block addPotted(Identifier plantTexture);

	public Identifier extendIdentifier(String suffix) {
		return extendIdentifier(baseNameIdentifier, suffix);
	}

	public Identifier extendIdentifier(Identifier identifier, String suffix) {
		return new Identifier(getModId(), identifier.getPath() + suffix);
	}
}