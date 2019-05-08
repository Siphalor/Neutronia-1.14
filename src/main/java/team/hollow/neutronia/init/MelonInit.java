package team.hollow.neutronia.init;

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
import team.hollow.neutronia.BlockChiseler;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.blocks.NeutroniaCarvedMelonBlock;
import team.hollow.neutronia.enums.CarvedFaceTypes;
import team.hollow.neutronia.utils.NeutroniaTags;
import team.hollow.neutronia.utils.registry.RegistryUtils;

import java.util.ArrayList;
import java.util.List;

class MelonInit {
    public static Block carvedMelon;

    static void init() {
        List<Block> carvedMelons = new ArrayList<>(CarvedFaceTypes.values().length + 1);
        carvedMelon = RegistryUtils.register(new NeutroniaCarvedMelonBlock(false), new Identifier(Neutronia.MOD_ID, "carved_melon"));
        carvedMelons.add(carvedMelon);
        List<Block> melOLanterns = new ArrayList<>(CarvedFaceTypes.values().length + 1);
        melOLanterns.add(RegistryUtils.register(new NeutroniaCarvedMelonBlock(false), new Identifier(Neutronia.MOD_ID, "mel_o_lantern")));
        for(CarvedFaceTypes carvedFaceType : CarvedFaceTypes.values()) {
            final String name = carvedFaceType.asString();
            Block melon = RegistryUtils.register(new NeutroniaCarvedMelonBlock(false), new Identifier(Neutronia.MOD_ID, "carved_" + name + "_melon"));
            Block lantern = RegistryUtils.register(new NeutroniaCarvedMelonBlock(true), new Identifier(Neutronia.MOD_ID, name + "_mel_o_lantern"));
            carvedMelons.add(melon);
            melOLanterns.add(lantern);
        }
        BlockChiseler.create(new Identifier(Neutronia.MOD_ID, "carved_melons"), NeutroniaTags.SHEARS, carvedMelons);
        BlockChiseler.create(new Identifier(Neutronia.MOD_ID, "mel_o_lanterns"), NeutroniaTags.SHEARS, melOLanterns);

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            ItemStack itemStack_1 = player.getStackInHand(hand);
            if(itemStack_1.getItem() == Items.SHEARS && world.getBlockState(hitResult.getBlockPos()).getBlock() == Blocks.MELON) {
                if(!world.isClient) {
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
    }

}
