package generators;

import net.minecraft.block.Block;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemProvider;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.blocks.*;
import team.hollow.neutronia.unsure.ContentBuilder;

public class ContentResourceBuilder extends ContentBuilder {
	protected Identifier baseIdentifier;

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
		Identifier identifier = new Identifier(Neutronia.MOD_ID, name);
		Identifier textureIdentifier = new Identifier(Neutronia.MOD_ID, textureName);

		if(block instanceof PillarBlock) {
			ResourceGenerator.genPillarBlock(identifier, extendIdentifier(textureIdentifier, "_top"), textureIdentifier);
		} else if(block instanceof NeutroniaBookshelfBlock) {
			ResourceGenerator.genSimpleBlockstates(identifier);
			ResourceGenerator.genPillarBlockModel(identifier, baseIdentifier, textureIdentifier);
			ResourceGenerator.genSimpleBlockItemModel(identifier);
		} else if(block instanceof NeutroniaSaplingBlock) {
            ResourceGenerator.genPlant(identifier, textureIdentifier);
		} else if(block instanceof NeutroniaDoorBlock) {
			// TODO
		} else if(block instanceof NeutroniaTrapdoorBlock) {
			// TODO
		} else if(block instanceof NeutroniaBottomTopBlock) {
			ResourceGenerator.genBottomTopBlockModel(identifier, extendIdentifier(textureIdentifier, "_bottom"), extendIdentifier(textureIdentifier, "_top"), textureIdentifier);

		} else {
			ResourceGenerator.genSimpleBlock(identifier, textureIdentifier);
		}
		return null;
	}

	@Override
	public Block newCompressedBlock(String name, Block block) {
		newBlock(name, block);
		return null;
	}

	@Override
	public void setBaseName(String name) {
		baseIdentifier = new Identifier(Neutronia.MOD_ID, name);
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
		ResourceGenerator.genSlab(extendIdentifier(baseIdentifier, "_slab"), baseIdentifier, baseIdentifier, baseIdentifier, baseIdentifier);
		return null;
	}

	@Override
	public Block stairs() {
		ResourceGenerator.genStair(extendIdentifier(baseIdentifier, "_stairs"), baseIdentifier, baseIdentifier, baseIdentifier);
		return null;
	}

	@Override
	public Block fence() {
		ResourceGenerator.genFenceBlock(extendIdentifier(baseIdentifier, "_fence"), baseIdentifier);
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
        // TODO
		return null;
	}

	@Override
	public Block pressurePlate(PressurePlateBlock.Type type) {
        ResourceGenerator.genPressurePlate(extendIdentifier(baseIdentifier, "_pressure_plate"), baseIdentifier);
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
		// TODO
		return null;
	}
}
