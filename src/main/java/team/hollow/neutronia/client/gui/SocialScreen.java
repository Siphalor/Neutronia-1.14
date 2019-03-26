package team.hollow.neutronia.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.InputListener;
import net.minecraft.client.gui.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.TranslatableTextComponent;
import team.hollow.neutronia.entity.SocialVillager;

import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class SocialScreen extends Screen implements InputListener {

    public List<SocialButton> positiveButtons = new ArrayList<>();
    public List<SocialButton> neutralButtons = new ArrayList<>();
    private SocialVillager target;
    private PlayerEntity talker;
    private SocialButton charmButton;
    private SocialButton apologyButton;
    private SocialButton examineButton;
    private SocialButton recruitButton;
    private SocialButton favorButton;
    private SocialButton tradeButton;

    public SocialScreen(SocialVillager entity, PlayerEntity player) {
        super(new TranslatableTextComponent("narrator.screen.title"));
        this.target = entity;
        this.talker = player;
    }

    @Override
    public void init(MinecraftClient client, int int1, int int2) {
        super.init(client, int1, int2);
        this.addButton(new SocialButton((width - 50), (height - 60), 50, 20, "Socialize"));
        this.addButton(new SocialButton((width - 50), (height - 40), 50, 20, "Influence"));
        this.addButton(charmButton = new SocialButton((width - 100), (height - 60), 50, 20, "Charm"));
        charmButton.visible = false;
        positiveButtons.add(charmButton);
        this.addButton(apologyButton = new SocialButton((width - 150), (height - 60), 50, 20, "Apologize"));
        apologyButton.visible = false;
        positiveButtons.add(apologyButton);
        this.addButton(examineButton = new SocialButton((width - 200), (height - 60), 50, 20, "Examine"));
        examineButton.visible = false;
        positiveButtons.add(examineButton);
        this.addButton(recruitButton = new SocialButton((width - 100), (height - 40), 50, 20, "Recruit"));
        recruitButton.visible = false;
        neutralButtons.add(recruitButton);
        this.addButton(favorButton = new SocialButton((width - 150), (height - 40), 50, 20, "Favor"));
        favorButton.visible = false;
        neutralButtons.add(favorButton);
        this.addButton(tradeButton = new SocialButton((width - 200), (height - 40), 50, 20, "Barter"));
        tradeButton.visible = false;
        neutralButtons.add(tradeButton);

    }

    public SocialVillager getTarget() {
        return this.target;
    }

    public PlayerEntity getTalker() {
        return this.talker;
    }
}