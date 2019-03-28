package team.hollow.neutronia.client.book;

import com.google.gson.*;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.resource.ReloadableResourceManagerImpl;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceReloadListener;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.book.Book;
import team.hollow.neutronia.book.BookRegistry;
import vazkii.patchouli.client.book.page.*;
import vazkii.patchouli.client.book.template.BookTemplate;
import vazkii.patchouli.client.book.template.TemplateComponent;
import vazkii.patchouli.common.util.SerializationUtil;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class ClientBookRegistry implements SimpleSynchronousResourceReloadListener {

	public final Map<String, Class<? extends BookPage>> pageTypes = new HashMap<>();

	private boolean firstLoad = true;

	public Gson gson;
	public String currentLang;

	public static final ClientBookRegistry INSTANCE = new ClientBookRegistry();
	
	private ClientBookRegistry() { 
		gson = new GsonBuilder()
				.registerTypeHierarchyAdapter(BookPage.class, new LexiconPageAdapter())
				.registerTypeHierarchyAdapter(TemplateComponent.class, new TemplateComponentAdapter())
				.create();
	}
	
	public void init() {
		addPageTypes();

		ResourceManager manager = MinecraftClient.getInstance().getResourceManager();
		if (manager instanceof SimpleSynchronousResourceReloadListener)
			ResourceManagerHelper.get(ResourceType.DATA).registerReloadListener(this);
		else
			throw new RuntimeException("Minecraft's resource manager is not reloadable. Something went way wrong.");
	}

	private void addPageTypes() {
		pageTypes.put("text", PageText.class);
		pageTypes.put("crafting", PageCrafting.class);
		pageTypes.put("smelting", PageSmelting.class);
		pageTypes.put("image", PageImage.class);
		pageTypes.put("spotlight", PageSpotlight.class);
		pageTypes.put("empty", PageEmpty.class);
		pageTypes.put("multiblock", PageMultiblock.class); 
		pageTypes.put("link", PageLink.class);
		pageTypes.put("relations", PageRelations.class);
		pageTypes.put("entity", PageEntity.class);
		pageTypes.put("quest", PageQuest.class);
	}

	public void displayBookGui(String bookStr) {
		Identifier res = new Identifier(bookStr);
		Book book = BookRegistry.INSTANCE.books.get(res);
		
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

	@Override
	public void apply(ResourceManager var1) {
		currentLang = MinecraftClient.getInstance().getLanguageManager().getLanguage().getCode();

		if(!firstLoad)
			BookRegistry.INSTANCE.reload();
		firstLoad = false;
	}

	public static class LexiconPageAdapter implements JsonDeserializer<BookPage> {
		
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
		
	}	
}
