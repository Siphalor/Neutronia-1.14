package team.abnormals.neutronia;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.world.gen.feature.Feature;
import team.abnormals.neutronia.init.*;

import java.util.Locale;

import static net.fabricmc.fabric.events.PlayerInteractionEvent.INTERACT_BLOCK;

public class Neutronia implements ModInitializer {

	@Override
	public void onInitialize() {
		CarvedPlanks.init();
		FrostedGlass.init();
        StorageBlocks.init();
        NBlocks.init();
        NItems.init();
        NBlockEntities.init();
//		CommandRegistry.INSTANCE.register(false, (Locate2Command::register));
		ModVillagers.init();
		NEntityTypes.init();
		NRecipeType.init();
		NRecipeSerializers.init();

		events();

        Feature.STRUCTURES.put("Pillager_Outpost_Test".toLowerCase(Locale.ROOT), NFeatures.PILLAGER_OUTPOST);
	}

	public void events() {
        INTERACT_BLOCK.register((playerEntity, world, hand, blockPos, direction, v, v1, v2) -> {
            ItemStack stack = playerEntity.getStackInHand(hand);
            if(stack.getItem() == Items.SHEARS) {
                if(world.getBlockState(blockPos).getBlock() == Blocks.PUMPKIN) {
                    world.setBlockState(blockPos, NBlocks.PUMPKIN.getDefaultState());
                    return ActionResult.SUCCESS;
                }
                if(world.getBlockState(blockPos).getBlock() == Blocks.JACK_O_LANTERN) {
                    world.setBlockState(blockPos, NBlocks.JACK_O_LANTERN.getDefaultState());
                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.PASS;
        });
    }

}