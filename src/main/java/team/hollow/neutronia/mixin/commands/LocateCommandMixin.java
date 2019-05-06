package team.hollow.neutronia.mixin.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.network.chat.*;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.LocateCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LocateCommand.class)
public class LocateCommandMixin {

    @Final
    @Shadow
    private static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType(new TranslatableComponent("commands.locate.failed"));

    @Redirect(method = "register(Lcom/mojang/brigadier/CommandDispatcher;)V", at = @At(value = "INVOKE", target = "Lcom/mojang/brigadier/CommandDispatcher;register(Lcom/mojang/brigadier/builder/LiteralArgumentBuilder;)Lcom/mojang/brigadier/tree/LiteralCommandNode;"))
    private static LiteralCommandNode register(CommandDispatcher dispatcher, final LiteralArgumentBuilder command) {
        command.then(CommandManager.literal("Pillager_Mansion").executes((commandContext_1) ->
                execute(commandContext_1.getSource(), "neutronia:pillager_mansion"))
        ).then(CommandManager.literal("Ritual_Site").executes((commandContext_1) ->
                execute(commandContext_1.getSource(), "neutronia:ritual_site"))
        ).then(CommandManager.literal("Totem_Poles").executes((commandContext_1) ->
                execute(commandContext_1.getSource(), "neutronia:totem_pole"))
        )/*.then(CommandManager.literal("Celebrating_Vinny").executes((commandContext_1) ->
                execute(commandContext_1.getSource(), "neutronia:celebrating_vinny"))
        )*/;
        final LiteralCommandNode build = command.build();
        dispatcher.getRoot().addChild(build);
        return build;
    }

    @Shadow
    private static int execute(ServerCommandSource var0, String var1) throws CommandSyntaxException {
        BlockPos var2 = new BlockPos(var0.getPosition());
        BlockPos var3 = var0.getWorld().locateStructure(var1, var2, 100, false);
        if (var3 == null) {
            throw FAILED_EXCEPTION.create();
        } else {
            int var4 = MathHelper.floor(getDistance(var2.getX(), var2.getZ(), var3.getX(), var3.getZ()));
            Component var5 = Components.bracketed(new TranslatableComponent("chat.coordinates", var3.getX(), "~", var3.getZ())).modifyStyle((var1x) ->
                    var1x.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tp @s " + var3.getX() + " ~ " + var3.getZ()))
                            .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslatableComponent("chat.coordinates.tooltip"))));
            var0.sendFeedback(new TranslatableComponent("commands.locate.success", var1, var5, var4), false);
            return var4;
        }
    }

    @Shadow
    private static float getDistance(int var0, int var1, int var2, int var3) {
        int var4 = var2 - var0;
        int var5 = var3 - var1;
        return MathHelper.sqrt((float) (var4 * var4 + var5 * var5));
    }

}