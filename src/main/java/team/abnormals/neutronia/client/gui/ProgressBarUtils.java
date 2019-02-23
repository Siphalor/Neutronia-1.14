package team.abnormals.neutronia.client.gui;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.util.math.MathHelper;
import org.apache.commons.lang3.StringUtils;

import static net.minecraft.client.gui.DrawableHelper.drawRect;
import static org.lwjgl.opengl.GL11.*;

public class ProgressBarUtils {

    private static final int barWidth = 400;
    private static final int barHeight = 15;
    private static final int textHeight2 = 20;
    private static final int fontColor = 0x000000;
    private static final int barBorderColor = 0x000000;
    private static final int barColor = 0xCB3D35;
    private static final int barBackgroundColor = 0xFFFFFF;
    private static final int memoryGoodColor = 0xFF78CB34;
    private static final int memoryWarnColor = 0xFFE6E84A;
    private static final int memoryLowColor = 0xFFE42F2F;
    private static float memoryColorPercent;
    private static long memoryColorChangeTime;

    public static void setColor(int color) {
        byte byte1 = (byte) ((color >> 16) & 0xFF);
        byte byte2 = (byte) ((color >> 8) & 0xFF);
        byte byte3 = (byte) (color & 0xFF);
        glColor3ub(byte1, byte2, byte3);
    }

    public static void drawBox(int w, int h) {
        glBegin(GL_QUADS);
        glVertex2f(0, 0);
        glVertex2f(0, h);
        glVertex2f(w, h);
        glVertex2f(w, 0);
        glEnd();
    }

    public static void renderMemoryBar(TextRenderer fontRenderer, int xMin, int yMin, int xMax, int yMax, float fadeAmount) {
        int maxMemory = bytesToMb(Runtime.getRuntime().maxMemory());
        int totalMemory = bytesToMb(Runtime.getRuntime().totalMemory());
        int freeMemory = bytesToMb(Runtime.getRuntime().freeMemory());
        int usedMemory = totalMemory - freeMemory;
        float usedMemoryPercent = usedMemory / (float) maxMemory;
        //xMax for the inner bar
        int activeBarWidth = MathHelper.ceil((float)(xMax - xMin - 2) * usedMemoryPercent);
        int memoryBarColor;
        if (memoryColorPercent < 0.75f) {
            memoryBarColor = memoryGoodColor;
        }
        else if (memoryColorPercent < 0.85f) {
            memoryBarColor = memoryWarnColor;
        }
        else {
            memoryBarColor = memoryLowColor;
        }
        //Title
        fontRenderer.draw("Memory Used / Total", xMin + 20, yMax - 13, 0x000000);
        //Draws the outer border
        drawRect(xMin - 1, yMin - 1, xMax + 1, yMax + 1, 0xFF000000);
        //Draws the inner white
        drawRect(xMin, yMin, xMax, yMax, 0xFFFFFFFF);
        //Draws the inner bar
        drawRect(xMin + 1, yMin + 1, xMin + activeBarWidth, yMax - 1, memoryBarColor);
        //Progress text
        String progress = getMemoryString(usedMemory) + " / " + getMemoryString(maxMemory);
        fontRenderer.draw(progress, xMin + 20, yMax - 11, 0x000000);
    }

    private static int bytesToMb(long bytes) {
        return (int) (bytes / 1024L / 1024L);
    }

    private static String getMemoryString(int memory) {
        return StringUtils.leftPad(Integer.toString(memory), 4, ' ') + " MB";
    }

}
