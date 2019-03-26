//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.hollow.neutronia.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Supplier;

public class JsonConfig<T> {
    private static final Gson DEFAULT_GSON = (new GsonBuilder()).setPrettyPrinting().create();
    private final File configFile;
    private final JsonConfig.CachedSupplier<T> configGetter;
    private Gson gson;

    public JsonConfig(String fileName, Class<T> configClass, Supplier<T> defaultFactory) {
        this.gson = DEFAULT_GSON;
        this.configFile = new File(FabricLoader.INSTANCE.getConfigDirectory(), fileName + (fileName.endsWith(".json") ? "" : ".json"));
        this.configGetter = new JsonConfig.CachedSupplier(() -> {
            if (!this.configFile.exists()) {
                T def = defaultFactory.get();
                this.write(def, false);
                return def;
            } else {
                try {
                    FileReader reader = new FileReader(this.configFile);
                    Throwable var4 = null;

                    Object var5;
                    try {
                        var5 = this.gson.fromJson(reader, configClass);
                    } catch (Throwable var15) {
                        var4 = var15;
                        throw var15;
                    } finally {
                        if (reader != null) {
                            if (var4 != null) {
                                try {
                                    reader.close();
                                } catch (Throwable var14) {
                                    var4.addSuppressed(var14);
                                }
                            } else {
                                reader.close();
                            }
                        }

                    }

                    return var5;
                } catch (IOException var17) {
                    var17.printStackTrace();
                    return defaultFactory.get();
                }
            }
        });
    }

    public JsonConfig(String fileName, Class<T> configClass) {
        this(fileName, configClass, () -> {
            try {
                return configClass.newInstance();
            } catch (IllegalAccessException | InstantiationException var2) {
                throw new RuntimeException("Failed to create new config instance", var2);
            }
        });
    }

    public JsonConfig<T> withGson(Gson gson) {
        this.gson = gson;
        return this;
    }

    public T get() {
        return this.configGetter.get();
    }

    public void save() {
        this.write(this.get(), false);
    }

    public void write(T t, boolean invalidate) {
        try {
            FileWriter writer = new FileWriter(this.configFile);
            Throwable var4 = null;

            try {
                writer.write(this.gson.toJson(t));
                if (invalidate) {
                    this.invalidate();
                }
            } catch (Throwable var14) {
                var4 = var14;
                throw var14;
            } finally {
                if (writer != null) {
                    if (var4 != null) {
                        try {
                            writer.close();
                        } catch (Throwable var13) {
                            var4.addSuppressed(var13);
                        }
                    } else {
                        writer.close();
                    }
                }

            }
        } catch (IOException var16) {
            var16.printStackTrace();
        }

    }

    public void invalidate() {
        this.configGetter.invalidate();
    }

    static class CachedSupplier<T> {
        private final Supplier<T> supplier;
        private T value;

        public CachedSupplier(Supplier<T> supplier) {
            this.supplier = supplier;
        }

        public T get() {
            return this.value == null ? (this.value = this.supplier.get()) : this.value;
        }

        public void invalidate() {
            this.value = null;
        }
    }
}
