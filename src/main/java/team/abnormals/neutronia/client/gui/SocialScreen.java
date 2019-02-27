package team.abnormals.neutronia.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.InputListener;
import net.minecraft.client.gui.Screen;
import net.minecraft.entity.player.PlayerEntity;
import team.abnormals.neutronia.entity.SocialVillager;

import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class SocialScreen extends Screen implements InputListener {

	private SocialVillager target;
	private PlayerEntity talker;
	private SocialButton charmButton;
	private SocialButton apologyButton;
	private SocialButton examineButton;
	private SocialButton recruitButton;
	private SocialButton favorButton;
	private SocialButton tradeButton;
	public List<SocialButton> positiveButtons = new ArrayList<>();
	public List<SocialButton> neutralButtons = new ArrayList<>();
	public SocialScreen(SocialVillager entity, PlayerEntity player)
	{
		super();
		this.target = entity;
		this.talker = player;
		
	}
	public void initialize(MinecraftClient client, int int1, int int2)
	{
		super.initialize(client, int1, int2);
		this.addButton(new SocialButton((screenWidth - 50), (screenHeight - 60), 50, 20, "Socialize"));
		this.addButton(new SocialButton((screenWidth - 50), (screenHeight - 40), 50, 20, "Influence"));
		this.addButton(charmButton = new SocialButton((screenWidth - 100), (screenHeight - 60), 50, 20, "Charm"));
		charmButton.visible = false;
		positiveButtons.add(charmButton);
		this.addButton(apologyButton = new SocialButton((screenWidth - 150), (screenHeight - 60), 50, 20, "Apologize"));
		apologyButton.visible = false;
		positiveButtons.add(apologyButton);
		this.addButton(examineButton = new SocialButton((screenWidth - 200), (screenHeight - 60), 50, 20, "Examine"));
		examineButton.visible = false;
		positiveButtons.add(examineButton);
		this.addButton(recruitButton = new SocialButton((screenWidth - 100), (screenHeight - 40), 50, 20, "Recruit"));
		recruitButton.visible = false;
		neutralButtons.add(recruitButton);
		this.addButton(favorButton = new SocialButton((screenWidth - 150), (screenHeight - 40), 50, 20, "Favor"));
		favorButton.visible = false;
		neutralButtons.add(favorButton);
		this.addButton(tradeButton = new SocialButton((screenWidth - 200), (screenHeight - 40), 50, 20, "Barter"));
		tradeButton.visible = false;
		neutralButtons.add(tradeButton);
		
	}
	public SocialVillager getTarget()
	{
		return this.target;
	}
	public PlayerEntity getTalker()
	{
		return this.talker;
	}
}