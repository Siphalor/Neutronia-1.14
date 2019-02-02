package team.abnormals.neutronia.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.abnormals.neutronia.blocks.entity.SignBlockEntity;
import team.abnormals.neutronia.client.gui.ingame.EditSignGui;

public class SignItem extends WallStandingBlockItem {

   public SignItem(Item.Settings item$Settings_1, Block block_1, Block block_2) {
      super(block_1, block_2, item$Settings_1);
   }

   protected boolean afterBlockPlaced(BlockPos blockPos_1, World world_1, PlayerEntity playerEntity_1, ItemStack itemStack_1, BlockState blockState_1) {
      boolean boolean_1 = super.afterBlockPlaced(blockPos_1, world_1, playerEntity_1, itemStack_1, blockState_1);
      if (!world_1.isClient && !boolean_1 && playerEntity_1 != null) {
         MinecraftClient.getInstance().openScreen(new EditSignGui((SignBlockEntity) world_1.getBlockEntity(blockPos_1)));
      }

      return boolean_1;
   }

}