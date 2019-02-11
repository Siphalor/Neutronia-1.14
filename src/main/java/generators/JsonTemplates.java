package json_generator;

public class JsonTemplates {

    public static final String STAIRS = "{\n" +
            "    \"variants\": {\n" +
            "        \"facing=east,half=bottom,shape=straight\":  { \"model\": \"modid:block/block_model\" },\n" +
            "        \"facing=west,half=bottom,shape=straight\":  { \"model\": \"modid:block/block_model\", \"y\": 180, \"uvlock\": true },\n" +
            "        \"facing=south,half=bottom,shape=straight\": { \"model\": \"modid:block/block_model\", \"y\": 90, \"uvlock\": true },\n" +
            "        \"facing=north,half=bottom,shape=straight\": { \"model\": \"modid:block/block_model\", \"y\": 270, \"uvlock\": true },\n" +
            "        \"facing=east,half=bottom,shape=outer_right\":  { \"model\": \"modid:block/block_model2\" },\n" +
            "        \"facing=west,half=bottom,shape=outer_right\":  { \"model\": \"modid:block/block_model2\", \"y\": 180, \"uvlock\": true },\n" +
            "        \"facing=south,half=bottom,shape=outer_right\": { \"model\": \"modid:block/block_model2\", \"y\": 90, \"uvlock\": true },\n" +
            "        \"facing=north,half=bottom,shape=outer_right\": { \"model\": \"modid:block/block_model2\", \"y\": 270, \"uvlock\": true },\n" +
            "        \"facing=east,half=bottom,shape=outer_left\":  { \"model\": \"modid:block/block_model2\", \"y\": 270, \"uvlock\": true },\n" +
            "        \"facing=west,half=bottom,shape=outer_left\":  { \"model\": \"modid:block/block_model2\", \"y\": 90, \"uvlock\": true },\n" +
            "        \"facing=south,half=bottom,shape=outer_left\": { \"model\": \"modid:block/block_model2\" },\n" +
            "        \"facing=north,half=bottom,shape=outer_left\": { \"model\": \"modid:block/block_model2\", \"y\": 180, \"uvlock\": true },\n" +
            "        \"facing=east,half=bottom,shape=inner_right\":  { \"model\": \"modid:block/block_model3\" },\n" +
            "        \"facing=west,half=bottom,shape=inner_right\":  { \"model\": \"modid:block/block_model3\", \"y\": 180, \"uvlock\": true },\n" +
            "        \"facing=south,half=bottom,shape=inner_right\": { \"model\": \"modid:block/block_model3\", \"y\": 90, \"uvlock\": true },\n" +
            "        \"facing=north,half=bottom,shape=inner_right\": { \"model\": \"modid:block/block_model3\", \"y\": 270, \"uvlock\": true },\n" +
            "        \"facing=east,half=bottom,shape=inner_left\":  { \"model\": \"modid:block/block_model3\", \"y\": 270, \"uvlock\": true },\n" +
            "        \"facing=west,half=bottom,shape=inner_left\":  { \"model\": \"modid:block/block_model3\", \"y\": 90, \"uvlock\": true },\n" +
            "        \"facing=south,half=bottom,shape=inner_left\": { \"model\": \"modid:block/block_model3\" },\n" +
            "        \"facing=north,half=bottom,shape=inner_left\": { \"model\": \"modid:block/block_model3\", \"y\": 180, \"uvlock\": true },\n" +
            "        \"facing=east,half=top,shape=straight\":  { \"model\": \"modid:block/block_model\", \"x\": 180, \"uvlock\": true },\n" +
            "        \"facing=west,half=top,shape=straight\":  { \"model\": \"modid:block/block_model\", \"x\": 180, \"y\": 180, \"uvlock\": true },\n" +
            "        \"facing=south,half=top,shape=straight\": { \"model\": \"modid:block/block_model\", \"x\": 180, \"y\": 90, \"uvlock\": true },\n" +
            "        \"facing=north,half=top,shape=straight\": { \"model\": \"modid:block/block_model\", \"x\": 180, \"y\": 270, \"uvlock\": true },\n" +
            "        \"facing=east,half=top,shape=outer_right\":  { \"model\": \"modid:block/block_model2\", \"x\": 180, \"y\": 90, \"uvlock\": true },\n" +
            "        \"facing=west,half=top,shape=outer_right\":  { \"model\": \"modid:block/block_model2\", \"x\": 180, \"y\": 270, \"uvlock\": true },\n" +
            "        \"facing=south,half=top,shape=outer_right\": { \"model\": \"modid:block/block_model2\", \"x\": 180, \"y\": 180, \"uvlock\": true },\n" +
            "        \"facing=north,half=top,shape=outer_right\": { \"model\": \"modid:block/block_model2\", \"x\": 180, \"uvlock\": true },\n" +
            "        \"facing=east,half=top,shape=outer_left\":  { \"model\": \"modid:block/block_model2\", \"x\": 180, \"uvlock\": true },\n" +
            "        \"facing=west,half=top,shape=outer_left\":  { \"model\": \"modid:block/block_model2\", \"x\": 180, \"y\": 180, \"uvlock\": true },\n" +
            "        \"facing=south,half=top,shape=outer_left\": { \"model\": \"modid:block/block_model2\", \"x\": 180, \"y\": 90, \"uvlock\": true },\n" +
            "        \"facing=north,half=top,shape=outer_left\": { \"model\": \"modid:block/block_model2\", \"x\": 180, \"y\": 270, \"uvlock\": true },\n" +
            "        \"facing=east,half=top,shape=inner_right\":  { \"model\": \"modid:block/block_model3\", \"x\": 180, \"y\": 90, \"uvlock\": true },\n" +
            "        \"facing=west,half=top,shape=inner_right\":  { \"model\": \"modid:block/block_model3\", \"x\": 180, \"y\": 270, \"uvlock\": true },\n" +
            "        \"facing=south,half=top,shape=inner_right\": { \"model\": \"modid:block/block_model3\", \"x\": 180, \"y\": 180, \"uvlock\": true },\n" +
            "        \"facing=north,half=top,shape=inner_right\": { \"model\": \"modid:block/block_model3\", \"x\": 180, \"uvlock\": true },\n" +
            "        \"facing=east,half=top,shape=inner_left\":  { \"model\": \"modid:block/block_model3\", \"x\": 180, \"uvlock\": true },\n" +
            "        \"facing=west,half=top,shape=inner_left\":  { \"model\": \"modid:block/block_model3\", \"x\": 180, \"y\": 180, \"uvlock\": true },\n" +
            "        \"facing=south,half=top,shape=inner_left\": { \"model\": \"modid:block/block_model3\", \"x\": 180, \"y\": 90, \"uvlock\": true },\n" +
            "        \"facing=north,half=top,shape=inner_left\": { \"model\": \"modid:block/block_model3\", \"x\": 180, \"y\": 270, \"uvlock\": true }\n" +
            "    }\n" +
            "}\n";

}
