package team.hollow.neutronia.client.guidebook.text;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.TextFormat;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.client.guidebook.BookEntry;
import team.hollow.neutronia.client.guidebook.gui.GuiBook;
import team.hollow.neutronia.client.guidebook.gui.GuiBookEntry;
import team.hollow.neutronia.notebook.Notebook;

import java.util.*;

public class BookTextParser {
	private static final Map<String, CommandProcessor> COMMANDS = new HashMap<>();
	private static final Map<String, FunctionProcessor> FUNCTIONS = new HashMap<>();

	private static void register(CommandProcessor handler, String... names) {
		for (String name : names)
			COMMANDS.put(name, handler);
	}

	private static void register(FunctionProcessor function, String... names) {
		for (String name : names)
			FUNCTIONS.put(name, function);
	}

	static {
		register(state -> {
			state.lineBreaks = 1;
			return "";
		}, "br");
		register(state -> {
			state.lineBreaks = 2;
			return "";
		}, "br2", "2br", "p");
		register(state -> {
			state.endingExternal = state.isExternalLink;
			state.color = state.prevColor;
			state.cluster = null;
			state.tooltip = "";
			state.onClick = null;
			state.isExternalLink = false;
			return "";
		}, "/l");
		register(state -> {
			state.cluster = null;
			state.tooltip = "";
			return "";
		}, "/t");
		register(state -> state.gui.getMinecraft().player.getEntityName(), "playername");
		register(state -> state.codes( "\u00A7k"), "k", "obf");
		register(state -> state.codes("\u00A7l"), "l", "bold");
		register(state -> state.codes("\u00A7m"), "m", "strike");
		register(state -> state.codes("\u00A7o"), "o", "italic", "italics");
		register(state -> { state.reset(); return ""; }, "", "reset", "clear");
		register(state -> state.color(state.font.getCharWidth('0')), "nocolor");

		register((parameter, state) -> {
			KeyBinding result = getKeybindKey(state, parameter);
			if (result == null) {
				state.tooltip = I18n.translate("patchouli.gui.lexicon.keybind_missing", parameter);
				return "N/A";
			}

			state.tooltip = I18n.translate("patchouli.gui.lexicon.keybind", I18n.translate(result.getName()));
			return result.getLocalizedName();
		}, "k");
		register((parameter, state) -> {
			state.cluster = new LinkedList<>();

			state.prevColor = state.color;
			state.color = state.book.linkColor;
			boolean isExternal = parameter.matches("^https?\\:.*");

			if (isExternal) {
				String url = parameter;
				state.tooltip = I18n.translate("patchouli.gui.lexicon.external_link");
				state.isExternalLink = true;
				state.onClick = () -> GuiBook.openWebLink(url);
			} else {
				int hash = parameter.indexOf('#');
				String anchor = null;
				if (hash >= 0) {
					anchor = parameter.substring(hash + 1);
					parameter = parameter.substring(0, hash);
				}

				Identifier href = new Identifier(state.book.getModNamespace(), parameter);
				BookEntry entry = state.book.contents.entries.get(href);
				if(entry != null) {
					state.tooltip = entry.getName();
					GuiBook gui = state.gui;
					Notebook book = state.book;
					int page = 0;
					if (anchor != null) {
						int anchorPage = entry.getPageFromAnchor(anchor);
						if (anchorPage >= 0)
							page = anchorPage / 2;
						else
							state.tooltip += " (INVALID ANCHOR:" + anchor + ")";
					}
					int finalPage = page;
					state.onClick = () -> {
						GuiBookEntry entryGui = new GuiBookEntry(book, entry, finalPage);
						//TODO: Fix gui
//						gui.displayLexiconGui(entryGui, true);
						GuiBook.playBookFlipSound(book);
					};
				} else {
					state.tooltip = "BAD LINK: " + parameter;
				}
			}
			return "";
		}, "l");
		register((parameter, state) -> {
			state.tooltip = parameter;
			state.cluster = new LinkedList<>();
			return "";
		}, "tooltip", "t");
	}

	private final GuiBook gui;
	private final Notebook book;
	private final int x, y, width;
	private final int lineHeight;
	private final int baseColor;
	private final TextRenderer font;
	private final int spaceWidth;

	public BookTextParser(GuiBook gui, Notebook book, int x, int y, int width, int lineHeight, int baseColor) {
		this.gui = gui;
		this.book = book;
		this.x = x;
		this.y = y;
		this.width = width;
		this.lineHeight = lineHeight;
		this.baseColor = baseColor;

		this.font = gui.getMinecraft().textRenderer;
		this.spaceWidth = font.getStringWidth(" ");
	}

	public List<Word> parse(String text) {
		boolean wasUnicode = font.isRightToLeft();
		font.setRightToLeft(true);

		String actualText = text;
		if(actualText == null)
			actualText = "[ERROR]";

		for(String key : book.macros.keySet())
			actualText = actualText.replace(key, book.macros.get(key));

		List<Span> spans = processCommands(actualText);
		List<Word> words = layout(spans);

		font.setRightToLeft(wasUnicode);
		return words;
	}

	private List<Word> layout(List<Span> spans) {
		TextLayouter layouter = new TextLayouter(gui, x, y, lineHeight, width);
		layouter.layout(spans);
		return layouter.getWords();
	}

	private List<Span> processCommands(String text) {
		SpanState state = new SpanState(gui, book, baseColor, font);
		List<Span> spans = new ArrayList<>();
		int from = 0;
		char[] chars = text.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == '$' && i + 1 < chars.length && chars[i + 1] == '(') {
				if (i > from)
					spans.add(new Span(state, text.substring(from, i)));

				from = i;
				while (i < chars.length && chars[i] != ')')
					i++;

				if (i == chars.length) {
					spans.add(Span.error(state, "[ERROR: UNFINISHED COMMAND]"));
					break;
				}

				try {
					String processed = processCommand(state, text.substring(from + 2, i));
					if (!processed.isEmpty()) {
						spans.add(new Span(state, processed));

						if (state.cluster == null)
							state.tooltip = "";
					}
				} catch (Exception ex) {
					spans.add(Span.error(state, "[ERROR]"));
				}

				from = i + 1;
			}
		}
		spans.add(new Span(state, text.substring(from)));
		return spans;
	}

	private String processCommand(SpanState state, String cmd) {
		state.endingExternal = false;
		String result = "";

		if (cmd.length() == 1 && cmd.matches("^[0123456789abcdef]$")) { // Vanilla colors
			state.color = (int) font.getCharWidth(cmd.charAt(0));
			return "";
		}
		else if(cmd.startsWith("#") && (cmd.length() == 4 || cmd.length() == 7)) { // Hex colors
			String parse = cmd.substring(1);
			if(parse.length() == 3)
				parse = "" + parse.charAt(0) + parse.charAt(0) + parse.charAt(1) + parse.charAt(1) + parse.charAt(2) + parse.charAt(2);
			try {
				state.color = Integer.parseInt(parse, 16);
			} catch(NumberFormatException e) {
				state.color = baseColor;
			}
			return "";
		}
		else if (cmd.matches("li\\d?")) { // List Element
			char c = cmd.length() > 2 ? cmd.charAt(2) : '1';
			int dist = Character.isDigit(c) ? Character.digit(c, 10) : 1;
			int pad = dist * 4;
			char bullet = dist % 2 == 0 ? '\u25E6' : '\u2022';
			state.lineBreaks = 1;
			state.spacingLeft = pad;
			state.spacingRight = spaceWidth;
			return TextFormat.BLACK.toString() + bullet;
		}

		if (cmd.indexOf(':') > 0) {
			int index = cmd.indexOf(':');
			String function = cmd.substring(0, index);
			String parameter = cmd.substring(index + 1);
			if (FUNCTIONS.containsKey(function)) {
				result = FUNCTIONS.get(function).process(parameter, state);
			} else {
				result = "[MISSING FUNCTION: " + function + "]";
			}
		}
		else if (COMMANDS.containsKey(cmd)) {
			result = COMMANDS.get(cmd).process(state);
		}

		if(state.endingExternal)
			result += TextFormat.GRAY + "\u21AA";

		return result;
	}

	private static KeyBinding getKeybindKey(SpanState state, String keybind) {
		String alt = "key." + keybind;

		KeyBinding[] keys = state.gui.getMinecraft().options.keysAll;
		for(KeyBinding k : keys) {
			String name = k.getName();
			if(name.equals(keybind) || name.equals(alt))
				return k;
		}

		return null;
	}
	
	public interface CommandProcessor {
		String process(SpanState state);
	}
	
	public interface FunctionProcessor {
		String process(String parameter, SpanState state);
	}

}