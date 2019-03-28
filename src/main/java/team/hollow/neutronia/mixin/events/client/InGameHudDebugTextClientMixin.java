package team.hollow.neutronia.mixin.events.client;

import com.google.common.base.Strings;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.hollow.neutronia.event_system.DebugTextEventHandler;

import java.util.ArrayList;


@Mixin(value = {InGameHud.class})
@Environment(EnvType.CLIENT)
public class InGameHudDebugTextClientMixin {
    @Inject(method = "draw",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/hud/InGameHud;renderStatusEffectOverlay()V"
            )
    )
    private void on_draw(CallbackInfo info) {
        //System.out.println("huddraw");
        if (MinecraftClient.getInstance() != null && MinecraftClient.getInstance().textRenderer != null) {
            if (!MinecraftClient.getInstance().options.debugEnabled) {
                TextRenderer fontRenderer = MinecraftClient.getInstance().textRenderer;

                ArrayList<String> lines = DebugTextEventHandler.leftLines;
                for (int i = 0; i < lines.size(); ++i) {
                    String str = lines.get(i);
                    if (!Strings.isNullOrEmpty(str)) {
                        int text_line_height = 9;
                        int line_plus_sp = 2 + text_line_height * i;
                        fontRenderer.draw(str, 2.0F, (float) line_plus_sp, 14737632);
                    }
                }
            }
			/*lines = DebugTextEventHandler.rightLines;
			for(int i = 0; i < lines.size(); ++i)
			{
				String str = (String)lines.get(i);
				if (!Strings.isNullOrEmpty(str))
				{
					int text_line_height = 9;
					int line_plus_sp = 2 + text_line_height * i;
					fontRenderer.draw(str, 2.0F, (float)line_plus_sp, 14737632);
				}
			}*/
        }
    }

}
