package team.hollow.neutronia.notebook;

import com.google.gson.annotations.SerializedName;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.client.guidebook.BookContents;
import team.hollow.neutronia.client.guidebook.ExternalBookContents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Notebook {

    public static final String DEFAULT_MODEL = Neutronia.PREFIX + "brown_book";
    public static final ModelIdentifier DEFAULT_MODEL_RES = new ModelIdentifier(DEFAULT_MODEL, "inventory");

    private static final Map<String, String> DEFAULT_MACROS = new HashMap<String, String>() {{
        put("$(list", "$(li"); //  The lack of ) is intended
        put("/$", "$()");
        put("<br>", "$(br)");

        put("$(item)", "$(#b0b)");
        put("$(thing)", "$(#490)");
    }};

    public transient BookContents contents;

    private transient boolean wasUpdated = false;

    public transient ModContainer owner;
    public transient Identifier resourceLoc;
    public transient ModelIdentifier modelResourceLoc;
    private transient ItemStack bookItem;

    public transient Identifier bookResource, fillerResource, craftingResource;
    public transient int textColor, headerColor, nameplateColor, linkColor, linkHoverColor, progressBarColor, progressBarBackground;

    public transient boolean isExternal;

    // JSON Loaded properties
    public String name = "";
    @SerializedName("registry_name")
    public String registryName = "";
    @SerializedName("landing_text")
    public String landingText = "neutronia.gui.lexicon.landing_info";

    @SerializedName("advancement_namespaces")
    public List<String> advancementNamespaces = new ArrayList<>();

    @SerializedName("book_texture")
    public String bookTexture = Neutronia.PREFIX + "textures/gui/brown_book.png";
    @SerializedName("filler_texture")
    public String fillerTexture = Neutronia.PREFIX + "textures/gui/page_filler.png";
    @SerializedName("crafting_texture")
    public String craftingTexture = Neutronia.PREFIX + "textures/gui/crafting.png";

    public String model = DEFAULT_MODEL;

    @SerializedName("text_color")
    public String textColorRaw = "000000";
    @SerializedName("header_color")
    public String headerColorRaw = "333333";
    @SerializedName("nameplate_color")
    public String nameplateColorRaw = "FFDD00";
    @SerializedName("link_color")
    public String linkColorRaw = "0000EE";
    @SerializedName("link_hover_color")
    public String linkHoverColorRaw = "8800EE";

    @SerializedName("progress_bar_color")
    public String progressBarColorRaw = "FFFF55";
    @SerializedName("progress_bar_background")
    public String progressBarBackgroundRaw = "DDDDDD";

    @SerializedName("open_sound")
    public String openSound = "neutronia:book_open";
    @SerializedName("flip_sound")
    public String flipSound = "neutronia:book_flip";

    @SerializedName("show_progress")
    public boolean showProgress = true;

    @SerializedName("index_icon")
    public String indexIconRaw = "";

    public String version = "0";
    public String subtitle = "";

    @SerializedName("item_group")
    public String creativeTab = "misc";

    @SerializedName("dont_generate_book")
    public boolean noBook = false;
    @SerializedName("custom_book_item")
    public String customBookItem = "";

    @SerializedName("show_toasts")
    public boolean showToasts = true;

    @SerializedName("extend")
    public String extend = "";
    @SerializedName("allow_extensions")
    public boolean allowExtensions = true;

    public Map<String, String> macros = new HashMap<>();

    public void build(ModContainer owner, Identifier resource, boolean external) {
        this.owner = owner;
        this.resourceLoc = resource;
        this.isExternal = external;

        modelResourceLoc = new ModelIdentifier(model, "inventory");

        bookResource = new Identifier(bookTexture);
        fillerResource = new Identifier(fillerTexture);
        craftingResource = new Identifier(craftingTexture);

        textColor = 0xFF000000 | Integer.parseInt(textColorRaw, 16);
        headerColor = 0xFF000000 | Integer.parseInt(headerColorRaw, 16);
        nameplateColor = 0xFF000000 | Integer.parseInt(nameplateColorRaw, 16);
        linkColor = 0xFF000000 | Integer.parseInt(linkColorRaw, 16);
        linkHoverColor = 0xFF000000 | Integer.parseInt(linkHoverColorRaw, 16);
        progressBarColor = 0xFF000000 | Integer.parseInt(progressBarColorRaw, 16);
        progressBarBackground = 0xFF000000 | Integer.parseInt(progressBarBackgroundRaw, 16);

        for(String m : DEFAULT_MACROS.keySet())
            if(!macros.containsKey(m))
                macros.put(m, DEFAULT_MACROS.get(m));
    }

    public String getModNamespace() {
        return resourceLoc.getNamespace();
    }

    /*public ItemStack getBookItem() {
        if(bookItem == null) {
            if(noBook)
                bookItem = ItemStackUtil.loadStackFromString(customBookItem);
            else bookItem = NotebookItem.forBook(this);
        }

        return bookItem;
    }*/

    @Environment(EnvType.CLIENT)
    public void markUpdated() {
        wasUpdated = true;
    }

    public boolean popUpdated() {
        boolean updated = wasUpdated;
        wasUpdated = false;
        return updated;
    }

    @Environment(EnvType.CLIENT)
    public void reloadContents() {
        if(contents == null)
            contents = isExternal ? new ExternalBookContents(this) : new BookContents(this);
        contents.reload(false);
    }

    public String getOwnerName() {
        return owner.getMetadata().getName();
    }

}
