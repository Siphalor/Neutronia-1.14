package team.abnormals.neutronia;

import net.fabricmc.api.ModInitializer;
import net.minecraft.world.gen.feature.Feature;
import team.abnormals.neutronia.init.*;

import java.util.Locale;

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

        /*INTERACT_BLOCK.register((playerEntity, world, hand, blockPos, direction, v, v1, v2) -> {
            ItemStack stack = playerEntity.getStackInHand(hand);
            if (world.getBlockState(blockPos).getBlock() == Blocks.PUMPKIN) {
                if (stack.getItem() == Items.SHEARS) {
                    System.out.println("This is a test");
                    world.setBlockState(blockPos, PumpkinHelper.getNext(Registry.BLOCK.getId(NBlocks.PUMPKIN)));
                    return ActionResult.SUCCESS;
                }
            }
            if (world.getBlockState(blockPos).getBlock() == Blocks.JACK_O_LANTERN) {
                if (stack.getItem() == Items.SHEARS) {
                    System.out.println("This is a test");
                    world.setBlockState(blockPos, JackOLanternHelper.getNext(Registry.BLOCK.getId(NBlocks.JACK_O_LANTERN)));
                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.PASS;
        });*/

        Feature.STRUCTURES.put("Pillager_Outpost_Test".toLowerCase(Locale.ROOT), NFeatures.PILLAGER_OUTPOST);
	}

}