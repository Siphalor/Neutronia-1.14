package team.hollow.neutronia.client.guidebook.page;

import com.google.gson.annotations.SerializedName;
import team.hollow.neutronia.client.guidebook.BookPage;
import team.hollow.neutronia.client.guidebook.gui.GuiBook;

public class PageEmpty extends BookPage {

    @SerializedName("draw_filler")
    boolean filler = true;

    @Override
    public void render(int mouseX, int mouseY, float pticks) {
        if (filler)
            GuiBook.drawPageFiller(book, 0, 0);
    }

}