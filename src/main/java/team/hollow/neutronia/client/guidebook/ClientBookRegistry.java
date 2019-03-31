package team.hollow.neutronia.client.guidebook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.client.guidebook.page.PageEmpty;
import team.hollow.neutronia.notebook.Notebook;
import team.hollow.neutronia.notebook.NotebookRegistry;

import java.util.HashMap;
import java.util.Map;

public class ClientBookRegistry implements SimpleSynchronousResourceReloadListener {

	public final Map<String, Class<? extends BookPage>> pageTypes = new HashMap<>();

	private boolean firstLoad = true;

	public Gson gson;
	public String currentLang;

	public static final ClientBookRegistry INSTANCE = new ClientBookRegistry();
	
	private ClientBookRegistry() { 
		gson = new GsonBuilder()
//				.registerTypeHierarchyAdapter(BookPage.class, new LexiconPageAdapter())
//				.registerTypeHierarchyAdapter(TemplateComponent.class, new TemplateComponentAdapter())
				.create();
	}
	
	public void init() {
		addPageTypes();
		ResourceManagerHelper.get(ResourceType.DATA).registerReloadListener(this);
	}

	private void addPageTypes() {
		/*pageTypes.put("text", PageText.class);
		pageTypes.put("crafting", PageCrafting.class);
		pageTypes.put("smelting", PageSmelting.class);
		pageTypes.put("image", PageImage.class);
		pageTypes.put("spotlight", PageSpotlight.class);*/
		pageTypes.put("empty", PageEmpty.class);
		/*pageTypes.put("multiblock", PageMultiblock.class);
		pageTypes.put("link", PageLink.class);
		pageTypes.put("relations", PageRelations.class);
		pageTypes.put("entity", PageEntity.class);
		pageTypes.put("quest", PageQuest.class);*/
	}

	@Override
	public void apply(ResourceManager resourceManager) {
		currentLang = MinecraftClient.getInstance().getLanguageManager().getLanguage().getCode();
		
		if(!firstLoad)
			NotebookRegistry.INSTANCE.reload();
		firstLoad = false;
	}
	
	public void displayBookGui(String bookStr) {
		Identifier res = new Identifier(bookStr);
		Notebook book = NotebookRegistry.INSTANCE.books.get(res);
		
		if(book != null) {
			if (!book.contents.getCurrentGui().canBeOpened()) {
				book.contents.currentGui = null;
				book.contents.guiStack.clear();
			}

			book.contents.openLexiconGui(book.contents.getCurrentGui(), false);
		}
	}

	@Override
	public Identifier getFabricId() {
		return new Identifier(Neutronia.MOD_ID, "client_book_registry");
	}

	/*public static class LexiconPageAdapter implements JsonDeserializer<BookPage> {
		
		@Override
		public BookPage deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
	        JsonObject obj = json.getAsJsonObject();
	        JsonPrimitive prim = (JsonPrimitive) obj.get("type");
	        String type = prim.getAsString();
	        Class<? extends BookPage> clazz = ClientBookRegistry.INSTANCE.pageTypes.get(type);
	        if(clazz == null)
	        	clazz = PageTemplate.class;
	        
	        BookPage page = SerializationUtil.RAW_GSON.fromJson(json, clazz);
	        page.sourceObject = obj;
	        
	        return page;
		}
		
	}
	
	public static class TemplateComponentAdapter implements JsonDeserializer<TemplateComponent> {
		
		@Override
		public TemplateComponent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
	        JsonObject obj = json.getAsJsonObject();
	        JsonPrimitive prim = (JsonPrimitive) obj.get("type");
	        String type = prim.getAsString();
	        Class<? extends TemplateComponent> clazz = BookTemplate.componentTypes.get(type);
	        
	        if(clazz == null)
	        	return null;
	        
	        TemplateComponent component = SerializationUtil.RAW_GSON.fromJson(json, clazz);
	        component.sourceObject = obj;

	        return component;
		}
		
	}*/
}