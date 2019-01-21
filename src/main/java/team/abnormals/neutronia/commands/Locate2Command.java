package team.abnormals.neutronia.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.server.command.ServerCommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TextComponent;
import net.minecraft.text.TextFormatter;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.text.event.ClickEvent;
import net.minecraft.text.event.HoverEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class Locate2Command {

    private static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType(new TranslatableTextComponent("commands.locate.failed"));

    public static void register(CommandDispatcher<ServerCommandSource> var0) {
        var0.register((ServerCommandManager.literal("locate2").requires((var0x) ->
            var0x.hasPermissionLevel(2)
        )).then(ServerCommandManager.literal("New_Desert_Temple").executes((var0x) ->
            method_13457(var0x.getSource(), "New_Desert_Temple")
        )));
    }

    private static int method_13457(ServerCommandSource var0, String var1) throws CommandSyntaxException {
        BlockPos var2 = new BlockPos(var0.getPosition());
        BlockPos var3 = var0.getWorld().locateStructure(var1, var2, 100, false);
        if (var3 == null) {
            throw FAILED_EXCEPTION.create();
        } else {
            int var4 = MathHelper.floor(method_13439(var2.getX(), var2.getZ(), var3.getX(), var3.getZ()));
            TextComponent var5 = TextFormatter.bracketed(new TranslatableTextComponent("chat.coordinates", var3.getX(), "~", var3.getZ())).modifyStyle((var1x) ->
                    var1x.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tp @s " + var3.getX() + " ~ " + var3.getZ()))
                            .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslatableTextComponent("chat.coordinates.tooltip"))));
            var0.sendFeedback(new TranslatableTextComponent("commands.locate.success", var1, var5, var4), false);
            return var4;
        }
    }

    private static float method_13439(int var0, int var1, int var2, int var3) {
        int var4 = var2 - var0;
        int var5 = var3 - var1;
        return MathHelper.sqrt((float)(var4 * var4 + var5 * var5));
    }

}
