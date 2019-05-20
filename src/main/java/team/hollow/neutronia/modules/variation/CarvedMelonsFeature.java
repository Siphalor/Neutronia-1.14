package team.hollow.neutronia.modules.variation;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import team.hollow.abnormalib.modules.api.features.OptionalFeature;
import team.hollow.abnormalib.utils.ContentBuilder;
import team.hollow.abnormalib.utils.registry.BlockChiseler;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.blocks.CarvedMelonBlock;
import team.hollow.neutronia.enums.CarvedFaceType;
import team.hollow.neutronia.utils.NeutroniaTags;

import java.util.ArrayList;
import java.util.List;

public class CarvedMelonsFeature extends OptionalFeature {
	public static Block carvedMelon;

	public CarvedMelonsFeature() {
		super("carved_melons", "Adds carved melons and Mel o'Lanterns");
	}

	@Override
	protected void applyEnabled() {
        ContentBuilder contentBuilder = Neutronia.getContentBuilder();
		List<Block> carvedMelons = new ArrayList<>(CarvedFaceType.values().length + 1);
		carvedMelon = contentBuilder.newBlock("carved_melon", new CarvedMelonBlock(false), "melons/carved_melon");
		carvedMelons.add(carvedMelon);
		List<Block> melOLanterns = new ArrayList<>(CarvedFaceType.values().length + 1);
		carvedMelons.add(contentBuilder.newBlock("mel_o_lantern", new CarvedMelonBlock(false), "mel_o_lanterns/mel_o_lantern"));
		for(CarvedFaceType carvedFaceType : CarvedFaceType.values()) {
			final String name = carvedFaceType.asString();
			Block melon = contentBuilder.newBlock("carved_melon_" + name, new CarvedMelonBlock(false), "melons/carved_melon_" + name);
			Block lantern = contentBuilder.newBlock("mel_o_lantern_" + name, new CarvedMelonBlock(true), "mel_o_lanterns/mel_o_lantern_" + name);
            carvedMelons.add(melon);
            melOLanterns.add(lantern);
		}

		contentBuilder.runGameTask(() -> {
			BlockChiseler.create(new Identifier(Neutronia.MOD_ID, "carved_melons"), NeutroniaTags.SHEARS, carvedMelons);
			BlockChiseler.create(new Identifier(Neutronia.MOD_ID, "mel_o_lanterns"), NeutroniaTags.SHEARS, melOLanterns);

			UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
				ItemStack itemStack_1 = player.getStackInHand(hand);
				if (itemStack_1.getItem() == Items.SHEARS && world.getBlockState(hitResult.getBlockPos()).getBlock() == Blocks.MELON) {
					if (!world.isClient) {
						Direction hitDirection = hitResult.getSide();
						Direction resultDirection = hitDirection.getAxis() == Direction.Axis.Y ? player.getHorizontalFacing().getOpposite() : hitDirection;
						world.playSound(null, hitResult.getBlockPos(), SoundEvents.BLOCK_PUMPKIN_CARVE, SoundCategory.BLOCKS, 1.0F, 1.0F);
						world.setBlockState(hitResult.getBlockPos(), carvedMelon.getDefaultState().with(HorizontalFacingBlock.FACING, resultDirection), 11);
						ItemEntity itemEntity = new ItemEntity(world, (double) hitResult.getBlockPos().getX() + 0.5D + (double) resultDirection.getOffsetX() * 0.65D, (double) hitResult.getBlockPos().getY() + 0.1D, (double) hitResult.getBlockPos().getZ() + 0.5D + (double) resultDirection.getOffsetZ() * 0.65D, new ItemStack(Items.PUMPKIN_SEEDS, 4));
						itemEntity.setVelocity(0.05D * (double) resultDirection.getOffsetX() + world.random.nextDouble() * 0.02D, 0.05D, 0.05D * (double) resultDirection.getOffsetZ() + world.random.nextDouble() * 0.02D);
						world.spawnEntity(itemEntity);
						itemStack_1.applyDamage(1, player, playerEntity -> playerEntity.sendToolBreakStatus(hand));
					}
					return ActionResult.SUCCESS;
				}
				return ActionResult.PASS;
			});
		});
	}
}
