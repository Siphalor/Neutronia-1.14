package team.hollow.neutronia.event_system.loot;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.loot.LootSupplier;
import net.minecraft.world.loot.context.LootContext;

import java.util.ArrayList;
import java.util.List;

public class LootLookup
{
	
	private static int containsItem(ItemStack a, List<ItemStack> l)
	{
		
		for(int i =0; i < l.size(); i++)
		{
			ItemStack e = l.get(i);
			if(e.getItem().equals(a.getItem()))
			{
				return i;
			}
		}
		return -1;
		
	}
	
	public static List<ItemStack> getLootFor(PlayerEntity killer, Entity entity, World world, int multiplier)
	{
		if(killer == null || entity == null || world == null)return new ArrayList<ItemStack>();
		ServerWorldRedirectManager inst = ServerWorldRedirectManager.getInstance();
		inst.reWorld.that = world;
		LootContext ctx = inst.killContext(killer, entity);
		LootSupplier supplier = inst.reServer.getLootManager().getSupplier(entity.getType().getLootTableId());
		
		List<ItemStack> r = new ArrayList<ItemStack>();
		for(int c = 0; c < multiplier; c++)
		{
			List<ItemStack> drops = supplier.getDrops(ctx);
			for(int d = 0; d < drops.size(); d++)
			{
				ItemStack d_i = drops.get(d);
				int cont = containsItem(d_i, r);
				if(cont == -1)r.add(d_i);
				else
				{
					ItemStack n = r.get(cont);
					n.addAmount(d_i.getAmount());
					//r.set(cont, n);
				}
			}
		}
        return r;
	}
	public static List<ItemStack> getLootFor(EntityType<?> type, World world, int multiplier)
	{
		int raw_id = Registry.ENTITY_TYPE.getRawId(type);
		Entity entity = EntityType.createInstanceFromId(raw_id,  MinecraftClient.getInstance().world);
		PlayerEntity player = world.getPlayers().get(0);
		return getLootFor(player, entity, world, multiplier);
	}
	public static List<ItemStack> getLootFor(EntityType<?> type, World world)
	{
		return getLootFor( type,world, 1);
	}
	public static List<ItemStack> getLootFor(BlockPos position, World world, int multiplier)
	{
		ServerWorldRedirectManager inst = ServerWorldRedirectManager.getInstance();
		inst.reWorld.that = world;
		
		
		BlockState iblockstate = world.getBlockState(position);
		Block block = iblockstate.getBlock();
		BlockEntity blockentity = world.getBlockEntity(position);
		return Block.getDroppedStacks(iblockstate, inst.reWorld, position, blockentity);
	}
	public static List<ItemStack> getLootFor(BlockPos position, World world)
	{
		return getLootFor(position,world, 1);
	}
}
