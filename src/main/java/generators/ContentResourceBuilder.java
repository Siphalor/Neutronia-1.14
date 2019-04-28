package generators;

import net.minecraft.block.Block;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemProvider;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.hollow.neutronia.blocks.*;
import team.hollow.neutronia.unsure.ContentBuilder;

public class ContentResourceBuilder extends ContentBuilder {
	protected Identifier baseTextureIdentifier;
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
			resourceGenerator.genPillarBlockModel(identifier, baseTextureIdentifier, textureIdentifier);
			resourceGenerator.genSimpleBlockItemModel(identifier);
		} else if(block instanceof NeutroniaSaplingBlock) {
            resourceGenerator.genPlant(identifier, textureIdentifier);
		} else if(block instanceof NeutroniaBottomTopBlock) {
			resourceGenerator.genSimpleBlockstates(identifier);
			resourceGenerator.genBottomTopBlockModel(identifier, extendIdentifier(textureIdentifier, "_bottom"), extendIdentifier(textureIdentifier, "_top"), textureIdentifier);
			resourceGenerator.genSimpleBlockItemModel(identifier);
		} else if(block instanceof CakeBaseBlock) {
			resourceGenerator.genCake(7, identifier, extendIdentifier(textureIdentifier, "_bottom"), extendIdentifier(textureIdentifier, "_side"), extendIdentifier(textureIdentifier, "_inner"), extendIdentifier(textureIdentifier, "_top"));
		} else if(block instanceof PieBlock) {
			resourceGenerator.genCake(4, identifier, extendIdentifier(textureIdentifier, "_bottom"), extendIdentifier(textureIdentifier, "_side"), extendIdentifier(textureIdentifier, "_inner"), extendIdentifier(textureIdentifier, "_top"));
		} else {
			resourceGenerator.genSimpleBlock(identifier, textureIdentifier);
		}

		resourceGenerator.genSimpleLootTable(identifier, identifier);
		return null;
	}

	@Override
	public void asBaseBlock(Block block, Identifier name) {
		super.asBaseBlock(block, name);
		baseTextureIdentifier = name;
	}

	@Override
	public Block newCompressedBlock(String name, Block block) {
		newBlock(name, block);
		return null;
	}

	@Override
	public void setBaseTexture(Identifier name) {
		baseTextureIdentifier = name;
	}

	@Override
	public void setBaseName(Identifier name) {
		baseNameIdentifier = name;
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
		Identifier identifier = extendIdentifier("_slab");
		resourceGenerator.genSlab(identifier, baseTextureIdentifier, baseTextureIdentifier, baseTextureIdentifier, baseTextureIdentifier);
		resourceGenerator.genSimpleLootTable(identifier, identifier);
		return null;
	}

	@Override
	public Block stairs() {
		Identifier identifier = extendIdentifier("_stairs");
		resourceGenerator.genStair(identifier, baseTextureIdentifier, baseTextureIdentifier, baseTextureIdentifier);
		resourceGenerator.genSimpleLootTable(identifier, identifier);
		return null;
	}

	@Override
	public Block fence() {
		Identifier identifier = extendIdentifier("_fence");
		resourceGenerator.genFence(identifier, baseTextureIdentifier, false);
        resourceGenerator.genSimpleLootTable(identifier, identifier);
		return null;
	}

	@Override
	public Block fenceGate() {
        Identifier identifier = extendIdentifier("_fence_gate");
        resourceGenerator.genFenceGate(identifier, baseTextureIdentifier);
        resourceGenerator.genSimpleLootTable(identifier, identifier);
		return null;
	}

	@Override
	public Block door() {
		Identifier identifier = extendIdentifier("_door");
		resourceGenerator.genDoor(identifier, extendIdentifier(identifier, "_bottom"), extendIdentifier(identifier, "_top"));
		resourceGenerator.genSimpleLootTable(identifier, identifier);
		return null;
	}

	@Override
	public Block trapDoor() {
		Identifier identifier = extendIdentifier("_trapdoor");
		resourceGenerator.genTrapdoor(identifier, identifier);
		resourceGenerator.genSimpleLootTable(identifier, identifier);
		return null;
	}

	@Override
	public Block wall() {
        Identifier identifier = extendIdentifier("_wall");
        resourceGenerator.genFence(identifier, baseTextureIdentifier, true);
        resourceGenerator.genSimpleLootTable(identifier, identifier);
		return null;
	}

	@Override
	public Block button(boolean wooden) {
        Identifier identifier = extendIdentifier("_button");
        resourceGenerator.genButton(identifier, baseTextureIdentifier);
        resourceGenerator.genSimpleLootTable(identifier, identifier);
		return null;
	}

	@Override
	public Block pressurePlate(PressurePlateBlock.Type type) {
		Identifier identifier = extendIdentifier("_pressure_plate");
        resourceGenerator.genPressurePlate(identifier, baseTextureIdentifier);
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
        Identifier identifier = extendIdentifier("_siding");
        resourceGenerator.genSiding(identifier, baseTextureIdentifier);
        resourceGenerator.genSimpleLootTable(identifier, identifier);
		return null;
	}

	@Override
	public Block addPotted() {
		addPotted(new Identifier(baseTextureIdentifier.getNamespace(), "blocks/" + baseTextureIdentifier.getPath()));
		return null;
	}

	@Override
	public Block addPotted(Identifier plantTexture) {
		Identifier identifier = new Identifier(getModId(), "potted_" + baseNameIdentifier.getPath());
		resourceGenerator.genPottedBlock(identifier, plantTexture);
        resourceGenerator.genSimpleLootTable(identifier, baseNameIdentifier, Registry.ITEM.getId(Items.FLOWER_POT));
		return null;
	}
}
