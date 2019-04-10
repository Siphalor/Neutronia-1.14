package team.hollow.neutronia.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

public class SerializationUtil {

    public static final Gson RAW_GSON = new Gson();
    public static final Gson PRETTY_GSON = new GsonBuilder().setPrettyPrinting().create();

    public static <T> T loadFromFile(File f, Class<? extends T> clazz, Supplier<T> baseCase) {
        return loadFromFile(RAW_GSON, f, clazz, baseCase);
    }

    public static <T> T loadFromFile(Gson gson, File f, Class<? extends T> clazz, Supplier<T> baseCase) {
        try {
            if (!f.exists()) {
                T t = baseCase.get();
                saveToFile(gson, f, clazz, t);
                return t;
            }

            FileInputStream in = new FileInputStream(f);
            return gson.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> void saveToFile(File f, Class<? extends T> clazz, T obj) {
        saveToFile(RAW_GSON, f, clazz, obj);
    }

    public static <T> void saveToFile(Gson gson, File f, Class<? extends T> clazz, T obj) {
        String json = gson.toJson(obj, clazz);
        try {
            if (!f.exists())
                f.createNewFile();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}