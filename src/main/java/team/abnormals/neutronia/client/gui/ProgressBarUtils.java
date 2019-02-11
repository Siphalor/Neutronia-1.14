package team.abnormals.neutronia.client.gui;

import net.minecraft.client.font.FontRenderer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glPopMatrix;

public class ProgressBarUtils {

    private static final int barWidth = 400;
    private static final int barHeight = 20;
    private static final int textHeight2 = 20;
    private final int barOffset = 55;

    private static final int fontColor = 0x000000;
    private static final int barBorderColor = 0xC0C0C0;
    private static final int barColor = 0xCB3D35;
    private static final int barBackgroundColor = 0xFFFFFF;

    public static void setColor(int color) {
        glColor3ub((byte)((color >> 16) & 0xFF), (byte)((color >> 8) & 0xFF), (byte)(color & 0xFF));
    }

    public static void drawBox(int w, int h) {
        glBegin(GL_QUADS);
        glVertex2f(0, 0);
        glVertex2f(0, h);
        glVertex2f(w, h);
        glVertex2f(w, 0);
        glEnd();
    }

    public static void drawBar(FontRenderer fontRenderer, ProgressManager.ProgressBar b) {
        glPushMatrix();
        // title - message
        setColor(fontColor);
        glScalef(2, 2, 1);
        glEnable(GL_TEXTURE_2D);
        fontRenderer.draw(b.getTitle() + " - " + b.getMessage(), 0, 0, 0x000000);
        glDisable(GL_TEXTURE_2D);
        glPopMatrix();
        // border
        glPushMatrix();
        glTranslatef(0, textHeight2, 0);
        setColor(barBorderColor);
        drawBox(barWidth, barHeight);
        // interior
        setColor(barBackgroundColor);
        glTranslatef(1, 1, 0);
        drawBox(barWidth - 2, barHeight - 2);
        // slidy part
        setColor(barColor);
        drawBox((barWidth - 2) * (b.getStep() + 1) / (b.getSteps() + 1), barHeight - 2); // Step can sometimes be 0.
        // progress text
        String progress = "" + b.getStep() + "/" + b.getSteps();
        glTranslatef(((float)barWidth - 2) / 2 - fontRenderer.getStringWidth(progress), 2, 0);
        setColor(fontColor);
        glScalef(2, 2, 1);
        glEnable(GL_TEXTURE_2D);
        fontRenderer.draw(progress, 0, 0, 0x000000);
        glPopMatrix();
    }

}
