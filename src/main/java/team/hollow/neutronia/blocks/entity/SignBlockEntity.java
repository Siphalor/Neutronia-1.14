package team.hollow.neutronia.blocks.entity;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.network.packet.BlockEntityUpdateS2CPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.Style;
import net.minecraft.text.TextComponent;
import net.minecraft.text.TextFormatter;
import net.minecraft.text.event.ClickEvent;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import team.hollow.neutronia.init.NBlockEntities;

import java.util.Objects;
import java.util.function.Function;

public class SignBlockEntity extends BlockEntity {
    public final TextComponent[] text = new TextComponent[]{new StringTextComponent(""), new StringTextComponent(""), new StringTextComponent(""), new StringTextComponent("")};
    private final String[] textBeingEdited = new String[4];
    @Environment(EnvType.CLIENT)
    private boolean caretVisible;
    private int currentRow = -1;
    private int selectionStart = -1;
    private int selectionEnd = -1;
    private boolean editable = true;
    private PlayerEntity editor;
    private DyeColor textColor;

    public SignBlockEntity() {
        super(NBlockEntities.TILE_SIGN);
        this.textColor = DyeColor.BLACK;
    }

    public CompoundTag toTag(CompoundTag compoundTag_1) {
        super.toTag(compoundTag_1);

        for (int int_1 = 0; int_1 < 4; ++int_1) {
            String string_1 = TextComponent.Serializer.toJsonString(this.text[int_1]);
            compoundTag_1.putString("Text" + (int_1 + 1), string_1);
        }

        compoundTag_1.putString("Color", this.textColor.getName());
        return compoundTag_1;
    }

    public void fromTag(CompoundTag compoundTag_1) {
        this.editable = false;
        super.fromTag(compoundTag_1);
        this.textColor = DyeColor.byName(compoundTag_1.getString("Color"), DyeColor.BLACK);

        for (int int_1 = 0; int_1 < 4; ++int_1) {
            String string_1 = compoundTag_1.getString("Text" + (int_1 + 1));
            TextComponent textComponent_1 = TextComponent.Serializer.fromJsonString(string_1);
            if (this.world instanceof ServerWorld) {
                try {
                    this.text[int_1] = TextFormatter.method_10881(this.getCommandSource(null), Objects.requireNonNull(textComponent_1), null);
                } catch (CommandSyntaxException var6) {
                    this.text[int_1] = textComponent_1;
                }
            } else {
                this.text[int_1] = textComponent_1;
            }

            this.textBeingEdited[int_1] = null;
        }

    }

    @Environment(EnvType.CLIENT)
    public TextComponent getTextOnRow(int int_1) {
        return this.text[int_1];
    }

    public void setTextOnRow(int int_1, TextComponent textComponent_1) {
        this.text[int_1] = textComponent_1;
        this.textBeingEdited[int_1] = null;
    }

    @Environment(EnvType.CLIENT)
    public String getTextBeingEditedOnRow(int int_1, Function<TextComponent, String> function_1) {
        if (this.textBeingEdited[int_1] == null && this.text[int_1] != null) {
            this.textBeingEdited[int_1] = function_1.apply(this.text[int_1]);
        }

        return this.textBeingEdited[int_1];
    }

    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return new BlockEntityUpdateS2CPacket(this.pos, 9, this.toInitialChunkDataTag());
    }

    public CompoundTag toInitialChunkDataTag() {
        return this.toTag(new CompoundTag());
    }

    public boolean shouldNotCopyTagFromItem() {
        return true;
    }

    public boolean isEditable() {
        return this.editable;
    }

    @Environment(EnvType.CLIENT)
    public void setEditable(boolean boolean_1) {
        this.editable = boolean_1;
        if (!boolean_1) {
            this.editor = null;
        }

    }

    public PlayerEntity getEditor() {
        return this.editor;
    }

    public void setEditor(PlayerEntity playerEntity_1) {
        this.editor = playerEntity_1;
    }

    public boolean onActivate(PlayerEntity playerEntity_1) {
        TextComponent[] var2 = this.text;
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            TextComponent textComponent_1 = var2[var4];
            Style style_1 = textComponent_1 == null ? null : textComponent_1.getStyle();
            if (style_1 != null && style_1.getClickEvent() != null) {
                ClickEvent clickEvent_1 = style_1.getClickEvent();
                if (clickEvent_1.getAction() == ClickEvent.Action.RUN_COMMAND) {
                    Objects.requireNonNull(playerEntity_1.getServer()).getCommandManager().execute(this.getCommandSource((ServerPlayerEntity) playerEntity_1), clickEvent_1.getValue());
                }
            }
        }

        return true;
    }

    private ServerCommandSource getCommandSource(ServerPlayerEntity serverPlayerEntity_1) {
        String string_1 = serverPlayerEntity_1 == null ? "Sign" : serverPlayerEntity_1.getName().getString();
        TextComponent textComponent_1 = serverPlayerEntity_1 == null ? new StringTextComponent("Sign") : serverPlayerEntity_1.getDisplayName();
        return new ServerCommandSource(CommandOutput.field_17395, new Vec3d((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D), Vec2f.ZERO, (ServerWorld) this.world, 2, string_1, textComponent_1, this.world.getServer(), serverPlayerEntity_1);
    }

    public DyeColor getTextColor() {
        return this.textColor;
    }

    public boolean setTextColor(DyeColor dyeColor_1) {
        if (dyeColor_1 != this.getTextColor()) {
            this.textColor = dyeColor_1;
            this.markDirty();
            this.world.updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), 3);
            return true;
        } else {
            return false;
        }
    }

    @Environment(EnvType.CLIENT)
    public void setSelectionState(int int_1, int int_2, int int_3, boolean boolean_1) {
        this.currentRow = int_1;
        this.selectionStart = int_2;
        this.selectionEnd = int_3;
        this.caretVisible = boolean_1;
    }

    @Environment(EnvType.CLIENT)
    public void resetSelectionState() {
        this.currentRow = -1;
        this.selectionStart = -1;
        this.selectionEnd = -1;
        this.caretVisible = false;
    }

    @Environment(EnvType.CLIENT)
    public boolean isCaretVisible() {
        return this.caretVisible;
    }

    @Environment(EnvType.CLIENT)
    public int getCurrentRow() {
        return this.currentRow;
    }

    @Environment(EnvType.CLIENT)
    public int getSelectionStart() {
        return this.selectionStart;
    }

    @Environment(EnvType.CLIENT)
    public int getSelectionEnd() {
        return this.selectionEnd;
    }
}
