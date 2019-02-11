package generators;

public class JsonTemplates {

    public static final String STAIRS = "{\n" +
            "    \"variants\": {\n" +
            "        \"facing=east,half=bottom,shape=straight\":  { \"model\": \"modid:block/block_model\" },\n" +
            "        \"facing=west,half=bottom,shape=straight\":  { \"model\": \"modid:block/block_model\", \"y\": 180, \"uvlock\": true },\n" +
            "        \"facing=south,half=bottom,shape=straight\": { \"model\": \"modid:block/block_model\", \"y\": 90, \"uvlock\": true },\n" +
            "        \"facing=north,half=bottom,shape=straight\": { \"model\": \"modid:block/block_model\", \"y\": 270, \"uvlock\": true },\n" +
            "        \"facing=east,half=bottom,shape=outer_right\":  { \"model\": \"modid:block/block_model_outer\" },\n" +
            "        \"facing=west,half=bottom,shape=outer_right\":  { \"model\": \"modid:block/block_model_outer\", \"y\": 180, \"uvlock\": true },\n" +
            "        \"facing=south,half=bottom,shape=outer_right\": { \"model\": \"modid:block/block_model_outer\", \"y\": 90, \"uvlock\": true },\n" +
            "        \"facing=north,half=bottom,shape=outer_right\": { \"model\": \"modid:block/block_model_outer\", \"y\": 270, \"uvlock\": true },\n" +
            "        \"facing=east,half=bottom,shape=outer_left\":  { \"model\": \"modid:block/block_model_outer\", \"y\": 270, \"uvlock\": true },\n" +
            "        \"facing=west,half=bottom,shape=outer_left\":  { \"model\": \"modid:block/block_model_outer\", \"y\": 90, \"uvlock\": true },\n" +
            "        \"facing=south,half=bottom,shape=outer_left\": { \"model\": \"modid:block/block_model_outer\" },\n" +
            "        \"facing=north,half=bottom,shape=outer_left\": { \"model\": \"modid:block/block_model_outer\", \"y\": 180, \"uvlock\": true },\n" +
            "        \"facing=east,half=bottom,shape=inner_right\":  { \"model\": \"modid:block/block_model_inner\" },\n" +
            "        \"facing=west,half=bottom,shape=inner_right\":  { \"model\": \"modid:block/block_model_inner\", \"y\": 180, \"uvlock\": true },\n" +
            "        \"facing=south,half=bottom,shape=inner_right\": { \"model\": \"modid:block/block_model_inner\", \"y\": 90, \"uvlock\": true },\n" +
            "        \"facing=north,half=bottom,shape=inner_right\": { \"model\": \"modid:block/block_model_inner\", \"y\": 270, \"uvlock\": true },\n" +
            "        \"facing=east,half=bottom,shape=inner_left\":  { \"model\": \"modid:block/block_model_inner\", \"y\": 270, \"uvlock\": true },\n" +
            "        \"facing=west,half=bottom,shape=inner_left\":  { \"model\": \"modid:block/block_model_inner\", \"y\": 90, \"uvlock\": true },\n" +
            "        \"facing=south,half=bottom,shape=inner_left\": { \"model\": \"modid:block/block_model_inner\" },\n" +
            "        \"facing=north,half=bottom,shape=inner_left\": { \"model\": \"modid:block/block_model_inner\", \"y\": 180, \"uvlock\": true },\n" +
            "        \"facing=east,half=top,shape=straight\":  { \"model\": \"modid:block/block_model\", \"x\": 180, \"uvlock\": true },\n" +
            "        \"facing=west,half=top,shape=straight\":  { \"model\": \"modid:block/block_model\", \"x\": 180, \"y\": 180, \"uvlock\": true },\n" +
            "        \"facing=south,half=top,shape=straight\": { \"model\": \"modid:block/block_model\", \"x\": 180, \"y\": 90, \"uvlock\": true },\n" +
            "        \"facing=north,half=top,shape=straight\": { \"model\": \"modid:block/block_model\", \"x\": 180, \"y\": 270, \"uvlock\": true },\n" +
            "        \"facing=east,half=top,shape=outer_right\":  { \"model\": \"modid:block/block_model_outer\", \"x\": 180, \"y\": 90, \"uvlock\": true },\n" +
            "        \"facing=west,half=top,shape=outer_right\":  { \"model\": \"modid:block/block_model_outer\", \"x\": 180, \"y\": 270, \"uvlock\": true },\n" +
            "        \"facing=south,half=top,shape=outer_right\": { \"model\": \"modid:block/block_model_outer\", \"x\": 180, \"y\": 180, \"uvlock\": true },\n" +
            "        \"facing=north,half=top,shape=outer_right\": { \"model\": \"modid:block/block_model_outer\", \"x\": 180, \"uvlock\": true },\n" +
            "        \"facing=east,half=top,shape=outer_left\":  { \"model\": \"modid:block/block_model_outer\", \"x\": 180, \"uvlock\": true },\n" +
            "        \"facing=west,half=top,shape=outer_left\":  { \"model\": \"modid:block/block_model_outer\", \"x\": 180, \"y\": 180, \"uvlock\": true },\n" +
            "        \"facing=south,half=top,shape=outer_left\": { \"model\": \"modid:block/block_model_outer\", \"x\": 180, \"y\": 90, \"uvlock\": true },\n" +
            "        \"facing=north,half=top,shape=outer_left\": { \"model\": \"modid:block/block_model_outer\", \"x\": 180, \"y\": 270, \"uvlock\": true },\n" +
            "        \"facing=east,half=top,shape=inner_right\":  { \"model\": \"modid:block/block_model_inner\", \"x\": 180, \"y\": 90, \"uvlock\": true },\n" +
            "        \"facing=west,half=top,shape=inner_right\":  { \"model\": \"modid:block/block_model_inner\", \"x\": 180, \"y\": 270, \"uvlock\": true },\n" +
            "        \"facing=south,half=top,shape=inner_right\": { \"model\": \"modid:block/block_model_inner\", \"x\": 180, \"y\": 180, \"uvlock\": true },\n" +
            "        \"facing=north,half=top,shape=inner_right\": { \"model\": \"modid:block/block_model_inner\", \"x\": 180, \"uvlock\": true },\n" +
            "        \"facing=east,half=top,shape=inner_left\":  { \"model\": \"modid:block/block_model_inner\", \"x\": 180, \"uvlock\": true },\n" +
            "        \"facing=west,half=top,shape=inner_left\":  { \"model\": \"modid:block/block_model_inner\", \"x\": 180, \"y\": 180, \"uvlock\": true },\n" +
            "        \"facing=south,half=top,shape=inner_left\": { \"model\": \"modid:block/block_model_inner\", \"x\": 180, \"y\": 90, \"uvlock\": true },\n" +
            "        \"facing=north,half=top,shape=inner_left\": { \"model\": \"modid:block/block_model_inner\", \"x\": 180, \"y\": 270, \"uvlock\": true }\n" +
            "    }\n" +
            "}\n";

    

}
