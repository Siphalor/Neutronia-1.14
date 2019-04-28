package generators;

import net.minecraft.block.Block;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemProvider;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.blocks.*;
import team.hollow.neutronia.unsure.ContentBuilder;

public class ContentResourceBuilder extends ContentBuilder {
	protected Identifier baseIdentifier;
	protected String modId;
	protected ResourceGenerator resourceGenerator;

	public ContentResourceBuilder(String modId) {
		this.modId = modId;
		this.resourceGenerator = new ResourceGenerator();
	}

	@Override
	public String getModId() {
		return modId;
	}

	@Override
	public void finish() {
		resourceGenerator.finish();
	}

	@Override
	public Item newItem(String name, Item item) {
        // TODO
		return null;
	}

	@Override
	public Block newBlock(String name, Block block) {
		return newBlock(name, block, name);
	}

	@Override
	public Block newBlock(String name, Block block, String textureName) {
		Identifier identifier = new Identifier(getModId(), name);
		Identifier textureIdentifier = new Identifier(getModId(), textureName);

		if(block instanceof PillarBlock) {
			resourceGenerator.genPillarBlock(identifier, extendIdentifier(textureIdentifier, "_top"), textureIdentifier);
		} else if(block instanceof NeutroniaBookshelfBlock) {
			resourceGenerator.genSimpleBlockstates(identifier);
			resourceGenerator.genPillarBlockModel(identifier, baseIdentifier, textureIdentifier);
			resourceGenerator.genSimpleBlockItemModel(identifier);
		} else if(block instanceof NeutroniaSaplingBlock) {
            resourceGenerator.genPlant(identifier, textureIdentifier);
		} else if(block instanceof NeutroniaDoorBlock) {
			resourceGenerator.genDoor(identifier, extendIdentifier(textureIdentifier, "_bottom"), extendIdentifier(textureIdentifier, "_top"));
		} else if(block instanceof NeutroniaTrapdoorBlock) {
            resourceGenerator.genTrapdoor(identifier, textureIdentifier);
		} else if(block instanceof NeutroniaBottomTopBlock) {
			resourceGenerator.genSimpleBlockstates(identifier);
			resourceGenerator.genBottomTopBlockModel(identifier, extendIdentifier(textureIdentifier, "_bottom"), extendIdentifier(textureIdentifier, "_top"), textureIdentifier);
			resourceGenerator.genSimpleBlockItemModel(identifier);

		} else {
			resourceGenerator.genSimpleBlock(identifier, textureIdentifier);
		}

		resourceGenerator.genSimpleLootTable(identifier, identifier);
		return null;
	}

	@Override
	public Block newCompressedBlock(String name, Block block) {
		newBlock(name, block);
		return null;
	}

	@Override
	public void setBaseName(Identifier name) {
		baseIdentifier = name;
	}

	@Override
	public void setBaseBlock(Block block) {

	}

	@Override
	public void setBaseItem(ItemProvider itemProvider) {

	}

	@Override
	public void setSecondaryItem(ItemProvider itemProvider) {

	}

	@Override
	public Block slab() {
		Identifier identifier = extendIdentifier(baseIdentifier, "_slab");
		resourceGenerator.genSlab(identifier, baseIdentifier, baseIdentifier, baseIdentifier, baseIdentifier);
		resourceGenerator.genSimpleLootTable(identifier, identifier);
		return null;
	}

	@Override
	public Block stairs() {
		Identifier identifier = extendIdentifier(baseIdentifier, "_stairs");
		resourceGenerator.genStair(identifier, baseIdentifier, baseIdentifier, baseIdentifier);
		resourceGenerator.genSimpleLootTable(identifier, identifier);
		return null;
	}

	@Override
	public Block fence() {
		Identifier identifier = extendIdentifier(baseIdentifier, "_fence");
		resourceGenerator.genFence(identifier, baseIdentifier);
        resourceGenerator.genSimpleLootTable(identifier, identifier);
		return null;
	}

	@Override
	public Block fenceGate() {
        // TODO
		return null;
	}

	@Override
	public Block wall() {
        // TODO
		return null;
	}

	@Override
	public Block button(boolean wooden) {
        Identifier identifier = extendIdentifier(baseIdentifier, "_button");
        resourceGenerator.genButton(identifier, baseIdentifier);
        resourceGenerator.genSimpleLootTable(identifier, identifier);
		return null;
	}

	@Override
	public Block pressurePlate(PressurePlateBlock.Type type) {
		Identifier identifier = extendIdentifier(baseIdentifier, "_pressure_plate");
        resourceGenerator.genPressurePlate(identifier, baseIdentifier);
        resourceGenerator.genSimpleLootTable(identifier, identifier);
		return null;
	}

	@Override
	public Block corner() {
		// TODO
		return null;
	}

	@Override
	public Block post() {
		// TODO
		return null;
	}

	@Override
	public Block siding() {
        Identifier identifier = extendIdentifier(baseIdentifier, "_siding");
        resourceGenerator.genSiding(identifier, baseIdentifier);
        resourceGenerator.genSimpleLootTable(identifier, identifier);
		return null;
	}
}
