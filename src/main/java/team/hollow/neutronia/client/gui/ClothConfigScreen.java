//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.hollow.neutronia.client.gui;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.AtomicDouble;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.menu.YesNoScreen;
import net.minecraft.client.gui.widget.AbstractButtonWidget;
import net.minecraft.client.gui.widget.AbstractPressableButtonWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.client.gui.widget.ElementListWidget.ElementItem;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.math.MathHelper;
import team.hollow.neutronia.api.ConfigScreenBuilder;
import team.hollow.neutronia.client.gui.entries.*;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class ClothConfigScreen extends Screen {
    private static final Identifier CONFIG_TEX = new Identifier("cloth-config", "textures/gui/cloth_config.png");
    public int nextTabIndex;
    public int selectedTabIndex;
    private Screen parent;
    private ClothConfigScreen.ListWidget listWidget;
    private LinkedHashMap<String, List<ClothConfigScreen.AbstractListEntry>> tabbedEntries;
    private List<Pair<String, Integer>> tabs;
    private boolean edited;
    private boolean confirmSave;
    private AbstractButtonWidget buttonQuit;
    private AbstractButtonWidget buttonSave;
    private AbstractButtonWidget buttonLeftTab;
    private AbstractButtonWidget buttonRightTab;
    private Rectangle tabsBounds;
    private Rectangle tabsLeftBounds;
    private Rectangle tabsRightBounds;
    private String title;
    private double tabsScrollProgress;
    private double tabsMaximumScrolled;
    private boolean displayErrors;
    private List<ClothConfigTabButton> tabButtons;

    public ClothConfigScreen(Screen parent, String title, Map<String, List<Pair<String, Object>>> o) {
        this(parent, title, o, true, true);
    }

    public ClothConfigScreen(Screen parent, String title, Map<String, List<Pair<String, Object>>> o, boolean confirmSave, boolean displayErrors) {
        super(new StringTextComponent(""));
        this.tabsScrollProgress = 0.0D;
        this.tabsMaximumScrolled = -1.0D;
        this.parent = parent;
        this.title = title;
        this.tabbedEntries = Maps.newLinkedHashMap();
        o.forEach((tab, pairs) -> {
            List<ClothConfigScreen.AbstractListEntry> list = Lists.newArrayList();
            Iterator<Pair<String, Object>> var4 = pairs.iterator();

            while(true) {
                while(var4.hasNext()) {
                    Pair<String, Object> pair = var4.next();
                    if (pair.getRight() instanceof ClothConfigScreen.ListEntry) {
                        list.add((ClothConfigScreen.ListEntry)pair.getRight());
                    } else {
                        if (pair.getRight() instanceof ClothConfigScreen.AbstractListEntry) {
                            throw new IllegalArgumentException("Unsupported Type (" + pair.getLeft() + "): AbstractListEntry");
                        }

                        if (!Boolean.TYPE.isAssignableFrom(pair.getRight().getClass()) && !Boolean.class.isAssignableFrom(pair.getRight().getClass())) {
                            if (String.class.isAssignableFrom(pair.getRight().getClass())) {
                                list.add(new StringListEntry(pair.getLeft(), (String)pair.getRight(), null));
                            } else if (!Integer.TYPE.isAssignableFrom(pair.getRight().getClass()) && !Integer.class.isAssignableFrom(pair.getRight().getClass())) {
                                if (!Long.TYPE.isAssignableFrom(pair.getRight().getClass()) && !Long.class.isAssignableFrom(pair.getRight().getClass())) {
                                    if (!Float.TYPE.isAssignableFrom(pair.getRight().getClass()) && !Float.class.isAssignableFrom(pair.getRight().getClass())) {
                                        if (!Double.TYPE.isAssignableFrom(pair.getRight().getClass()) && !Double.class.isAssignableFrom(pair.getRight().getClass())) {
                                            throw new IllegalArgumentException("Unsupported Type (" + pair.getLeft() + "): " + pair.getRight().getClass().getSimpleName());
                                        }

                                        list.add(new DoubleListEntry(pair.getLeft(), (Double)pair.getRight(), null));
                                    } else {
                                        list.add(new FloatListEntry(pair.getLeft(), (Float)pair.getRight(), null));
                                    }
                                } else {
                                    list.add(new LongListEntry(pair.getLeft(), (Long)pair.getRight(), null));
                                }
                            } else {
                                list.add(new IntegerListEntry(pair.getLeft(), (Integer)pair.getRight(), null));
                            }
                        } else {
                            list.add(new BooleanListEntry(pair.getLeft(), (Boolean)pair.getRight(), null));
                        }
                    }
                }

                list.forEach((entry) -> entry.screen = this);
                this.tabbedEntries.put(tab, list);
                return;
            }
        });
        this.nextTabIndex = 0;
        this.selectedTabIndex = 0;
        this.confirmSave = confirmSave;
        this.edited = false;
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        this.tabs = this.tabbedEntries.keySet().stream().map((s) -> new Pair<>(s, textRenderer.getStringWidth(I18n.translate(s)) + 8)).collect(Collectors.toList());
        this.tabsScrollProgress = 0.0D;
        this.tabButtons = Lists.newArrayList();
        this.displayErrors = displayErrors;
    }

    public boolean isEdited() {
        return this.edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
        this.buttonQuit.setMessage(edited ? I18n.translate("text.cloth-config.cancel_discard") : I18n.translate("gui.cancel"));
        this.buttonSave.active = edited;
    }

    protected void init() {
        super.init();
        this.children.clear();
        this.tabButtons.clear();
        if (this.listWidget != null) {
            this.tabbedEntries.put((String) ((Pair)this.tabs.get(this.selectedTabIndex)).getLeft(), this.listWidget.children());
        }

        this.selectedTabIndex = this.nextTabIndex;
        this.children.add(this.listWidget = new ClothConfigScreen.ListWidget(this.minecraft, this.width, this.height, 70, this.height - 32, 24));
        if (this.tabbedEntries.size() > this.selectedTabIndex) {
            Lists.newArrayList(this.tabbedEntries.values()).get(this.selectedTabIndex).forEach((entry) -> this.listWidget.children().add(entry));
        }

        this.clampTabsScrolled();
        this.addButton(this.buttonQuit = new ButtonWidget(this.width / 2 - 154, this.height - 26, 150, 20, this.edited ? I18n.translate("text.cloth-config.cancel_discard") : I18n.translate("gui.cancel"), (widget) -> {
            if (this.confirmSave && this.edited) {
                this.minecraft.openScreen(new YesNoScreen(t -> {

                }, new TranslatableTextComponent("text.cloth-config.quit_config"), new TranslatableTextComponent("text.cloth-config.quit_config_sure"), I18n.translate("text.cloth-config.quit_discard"), I18n.translate("gui.cancel")));
            } else {
                this.minecraft.openScreen(this.parent);
            }

        }));
        this.addButton(this.buttonSave = new AbstractPressableButtonWidget(this.width / 2 + 4, this.height - 26, 150, 20, "") {
            public void onPress() {
                Map<String, List<Pair<String, Object>>> map = Maps.newLinkedHashMap();
                ClothConfigScreen.this.tabbedEntries.forEach((s, abstractListEntries) -> {
                    List list = abstractListEntries.stream().map((entry) -> new Pair<>(entry.getFieldName(), entry.getObject())).collect(Collectors.toList());
                    map.put(s, list);
                });

                for (List<AbstractListEntry> abstractListEntries : Lists.newArrayList(ClothConfigScreen.this.tabbedEntries.values())) {
                    for (AbstractListEntry entry : abstractListEntries) {
                        entry.save();
                    }
                }

                ClothConfigScreen.this.onSave(map);
                ClothConfigScreen.this.minecraft.openScreen(ClothConfigScreen.this.parent);
            }

            public void render(int int_1, int int_2, float float_1) {
                boolean hasErrors = false;
                if (ClothConfigScreen.this.displayErrors) {
                    for (List<AbstractListEntry> entries : Lists.newArrayList(ClothConfigScreen.this.tabbedEntries.values())) {
                        for (AbstractListEntry entry : entries) {
                            if (entry.getError().isPresent()) {
                                hasErrors = true;
                                break;
                            }
                        }
                        if (hasErrors) {
                            break;
                        }
                    }
                }

                this.active = ClothConfigScreen.this.edited && !hasErrors;
                this.setMessage(ClothConfigScreen.this.displayErrors && hasErrors ? I18n.translate("text.cloth-config.error_cannot_save") : I18n.translate("text.cloth-config.save_and_done"));
                super.render(int_1, int_2, float_1);
            }
        });
        this.buttonSave.active = this.edited;
        this.tabsBounds = new Rectangle(0, 41, this.width, 24);
        this.tabsLeftBounds = new Rectangle(0, 41, 18, 24);
        this.tabsRightBounds = new Rectangle(this.width - 18, 41, 18, 24);
        this.children.add(this.buttonLeftTab = new AbstractPressableButtonWidget(4, 44, 12, 18, "") {
            public void onPress() {
                ClothConfigScreen.this.tabsScrollProgress = -2.147483648E9D;
                ClothConfigScreen.this.clampTabsScrolled();
            }

            public void renderButton(int int_1, int int_2, float float_1) {
                Objects.requireNonNull(ClothConfigScreen.this.minecraft).getTextureManager().bindTexture(ClothConfigScreen.CONFIG_TEX);
                GlStateManager.color4f(1.0F, 1.0F, 1.0F, this.alpha);
                int int_3 = this.getYImage(this.isHovered());
                GlStateManager.enableBlend();
                GlStateManager.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
                GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
                this.blit(this.x, this.y, 12, 18 * int_3, this.width, this.height);
            }
        });
        int j = 0;
        int xx = 20 - (int)this.tabsScrollProgress;

        for(Iterator<Pair<String, Integer>> var3 = this.tabs.iterator(); var3.hasNext(); ++j) {
            Pair<String, Integer> tab = var3.next();
            this.tabButtons.add(new ClothConfigTabButton(this, j, -100, 43, tab.getRight(), 20, I18n.translate(tab.getLeft())));
        }

        List<net.minecraft.client.gui.Element> var10001 = this.children;
        this.tabButtons.forEach(var10001::add);
        this.children.add(this.buttonRightTab = new AbstractPressableButtonWidget(this.width - 16, 44, 12, 18, "") {
            public void onPress() {
                ClothConfigScreen.this.tabsScrollProgress = 2.147483647E9D;
                ClothConfigScreen.this.clampTabsScrolled();
            }

            public void renderButton(int int_1, int int_2, float float_1) {
                Objects.requireNonNull(ClothConfigScreen.this.minecraft).getTextureManager().bindTexture(ClothConfigScreen.CONFIG_TEX);
                GlStateManager.color4f(1.0F, 1.0F, 1.0F, this.alpha);
                int int_3 = this.getYImage(this.isHovered());
                GlStateManager.enableBlend();
                GlStateManager.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
                GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
                this.blit(this.x, this.y, 0, 18 * int_3, this.width, this.height);
            }
        });
    }

    public boolean mouseScrolled(double double_1, double double_2, double double_3) {
        if (this.tabsBounds.contains(double_1, double_2) && !this.tabsLeftBounds.contains(double_1, double_2) && !this.tabsRightBounds.contains(double_1, double_2) && double_3 != 0.0D) {
            if (double_3 < 0.0D) {
                this.tabsScrollProgress += 16.0D;
            }

            if (double_3 > 0.0D) {
                this.tabsScrollProgress -= 16.0D;
            }

            this.clampTabsScrolled();
            return true;
        } else {
            return super.mouseScrolled(double_1, double_2, double_3);
        }
    }

    public double getTabsMaximumScrolled() {
        if (this.tabsMaximumScrolled == -1.0D) {
            AtomicDouble d = new AtomicDouble();
            this.tabs.forEach((pair) -> {
                d.addAndGet((double)(pair.getRight() + 2));
            });
            this.tabsMaximumScrolled = d.get();
        }

        return this.tabsMaximumScrolled;
    }

    public void resetTabsMaximumScrolled() {
        this.tabsMaximumScrolled = -1.0D;
    }

    public void clampTabsScrolled() {
        int xx = 0;

        ClothConfigTabButton tabButton;
        for(Iterator<ClothConfigTabButton> var2 = this.tabButtons.iterator(); var2.hasNext(); xx += tabButton.getWidth() + 2) {
            tabButton = var2.next();
        }

        if (xx > this.width - 40) {
            this.tabsScrollProgress = MathHelper.clamp(this.tabsScrollProgress, 0.0D, this.getTabsMaximumScrolled() - (double)this.width + 40.0D);
        } else {
            this.tabsScrollProgress = 0.0D;
        }

    }

    public void render(int int_1, int int_2, float float_1) {
        this.clampTabsScrolled();
        int xx = 20 - (int)this.tabsScrollProgress;

        ClothConfigTabButton tabButton;
        for(Iterator<ClothConfigTabButton> var5 = this.tabButtons.iterator(); var5.hasNext(); xx += tabButton.getWidth() + 2) {
            tabButton = var5.next();
            tabButton.x = xx;
        }

        this.buttonLeftTab.active = this.tabsScrollProgress > 0.0D;
        this.buttonRightTab.active = this.tabsScrollProgress < this.getTabsMaximumScrolled() - (double)this.width - 16.0D;
        this.renderDirtBackground(0);
        this.listWidget.render(int_1, int_2, float_1);
        this.overlayBackground(this.tabsBounds, 32, 32, 32, 255, 255);
        this.drawCenteredString(Objects.requireNonNull(this.minecraft).textRenderer, this.title, this.width / 2, 18, -1);
        this.tabButtons.forEach((widget) -> {
            widget.render(int_1, int_2, float_1);
        });
        this.overlayBackground(this.tabsLeftBounds, 64, 64, 64, 255, 255);
        this.overlayBackground(this.tabsRightBounds, 64, 64, 64, 255, 255);
        this.drawShades();
        this.buttonLeftTab.render(int_1, int_2, float_1);
        this.buttonRightTab.render(int_1, int_2, float_1);
        if (this.displayErrors) {
            List<String> errors = Lists.newArrayList();

            for (List<AbstractListEntry> entries : Lists.newArrayList(this.tabbedEntries.values())) {
                for (AbstractListEntry entry : entries) {
                    if (entry.getError().isPresent()) {
                        errors.add(entry.getError().get());
                    }
                }
            }

            if (errors.size() > 0) {
                this.minecraft.getTextureManager().bindTexture(CONFIG_TEX);
                GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                this.blit(10, 10, 0, 54, 3, 11);
                if (errors.size() == 1) {
                    this.drawString(this.minecraft.textRenderer, "§c" + errors.get(0), 18, 12, -1);
                } else {
                    this.drawString(this.minecraft.textRenderer, "§c" + I18n.translate("text.cloth-config.multi_error"), 18, 12, -1);
                }
            }
        }

        super.render(int_1, int_2, float_1);
    }

    private void drawShades() {
        GlStateManager.enableBlend();
        GlStateManager.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ZERO, DestFactor.ONE);
        GlStateManager.disableAlphaTest();
        GlStateManager.shadeModel(7425);
        GlStateManager.disableTexture();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBufferBuilder();
        buffer.begin(7, VertexFormats.POSITION_UV_COLOR);
        buffer.vertex(this.tabsBounds.getMinX() + 20.0D, this.tabsBounds.getMinY() + 4.0D, 0.0D).texture(0.0D, 1.0D).color(0, 0, 0, 0).next();
        buffer.vertex(this.tabsBounds.getMaxX() - 20.0D, this.tabsBounds.getMinY() + 4.0D, 0.0D).texture(1.0D, 1.0D).color(0, 0, 0, 0).next();
        buffer.vertex(this.tabsBounds.getMaxX() - 20.0D, this.tabsBounds.getMinY(), 0.0D).texture(1.0D, 0.0D).color(0, 0, 0, 255).next();
        buffer.vertex(this.tabsBounds.getMinX() + 20.0D, this.tabsBounds.getMinY(), 0.0D).texture(0.0D, 0.0D).color(0, 0, 0, 255).next();
        tessellator.draw();
        buffer.begin(7, VertexFormats.POSITION_UV_COLOR);
        buffer.vertex(this.tabsBounds.getMinX() + 20.0D, this.tabsBounds.getMaxY(), 0.0D).texture(0.0D, 1.0D).color(0, 0, 0, 255).next();
        buffer.vertex(this.tabsBounds.getMaxX() - 20.0D, this.tabsBounds.getMaxY(), 0.0D).texture(1.0D, 1.0D).color(0, 0, 0, 255).next();
        buffer.vertex(this.tabsBounds.getMaxX() - 20.0D, this.tabsBounds.getMaxY() - 4.0D, 0.0D).texture(1.0D, 0.0D).color(0, 0, 0, 0).next();
        buffer.vertex(this.tabsBounds.getMinX() + 20.0D, this.tabsBounds.getMaxY() - 4.0D, 0.0D).texture(0.0D, 0.0D).color(0, 0, 0, 0).next();
        tessellator.draw();
        GlStateManager.enableTexture();
        GlStateManager.shadeModel(7424);
        GlStateManager.enableAlphaTest();
        GlStateManager.disableBlend();
    }

    protected void overlayBackground(Rectangle rect, int red, int green, int blue, int startAlpha, int endAlpha) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBufferBuilder();
        Objects.requireNonNull(this.minecraft).getTextureManager().bindTexture(DrawableHelper.BACKGROUND_LOCATION);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        float f = 32.0F;
        buffer.begin(7, VertexFormats.POSITION_UV_COLOR);
        buffer.vertex(rect.getMinX(), rect.getMaxY(), 0.0D).texture(rect.getMinX() / 32.0D, rect.getMaxY() / 32.0D).color(red, green, blue, endAlpha).next();
        buffer.vertex(rect.getMaxX(), rect.getMaxY(), 0.0D).texture(rect.getMaxX() / 32.0D, rect.getMaxY() / 32.0D).color(red, green, blue, endAlpha).next();
        buffer.vertex(rect.getMaxX(), rect.getMinY(), 0.0D).texture(rect.getMaxX() / 32.0D, rect.getMinY() / 32.0D).color(red, green, blue, startAlpha).next();
        buffer.vertex(rect.getMinX(), rect.getMinY(), 0.0D).texture(rect.getMinX() / 32.0D, rect.getMinY() / 32.0D).color(red, green, blue, startAlpha).next();
        tessellator.draw();
    }

    public boolean keyPressed(int int_1, int int_2, int int_3) {
        if (int_1 == 256 && this.shouldCloseOnEsc()) {
            if (this.confirmSave && this.edited) {
                Objects.requireNonNull(this.minecraft).openScreen(new YesNoScreen(t -> {
                    if (!t) {
                        this.minecraft.openScreen(this);
                    } else {
                        this.minecraft.openScreen(this.parent);
                    }
                }, new TranslatableTextComponent("text.cloth-config.quit_config"), new TranslatableTextComponent("text.cloth-config.quit_config_sure"), I18n.translate("text.cloth-config.quit_discard"), I18n.translate("gui.cancel")));
            } else {
                Objects.requireNonNull(this.minecraft).openScreen(this.parent);
            }

            return true;
        } else {
            return super.keyPressed(int_1, int_2, int_3);
        }
    }

    public abstract void onSave(Map<String, List<Pair<String, Object>>> var1);

    public class ListWidget extends ElementListWidget {
        ListWidget(MinecraftClient client, int int_1, int int_2, int int_3, int int_4, int int_5) {
            super(client, int_1, int_2, int_3, int_4, int_5);
            this.visible = false;
        }

        public int getItemWidth() {
            return this.width - 80;
        }

        public ClothConfigScreen getScreen() {
            return ClothConfigScreen.this;
        }

        protected int getScrollbarPosition() {
            return this.width - 36;
        }

        protected final void clearStuff() {
            this.clearItems();
        }
    }

    public abstract static class AbstractListEntry extends ElementItem<ClothConfigScreen.AbstractListEntry> {
        private ClothConfigScreen screen;

        AbstractListEntry() {
        }

        public abstract String getFieldName();

        public abstract Object getObject();

        public Optional<String> getError() {
            return Optional.empty();
        }

        public abstract Optional<Object> getDefaultValue();

        public final ClothConfigScreen.ListWidget getParent() {
            return this.screen.listWidget;
        }

        public final ClothConfigScreen getScreen() {
            return this.screen;
        }

        public void save() {
        }
    }

    public abstract static class ListEntry extends ClothConfigScreen.AbstractListEntry {
        private String fieldName;

        public ListEntry(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getFieldName() {
            return this.fieldName;
        }
    }

    public static class Builder implements ConfigScreenBuilder {
        private Screen parentScreen;
        private Map<String, List<Pair<String, Object>>> dataMap;
        private String title;
        private Consumer<ConfigScreenBuilder.SavedConfig> onSave;
        private boolean confirmSave;
        private boolean displayErrors;

        public Builder(Screen parentScreen, String title, Consumer<ConfigScreenBuilder.SavedConfig> onSave) {
            this.parentScreen = parentScreen;
            this.title = I18n.translate(title);
            this.dataMap = Maps.newLinkedHashMap();
            this.onSave = onSave;
            this.confirmSave = true;
            this.displayErrors = true;
        }

        public Screen getParentScreen() {
            return this.parentScreen;
        }

        public void setParentScreen(Screen parent) {
            this.parentScreen = parent;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String title) {
            this.title = title == null ? "" : title;
        }

        public Consumer<ConfigScreenBuilder.SavedConfig> getOnSave() {
            return this.onSave;
        }

        public void setOnSave(Consumer<ConfigScreenBuilder.SavedConfig> onSave) {
            this.onSave = onSave;
        }

        public List<String> getCategories() {
            return Collections.unmodifiableList(Lists.newArrayList(this.dataMap.keySet()));
        }

        public boolean hasCategory(String category) {
            return this.getCategories().contains(category);
        }

        public CategoryBuilder addCategory(String category) {
            if (this.hasCategory(category)) {
                throw new IllegalArgumentException("The category is already added!");
            } else {
                this.dataMap.put(category, Lists.newLinkedList());
                return this.getCategory(category);
            }
        }

        public CategoryBuilder getCategory(String category) {
            if (!this.hasCategory(category)) {
                throw new IllegalArgumentException("This category doesn't exist!");
            } else {
                return new ClothConfigScreen.Builder.Category(category, this);
            }
        }

        public void removeCategory(String category) {
            if (!this.hasCategory(category)) {
                throw new IllegalArgumentException("The category doesn't exist!");
            } else {
                this.dataMap.remove(category);
            }
        }

        public void addOption(String category, String key, Object object) {
            if (!this.hasCategory(category)) {
                throw new IllegalArgumentException("The category doesn't exist!");
            } else {
                this.dataMap.get(category).add(new Pair<>(key, object));
            }
        }

        public void addOption(String category, ClothConfigScreen.AbstractListEntry entry) {
            if (!this.hasCategory(category)) {
                throw new IllegalArgumentException("The category doesn't exist!");
            } else {
                this.dataMap.get(category).add(new Pair<>(entry.getFieldName(), entry));
            }
        }

        public List<Pair<String, Object>> getOptions(String category) {
            if (!this.hasCategory(category)) {
                throw new IllegalArgumentException("The category doesn't exist!");
            } else {
                return Collections.unmodifiableList(this.dataMap.get(category));
            }
        }

        public void setDoesConfirmSave(boolean confirmSave) {
            this.confirmSave = confirmSave;
        }

        public boolean doesConfirmSave() {
            return this.confirmSave;
        }

        /** @deprecated */
        @Deprecated
        public Map<String, List<Pair<String, Object>>> getDataMap() {
            return this.dataMap;
        }

        public void setShouldProcessErrors(boolean processErrors) {
            this.displayErrors = processErrors;
        }

        public boolean shouldProcessErrors() {
            return this.displayErrors;
        }

        public ClothConfigScreen build() {
            return new ClothConfigScreen(this.parentScreen, this.title, this.dataMap, this.confirmSave, this.displayErrors) {
                public void onSave(Map<String, List<Pair<String, Object>>> o) {
                    if (Builder.this.getOnSave() != null) {
                        Builder.this.getOnSave().accept(new ClothConfigScreen.Builder.SavedConfig(o));
                    }

                }
            };
        }

        public static class SavedOption implements ConfigScreenBuilder.SavedOption {
            private String key;
            private Object value;

            SavedOption(String key, Object value) {
                this.key = key;
                this.value = value;
            }

            public String getFieldKey() {
                return this.key;
            }

            public Object getValue() {
                return this.value;
            }
        }

        public static class SavedCategory implements ConfigScreenBuilder.SavedCategory {
            private ClothConfigScreen.Builder.SavedConfig savedConfig;
            private String category;
            private List<ConfigScreenBuilder.SavedOption> options;

            SavedCategory(ClothConfigScreen.Builder.SavedConfig savedConfig, String category) {
                this.savedConfig = savedConfig;
                this.category = category;
                this.options = this.getOptionPairs().stream().map((pair) -> new SavedOption(pair.getLeft(), pair.getRight())).collect(Collectors.toList());
            }

            public boolean exists() {
                return this.savedConfig.map.containsKey(this.category);
            }

            public String getName() {
                return this.category;
            }

            public List<Pair<String, Object>> getOptionPairs() {
                return this.savedConfig.map.getOrDefault(this.category, Collections.emptyList());
            }

            public List<ConfigScreenBuilder.SavedOption> getOptions() {
                return this.options;
            }

            public Optional<ConfigScreenBuilder.SavedOption> getOption(String fieldKey) {
                return this.options.stream().filter((savedOption) -> {
                    return savedOption.getFieldKey().equals(fieldKey);
                }).findAny();
            }
        }

        public static class SavedConfig implements ConfigScreenBuilder.SavedConfig {
            private Map<String, List<Pair<String, Object>>> map;
            private Map<String, ClothConfigScreen.Builder.SavedCategory> categories;

            SavedConfig(Map<String, List<Pair<String, Object>>> map) {
                this.map = map;
                this.categories = Maps.newLinkedHashMap();
                map.forEach((s, pairs) -> {
                    ClothConfigScreen.Builder.SavedCategory var10000 = this.categories.put(s, new SavedCategory(this, s));
                });
            }

            public boolean containsCategory(String category) {
                return this.categories.containsKey(category);
            }

            public ConfigScreenBuilder.SavedCategory getCategory(String category) {
                return this.categories.getOrDefault(category, new SavedCategory(this, category));
            }

            public List<ConfigScreenBuilder.SavedCategory> getCategories() {
                return Lists.newArrayList(this.categories.values());
            }
        }

        public static class Category implements CategoryBuilder {
            private String category;
            private ConfigScreenBuilder builder;

            Category(String category, ConfigScreenBuilder builder) {
                this.category = category;
                this.builder = builder;
            }

            public List<Pair<String, Object>> getOptions() {
                return this.builder.getOptions(this.category);
            }

            public CategoryBuilder addOption(ClothConfigScreen.AbstractListEntry entry) {
                this.builder.addOption(this.category, entry);
                return this;
            }

            /** @deprecated */
            @Deprecated
            public CategoryBuilder addOption(String key, Object object) {
                this.builder.addOption(this.category, key, object);
                return this;
            }

            public ConfigScreenBuilder removeFromParent() {
                this.builder.removeCategory(this.category);
                return this.builder;
            }

            public ConfigScreenBuilder parent() {
                return this.builder;
            }

            public String getName() {
                return this.category;
            }

            public boolean exists() {
                return this.builder.hasCategory(this.category);
            }
        }
    }
}
