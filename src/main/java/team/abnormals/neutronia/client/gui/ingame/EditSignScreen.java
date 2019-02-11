package team.abnormals.neutronia.client.gui.ingame;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.SelectionManager;
import net.minecraft.server.network.packet.UpdateSignServerPacket;
import net.minecraft.tag.BlockTags;
import net.minecraft.text.StringTextComponent;
import team.abnormals.neutronia.blocks.NeutroniaSignBlock;
import team.abnormals.neutronia.blocks.NeutroniaWallSignBlock;
import team.abnormals.neutronia.blocks.entity.SignBlockEntity;

@Environment(EnvType.CLIENT)
public class EditSignScreen extends Screen {
    private final SignBlockEntity sign;
    private int ticksSinceOpened;
    private int currentRow;
    private SelectionManager selectionManager;

    public EditSignScreen(SignBlockEntity signBlockEntity_1) {
        this.sign = signBlockEntity_1;
    }

    protected void onInitialized() {
        this.client.keyboard.enableRepeatEvents(true);
        this.addButton(new ButtonWidget(0, this.width / 2 - 100, this.height / 4 + 120, I18n.translate("gui.done")) {
            public void onPressed(double double_1, double double_2) {
                EditSignScreen.this.method_2526();
            }
        });
        this.sign.setEditable(false);
        this.selectionManager = new SelectionManager(this.client, () -> this.sign.getTextOnRow(this.currentRow).getString(), (string_1) -> this.sign.setTextOnRow(this.currentRow, new StringTextComponent(string_1)), 90);
    }

    public void onClosed() {
        this.client.keyboard.enableRepeatEvents(false);
        ClientPlayNetworkHandler clientPlayNetworkHandler_1 = this.client.getNetworkHandler();
        if (clientPlayNetworkHandler_1 != null) {
            clientPlayNetworkHandler_1.sendPacket(new UpdateSignServerPacket(this.sign.getPos(), this.sign.getTextOnRow(0), this.sign.getTextOnRow(1), this.sign.getTextOnRow(2), this.sign.getTextOnRow(3)));
        }

        this.sign.setEditable(true);
    }

    public void update() {
        ++this.ticksSinceOpened;
    }

    private void method_2526() {
        this.sign.markDirty();
        this.client.openScreen(null);
    }

    public boolean charTyped(char char_1, int int_1) {
        this.selectionManager.insert(char_1);
        return true;
    }

    public void close() {
        this.method_2526();
    }

    public boolean keyPressed(int int_1, int int_2, int int_3) {
        if (int_1 == 265) {
            this.currentRow = this.currentRow - 1 & 3;
            this.selectionManager.moveCaretToEnd();
            return true;
        } else if (int_1 != 264 && int_1 != 257 && int_1 != 335) {
            return this.selectionManager.handleSpecialKey(int_1) || super.keyPressed(int_1, int_2, int_3);
        } else {
            this.currentRow = this.currentRow + 1 & 3;
            this.selectionManager.moveCaretToEnd();
            return true;
        }
    }

    public void draw(int int_1, int int_2, float float_1) {
        this.drawBackground();
        this.drawStringCentered(this.fontRenderer, I18n.translate("sign.edit"), this.width / 2, 40, 16777215);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.translatef((float) (this.width / 2), 0.0F, 50.0F);
        GlStateManager.scalef(-93.75F, -93.75F, -93.75F);
        GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);
        BlockState blockState_1 = this.sign.getCachedState();
        float float_4;
        if (blockState_1.getBlock().matches(BlockTags.STANDING_SIGNS)) {
            float_4 = (float) (blockState_1.get(NeutroniaSignBlock.ROTATION) * 360) / 16.0F;
        } else {
            float_4 = blockState_1.get(NeutroniaWallSignBlock.FACING).asRotation();
        }

        GlStateManager.rotatef(float_4, 0.0F, 1.0F, 0.0F);
        GlStateManager.translatef(0.0F, -1.0625F, 0.0F);
        this.sign.setSelectionState(this.currentRow, this.selectionManager.getSelectionStart(), this.selectionManager.getSelectionEnd(), this.ticksSinceOpened / 6 % 2 == 0);
        BlockEntityRenderDispatcher.INSTANCE.renderEntity(this.sign, -0.5D, -0.75D, -0.5D, 0.0F);
        this.sign.resetSelectionState();
        GlStateManager.popMatrix();
        super.draw(int_1, int_2, float_1);
    }
}
