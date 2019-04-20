//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.hollow.neutronia.api;

import net.minecraft.client.gui.Screen;
import net.minecraft.util.Pair;
import team.hollow.neutronia.client.gui.ClothConfigScreen;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public interface ConfigScreenBuilder {
    static ConfigScreenBuilder create(Screen parentScreen, String title, Consumer<ConfigScreenBuilder.SavedConfig> onSave) {
        return new ClothConfigScreen.Builder(parentScreen, title, onSave);
    }

    static ConfigScreenBuilder create() {
        return create((Screen)null, "text.cloth-config.config", (Consumer)null);
    }

    String getTitle();

    void setTitle(String var1);

    Screen getParentScreen();

    void setParentScreen(Screen var1);

    Consumer<ConfigScreenBuilder.SavedConfig> getOnSave();

    void setOnSave(Consumer<ConfigScreenBuilder.SavedConfig> var1);

    ClothConfigScreen build();

    Screen build(Consumer<ClothConfigScreen> afterInitConsumer);

    List<String> getCategories();

    ConfigScreenBuilder.CategoryBuilder addCategory(String var1);

    ConfigScreenBuilder.CategoryBuilder getCategory(String var1);

    default void addCategories(String... categories) {
        String[] var2 = categories;
        int var3 = categories.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String category = var2[var4];
            this.addCategory(category);
        }

    }

    void removeCategory(String var1);

    default void removeCategories(String... categories) {
        String[] var2 = categories;
        int var3 = categories.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String category = var2[var4];
            this.removeCategory(category);
        }

    }

    boolean hasCategory(String var1);

    void addOption(String var1, String var2, Object var3);

    void addOption(String var1, ClothConfigScreen.AbstractListEntry var2);

    /** @deprecated */
    @Deprecated
    List<Pair<String, Object>> getOptions(String var1);

    void setDoesConfirmSave(boolean var1);

    boolean doesConfirmSave();

    void setShouldProcessErrors(boolean var1);

    boolean shouldProcessErrors();

    /** @deprecated */
    @Deprecated
    Map<String, List<Pair<String, Object>>> getDataMap();

    public interface SavedOption {
        String getFieldKey();

        Object getValue();
    }

    public interface SavedCategory {
        boolean exists();

        String getName();

        /** @deprecated */
        @Deprecated
        List<Pair<String, Object>> getOptionPairs();

        List<ConfigScreenBuilder.SavedOption> getOptions();

        Optional<ConfigScreenBuilder.SavedOption> getOption(String var1);
    }

    public interface SavedConfig {
        boolean containsCategory(String var1);

        ConfigScreenBuilder.SavedCategory getCategory(String var1);

        List<ConfigScreenBuilder.SavedCategory> getCategories();
    }

    public interface CategoryBuilder {
        /** @deprecated */
        @Deprecated
        List<Pair<String, Object>> getOptions();

        ConfigScreenBuilder.CategoryBuilder addOption(ClothConfigScreen.AbstractListEntry var1);

        /** @deprecated */
        @Deprecated
        ConfigScreenBuilder.CategoryBuilder addOption(String var1, Object var2);

        ConfigScreenBuilder removeFromParent();

        ConfigScreenBuilder parent();

        String getName();

        boolean exists();
    }
}
