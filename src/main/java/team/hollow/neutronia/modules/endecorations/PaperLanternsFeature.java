package team.hollow.neutronia.modules.endecorations;

import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.util.registry.Registry;
import team.hollow.module_api.api.features.woodtype.WoodTypeFeature;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.blocks.PaperLanternBlock;
import team.hollow.neutronia.entity.FlyingLanternEntity;
import team.hollow.neutronia.registry.ContentBuilder;
import team.hollow.neutronia.registry.WoodType;

import java.util.Collections;

public class PaperLanternsFeature extends WoodTypeFeature {
	public static EntityType flyingLanternEntityEntityType;

	public PaperLanternsFeature() {
		super("paper-lanterns", "Adds floating paper lanterns", Collections.emptySet());
	}

	private static ItemStack dispense(BlockPointer blockPointer, ItemStack itemStack) {
		Direction direction = blockPointer.getBlockState().get(DispenserBlock.FACING);
		Position output = DispenserBlock.getOutputLocation(blockPointer);

		FlyingLanternEntity entity = FlyingLanternEntity.create(blockPointer.getWorld(), output.getX(), output.getY(), output.getZ(), Block.getBlockFromItem(itemStack.getItem()).getDefaultState());
		entity.setVelocity(direction.getOffsetX() * 0.03, 0, direction.getOffsetZ() * 0.03);
		blockPointer.getWorld().spawnEntity(entity);

		itemStack.subtractAmount(1);
		return itemStack;
	}

	@Override
	protected void applyEnabled() {
		super.applyEnabled();

		flyingLanternEntityEntityType = Registry.register(Registry.ENTITY_TYPE, new Identifier(Neutronia.MOD_ID, "flying_lantern"), FabricEntityTypeBuilder.create(EntityCategory.MISC, FlyingLanternEntity::new).size(1.0F, 1.0F).build());
	}

	@Override
	protected void process(WoodType woodType) {
		ContentBuilder contentBuilder = ContentBuilder.getInstance();
		Block lantern = contentBuilder.newBlock(woodType.getIdentifier().getPath() + "_paper_lantern", new PaperLanternBlock());

		DispenserBlock.registerBehavior(lantern, PaperLanternsFeature::dispense);
	}
}
