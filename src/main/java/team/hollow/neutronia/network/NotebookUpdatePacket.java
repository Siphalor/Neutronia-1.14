package team.hollow.neutronia.network;

import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.init.ArcaneMagicConstants;
import team.hollow.neutronia.init.NItems;

public class NotebookUpdatePacket implements IPacket
{
	public static final Identifier ID = new Identifier(Neutronia.MOD_ID, "notebook_update");

	private String section;
	private int page;

	private NotebookUpdatePacket()
	{
	}

	public NotebookUpdatePacket(String section, int page)
	{
		this.section = section;
		this.page = page;
	}

	@Override
	public void read(PacketByteBuf buf)
	{
		section = buf.readString(buf.readInt());
		page = buf.readInt();
	}

	@Override
	public void write(PacketByteBuf buf)
	{
		buf.writeInt(section.length());
		buf.writeString(section);
		buf.writeInt(page);
	}

	@Override
	public Identifier getID()
	{
		return ID;
	}

	public static class Handler extends MessageHandler<NotebookUpdatePacket>
	{
		@Override
		protected NotebookUpdatePacket create()
		{
			return new NotebookUpdatePacket();
		}

		@Override
		public void handle(PacketContext ctx, NotebookUpdatePacket message)
		{
			ItemStack stack = ctx.getPlayer().getMainHandStack();
			if (stack.getItem() != NItems.NOTEBOOK)
			{
				stack = ctx.getPlayer().getOffHandStack();
			}

			if (stack.getItem() == NItems.NOTEBOOK)
			{
				stack.getOrCreateTag().putString(ArcaneMagicConstants.NOTEBOOK_SECTION_KEY, message.section);
				stack.getOrCreateTag().putInt(ArcaneMagicConstants.NOTEBOOK_PAGE_KEY, message.page);
			}
		}
	}
}