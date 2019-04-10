package team.hollow.neutronia.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.TranslatableTextComponent;
import team.hollow.neutronia.entity.SocialVillager;

import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class SocialScreen extends Screen {

    List<SocialButton> positiveButtons = new ArrayList<>();
    List<SocialButton> neutralButtons = new ArrayList<>();
    private SocialButton charmButton;
    private SocialButton apologyButton;
    private SocialButton examineButton;
    private SocialButton recruitButton;
    private SocialButton favorButton;
    private SocialButton tradeButton;
    private SocialVillager target;
    private PlayerEntity talker;

    public SocialScreen(SocialVillager entity, PlayerEntity player) {
        super(new TranslatableTextComponent("narrator.screen.title"));
        this.target = entity;
        this.talker = player;
    }

    @Override
    public void init(MinecraftClient client, int int1, int int2) {
        super.init(client, int1, int2);
        this.addButton(new SocialButton((width - 60), (height - 90), 60, 30, "Socialize"));
        this.addButton(new SocialButton((width - 60), (height - 60), 60, 30, "Influence"));
        this.addButton(charmButton = new SocialButton((width - 120), (height - 90), 60, 30, "Charm"));
        charmButton.visible = false;
        positiveButtons.add(charmButton);
        this.addButton(apologyButton = new SocialButton((width - 180), (height - 90), 60, 30, "Apologize"));
        apologyButton.visible = false;
        positiveButtons.add(apologyButton);
        this.addButton(examineButton = new SocialButton((width - 240), (height - 90), 60, 30, "Examine"));
        examineButton.visible = false;
        positiveButtons.add(examineButton);
        this.addButton(recruitButton = new SocialButton((width - 120), (height - 60), 60, 30, "Recruit"));
        recruitButton.visible = false;
        neutralButtons.add(recruitButton);
        this.addButton(favorButton = new SocialButton((width - 180), (height - 60), 60, 30, "Favor"));
        favorButton.visible = false;
        neutralButtons.add(favorButton);
        this.addButton(tradeButton = new SocialButton((width - 240), (height - 60), 60, 30, "Barter"));
        tradeButton.visible = false;
        neutralButtons.add(tradeButton);
    }

    public SocialVillager getTarget() {
        return this.target;
    }

    PlayerEntity getTalker() {
        return this.talker;
    }

}