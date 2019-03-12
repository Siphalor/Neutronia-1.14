package generators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import net.minecraft.util.Identifier;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.text.WordUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ModelGenerator {

    public static void genStair(Identifier modIdAndName, Identifier bottomTexture, Identifier topTexture, Identifier sideTexture) {

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        String baseFile = JsonTemplates.STAIRS.replace("modid", modIdAndName.getNamespace())
                .replace("block_model", modIdAndName.getPath());

        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), baseFile, CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", modIdAndName.getPath());
        }

        genStairBlockJson(modIdAndName, bottomTexture, topTexture, sideTexture);
        genStairItemModel(modIdAndName);
    }

    public static void genStairBlockJson(Identifier modIdAndName, Identifier bottomTexture, Identifier topTexture, Identifier sideTexture) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "models", "block");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject textures = new JsonObject();
        textures.addProperty("bottom", String.format("%s:block/%s", bottomTexture.getNamespace(), bottomTexture.getPath()));
        textures.addProperty("top", String.format("%s:block/%s", topTexture.getNamespace(), topTexture.getPath()));
        textures.addProperty("side", String.format("%s:block/%s", sideTexture.getNamespace(), sideTexture.getPath()));

        JsonObject root = new JsonObject();
        root.addProperty("parent", "block/stairs");
        root.add("textures", textures);

        String json = gson.toJson(root);

        JsonObject root2 = new JsonObject();
        root2.addProperty("parent", "block/inner_stairs");
        root2.add("textures", textures);

        String json2 = gson.toJson(root2);

        JsonObject root3 = new JsonObject();
        root3.addProperty("parent", "block/outer_stairs");
        root3.add("textures", textures);

        String json3 = gson.toJson(root3);

        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + "_inner.json").toFile(), StringEscapeUtils.unescapeJson(json2), CharEncoding.UTF_8);
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + "_outer.json").toFile(), StringEscapeUtils.unescapeJson(json3), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", modIdAndName.getPath()));
        }

    }

    public static void genStairItemModel(Identifier modIdAndName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "models", "item");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("parent", String.format("%s:block/%s", modIdAndName.getNamespace(), modIdAndName.getPath()));

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", modIdAndName.getPath()));
        }

    }

    public static void genBlock(Identifier modIdAndName, Identifier textureName) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();

        JsonObject variants = new JsonObject();

        JsonObject model = new JsonObject();
        model.addProperty("model", String.format("%s:block/%s", modIdAndName.getNamespace(), modIdAndName.getPath()));

        variants.add("", model);
        root.add("variants", variants);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", modIdAndName.getPath());
        }

        genBlockModel(modIdAndName, textureName);
        genBlockItemModel(modIdAndName);
    }

    public static void genBlockModel(Identifier modIdAndName, Identifier textureName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "models", "block");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("parent", "block/cube_all");

        JsonObject textures = new JsonObject();
        textures.addProperty("all", textureName.getNamespace() + ":block/" + textureName.getPath());
        root.add("textures", textures);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", modIdAndName.getPath()));
        }

    }

    public static void genBlockItemModel(Identifier modIdAndName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "models", "item");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("parent", String.format("%s:block/%s", modIdAndName.getNamespace(), modIdAndName.getPath()));

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", modIdAndName.getPath()));
        }

    }

    public static void genCustomBlock(Identifier modIdAndName, Identifier modelPath) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("forge_marker", 1);

        JsonObject defaults = new JsonObject();
        defaults.addProperty("model", modelPath.getNamespace() + ":" + modelPath.getPath());

        defaults.addProperty("transform", "forge:default-block");
        root.add("defaults", defaults);

        JsonObject variants = new JsonObject();

        JsonArray empty = new JsonArray();
        empty.add(new JsonObject());

        variants.add("normal", empty);
        variants.add("inventory", empty);
        root.add("variants", variants);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", modIdAndName.getPath());
        }
    }

    public static void genCustomBlockWithTexture(Identifier modIdAndName, Identifier modelPath, Identifier textureLocation) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("forge_marker", 1);

        JsonObject defaults = new JsonObject();
        defaults.addProperty("model", modelPath.getNamespace() + ":" + modelPath.getPath());

        JsonObject textures = new JsonObject();
        textures.addProperty("all", textureLocation.getNamespace() + ":block/" + textureLocation.getPath());
        defaults.add("textures", textures);

        defaults.addProperty("transform", "forge:default-block");
        root.add("defaults", defaults);

        JsonObject variants = new JsonObject();

        JsonArray empty = new JsonArray();
        empty.add(new JsonObject());

        variants.add("normal", empty);
        variants.add("inventory", empty);
        root.add("variants", variants);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", modIdAndName.getPath());
        }
    }

    public static void genPlant(String modId, String blockName, String textureName) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modId, "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("forge_marker", 1);

        JsonObject defaults = new JsonObject();
        defaults.addProperty("model", modId + ":" + blockName);
        defaults.addProperty("transform", "forge:default-block");
        root.add("defaults", defaults);

        JsonObject variants = new JsonObject();

        JsonArray empty = new JsonArray();
        empty.add(new JsonObject());

        variants.add("normal", empty);
        variants.add("inventory", empty);
        root.add("variants", variants);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(blockName + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", blockName);
        }

        genPlantBlockModel(modId, blockName, textureName);
        genPlantItemModel(modId, blockName, textureName);
    }

    public static void genPlantBlockModel(String modId, String blockName, String textureName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modId, "models", "block");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("parent", "block/cross");

        JsonObject textures = new JsonObject();
        textures.addProperty("cross", modId + ":block/" + textureName);
        root.add("textures", textures);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(blockName + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", blockName);
        }

    }

    public static void genPlantItemModel(String modId, String blockName, String textureName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modId, "models", "item");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("parent", "item/generated");

        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", modId + ":block/" + textureName);
        root.add("textures", textures);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(blockName + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", blockName);
        }

    }

    public static void genOrientedBlock(Identifier modIdAndName, Identifier topTextureName, Identifier frontTextureName, Identifier sidesTextureName) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();

        JsonObject variants = new JsonObject();

        JsonObject north = new JsonObject();
        north.addProperty("model", String.format("%s:block/%s", modIdAndName.getNamespace(), modIdAndName.getPath()));
        variants.add("facing=north", north);

        JsonObject south = new JsonObject();
        south.addProperty("model", String.format("%s:block/%s", modIdAndName.getNamespace(), modIdAndName.getPath()));
        south.addProperty("y", 90);
        variants.add("facing=south", south);

        JsonObject east = new JsonObject();
        east.addProperty("model", String.format("%s:block/%s", modIdAndName.getNamespace(), modIdAndName.getPath()));
        east.addProperty("y", 180);
        variants.add("facing=east", east);

        JsonObject west = new JsonObject();
        west.addProperty("model", String.format("%s:block/%s", modIdAndName.getNamespace(), modIdAndName.getPath()));
        west.addProperty("y", 270);
        variants.add("facing=west", west);

        root.add("variants", variants);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", modIdAndName.getPath());
        }

        genBlockOrientedBlockModel(modIdAndName, topTextureName, frontTextureName, sidesTextureName);
        genBlockOrientedItemModel(modIdAndName);
    }

    public static void genBlockOrientedBlockModel(Identifier modIdAndName, Identifier topTextureName, Identifier frontTextureName, Identifier sidesTextureName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "models", "block");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }
        JsonObject root = new JsonObject();
        root.addProperty("parent", "block/orientable");
        JsonObject textures = new JsonObject();
        textures.addProperty("top", topTextureName.getNamespace() + ":block/" + topTextureName.getPath());
        textures.addProperty("front", frontTextureName.getNamespace() + ":block/" + frontTextureName.getPath());
        textures.addProperty("side", sidesTextureName.getNamespace() + ":block/" + sidesTextureName.getPath());
        root.add("textures", textures);
        String json = gson.toJson(root);
        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", modIdAndName.getPath());
        }
    }

    public static void genBlockOrientedItemModel(Identifier modIdAndName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "models", "item");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("parent", String.format("%s:block/%s", modIdAndName.getNamespace(), modIdAndName.getPath()));

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", modIdAndName.getPath()));
        }
    }

    public static void genPillarBlock(Identifier modIdAndName, Identifier endTextureName, Identifier sidesTextureName) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();

        JsonArray empty = new JsonArray();
        empty.add(new JsonObject());

        JsonObject variants = new JsonObject();

        JsonObject axis = new JsonObject();

        JsonObject model = new JsonObject();
        model.addProperty("model", String.format("%s:block/%s_pillar", modIdAndName.getNamespace(), modIdAndName.getPath()));

        JsonObject axisX = new JsonObject();
        axisX.add("model", model);
        axisX.addProperty("x", 90);
        axisX.addProperty("y", 90);
        axis.add("x", axisX);

        axis.add("y", empty);

        JsonObject axisZ = new JsonObject();
        axisZ.addProperty("x", 90);
        axis.add("z", axisZ);

        variants.add("axis", axis);

        variants.add("inventory", empty);
        root.add("variants", variants);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", modIdAndName.getPath());
        }
    }

    public static void genOrientedBlock(String modId, String blockName, String topTextureName, String frontTextureName, String sidesTextureName) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modId, "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v3.");
        root.addProperty("forge_marker", 1);

        JsonObject defaults = new JsonObject();
        defaults.addProperty("model", modId + ":" + blockName);
        defaults.addProperty("transform", "forge:default-block");
        root.add("defaults", defaults);

        JsonObject variants = new JsonObject();

        JsonObject facing = new JsonObject();
        facing.add("north", new JsonObject());

        JsonObject south = new JsonObject();
        south.addProperty("y", "90");
        facing.add("south", south);

        JsonObject east = new JsonObject();
        east.addProperty("y", "180");
        facing.add("east", east);

        JsonObject west = new JsonObject();
        west.addProperty("y", "270");
        facing.add("west", west);

        variants.add("facing", facing);

        JsonArray empty = new JsonArray();
        empty.add(new JsonObject());

        variants.add("inventory", empty);
        root.add("variants", variants);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(blockName + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", blockName));
        }

        genBlockOrientedModel(modId, blockName, topTextureName, frontTextureName, sidesTextureName);
        genBlockOrientedItemModel(modId, blockName);
    }

    public static void genBlockOrientedModel(String modId, String blockName, String topTextureName, String frontTextureName, String sidesTextureName) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modId, "models", "block");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("parent", "block/orientable");

        JsonObject textures = new JsonObject();
        textures.addProperty("top", modId + ":block/" + topTextureName);
        textures.addProperty("front", modId + ":block/" + frontTextureName);
        textures.addProperty("side", modId + ":block/" + sidesTextureName);
        root.add("textures", textures);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(blockName + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", blockName));
        }

    }

    public static void genBlockOrientedItemModel(String modId, String blockName) {

        File fileDir = Paths.get("src", "main", "resources", "assets", modId, "models", "item").toFile();
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        try {

            Writer writer = new OutputStreamWriter(new FileOutputStream(fileDir + "\\" + blockName + ".json"), StandardCharsets.UTF_8);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonWriter jw = gson.newJsonWriter(writer);

            jw.beginObject();

            jw.name("parent").value(modId + ":block/" + blockName);

            jw.endObject();

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void genStair(String modId, String blockName, String topTexture, String sideTexture, String bottomTexture) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modId, "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonArray empty = new JsonArray();
        empty.add(new JsonObject());

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v3.");
        root.addProperty("forge_marker", 1);

        JsonObject defaults = new JsonObject();

        JsonObject textures = new JsonObject();
        textures.addProperty("bottom", modId + ":blocks/" + bottomTexture);
        textures.addProperty("side", modId + ":blocks/" + sideTexture);
        textures.addProperty("top", modId + ":blocks/" + topTexture);

        defaults.add("textures", textures);
        defaults.addProperty("transform", "forge:default-block");
        root.add("defaults", defaults);

        JsonObject variants = new JsonObject();

        JsonObject straight = new JsonObject();
        straight.addProperty("model", "minecraft:stairs");
        straight.addProperty("y", 270);
        straight.addProperty("uvlock", true);

        JsonObject innerLeft = new JsonObject();
        innerLeft.addProperty("model", "minecraft:inner_stairs");
        innerLeft.addProperty("y", 270);
        innerLeft.addProperty("uvlock", true);

        JsonObject innerRight = new JsonObject();
        innerRight.addProperty("model", "minecraft:inner_stairs");
        innerRight.addProperty("y", 270);
        innerRight.addProperty("uvlock", true);

        JsonObject outerLeft = new JsonObject();
        innerLeft.addProperty("model", "minecraft:outer_stairs");
        innerLeft.addProperty("y", 270);
        innerLeft.addProperty("uvlock", true);

        JsonObject outerRight = new JsonObject();
        innerRight.addProperty("model", "minecraft:outer_stairs");
        innerRight.addProperty("y", 270);
        innerRight.addProperty("uvlock", true);

        variants.add("inventory", empty);
        root.add("variants", variants);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(blockName + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", blockName));
        }
        genStairItemModel(modId, blockName, topTexture, sideTexture, bottomTexture);
    }

    public static void genStairItemModel(String modId, String blockName, String topTexture, String sideTexture, String bottomTexture) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modId, "models", "item");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v3.");
        root.addProperty("parent", "block/stairs");

        JsonObject textures = new JsonObject();
        textures.addProperty("bottom", modId + ":blocks/" + bottomTexture);
        textures.addProperty("side", modId + ":blocks/" + sideTexture);
        textures.addProperty("top", modId + ":blocks/" + topTexture);
        root.add("textures", textures);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(blockName + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", blockName));
        }

    }

    public static void genPressurePlateBlock(String modId, String blockName, String textureName) {

        File fileDir = Paths.get("src", "main", "resources", "assets", modId, "blockstates").toFile();
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        try {

            Writer writer = new OutputStreamWriter(new FileOutputStream(fileDir + "\\" + blockName + ".json"), StandardCharsets.UTF_8);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonWriter jw = gson.newJsonWriter(writer);

            jw.beginObject();
            jw.name("_comment").value("Generated using Husky's JSON Generator v4.");
            jw.name("variants");
            jw.beginObject();

            jw.name("powered=false");
            jw.beginObject();
            jw.name("model").value(modId + ":" + blockName + "_up");
            jw.endObject();

            jw.name("powered=true");
            jw.beginObject();
            jw.name("model").value(modId + ":" + blockName + "_down");
            jw.endObject();

            jw.endObject();
            jw.endObject();

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        genBlockPressurePlateModel(modId, blockName, textureName);
        genBlockPressurePlateItemModel(modId, blockName);

    }

    public static void genBlockPressurePlateModel(String modId, String blockName, String textureName) {

        File fileDir = Paths.get("src", "main", "resources", "assets", modId, "models", "block").toFile();
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        try {

            Writer writer = new OutputStreamWriter(new FileOutputStream(fileDir + "\\" + blockName + "_up" + ".json"), StandardCharsets.UTF_8);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonWriter jw = gson.newJsonWriter(writer);

            jw.beginObject();
            jw.name("_comment").value("Generated using Husky's JSON Generator v4.");
            jw.name("parent").value("block/pressure_plate_up");
            jw.name("textures");
            jw.beginObject();
            jw.name("texture").value(modId + ":block/" + textureName);
            jw.endObject();
            jw.endObject();

            writer.close();

            Writer writer2 = new OutputStreamWriter(new FileOutputStream(fileDir + "\\" + blockName + "_down" + ".json"), StandardCharsets.UTF_8);
            JsonWriter jw2 = gson.newJsonWriter(writer2);

            jw2.beginObject();
            jw2.name("_comment").value("Generated using Husky's JSON Generator v4.");
            jw2.name("parent").value("block/pressure_plate_down");
            jw2.name("textures");
            jw2.beginObject();
            jw2.name("texture").value(modId + ":block/" + textureName);
            jw2.endObject();
            jw2.endObject();

            writer2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void genBlockPressurePlateItemModel(String modId, String blockName) {

        File fileDir = Paths.get("src", "main", "resources", "assets", modId, "models", "item").toFile();
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        try {

            Writer writer = new OutputStreamWriter(new FileOutputStream(fileDir + "\\" + blockName + ".json"), StandardCharsets.UTF_8);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonWriter jw = gson.newJsonWriter(writer);

            jw.beginObject();

            jw.name("_comment").value("Generated using Husky's JSON Generator v4.");
            jw.name("parent").value(modId + ":block/" + blockName + "_up");

            jw.endObject();

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void genSlab(Identifier modIDAndName, Identifier fullBlock, Identifier topTextureLocation, Identifier sideTextureLocation, Identifier bottomTextureLocation) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIDAndName.getNamespace(), "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();

        JsonObject variants = new JsonObject();

        JsonObject bottom = new JsonObject();
        bottom.addProperty("model", String.format("%s:block/%s", modIDAndName.getNamespace(), modIDAndName.getPath()));
        variants.add("type=bottom", bottom);

        JsonObject top = new JsonObject();
        top.addProperty("model", String.format("%s:block/%s", modIDAndName.getNamespace(), modIDAndName.getPath()));
        variants.add("type=top", top);

        JsonObject doubleSlab = new JsonObject();
        doubleSlab.addProperty("model", String.format("%s:block/%s", fullBlock.getNamespace(), fullBlock.getPath()));
        variants.add("type=double", doubleSlab);

        root.add("variants", variants);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(modIDAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", modIDAndName.getPath());
        }

        genSlabBlockModel(modIDAndName, topTextureLocation, sideTextureLocation, bottomTextureLocation);
        genSlabItemModel(modIDAndName.getNamespace(), modIDAndName.getPath());
    }

    public static void genSlabColored(Identifier modIDAndName, Identifier topTextureLocation, Identifier sideTextureLocation, Identifier bottomTextureLocation) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIDAndName.getNamespace(), "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("forge_marker", 1);

        JsonObject variants = new JsonObject();

        JsonObject half = new JsonObject();

        JsonObject upper = new JsonObject();
        upper.addProperty("model", modIDAndName.getNamespace() + ":upper_" + modIDAndName.getPath());
        half.add("top", upper);

        JsonObject lower = new JsonObject();
        lower.addProperty("model", modIDAndName.getNamespace() + ":half_" + modIDAndName.getPath());
        half.add("bottom", lower);

        variants.add("half", half);

        root.add("variants", variants);

        String json = gson.toJson(root);

        JsonObject root2 = new JsonObject();
        root2.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root2.addProperty("forge_marker", 1);

        JsonObject variants2 = new JsonObject();

        JsonObject prop = new JsonObject();

        JsonObject blarg = new JsonObject();
        blarg.addProperty("model", "neutronia_legacy:cube_all_colored");

        JsonObject textures = new JsonObject();
        textures.addProperty("all", sideTextureLocation.toString());

        blarg.add("textures", textures);

        prop.add("blarg", blarg);

        variants2.add("prop", prop);

        root2.add("variants", variants2);

        String json2 = gson.toJson(root2);

        try {
            FileUtils.writeStringToFile(base.resolve(modIDAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
            FileUtils.writeStringToFile(base.resolve(modIDAndName.getPath() + "_double.json").toFile(), StringEscapeUtils.unescapeJson(json2), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", modIDAndName.getPath());
        }

        genSlabBlockModel(modIDAndName, topTextureLocation, sideTextureLocation, bottomTextureLocation);
        genSlabItemModel(modIDAndName.getNamespace(), modIDAndName.getPath());

    }

    public static void genSlabBlockModel(Identifier modIDAndName, Identifier topTextureLocation, Identifier sideTextureLocation, Identifier bottomTextureLocation) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIDAndName.getNamespace(), "models", "block");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("parent", "minecraft:block/slab");

        JsonObject textures = new JsonObject();
        textures.addProperty("bottom", String.format("%s:block/%s", bottomTextureLocation.getNamespace(), bottomTextureLocation.getPath()));
        textures.addProperty("side", String.format("%s:block/%s", sideTextureLocation.getNamespace(), sideTextureLocation.getPath()));
        textures.addProperty("top", String.format("%s:block/%s", topTextureLocation.getNamespace(), topTextureLocation.getPath()));
        root.add("textures", textures);

        String json = gson.toJson(root);

        JsonObject root2 = new JsonObject();
        root2.addProperty("parent", "minecraft:block/slab_top");

        JsonObject textures2 = new JsonObject();
        textures2.addProperty("bottom", String.format("%s:block/%s", bottomTextureLocation.getNamespace(), bottomTextureLocation.getPath()));
        textures2.addProperty("side", String.format("%s:block/%s", sideTextureLocation.getNamespace(), sideTextureLocation.getPath()));
        textures2.addProperty("top", String.format("%s:block/%s", topTextureLocation.getNamespace(), topTextureLocation.getPath()));
        root2.add("textures", textures2);

        String json2 = gson.toJson(root2);

        try {
            FileUtils.writeStringToFile(base.resolve(modIDAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
            FileUtils.writeStringToFile(base.resolve(modIDAndName.getPath() + "_top" + ".json").toFile(), StringEscapeUtils.unescapeJson(json2), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", modIDAndName.getPath());
        }

    }

    public static void genSlabColoredBlockModel(Identifier modIDAndName, Identifier topTextureLocation, Identifier sideTextureLocation, Identifier bottomTextureLocation) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIDAndName.getNamespace(), "models", "block");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("parent", "neutronia:block/slab");

        JsonObject textures = new JsonObject();
        textures.addProperty("bottom", bottomTextureLocation.toString());
        textures.addProperty("side", sideTextureLocation.toString());
        textures.addProperty("top", topTextureLocation.toString());
        root.add("textures", textures);

        String json = gson.toJson(root);

        JsonObject root2 = new JsonObject();
        root2.addProperty("parent", "neutronia:block/slab_top");

        JsonObject textures2 = new JsonObject();
        textures2.addProperty("bottom", bottomTextureLocation.toString());
        textures2.addProperty("side", sideTextureLocation.toString());
        textures2.addProperty("top", topTextureLocation.toString());
        root2.add("textures", textures2);

        String json2 = gson.toJson(root2);

        try {
            FileUtils.writeStringToFile(base.resolve("half_" + modIDAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
            FileUtils.writeStringToFile(base.resolve("upper_" + modIDAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json2), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", modIDAndName.getPath());
        }

    }

    public static void genSlabItemModel(String modId, String blockName) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modId, "models", "item");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("parent", modId + ":block/" + "half_" + blockName);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(blockName + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", blockName);
        }

    }

    public static void genLayeredSlab(String modId, String blockName, Identifier mainTexture, Identifier overlayTexture) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modId, "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("forge_marker", 1);

        JsonObject variants = new JsonObject();

        JsonObject half = new JsonObject();

        JsonObject upper = new JsonObject();
        upper.addProperty("model", modId + ":upper_" + blockName);
        half.add("top", upper);

        JsonObject lower = new JsonObject();
        lower.addProperty("model", modId + ":half_" + blockName);
        half.add("bottom", lower);

        variants.add("half", half);

        root.add("variants", variants);

        String json = gson.toJson(root);

        JsonObject root2 = new JsonObject();
        root2.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root2.addProperty("forge_marker", 1);

        JsonObject variants2 = new JsonObject();

        JsonObject prop = new JsonObject();

        JsonObject blarg = new JsonObject();
        blarg.addProperty("model", modId + ":upper_" + blockName);

        prop.add("blarg", blarg);

        variants2.add("prop", prop);

        root2.add("variants", variants2);

        String json2 = gson.toJson(root2);

        try {
            FileUtils.writeStringToFile(base.resolve(blockName + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
            FileUtils.writeStringToFile(base.resolve(blockName + "_double.json").toFile(), StringEscapeUtils.unescapeJson(json2), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", blockName);
        }

        genLayeredSlabModel(modId, blockName, mainTexture, overlayTexture);
        genLayeredSlabItemModel(modId, blockName);

    }

    public static void genLayeredSlabModel(String modId, String blockName, Identifier mainTexture, Identifier overlayTexture) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modId, "models", "block");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("parent", new Identifier("neutronia_legacy", "block/cube_bottom_half_overlay_all").toString());

        JsonObject textures = new JsonObject();
        textures.addProperty("all", mainTexture.toString());
        textures.addProperty("overlay", overlayTexture.toString());
        root.add("textures", textures);

        String json = gson.toJson(root);

        JsonObject root2 = new JsonObject();
        root2.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root2.addProperty("parent", new Identifier("neutronia_legacy", "block/cube_top_half_overlay_all").toString());

        JsonObject textures2 = new JsonObject();
        textures2.addProperty("all", mainTexture.toString());
        textures2.addProperty("overlay", overlayTexture.toString());
        root2.add("textures", textures2);

        String json2 = gson.toJson(root2);

        try {
            FileUtils.writeStringToFile(base.resolve("half_" + blockName + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
            FileUtils.writeStringToFile(base.resolve("upper_" + blockName + ".json").toFile(), StringEscapeUtils.unescapeJson(json2), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", blockName);
        }

    }

    public static void genLayeredSlabItemModel(String modId, String blockName) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modId, "models", "item");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("parent", modId + ":block/" + "half_" + blockName);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(blockName + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", blockName);
        }

    }

    public static void genFenceBlock(Identifier modIdAndName, Identifier textureName) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");

        JsonArray multipart = new JsonArray();

        JsonObject pole = new JsonObject();

        JsonObject applyPost = new JsonObject();
        applyPost.addProperty("model", modIdAndName.getNamespace() + ":" + modIdAndName.getPath() + "_post");
        pole.add("apply", applyPost);

        multipart.add(pole);

        JsonObject sideNorth = new JsonObject();

        JsonObject whenNorth = new JsonObject();
        whenNorth.addProperty("north", true);
        sideNorth.add("when", whenNorth);

        JsonObject applyNorth = new JsonObject();
        applyNorth.addProperty("model", modIdAndName.getNamespace() + ":" + modIdAndName.getPath() + "_side");
        applyNorth.addProperty("uvlock", true);
        sideNorth.add("apply", applyNorth);

        multipart.add(sideNorth);

        JsonObject sideEast = new JsonObject();

        JsonObject whenEast = new JsonObject();
        whenEast.addProperty("north", true);
        sideEast.add("when", whenEast);

        JsonObject applyEast = new JsonObject();
        applyEast.addProperty("model", modIdAndName.getNamespace() + ":" + modIdAndName.getPath() + "_side");
        applyEast.addProperty("uvlock", true);
        applyEast.addProperty("y", 90);
        sideEast.add("apply", applyEast);

        multipart.add(sideEast);

        JsonObject sideSouth = new JsonObject();

        JsonObject whenSouth = new JsonObject();
        whenSouth.addProperty("north", true);
        sideSouth.add("when", whenSouth);

        JsonObject applySouth = new JsonObject();
        applySouth.addProperty("model", modIdAndName.getNamespace() + ":" + modIdAndName.getPath() + "_side");
        applySouth.addProperty("uvlock", true);
        applySouth.addProperty("y", 180);
        sideSouth.add("apply", applySouth);

        multipart.add(sideSouth);

        JsonObject sideWest = new JsonObject();

        JsonObject whenWest = new JsonObject();
        whenWest.addProperty("west", true);
        sideWest.add("when", whenWest);

        JsonObject applyWest = new JsonObject();
        applyWest.addProperty("model", modIdAndName.getNamespace() + ":" + modIdAndName.getPath() + "_side");
        applyWest.addProperty("uvlock", true);
        applyWest.addProperty("y", 270);
        sideWest.add("apply", applyWest);

        multipart.add(sideWest);

        root.add("multipart", multipart);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", modIdAndName.getPath());
        }

        genBlockFenceModel(modIdAndName, textureName);
        genBlockFenceItemModel(modIdAndName);

    }

    public static void genBlockFenceModel(Identifier modIdAndName, Identifier textureName) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "models", "block");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        {
            JsonObject root = new JsonObject();
            root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
            root.addProperty("parent", "block/fence_post");

            JsonObject textures = new JsonObject();
            textures.addProperty("texture", textureName.getNamespace() + ":block/" + textureName.getPath());
            root.add("textures", textures);

            String json = gson.toJson(root);

            try {
                FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + "_post.json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
            } catch (IOException e) {
                System.out.printf("Error creating file %s.json" + "\n", modIdAndName.getPath() + "_post");
            }
        }
        {
            JsonObject root = new JsonObject();
            root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
            root.addProperty("parent", "block/fence_side");

            JsonObject textures = new JsonObject();
            textures.addProperty("texture", textureName.getNamespace() + ":block/" + textureName.getPath());
            root.add("textures", textures);

            String json = gson.toJson(root);

            try {
                FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + "_side.json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
            } catch (IOException e) {
                System.out.printf("Error creating file %s.json" + "\n", modIdAndName.getPath() + "_side");
            }
        }
        {
            JsonObject root = new JsonObject();
            root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
            root.addProperty("parent", "block/fence_inventory");

            JsonObject textures = new JsonObject();
            textures.addProperty("texture", textureName.getNamespace() + ":block/" + textureName.getPath());
            root.add("textures", textures);

            String json = gson.toJson(root);

            try {
                FileUtils.writeStringToFile(base.resolve(modIdAndName.getPath() + "_inventory.json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
            } catch (IOException e) {
                System.out.printf("Error creating file %s.json" + "\n", modIdAndName.getPath() + "_inventory");
            }
        }

    }

    public static void genBlockFenceItemModel(Identifier modIdAndName) {

        File fileDir = Paths.get("src", "main", "resources", "assets", modIdAndName.getNamespace(), "models", "item").toFile();
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        try {

            Writer writer = new OutputStreamWriter(new FileOutputStream(fileDir + "\\" + modIdAndName.getPath() + ".json"), StandardCharsets.UTF_8);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonWriter jw = gson.newJsonWriter(writer);

            jw.beginObject();

            jw.name("parent").value(modIdAndName.getNamespace() + ":block/" + modIdAndName.getPath() + "_inventory");

            jw.endObject();

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void genItemModel(String modId, String itemName, String textureName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Path base = Paths.get("src", "main", "resources", "assets", modId, "models", "item");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }
        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("parent", "item/generated");
        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", modId + ":item/" + textureName);
        root.add("textures", textures);
        String json = gson.toJson(root);
        try {
            FileUtils.writeStringToFile(base.resolve(itemName + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", itemName);
        }
    }

    public static void genToolModel(String modId, String itemName, String textureName, String path) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Path base = Paths.get("src", "main", "resources", "assets", modId, "models", "item");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }
        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("parent", "item/handheld");
        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", modId + ":item/" + textureName);
        root.add("textures", textures);
        String json = gson.toJson(root);
        try {
            FileUtils.writeStringToFile(base.resolve(itemName + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", itemName);
        }
    }

    private static void genLangFile(String modid, String block_name, String unlocalized_name, String lang_file_name) {
        Path base = Paths.get("src", "main", "resources", "assets", modid, "lang");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }
        try (BufferedWriter w = Files.newBufferedWriter(base.resolve(String.format("%s.lang", lang_file_name)), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            String name = unlocalized_name.replace("_", " ");
            unlocalized_name = WordUtils.capitalizeFully(name);
            w.write("tile." + modid + ":" + block_name + ".name=" + unlocalized_name + "\n");
        } catch (IOException ignored) {
            System.out.printf("Error creating file %s.json" + "\n", lang_file_name);
        }
    }

    public static void genAdvancementRootJson(String modId, String advancement_name, String item_name, String title, String desc, String background_texture_name, boolean show_toast, boolean announce_to_chat, boolean hidden) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modId, "advancements");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");

        JsonObject display = new JsonObject();

        JsonObject icon = new JsonObject();
        icon.addProperty("item", modId + ":" + item_name);
        display.add("icon", icon);

        JsonObject titleObject = new JsonObject();
        titleObject.addProperty("translate", modId + ".advancements." + title);
        display.add("title", titleObject);

        JsonObject descObject = new JsonObject();
        descObject.addProperty("translate", modId + ".advancements." + desc + ".desc");
        display.add("description", descObject);

        display.addProperty("background", modId + ":textures/advancements/" + background_texture_name + ".png");
        display.addProperty("show_toast", show_toast);
        display.addProperty("announce_to_chat", announce_to_chat);
        display.addProperty("hidden", hidden);

        JsonObject criteria = new JsonObject();

        root.add("display", display);
        root.add("criteria", criteria);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve("root.json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", advancement_name);
        }

    }

    public static void genAdvancementJson(String modId, String advancement_name, String item_name, String title, String desc, String background_texture_name, boolean show_toast, boolean announce_to_chat, boolean hidden) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", modId, "advancements");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");

        JsonObject display = new JsonObject();

        JsonObject icon = new JsonObject();
        icon.addProperty("item", modId + ":" + item_name);
        display.add("icon", icon);

        JsonObject titleObject = new JsonObject();
        titleObject.addProperty("translate", modId + ".advancements." + title);
        display.add("title", titleObject);

        JsonObject descObject = new JsonObject();
        descObject.addProperty("translate", modId + ".advancements." + desc + ".desc");
        display.add("description", descObject);

        display.addProperty("background", modId + ":textures/advancements/" + background_texture_name + ".png");
        display.addProperty("show_toast", show_toast);
        display.addProperty("announce_to_chat", announce_to_chat);
        display.addProperty("hidden", hidden);

        JsonObject criteria = new JsonObject();

        root.add("display", display);
        root.addProperty("parent", modId + ":" + "root");
        root.add("criteria", criteria);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve("root.json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", advancement_name);
        }

    }

    private static void stringToFile(String text, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName + ".txt", true);
            writer.write(text + "\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(String text, String targetFilePath) throws IOException {
        Path targetPath = Paths.get(targetFilePath);
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
        Files.write(targetPath, bytes, StandardOpenOption.CREATE);
    }

}
