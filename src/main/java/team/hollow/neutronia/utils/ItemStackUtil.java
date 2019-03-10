package team.hollow.neutronia.utils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemStackUtil {

	public static ItemStack loadStackFromString(String res) {
		String nbt = "";
		int nbtStart = res.indexOf("{");
		if(nbtStart > 0) {
			nbt = res.substring(nbtStart).replaceAll("([^\\\\])'", "$1\"").replaceAll("\\\\'", "'");
			res = res.substring(0, nbtStart);
		}
		
		int meta = 0;
		
		String[] upper = res.split("#");
		String count = "1";
		if(upper.length > 1) {
			res = upper[0];
			count = upper[1];
		}
		
		String[] tokens = res.split(":");
		if(tokens.length < 2)
			return ItemStack.EMPTY;
		
		int countn = Integer.parseInt(count);
		Item item = Registry.ITEM.get(new Identifier(tokens[0], tokens[1]));
		ItemStack stack = new ItemStack(item, countn);
		/*if(!nbt.isEmpty())
			try {
				stack.setTag(NbtIo..getTagFromJson(nbt));
			} catch (NBTException e) {
				throw new RuntimeException("Failed to parse ItemStack JSON" , e);
			}*/
		
		return stack;
	}

}