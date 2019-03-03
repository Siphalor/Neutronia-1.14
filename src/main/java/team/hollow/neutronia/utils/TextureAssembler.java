package team.hollow.neutronia.utils;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

@Environment(value = EnvType.CLIENT)
public class TextureAssembler {

    private String eyeColor;
    private String hairColor;
    private String skinColor;
    private Integer hairstyle;
    private String gender;

    //Outfits
    private Identifier outfit1Female = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/female/clothes/outfit1.png");
    private Identifier outfit1Male = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/male/clothes/outfit1.png");
    private Identifier outfit1Genderless = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/genderless/clothes/outfit1.png");

    //Skin
    private Identifier lightSkinMale = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/male/skin/light.png");
    private Identifier medSkinMale = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/male/skin/medium.png");
    private Identifier darkSkinMale = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/male/skin/dark.png");
    private Identifier lightSkinFemale = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/female/skin/light.png");
    private Identifier mediumSkinFemale = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/female/skin/medium.png");
    private Identifier darkSkinFemale = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/female/skin/dark.png");

    //Eyes
    private Identifier blackEyes = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/eyes/black.png");
    private Identifier blueEyes = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/eyes/blue.png");
    private Identifier brownEyes = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/eyes/brown.png");
    private Identifier greenEyes = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/eyes/green.png");
    private Identifier limeEyes = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/eyes/lime.png");
    private Identifier pinkEyes = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/eyes/pink.png");
    private Identifier yellowEyes = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/eyes/yellow.png");

    //Hair male
    private Identifier blackHairMale = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/male/hair/black1.png");
    private Identifier blackHairMale2 = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/male/hair/black2.png");
    private Identifier blackHairMale3 = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/male/hair/black3.png");
    private Identifier blackHairMale4 = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/male/hair/black4.png");
    private Identifier brownHairMale = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/male/hair/brown1.png");
    private Identifier brownHairMale2 = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/male/hair/brown2.png");
    private Identifier brownHairMale3 = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/male/hair/brown3.png");
    private Identifier brownHairMale4 = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/male/hair/brown4.png");
    private Identifier blondeHairMale = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/male/hair/blonde1.png");
    private Identifier blondeHairMale2 = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/male/hair/blonde2.png");
    private Identifier blondeHairMale3 = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/male/hair/blonde3.png");
    private Identifier blondeHairMale4 = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/male/hair/blonde4.png");
    private Identifier redHairMale = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/male/hair/red1.png");
    private Identifier redHairMale2 = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/male/hair/red2.png");
    private Identifier redHairMale3 = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/male/hair/red3.png");
    private Identifier redHairMale4 = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/male/hair/red4.png");

    //Hair female
    private Identifier blondeHairFemale = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/female/hair/blonde1.png");
    private Identifier blondeHairFemale2 = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/female/hair/blonde2.png");
    private Identifier blondeHairFemale3 = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/female/hair/blonde3.png");
    private Identifier blondeHairFemale4 = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/female/hair/blonde4.png");
    private Identifier brownHairFemale = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/female/hair/brown1.png");
    private Identifier brownHairFemale2 = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/female/hair/brown2.png");
    private Identifier brownHairFemale3 = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/female/hair/brown3.png");
    private Identifier brownHairFemale4 = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/female/hair/brown4.png");
    private Identifier blackHairFemale = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/female/hair/black1.png");
    private Identifier blackHairFemale2 = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/female/hair/black2.png");
    private Identifier blackHairFemale3 = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/female/hair/black3.png");
    private Identifier blackHairFemale4 = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/female/hair/black4.png");
    private Identifier redHairFemale1 = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/female/hair/red1.png");
    private Identifier redHairFemale2 = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/female/hair/red2.png");
    private Identifier redHairFemale3 = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/female/hair/red3.png");
    private Identifier redHairFemale4 = new Identifier(Neutronia.MOD_ID, "textures/entity/npcs/female/hair/red4.png");

    public TextureAssembler(String eyeColor, String hairColor, String skinColor, Integer hairstyle, String gender) {
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.skinColor = skinColor;
        this.hairstyle = hairstyle;
        this.gender = gender;
    }

    public BufferedImage createTexture() {
        BufferedImage totalImage;
        BufferedImage hairImage;
        BufferedImage eyeImage;
        BufferedImage skinImage;
        BufferedImage outfitImage;

        try {
            InputStream inputstream;
            if (gender.equals("Male")) {
                inputstream = MinecraftClient.getInstance().getResourceManager().getResource(outfit1Male).getInputStream();
                outfitImage = ImageIO.read(inputstream);
                inputstream.close();
                switch (this.skinColor) {
                    case "Light":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(lightSkinMale).getInputStream();
                        break;
                    case "Medium":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(medSkinMale).getInputStream();
                        break;
                    case "Dark":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(darkSkinMale).getInputStream();
                        break;
                }
                skinImage = ImageIO.read(inputstream);
                inputstream.close();
                switch (hairColor) {
                    case "Black":
                        if (hairstyle.equals(1)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blackHairMale).getInputStream();
                        } else if (hairstyle.equals(2)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blackHairMale2).getInputStream();
                        } else if (hairstyle.equals(3)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blackHairMale3).getInputStream();
                        } else if (hairstyle.equals(4)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blackHairMale4).getInputStream();
                        }
                        break;
                    case "Blonde":
                        if (hairstyle.equals(1)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blondeHairMale).getInputStream();
                        } else if (hairstyle.equals(2)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blondeHairMale2).getInputStream();
                        } else if (hairstyle.equals(3)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blondeHairMale3).getInputStream();
                        } else if (hairstyle.equals(4)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blondeHairMale4).getInputStream();
                        }
                        break;
                    case "Brown":
                        if (hairstyle.equals(1)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(brownHairMale).getInputStream();
                        } else if (hairstyle.equals(2)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(brownHairMale2).getInputStream();
                        } else if (hairstyle.equals(3)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(brownHairMale3).getInputStream();
                        } else if (hairstyle.equals(4)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(brownHairMale4).getInputStream();
                        }
                        break;
                    case "Red":
                        if (hairstyle.equals(1)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(redHairMale).getInputStream();
                        } else if (hairstyle.equals(2)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(redHairMale2).getInputStream();
                        } else if (hairstyle.equals(3)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(redHairMale3).getInputStream();
                        } else if (hairstyle.equals(4)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(redHairMale4).getInputStream();
                        }
                        break;
                }
                hairImage = ImageIO.read(inputstream);
                inputstream.close();
                switch (eyeColor) {
                    case "Black":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blackEyes).getInputStream();
                        break;
                    case "Blue":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blueEyes).getInputStream();
                        break;
                    case "Brown":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(brownEyes).getInputStream();
                        break;
                    case "Green":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(greenEyes).getInputStream();
                        break;
                    case "Lime":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(limeEyes).getInputStream();
                        break;
                    case "Pink":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(pinkEyes).getInputStream();
                        break;
                    case "Yellow":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(yellowEyes).getInputStream();
                        break;
                }
                eyeImage = ImageIO.read(inputstream);
                inputstream.close();
            } else if (gender.equals("Female")) {
                inputstream = MinecraftClient.getInstance().getResourceManager().getResource(outfit1Female).getInputStream();
                outfitImage = ImageIO.read(inputstream);
                switch (this.skinColor) {
                    case "Light":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(lightSkinFemale).getInputStream();
                        break;
                    case "Medium":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(mediumSkinFemale).getInputStream();
                        break;
                    case "Dark":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(darkSkinFemale).getInputStream();
                        break;
                }
                skinImage = ImageIO.read(inputstream);
                inputstream.close();
                switch (this.hairColor) {
                    case "Black":
                        if (hairstyle.equals(1)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blackHairFemale).getInputStream();
                        } else if (hairstyle.equals(2)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blackHairFemale2).getInputStream();
                        } else if (hairstyle.equals(3)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blackHairFemale3).getInputStream();
                        } else if (hairstyle.equals(4)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blackHairFemale4).getInputStream();
                        }

                        break;
                    case "Blonde":
                        if (hairstyle.equals(1)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blondeHairFemale).getInputStream();
                        } else if (hairstyle.equals(2)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blondeHairFemale2).getInputStream();
                        } else if (hairstyle.equals(3)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blondeHairFemale3).getInputStream();
                        } else if (hairstyle.equals(4)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blondeHairFemale4).getInputStream();
                        }

                        break;
                    case "Brown":
                        if (hairstyle.equals(1)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(brownHairFemale).getInputStream();
                        } else if (hairstyle.equals(2)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(brownHairFemale2).getInputStream();
                        } else if (hairstyle.equals(3)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(brownHairFemale3).getInputStream();
                        } else if (hairstyle.equals(4)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(brownHairFemale4).getInputStream();
                        }
                        break;
                    case "Red":
                        if (hairstyle.equals(1)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(redHairFemale1).getInputStream();
                        } else if (hairstyle.equals(2)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(redHairFemale2).getInputStream();
                        } else if (hairstyle.equals(3)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(redHairFemale3).getInputStream();
                        } else if (hairstyle.equals(4)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(redHairFemale4).getInputStream();
                        }
                        break;
                }
                hairImage = ImageIO.read(inputstream);
                inputstream.close();
                switch (eyeColor) {
                    case "Black":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blackEyes).getInputStream();
                        break;
                    case "Blue":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blueEyes).getInputStream();
                        break;
                    case "Brown":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(brownEyes).getInputStream();
                        break;
                    case "Green":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(greenEyes).getInputStream();
                        break;
                    case "Lime":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(limeEyes).getInputStream();
                        break;
                    case "Pink":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(pinkEyes).getInputStream();
                        break;
                    case "Yellow":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(yellowEyes).getInputStream();
                        break;
                }
                eyeImage = ImageIO.read(inputstream);
                inputstream.close();
            } else {
                inputstream = MinecraftClient.getInstance().getResourceManager().getResource(outfit1Genderless).getInputStream();
                outfitImage = ImageIO.read(inputstream);
                switch (this.skinColor) {
                    case "Light":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(lightSkinFemale).getInputStream();
                        break;
                    case "Medium":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(mediumSkinFemale).getInputStream();
                        break;
                    case "Dark":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(darkSkinFemale).getInputStream();
                        break;
                }
                skinImage = ImageIO.read(inputstream);
                inputstream.close();
                switch (this.hairColor) {
                    case "Black":
                        if (hairstyle.equals(1)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blackHairFemale).getInputStream();
                        } else if (hairstyle.equals(2)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blackHairFemale2).getInputStream();
                        } else if (hairstyle.equals(3)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blackHairFemale3).getInputStream();
                        } else if (hairstyle.equals(4)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blackHairFemale4).getInputStream();
                        }

                        break;
                    case "Blonde":
                        if (hairstyle.equals(1)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blondeHairFemale).getInputStream();
                        } else if (hairstyle.equals(2)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blondeHairFemale2).getInputStream();
                        } else if (hairstyle.equals(3)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blondeHairFemale3).getInputStream();
                        } else if (hairstyle.equals(4)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blondeHairFemale4).getInputStream();
                        }

                        break;
                    case "Brown":
                        if (hairstyle.equals(1)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(brownHairFemale).getInputStream();
                        } else if (hairstyle.equals(2)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(brownHairFemale2).getInputStream();
                        } else if (hairstyle.equals(3)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(brownHairFemale3).getInputStream();
                        } else if (hairstyle.equals(4)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(brownHairFemale4).getInputStream();
                        }
                        break;
                    case "Red":
                        if (hairstyle.equals(1)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(redHairFemale1).getInputStream();
                        } else if (hairstyle.equals(2)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(redHairFemale2).getInputStream();
                        } else if (hairstyle.equals(3)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(redHairFemale3).getInputStream();
                        } else if (hairstyle.equals(4)) {
                            inputstream = MinecraftClient.getInstance().getResourceManager().getResource(redHairFemale4).getInputStream();
                        }
                        break;
                }
                hairImage = ImageIO.read(inputstream);
                inputstream.close();
                switch (eyeColor) {
                    case "Black":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blackEyes).getInputStream();
                        break;
                    case "Blue":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(blueEyes).getInputStream();
                        break;
                    case "Brown":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(brownEyes).getInputStream();
                        break;
                    case "Green":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(greenEyes).getInputStream();
                        break;
                    case "Lime":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(limeEyes).getInputStream();
                        break;
                    case "Pink":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(pinkEyes).getInputStream();
                        break;
                    case "Yellow":
                        inputstream = MinecraftClient.getInstance().getResourceManager().getResource(yellowEyes).getInputStream();
                        break;
                }
                eyeImage = ImageIO.read(inputstream);
                inputstream.close();
            }
            totalImage = new BufferedImage(skinImage.getWidth(), skinImage.getHeight(), 2);
            Graphics2D g = totalImage.createGraphics();
            g.drawImage(skinImage, 0, 0, null);
            g.drawImage(eyeImage, 0, 0, null);
            g.drawImage(outfitImage, 0, 0, null);
            g.drawImage(hairImage, 0, 0, null);
            g.dispose();

            return totalImage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}