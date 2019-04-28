package generators;

class JsonTemplates {

    static final String STAIRS = "{\n" +
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

    static final String DOOR = "{\n" +
        "    \"variants\": {\n" +
        "        \"facing=east,half=lower,hinge=left,open=false\":  { \"model\": \"modid:block/block_model_bottom\" },\n" +
        "        \"facing=south,half=lower,hinge=left,open=false\": { \"model\": \"modid:block/block_model_bottom\", \"y\": 90 },\n" +
        "        \"facing=west,half=lower,hinge=left,open=false\":  { \"model\": \"modid:block/block_model_bottom\", \"y\": 180 },\n" +
        "        \"facing=north,half=lower,hinge=left,open=false\": { \"model\": \"modid:block/block_model_bottom\", \"y\": 270 },\n" +
        "        \"facing=east,half=lower,hinge=right,open=false\":  { \"model\": \"modid:block/block_model_bottom_hinge\" },\n" +
        "        \"facing=south,half=lower,hinge=right,open=false\": { \"model\": \"modid:block/block_model_bottom_hinge\", \"y\": 90 },\n" +
        "        \"facing=west,half=lower,hinge=right,open=false\":  { \"model\": \"modid:block/block_model_bottom_hinge\", \"y\": 180 },\n" +
        "        \"facing=north,half=lower,hinge=right,open=false\": { \"model\": \"modid:block/block_model_bottom_hinge\", \"y\": 270 },\n" +
        "        \"facing=east,half=lower,hinge=left,open=true\":  { \"model\": \"modid:block/block_model_bottom_hinge\", \"y\": 90 },\n" +
        "        \"facing=south,half=lower,hinge=left,open=true\": { \"model\": \"modid:block/block_model_bottom_hinge\", \"y\": 180 },\n" +
        "        \"facing=west,half=lower,hinge=left,open=true\":  { \"model\": \"modid:block/block_model_bottom_hinge\", \"y\": 270 },\n" +
        "        \"facing=north,half=lower,hinge=left,open=true\": { \"model\": \"modid:block/block_model_bottom_hinge\" },\n" +
        "        \"facing=east,half=lower,hinge=right,open=true\":  { \"model\": \"modid:block/block_model_bottom\", \"y\": 270 },\n" +
        "        \"facing=south,half=lower,hinge=right,open=true\": { \"model\": \"modid:block/block_model_bottom\" },\n" +
        "        \"facing=west,half=lower,hinge=right,open=true\":  { \"model\": \"modid:block/block_model_bottom\", \"y\": 90 },\n" +
        "        \"facing=north,half=lower,hinge=right,open=true\": { \"model\": \"modid:block/block_model_bottom\", \"y\": 180 },\n" +
        "        \"facing=east,half=upper,hinge=left,open=false\":  { \"model\": \"modid:block/block_model_top\" },\n" +
        "        \"facing=south,half=upper,hinge=left,open=false\": { \"model\": \"modid:block/block_model_top\", \"y\": 90 },\n" +
        "        \"facing=west,half=upper,hinge=left,open=false\":  { \"model\": \"modid:block/block_model_top\", \"y\": 180 },\n" +
        "        \"facing=north,half=upper,hinge=left,open=false\": { \"model\": \"modid:block/block_model_top\", \"y\": 270 },\n" +
        "        \"facing=east,half=upper,hinge=right,open=false\":  { \"model\": \"modid:block/block_model_top_hinge\" },\n" +
        "        \"facing=south,half=upper,hinge=right,open=false\": { \"model\": \"modid:block/block_model_top_hinge\", \"y\": 90 },\n" +
        "        \"facing=west,half=upper,hinge=right,open=false\":  { \"model\": \"modid:block/block_model_top_hinge\", \"y\": 180 },\n" +
        "        \"facing=north,half=upper,hinge=right,open=false\": { \"model\": \"modid:block/block_model_top_hinge\", \"y\": 270 },\n" +
        "        \"facing=east,half=upper,hinge=left,open=true\":  { \"model\": \"modid:block/block_model_top_hinge\", \"y\": 90 },\n" +
        "        \"facing=south,half=upper,hinge=left,open=true\": { \"model\": \"modid:block/block_model_top_hinge\", \"y\": 180 },\n" +
        "        \"facing=west,half=upper,hinge=left,open=true\":  { \"model\": \"modid:block/block_model_top_hinge\", \"y\": 270 },\n" +
        "        \"facing=north,half=upper,hinge=left,open=true\": { \"model\": \"modid:block/block_model_top_hinge\" },\n" +
        "        \"facing=east,half=upper,hinge=right,open=true\":  { \"model\": \"modid:block/block_model_top\", \"y\": 270 },\n" +
        "        \"facing=south,half=upper,hinge=right,open=true\": { \"model\": \"modid:block/block_model_top\" },\n" +
        "        \"facing=west,half=upper,hinge=right,open=true\":  { \"model\": \"modid:block/block_model_top\", \"y\": 90 },\n" +
        "        \"facing=north,half=upper,hinge=right,open=true\": { \"model\": \"modid:block/block_model_top\", \"y\": 180 }\n" +
        "    }\n" +
        "}";

    static final String BUTTON = "{\n" +
        "    \"variants\": {\n" +
        "        \"face=floor,facing=east,powered=false\":  { \"model\": \"modid:block/block_model\", \"y\": 90 },\n" +
        "        \"face=floor,facing=west,powered=false\":  { \"model\": \"modid:block/block_model\", \"y\": 270 },\n" +
        "        \"face=floor,facing=south,powered=false\": { \"model\": \"modid:block/block_model\", \"y\": 180 },\n" +
        "        \"face=floor,facing=north,powered=false\": { \"model\": \"modid:block/block_model\" },\n" +
        "        \"face=wall,facing=east,powered=false\":  { \"model\": \"modid:block/block_model\", \"uvlock\": true, \"x\": 90, \"y\": 90 },\n" +
        "        \"face=wall,facing=west,powered=false\":  { \"model\": \"modid:block/block_model\", \"uvlock\": true, \"x\": 90, \"y\": 270 },\n" +
        "        \"face=wall,facing=south,powered=false\": { \"model\": \"modid:block/block_model\", \"uvlock\": true, \"x\": 90, \"y\": 180 },\n" +
        "        \"face=wall,facing=north,powered=false\": { \"model\": \"modid:block/block_model\", \"uvlock\": true, \"x\": 90 },\n" +
        "        \"face=ceiling,facing=east,powered=false\":  { \"model\": \"modid:block/block_model\", \"x\": 180, \"y\": 270 },\n" +
        "        \"face=ceiling,facing=west,powered=false\":  { \"model\": \"modid:block/block_model\", \"x\": 180, \"y\": 90 },\n" +
        "        \"face=ceiling,facing=south,powered=false\": { \"model\": \"modid:block/block_model\", \"x\": 180 },\n" +
        "        \"face=ceiling,facing=north,powered=false\": { \"model\": \"modid:block/block_model\", \"x\": 180, \"y\": 180 },\n" +
        "        \"face=floor,facing=east,powered=true\":  { \"model\": \"modid:block/block_model_pressed\", \"y\": 90 },\n" +
        "        \"face=floor,facing=west,powered=true\":  { \"model\": \"modid:block/block_model_pressed\", \"y\": 270 },\n" +
        "        \"face=floor,facing=south,powered=true\": { \"model\": \"modid:block/block_model_pressed\", \"y\": 180 },\n" +
        "        \"face=floor,facing=north,powered=true\": { \"model\": \"modid:block/block_model_pressed\" },\n" +
        "        \"face=wall,facing=east,powered=true\":  { \"model\": \"modid:block/block_model_pressed\", \"uvlock\": true, \"x\": 90, \"y\": 90 },\n" +
        "        \"face=wall,facing=west,powered=true\":  { \"model\": \"modid:block/block_model_pressed\", \"uvlock\": true, \"x\": 90, \"y\": 270 },\n" +
        "        \"face=wall,facing=south,powered=true\": { \"model\": \"modid:block/block_model_pressed\", \"uvlock\": true, \"x\": 90, \"y\": 180 },\n" +
        "        \"face=wall,facing=north,powered=true\": { \"model\": \"modid:block/block_model_pressed\", \"uvlock\": true, \"x\": 90 },\n" +
        "        \"face=ceiling,facing=east,powered=true\":  { \"model\": \"modid:block/block_model_pressed\", \"x\": 180, \"y\": 270 },\n" +
        "        \"face=ceiling,facing=west,powered=true\":  { \"model\": \"modid:block/block_model_pressed\", \"x\": 180, \"y\": 90 },\n" +
        "        \"face=ceiling,facing=south,powered=true\": { \"model\": \"modid:block/block_model_pressed\", \"x\": 180 },\n" +
        "        \"face=ceiling,facing=north,powered=true\": { \"model\": \"modid:block/block_model_pressed\", \"x\": 180, \"y\": 180 }\n" +
        "    }\n" +
        "}";

    static final String TRAPDOOR = "{\n" +
        "    \"variants\": {\n" +
        "        \"facing=north,half=bottom,open=false\": { \"model\": \"modid:block/block_model_bottom\" },\n" +
        "        \"facing=south,half=bottom,open=false\": { \"model\": \"modid:block/block_model_bottom\", \"y\": 180 },\n" +
        "        \"facing=east,half=bottom,open=false\": { \"model\": \"modid:block/block_model_bottom\", \"y\": 90 },\n" +
        "        \"facing=west,half=bottom,open=false\": { \"model\": \"modid:block/block_model_bottom\", \"y\": 270 },\n" +
        "        \"facing=north,half=top,open=false\": { \"model\": \"modid:block/block_model_top\" },\n" +
        "        \"facing=south,half=top,open=false\": { \"model\": \"modid:block/block_model_top\", \"y\": 180 },\n" +
        "        \"facing=east,half=top,open=false\": { \"model\": \"modid:block/block_model_top\", \"y\": 90 },\n" +
        "        \"facing=west,half=top,open=false\": { \"model\": \"modid:block/block_model_top\", \"y\": 270 },\n" +
        "        \"facing=north,half=bottom,open=true\": { \"model\": \"modid:block/block_model_open\" },\n" +
        "        \"facing=south,half=bottom,open=true\": { \"model\": \"modid:block/block_model_open\", \"y\": 180 },\n" +
        "        \"facing=east,half=bottom,open=true\": { \"model\": \"modid:block/block_model_open\", \"y\": 90 },\n" +
        "        \"facing=west,half=bottom,open=true\": { \"model\": \"modid:block/block_model_open\", \"y\": 270 },\n" +
        "        \"facing=north,half=top,open=true\": { \"model\": \"modid:block/block_model_open\", \"x\": 180, \"y\": 180 },\n" +
        "        \"facing=south,half=top,open=true\": { \"model\": \"modid:block/block_model_open\", \"x\": 180, \"y\": 0 },\n" +
        "        \"facing=east,half=top,open=true\": { \"model\": \"modid:block/block_model_open\", \"x\": 180, \"y\": 270 },\n" +
        "        \"facing=west,half=top,open=true\": { \"model\": \"modid:block/block_model_open\", \"x\": 180, \"y\": 90 }\n" +
        "    }\n" +
        "}\n";

    static final String SIDING = "{\n" +
        "\t\"variants\": {\n" +
        "\t\t\"facing=west,type=single\": { \"model\": \"derived_modid:block/block_model\" },\n" +
        "\t\t\"facing=east,type=single\": { \"model\": \"derived_modid:block/block_model\", \"y\": 180, \"uvlock\": true },\n" +
        "\t\t\"facing=north,type=single\": { \"model\": \"derived_modid:block/block_model\", \"y\": 90, \"uvlock\": true },\n" +
        "\t\t\"facing=south,type=single\": { \"model\": \"derived_modid:block/block_model\", \"y\": 270, \"uvlock\": true },\n" +
        "\t\t\"facing=east,type=double\": { \"model\": \"base_modid:block/base_model\" },\n" +
        "\t\t\"facing=west,type=double\": { \"model\": \"base_modid:block/base_model\" },\n" +
        "\t\t\"facing=south,type=double\": { \"model\": \"base_modid:block/base_model\" },\n" +
        "\t\t\"facing=north,type=double\": { \"model\": \"base_modid:block/base_model\" }\n" +
        "\t}\n" +
        "}\n";
}
