package team.hollow.neutronia.event_system;

import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil.Type;
import net.minecraft.util.Identifier;

import java.util.HashMap;

public class KeyBindAbstracto
{
	/*class AbstractKeyBind
	{
		
	}*/
	//class KeyBind
	
	
	
	//is this some kind of boilerplate fetish in action? ABSTRACTIFIOSA
	public static KeyBinding makeKeyboardBinding(String id, int code, String cat)
	{
		FabricKeyBinding binding = FabricKeyBinding.Builder.create(
			new Identifier(id), Type.KEY_KEYBOARD, code, cat).build();
		return binding;
	}
	
	public static void addKeyBindCategory(String category)
	{
		KeyBindingRegistry.INSTANCE.addCategory(category);
		
	}
	
	public static KeyBinding addKeyBind(KeyBinding kb)
	{
		addKeyBindCategory(kb.getCategory());
		KeyBindingRegistry.INSTANCE.register((FabricKeyBinding) kb);
		lastPress.put(kb,false);
		return kb;
	}
	
	static HashMap<KeyBinding, Boolean> lastPress = new HashMap<KeyBinding, Boolean>();
	
	public static boolean keyPressedOnce(KeyBinding key)
	{
		if(key.isPressed() && !lastPress.get(key))
		{
			lastPress.put(key, key.isPressed());
			return true;
		}
		lastPress.put(key, key.isPressed());
		return false;
		
	}
}
