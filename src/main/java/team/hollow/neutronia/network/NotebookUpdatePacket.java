package team.hollow.neutronia.network;

import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.init.NConstants;
import team.hollow.neutronia.items.NotebookItem;

public class NotebookUpdatePacket implements IPacket {
    public static final Identifier ID = new Identifier(Neutronia.MOD_ID, "notebook_update");

    private String section;
    private int page;
    private int contentsPage;

    private NotebookUpdatePacket() {
    }

    public NotebookUpdatePacket(String section, int page, int contentsPage) {
        this.section = section;
        this.page = page;
        this.contentsPage = contentsPage;
    }

    @Override
    public void read(PacketByteBuf buf) {
        section = buf.readString(buf.readInt());
        page = buf.readInt();
        contentsPage = buf.readInt();
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeInt(section.length());
        buf.writeString(section);
        buf.writeInt(page);
        buf.writeInt(contentsPage);
    }

    @Override
    public Identifier getID() {
        return ID;
    }

    public static class Handler extends MessageHandler<NotebookUpdatePacket> {
        @Override
        protected NotebookUpdatePacket create() {
            return new NotebookUpdatePacket();
        }

        @Override
        public void handle(PacketContext ctx, NotebookUpdatePacket message) {
            ItemStack stack = ctx.getPlayer().getActiveItem();
            if (stack.getItem() instanceof NotebookItem) {
                stack.getOrCreateTag().putString(NConstants.NOTEBOOK_SECTION_KEY, message.section);
                stack.getOrCreateTag().putInt(NConstants.NOTEBOOK_PAGE_KEY, message.page);
                stack.getOrCreateTag().putInt(NConstants.NOTEBOOK_CONTENTS_PAGE_KEY, message.contentsPage);
            }
        }
    }
}