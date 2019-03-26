package team.hollow.neutronia.client.gui;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.util.math.MathHelper;
import org.apache.commons.lang3.StringUtils;

import static net.minecraft.client.gui.DrawableHelper.*;
import static org.lwjgl.opengl.GL11.glColor3ub;

public class ProgressBarUtils {

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

    public static void renderMemoryBar(TextRenderer fontRenderer, int xMin, int yMin, int xMax, int yMax, float fadeAmount) {
        int maxMemory = bytesToMb(Runtime.getRuntime().maxMemory());
        int totalMemory = bytesToMb(Runtime.getRuntime().totalMemory());
        int freeMemory = bytesToMb(Runtime.getRuntime().freeMemory());
        int usedMemory = totalMemory - freeMemory;
        float usedMemoryPercent = usedMemory / (float) maxMemory;
        //xMax for the inner bar
        int activeBarWidth = MathHelper.ceil((float) (xMax - xMin - 2) * usedMemoryPercent);
        int memoryBarColor;
        if (memoryColorPercent < 0.75f) {
            memoryBarColor = memoryGoodColor;
        } else if (memoryColorPercent < 0.85f) {
            memoryBarColor = memoryWarnColor;
        } else {
            memoryBarColor = memoryLowColor;
        }
        //Title
        fontRenderer.draw("Memory Used / Total", xMin + 20, yMax - 13, 0x000000);
        //Draws the outer border
        fill(xMin - 1, yMin - 1, xMax + 1, yMax + 1, 0xFF000000);
        //Draws the inner white
        fill(xMin, yMin, xMax, yMax, 0xFFFFFFFF);
        //Draws the inner bar
        fill(xMin + 1, yMin + 1, xMin + activeBarWidth, yMax - 1, memoryBarColor);
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
