/*
package team.hollow.neutronia.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.entity.SocialVillager;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class SocialButton extends ButtonWidget {

    public Identifier texture = new Identifier(Neutronia.MOD_ID, "textures/gui/speech_buttons.png");

    SocialButton(int int_2, int int_3, int int_4, int int_5, String string_1) {
        super(int_2, int_3, int_4, int_5, string_1, var1 -> {
            SocialScreen screen = (SocialScreen) MinecraftClient.getInstance().currentScreen;
            switch (var1.getMessage()) {
                case "Socialize" -> {
                    List<SocialButton> buttons = Objects.requireNonNull(screen).positiveButtons;
                    List<SocialButton> otherButtons = screen.neutralButtons;
                    if (buttons.get(0).visible) {
                        for (SocialButton button : buttons) {
                            button.visible = false;
                        }
                    } else {
                        for (SocialButton button : buttons) {
                            button.visible = true;
                        }
                    }
                    for (SocialButton button : otherButtons) {
                        button.visible = false;
                    }
                }
                case "Influence" -> {
                    List<SocialButton> buttons = Objects.requireNonNull(screen).neutralButtons;
                    List<SocialButton> otherButtons = screen.positiveButtons;
                    if (buttons.get(0).visible) {
                        for (SocialButton button : buttons) {
                            button.visible = false;
                        }
                    } else {
                        for (SocialButton button : buttons) {
                            button.visible = true;
                        }
                    }
                    for (SocialButton button : otherButtons) {
                        button.visible = false;
                    }
                }
                case "Charm" -> {
                    SocialVillager target = Objects.requireNonNull(screen).getTarget();
                    PlayerEntity talker = screen.getTalker();
                    if (target.getCharmed()) {
                        talker.addChatMessage(new StringTextComponent("<" + target.firstName + " " + target.lastName + ">: ").append(new TranslatableTextComponent("text.social.charmrepeat")), false);
                        return;
                    }

                    int friendliness = target.getFriendliness();
                    float threshold = 0.66F + (float) (friendliness / 400);
                    Random rand = new Random();
                    if (rand.nextFloat() < threshold) {
                        talker.addChatMessage(new StringTextComponent("<" + target.firstName + " " + target.lastName + ">: ").append(new TranslatableTextComponent("text.social.charmsuccess")), false);
                    } else {
                        talker.addChatMessage(new StringTextComponent("<" + target.firstName + " " + target.lastName + ">: ").append(new TranslatableTextComponent("text.social.charmfailure")), false);
                    }
                    target.setCharmed();
                    int opinion = target.getOpinion(talker.getUuid());
                    target.setOpinion(talker.getUuid(), opinion + 5);
                }
                case "Apologize" -> {
                    SocialVillager target = Objects.requireNonNull(screen).getTarget();
                    PlayerEntity talker = screen.getTalker();
                    int opinion = target.getOpinion(talker.getUuid());
                    if (opinion >= 0) {
                        talker.addChatMessage(new StringTextComponent("<" + target.firstName + " " + target.lastName + ">: ").append(new TranslatableTextComponent("text.social.apologyunnecessary")), false);
                        return;
                    }
                    if (target.getApologized()) {

                        if (opinion > -25) {
                            talker.addChatMessage(new StringTextComponent("<" + target.firstName + " " + target.lastName + ">: ").append(new TranslatableTextComponent("text.social.apologyrepeat1")), false);
                        } else if (opinion > -50) {
                            talker.addChatMessage(new StringTextComponent("<" + target.firstName + " " + target.lastName + ">: ").append(new TranslatableTextComponent("text.social.apologyrepeat2")), false);
                        } else if (opinion > -75) {
                            talker.addChatMessage(new StringTextComponent("<" + target.firstName + " " + target.lastName + ">: ").append(new TranslatableTextComponent("text.social.apologyrepeat3")), false);
                        } else if (opinion > -100) {
                            talker.addChatMessage(new StringTextComponent("<" + target.firstName + " " + target.lastName + ">: ").append(new TranslatableTextComponent("text.social.apologyrepeat4")), false);
                        } else if (opinion == -100) {
                            talker.addChatMessage(new StringTextComponent("<" + target.firstName + " " + target.lastName + ">: ").append(new TranslatableTextComponent("text.social.apologyrepeat5")), false);
                        }
                    } else {
                        Random rand = new Random();
                        float threshold = 0.66F + (float) opinion / 400;
                        if (rand.nextFloat() <= threshold) {
                            if (opinion > -25) {
                                talker.addChatMessage(new StringTextComponent("<" + target.firstName + " " + target.lastName + ">: ").append(new TranslatableTextComponent("text.social.apologyaccepted1")), false);
                            } else if (opinion > -50) {
                                talker.addChatMessage(new StringTextComponent("<" + target.firstName + " " + target.lastName + ">: ").append(new TranslatableTextComponent("text.social.apologyaccepted2")), false);
                            } else if (opinion > -75) {
                                talker.addChatMessage(new StringTextComponent("<" + target.firstName + " " + target.lastName + ">: ").append(new TranslatableTextComponent("text.social.apologyaccepted3")), false);
                            } else if (opinion > -100) {
                                talker.addChatMessage(new StringTextComponent("<" + target.firstName + " " + target.lastName + ">: ").append(new TranslatableTextComponent("text.social.apologyaccepted4")), false);
                            } else if (opinion == -100) {
                                talker.addChatMessage(new StringTextComponent("<" + target.firstName + " " + target.lastName + ">: ").append(new TranslatableTextComponent("text.social.apologyaccepted5")), false);
                            }
                            target.setOpinion(talker.getUuid(), opinion + 10);
                            target.setApologized();
                        }
                    }
                }
                case "Examine" -> {
                    SocialVillager target = Objects.requireNonNull(screen).getTarget();
                    PlayerEntity talker = screen.getTalker();
                    int opinion = target.getOpinion(talker.getUuid());
                    if (opinion >= 0 && opinion < 25) {
                        talker.addChatMessage(new StringTextComponent(target.firstName + " " + target.lastName).append(new TranslatableTextComponent("text.social.examine1")), false);
                    } else if (opinion >= 25 && opinion < 50) {
                        talker.addChatMessage(new StringTextComponent(target.firstName + " " + target.lastName).append(new TranslatableTextComponent("text.social.examine2")), false);
                    } else if (opinion >= 50 && opinion < 75) {
                        talker.addChatMessage(new StringTextComponent(target.firstName + " " + target.lastName).append(new TranslatableTextComponent("text.social.examine3")), false);
                    } else if (opinion >= 75 && opinion <= 100) {
                        talker.addChatMessage(new StringTextComponent(target.firstName + " " + target.lastName).append(new TranslatableTextComponent("text.social.examine4")), false);
                    } else if (opinion < 0 && opinion > -25) {
                        talker.addChatMessage(new StringTextComponent(target.firstName + " " + target.lastName).append(new TranslatableTextComponent("text.social.examine5")), false);
                    } else if (opinion <= -25 && opinion > -50) {
                        talker.addChatMessage(new StringTextComponent(target.firstName + " " + target.lastName).append(new TranslatableTextComponent("text.social.examine6")), false);
                    } else if (opinion <= -50 && opinion > -75) {
                        talker.addChatMessage(new StringTextComponent(target.firstName + " " + target.lastName).append(new TranslatableTextComponent("text.social.examine7")), false);
                    } else if (opinion <= -75 && opinion >= -100) {
                        talker.addChatMessage(new StringTextComponent(target.firstName + " " + target.lastName).append(new TranslatableTextComponent("text.social.examine8")), false);
                    }
                }
            }
        });
    }

    @Override
    public void render(int mouseX, int mouseY, float float_1) {
        if (this.visible) {
            SocialScreen screen = (SocialScreen) MinecraftClient.getInstance().currentScreen;
            MinecraftClient minecraftClient_1 = MinecraftClient.getInstance();
            minecraftClient_1.getTextureManager().bindTexture(texture);
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            if (this.isHovered()) {
                if (!this.getMessage().equals("Socialize") && !this.getMessage().equals("Influence")) {
                    if (!this.getMessage().equals("Examine") && !this.getMessage().equals("Barter")) {
                        blit(x, y, 122, 31, width, height);
                    } else {
                        blit(x, y, 59, 31, width, height);
                    }

                } else if (this.getMessage().equals("Socialize")) {
                    if (!Objects.requireNonNull(screen).positiveButtons.get(0).visible) {
                        blit(x, y, 0, 31, width, height);

                    } else {
                        blit(x, y, 0, 62, width, height);
                    }
                } else if (this.getMessage().equals("Influence")) {
                    if (!Objects.requireNonNull(screen).neutralButtons.get(0).visible) {
                        blit(x, y, 0, 31, width, height);
                    } else {
                        blit(x, y, 0, 62, width, height);
                    }

                }

            } else if (this.getMessage().equals("Socialize")) {
                if (Objects.requireNonNull(screen).positiveButtons.get(0).visible) {
                    blit(x, y, 0, 62, width, height);
                } else {
                    blit(x, y, 0, 0, width, height);
                }

            } else if (this.getMessage().equals("Influence")) {
                if (Objects.requireNonNull(screen).neutralButtons.get(0).visible) {
                    blit(x, y, 0, 62, width, height);
                } else {
                    blit(x, y, 0, 0, width, height);
                }
            } else if (!this.getMessage().equals("Examine") && !this.getMessage().equals("Barter")) {
                blit(x, y, 122, 0, width, height);
            } else {
                blit(x, y, 59, 0, width, height);
            }
            MinecraftClient client = MinecraftClient.getInstance();
            TextRenderer fr = client.textRenderer;
            drawCenteredString(fr, "§0" + this.getMessage(), x + width / 2, y + (this.height - 16) / 2, 0);
        }
    }

    @Override
    public void drawCenteredString(TextRenderer textRenderer_1, String string_1, int int_1, int int_2, int int_3) {
        textRenderer_1.draw(string_1, (float) (int_1 - textRenderer_1.getStringWidth(string_1) / 2), (float) int_2, int_3);
    }

}*/
