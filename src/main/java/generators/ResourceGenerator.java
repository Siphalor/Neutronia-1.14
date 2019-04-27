package generators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.util.Identifier;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@SuppressWarnings({"WeakerAccess", "unused"})
public class ResourceGenerator {
	public static final Path ASSETS_PATH = Paths.get("..", "src", "main", "resources", "assets");
	public static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	public static Path getModPath(String modId) {
	    return ASSETS_PATH.resolve(modId);
    }

    public static Path getBlockstatesPath(String modId) {
	    Path path = getModPath(modId).resolve("blockstates");
        createPath(path.toFile());
	    return path;
    }

    public static Path getBlockModelsPath(String modId) {
	    Path path = getModPath(modId).resolve("models").resolve("block");
	    createPath(path.toFile());
	    return path;
    }

    public static Path getItemModelsPath(String modId) {
	    Path path = getModPath(modId).resolve("models").resolve("item");
	    createPath(path.toFile());
	    return path;
    }

    public static void createPath(File file) {
	    if(!file.exists())
	        file.mkdirs();
    }

    public static void writeJsonToFile(File file, JsonObject json) {
	    writeStringToFile(file, StringEscapeUtils.unescapeJson(GSON.toJson(json)));
    }

    public static void writeStringToFile(File file, String text) {
	    try {
	        FileUtils.writeStringToFile(file, text, CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", file.getAbsolutePath());
        }
    }

    public static void genStair(Identifier identifier, Identifier bottomTexture, Identifier topTexture, Identifier sideTexture) {

        String text = JsonTemplates.STAIRS.replace("modid", identifier.getNamespace())
                .replace("block_model", identifier.getPath());
        writeStringToFile(getBlockstatesPath(identifier.getNamespace()).resolve(identifier.getPath() + ".json").toFile(), text);

        genStairBlockJson(identifier, bottomTexture, topTexture, sideTexture);
        genStairItemModel(identifier);
    }

    public static void genStairBlockJson(Identifier identifier, Identifier bottomTexture, Identifier topTexture, Identifier sideTexture) {
        JsonObject textures = new JsonObject();
        textures.addProperty("bottom", String.format("%s:block/%s", bottomTexture.getNamespace(), bottomTexture.getPath()));
        textures.addProperty("top", String.format("%s:block/%s", topTexture.getNamespace(), topTexture.getPath()));
        textures.addProperty("side", String.format("%s:block/%s", sideTexture.getNamespace(), sideTexture.getPath()));

        JsonObject root = new JsonObject();
        root.addProperty("parent", "block/stairs");
        root.add("textures", textures);

        writeJsonToFile(getBlockModelsPath(identifier.getNamespace()).resolve(identifier.getPath() + ".json").toFile(), root);

        root = new JsonObject();
        root.addProperty("parent", "block/inner_stairs");
        root.add("textures", textures);

        writeJsonToFile(getBlockModelsPath(identifier.getNamespace()).resolve(identifier.getPath() + "_inner.json").toFile(), root);

        root = new JsonObject();
        root.addProperty("parent", "block/outer_stairs");
        root.add("textures", textures);

        writeJsonToFile(getBlockModelsPath(identifier.getNamespace()).resolve(identifier.getPath() + "_outer.json").toFile(), root);
    }

    public static void genStairItemModel(Identifier identifier) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", identifier.getNamespace() + ":block/" + identifier.getPath());

        writeJsonToFile(getItemModelsPath(identifier.getNamespace()).resolve(identifier.getPath() + ".json").toFile(), root);
    }

    public static void genSimpleBlockstates(Identifier identifier) {
        JsonObject root = new JsonObject();

        JsonObject variants = new JsonObject();

        JsonObject model = new JsonObject();
        model.addProperty("model", identifier.getNamespace() + ":block/" + identifier.getPath());

        variants.add("", model);
        root.add("variants", variants);

        writeJsonToFile(getBlockstatesPath(identifier.getNamespace()).resolve(identifier.getPath() + ".json").toFile(), root);
    }

    public static void genSimpleBlock(Identifier identifier, Identifier textureName) {
        genSimpleBlockstates(identifier);
        genSimpleBlockModel(identifier, textureName);
        genSimpleBlockItemModel(identifier);
    }

    public static void genSimpleBlockModel(Identifier identifier, Identifier textureName) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", "block/cube_all");

        JsonObject textures = new JsonObject();
        textures.addProperty("all", textureName.getNamespace() + ":block/" + textureName.getPath());
        root.add("textures", textures);

        writeJsonToFile(getBlockModelsPath(identifier.getNamespace()).resolve(identifier.getPath() + ".json").toFile(), root);
    }

    public static void genSimpleBlockItemModel(Identifier identifier) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", identifier.getNamespace() + ":block/" + identifier.getPath());

        writeJsonToFile(getItemModelsPath(identifier.getNamespace()).resolve(identifier.getPath() + ".json").toFile(), root);
    }

    public static void genFlatBlockItemModel(Identifier identifier, Identifier textureName) {
	    JsonObject root = new JsonObject();
	    root.addProperty("parent", "item/generated");
        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", textureName.getNamespace() + ":block/" + textureName.getPath());
        root.add("textures", textures);

        writeJsonToFile(getItemModelsPath(identifier.getNamespace()).resolve(identifier.getPath() + ".json").toFile(), root);
    }

    /*public static void genCustomBlock(Identifier identifier, Identifier modelPath) {
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
            FileUtils.writeStringToFile(base.resolve(identifier.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", identifier.getPath());
        }
    }

    public static void genCustomBlockWithTexture(Identifier identifier, Identifier modelPath, Identifier textureLocation) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", identifier.getNamespace(), "blockstates");
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
            FileUtils.writeStringToFile(base.resolve(identifier.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", identifier.getPath());
        }
    }*/

    public static void genPlant(Identifier identifier, Identifier textureName) {
        genSimpleBlockstates(identifier);
        genPlantBlockModel(identifier, textureName);
        genFlatBlockItemModel(identifier, textureName);
    }

    public static void genPlantBlockModel(Identifier identifier, Identifier textureName) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", "block/cross");

        JsonObject textures = new JsonObject();
        textures.addProperty("cross", textureName.getNamespace() + ":block/" + textureName.getPath());
        root.add("textures", textures);

        writeJsonToFile(getBlockModelsPath(identifier.getNamespace()).resolve(identifier.getPath() + ".json").toFile(), root);
    }

    public static void genOrientedBlock(Identifier identifier, Identifier topTextureName, Identifier frontTextureName, Identifier sidesTextureName) {
        JsonObject root = new JsonObject();

        JsonObject variants = new JsonObject();

        JsonObject north = new JsonObject();
        north.addProperty("model", String.format("%s:block/%s", identifier.getNamespace(), identifier.getPath()));
        variants.add("facing=north", north);

        JsonObject south = new JsonObject();
        south.addProperty("model", String.format("%s:block/%s", identifier.getNamespace(), identifier.getPath()));
        south.addProperty("y", 90);
        variants.add("facing=south", south);

        JsonObject east = new JsonObject();
        east.addProperty("model", String.format("%s:block/%s", identifier.getNamespace(), identifier.getPath()));
        east.addProperty("y", 180);
        variants.add("facing=east", east);

        JsonObject west = new JsonObject();
        west.addProperty("model", String.format("%s:block/%s", identifier.getNamespace(), identifier.getPath()));
        west.addProperty("y", 270);
        variants.add("facing=west", west);

        root.add("variants", variants);

        writeJsonToFile(getBlockstatesPath(identifier.getNamespace()).resolve(identifier.getPath() + ".json").toFile(), root);

        genOrientedBlockModel(identifier, topTextureName, frontTextureName, sidesTextureName);
        genSimpleBlockItemModel(identifier);
    }

    public static void genOrientedBlockModel(Identifier identifier, Identifier topTextureName, Identifier frontTextureName, Identifier sidesTextureName) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", "block/orientable");
        JsonObject textures = new JsonObject();
        textures.addProperty("top", topTextureName.getNamespace() + ":block/" + topTextureName.getPath());
        textures.addProperty("front", frontTextureName.getNamespace() + ":block/" + frontTextureName.getPath());
        textures.addProperty("side", sidesTextureName.getNamespace() + ":block/" + sidesTextureName.getPath());
        root.add("textures", textures);

        writeJsonToFile(getBlockModelsPath(identifier.getNamespace()).resolve(identifier.getPath() + ".json").toFile(), root);
    }

    public static void genPillarBlock(Identifier identifier, Identifier endTextureName, Identifier sidesTextureName) {
        String model = identifier.getNamespace() + ":block/" + identifier.getPath();

        JsonObject root = new JsonObject();
        JsonObject variants = new JsonObject();

        JsonObject axisY = new JsonObject();
        axisY.addProperty("model", model);
        variants.add("axis=y", axisY);

        JsonObject axisZ = new JsonObject();
        axisZ.addProperty("x", 90);
        axisZ.addProperty("model", model);
        variants.add("axis=z", axisZ);

        JsonObject axisX = new JsonObject();
        axisX.addProperty("x", 90);
        axisX.addProperty("y", 90);
        axisX.addProperty("model", model);
        variants.add("axis=x", axisX);

        root.add("variants", variants);

        writeJsonToFile(getBlockstatesPath(identifier.getNamespace()).resolve(identifier.getPath() + ".json").toFile(), root);

        genPillarBlockModel(identifier, endTextureName, sidesTextureName);
        genSimpleBlockItemModel(identifier);
    }

    public static void genPillarBlockModel(Identifier identifier, Identifier endTextureName, Identifier sidesTextureName) {
    	JsonObject textures = new JsonObject();
    	textures.addProperty("end", endTextureName.getNamespace() + ":block/" + endTextureName.getPath());
        textures.addProperty("side", sidesTextureName.getNamespace() + ":block/" + sidesTextureName.getPath());

        JsonObject root = new JsonObject();
        root.addProperty("parent", "block/cube_column");

        root.add("textures", textures);

        writeJsonToFile(getBlockModelsPath(identifier.getNamespace()).resolve(identifier.getPath() + ".json").toFile(), root);
    }

    public static void genBottomTopBlock(Identifier identifier, Identifier bottomTextureName, Identifier topTextureName, Identifier sideTextureName) {
        genSimpleBlockstates(identifier);
        genBottomTopBlockModel(identifier, bottomTextureName, topTextureName, sideTextureName);
        genSimpleBlockItemModel(identifier);
    }

    public static void genBottomTopBlockModel(Identifier identifier, Identifier bottomTextureName, Identifier topTextureName, Identifier sidesTextureName) {
    	JsonObject textures = new JsonObject();
        textures.addProperty("bottom", bottomTextureName.getNamespace() + ":block/" + bottomTextureName.getPath());
    	textures.addProperty("top", topTextureName.getNamespace() + ":block/" + topTextureName.getPath());
        textures.addProperty("side", sidesTextureName.getNamespace() + ":block/" + sidesTextureName.getPath());

        JsonObject root = new JsonObject();
        root.addProperty("parent", "block/cube_bottom_top");

        root.add("textures", textures);

        writeJsonToFile(getBlockModelsPath(identifier.getNamespace()).resolve(identifier.getPath() + ".json").toFile(), root);
    }

    public static void genPressurePlate(Identifier identifier, Identifier textureName) {
        JsonObject root = new JsonObject();
        JsonObject variants = new JsonObject();

        JsonObject modelUp = new JsonObject();
        modelUp.addProperty("model", identifier.getNamespace() + ":block/" + identifier.getPath());
        JsonObject modelDown = new JsonObject();
        modelDown.addProperty("model", identifier.getNamespace() + ":block/" + identifier.getPath() + "_down");

        variants.add("powered=false", modelUp);
        variants.add("powered=true", modelDown);
        root.add("variants", variants);

        writeJsonToFile(getBlockstatesPath(identifier.getNamespace()).resolve(identifier.getPath() + ".json").toFile(), root);
        genPressurePlateBlockModel(identifier, textureName);
        genSimpleBlockItemModel(identifier);
    }

    public static void genPressurePlateBlockModel(Identifier identifier, Identifier textureName) {
        JsonObject textures = new JsonObject();
        textures.addProperty("texture", textureName.getNamespace() + ":block/" + textureName.getPath());

        JsonObject root = new JsonObject();
        root.add("textures", textures);

        root.addProperty("parent", "block/pressure_plate_up");
        writeJsonToFile(getBlockModelsPath(identifier.getNamespace()).resolve(identifier.getPath() + ".json").toFile(), root);

        root.addProperty("parent", "block/pressure_plate_down");
        writeJsonToFile(getBlockModelsPath(identifier.getNamespace()).resolve(identifier.getPath() + "_down.json").toFile(), root);
    }

    public static void genSlab(Identifier identifier, Identifier fullBlock, Identifier topTextureLocation, Identifier sideTextureLocation, Identifier bottomTextureLocation) {
        JsonObject root = new JsonObject();

        JsonObject variants = new JsonObject();

        JsonObject bottom = new JsonObject();
        bottom.addProperty("model", identifier.getNamespace() + ":block/" + identifier.getPath());
        variants.add("type=bottom", bottom);

        JsonObject top = new JsonObject();
        top.addProperty("model", identifier.getNamespace() + ":block/" + identifier.getPath() + "_top");
        variants.add("type=top", top);

        JsonObject doubleSlab = new JsonObject();
        doubleSlab.addProperty("model", fullBlock.getNamespace() + ":block/" + fullBlock.getPath());
        variants.add("type=double", doubleSlab);

        root.add("variants", variants);

        writeJsonToFile(getBlockstatesPath(identifier.getNamespace()).resolve(identifier.getPath() + ".json").toFile(), root);

        genSlabBlockModel(identifier, topTextureLocation, sideTextureLocation, bottomTextureLocation);
        genSimpleBlockItemModel(identifier);
    }

    /*public static void genSlabColored(Identifier identifier, Identifier topTextureLocation, Identifier sideTextureLocation, Identifier bottomTextureLocation) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", identifier.getNamespace(), "blockstates");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Husky's JSON Generator v4.");
        root.addProperty("forge_marker", 1);

        JsonObject variants = new JsonObject();

        JsonObject half = new JsonObject();

        JsonObject upper = new JsonObject();
        upper.addProperty("model", identifier.getNamespace() + ":upper_" + identifier.getPath());
        half.add("top", upper);

        JsonObject lower = new JsonObject();
        lower.addProperty("model", identifier.getNamespace() + ":half_" + identifier.getPath());
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
            FileUtils.writeStringToFile(base.resolve(identifier.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
            FileUtils.writeStringToFile(base.resolve(identifier.getPath() + "_double.json").toFile(), StringEscapeUtils.unescapeJson(json2), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", identifier.getPath());
        }

        genSlabBlockModel(identifier, topTextureLocation, sideTextureLocation, bottomTextureLocation);
        genSlabItemModel(identifier.getNamespace(), identifier.getPath());

    }*/

    public static void genSlabBlockModel(Identifier identifier, Identifier topTextureLocation, Identifier sideTextureLocation, Identifier bottomTextureLocation) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", "minecraft:block/slab");

        JsonObject textures = new JsonObject();
        textures.addProperty("bottom", bottomTextureLocation.getNamespace() + ":block/" + bottomTextureLocation.getPath());
        textures.addProperty("side", sideTextureLocation.getNamespace() + ":block/" + sideTextureLocation.getPath());
        textures.addProperty("top", topTextureLocation.getNamespace() + ":block/" + topTextureLocation.getPath());
        root.add("textures", textures);

        writeJsonToFile(getBlockModelsPath(identifier.getNamespace()).resolve(identifier.getPath() + ".json").toFile(), root);

        root.addProperty("parent", "minecraft:block/slab_top");
        writeJsonToFile(getBlockModelsPath(identifier.getNamespace()).resolve(identifier.getPath() + "_top.json").toFile(), root);
    }

    /*public static void genSlabColoredBlockModel(Identifier identifier, Identifier topTextureLocation, Identifier sideTextureLocation, Identifier bottomTextureLocation) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("src", "main", "resources", "assets", identifier.getNamespace(), "models", "block");
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
            FileUtils.writeStringToFile(base.resolve("half_" + identifier.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
            FileUtils.writeStringToFile(base.resolve("upper_" + identifier.getPath() + ".json").toFile(), StringEscapeUtils.unescapeJson(json2), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.printf("Error creating file %s.json" + "\n", identifier.getPath());
        }

    }*/
    /*public static void genLayeredSlab(String modId, String blockName, Identifier mainTexture, Identifier overlayTexture) {

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

    }*/

    public static void genFenceBlock(Identifier identifier, Identifier textureName) {
        JsonObject root = new JsonObject();

        JsonArray multipart = new JsonArray();

        JsonObject pole = new JsonObject();

        JsonObject applyPost = new JsonObject();
        applyPost.addProperty("model", identifier.getNamespace() + ":" + identifier.getPath() + "_post");
        pole.add("apply", applyPost);

        multipart.add(pole);

        JsonObject sideNorth = new JsonObject();

        JsonObject whenNorth = new JsonObject();
        whenNorth.addProperty("north", true);
        sideNorth.add("when", whenNorth);

        JsonObject applyNorth = new JsonObject();
        applyNorth.addProperty("model", identifier.getNamespace() + ":" + identifier.getPath() + "_side");
        applyNorth.addProperty("uvlock", true);
        sideNorth.add("apply", applyNorth);

        multipart.add(sideNorth);

        JsonObject sideEast = new JsonObject();

        JsonObject whenEast = new JsonObject();
        whenEast.addProperty("north", true);
        sideEast.add("when", whenEast);

        JsonObject applyEast = new JsonObject();
        applyEast.addProperty("model", identifier.getNamespace() + ":" + identifier.getPath() + "_side");
        applyEast.addProperty("uvlock", true);
        applyEast.addProperty("y", 90);
        sideEast.add("apply", applyEast);

        multipart.add(sideEast);

        JsonObject sideSouth = new JsonObject();

        JsonObject whenSouth = new JsonObject();
        whenSouth.addProperty("north", true);
        sideSouth.add("when", whenSouth);

        JsonObject applySouth = new JsonObject();
        applySouth.addProperty("model", identifier.getNamespace() + ":" + identifier.getPath() + "_side");
        applySouth.addProperty("uvlock", true);
        applySouth.addProperty("y", 180);
        sideSouth.add("apply", applySouth);

        multipart.add(sideSouth);

        JsonObject sideWest = new JsonObject();

        JsonObject whenWest = new JsonObject();
        whenWest.addProperty("west", true);
        sideWest.add("when", whenWest);

        JsonObject applyWest = new JsonObject();
        applyWest.addProperty("model", identifier.getNamespace() + ":" + identifier.getPath() + "_side");
        applyWest.addProperty("uvlock", true);
        applyWest.addProperty("y", 270);
        sideWest.add("apply", applyWest);

        multipart.add(sideWest);

        root.add("multipart", multipart);

        writeJsonToFile(getBlockstatesPath(identifier.getNamespace()).resolve(identifier.getPath() + ".json").toFile(), root);

        genBlockFenceModel(identifier, textureName);
        genSimpleBlockItemModel(new Identifier(identifier.getNamespace(), identifier.getPath() + "_inventory"));
    }

    public static void genBlockFenceModel(Identifier identifier, Identifier textureName) {
        JsonObject root = new JsonObject();

        JsonObject textures = new JsonObject();
        textures.addProperty("texture", textureName.getNamespace() + ":block/" + textureName.getPath());
        root.add("textures", textures);

        root.addProperty("parent", "block/fence_post");
        writeJsonToFile(getBlockModelsPath(identifier.getNamespace()).resolve(identifier.getPath() + "_post.json").toFile(), root);

        root.addProperty("parent", "block/fence_side");
        writeJsonToFile(getBlockModelsPath(identifier.getNamespace()).resolve(identifier.getPath() + "_side.json").toFile(), root);

        root.addProperty("parent", "block/fence_inventory");
        writeJsonToFile(getBlockModelsPath(identifier.getNamespace()).resolve(identifier.getPath() + "_inventory.json").toFile(), root);
    }

    public static void genItemModel(Identifier identifier, Identifier textureName) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", "item/generated");
        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", textureName.getNamespace() + ":item/" + textureName.getPath());
        root.add("textures", textures);
        writeJsonToFile(getItemModelsPath(identifier.getNamespace()).resolve(identifier.getPath() + ".json").toFile(), root);
    }

    public static void genToolModel(Identifier identifier, Identifier textureName) {
        JsonObject root = new JsonObject();
        root.addProperty("parent", "item/handheld");
        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", textureName.getNamespace() + ":item/" + textureName.getPath());
        root.add("textures", textures);
        writeJsonToFile(getItemModelsPath(identifier.getNamespace()).resolve(identifier.getPath() + ".json").toFile(), root);
    }

    /*private static void genLangFile(String modid, String block_name, String unlocalized_name, String lang_file_name) {
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
    }*/

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

    public static void genPackMcMeta(String description) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path base = Paths.get("resourcepacks", "Neutronia");
        if (!base.toFile().exists()) {
            base.toFile().mkdirs();
        }

        JsonObject root = new JsonObject();

        JsonObject pack = new JsonObject();
        pack.addProperty("pack_format", 4);
        pack.addProperty("description", description);

        root.add("pack", pack);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(base.resolve("pack.mcmeta").toFile(), StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.println("Error creating file pack.mcmeta");
        }

    }
}
