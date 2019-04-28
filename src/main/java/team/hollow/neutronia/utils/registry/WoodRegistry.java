package team.hollow.neutronia.utils.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.*;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import team.hollow.abnormalib.blocks.*;
import team.hollow.abnormalib.utils.registry.RegistryUtils;
import team.hollow.neutronia.blocks.ChestBaseBlock;
import team.hollow.neutronia.blocks.NeutroniaWaterloggedSaplingBlock;
import team.hollow.neutronia.client.ClientInit;
import team.hollow.neutronia.client.NeutroniaLeavesColors;

import java.util.Map;

public class WoodRegistry {

    private static Block log;
    private Block wood;
    private Block strippedLog;
    private Block strippedWood;
    private Block stairs;
    private Block slab;
    private Block planks;
    private Block patternedPlanks;
    private Block carvedPlanks;
    private static Block leaves;
    private Block chest;
    private Block sapling;
    private Block fence;
    private Block fenceGate;
    private Block lectern;
    private Block paperLantern;
    private Block logCampfire;
    private Block strippedLogCampfire;
    private Block barrel;
    private Block bookshelf;
    private Block door;
    private Block trapdoor;
    private Block button;
    private Block pressurePlate;
    private Block ladder;

    public Identifier name;
    private SaplingGenerator saplingGenerator;

    public static WoodRegistry getInstance(Identifier name, SaplingGenerator saplingGenerator) {
        return new WoodRegistry(name, saplingGenerator);
    }

    public static WoodRegistry getInstance(Identifier name, Map<Map<Block, Block>, SaplingGenerator> saplingGenerator) {
        return new WoodRegistry(name, saplingGenerator.values().stream().findAny().get());
    }

    private WoodRegistry(Identifier name, SaplingGenerator saplingGenerator) {
        this.name = name;
        this.saplingGenerator = saplingGenerator;
    }

    public WoodRegistry log() {
        log = RegistryUtils.register(new PillarBlock(FabricBlockSettings.of(Material.WOOD, MaterialColor.SPRUCE).hardness(2.0F).sounds(BlockSoundGroup.WOOD).build()),
                new Identifier(name.getNamespace(), name.getPath() + "_log"), ItemGroup.BUILDING_BLOCKS);
        return this;
    }

    public WoodRegistry wood() {
        this.wood = RegistryUtils.register(new BaseModBlock(FabricBlockSettings.of(Material.WOOD, MaterialColor.WOOD).hardness(2.0F).sounds(BlockSoundGroup.WOOD)),
                new Identifier(name.getNamespace(), name.getPath() + "_wood"), ItemGroup.BUILDING_BLOCKS);
        return this;
    }

    public WoodRegistry strippedLog() {
        this.strippedLog = RegistryUtils.register(new PillarBlock(FabricBlockSettings.of(Material.WOOD, MaterialColor.SPRUCE).hardness(2.0F).sounds(BlockSoundGroup.WOOD)
                .build()), new Identifier(name.getNamespace(), "stripped_" + name.getPath() + "_log"), ItemGroup.BUILDING_BLOCKS);
        return this;
    }

    public WoodRegistry strippedWood() {
        this.strippedWood = RegistryUtils.register(new BaseModBlock(FabricBlockSettings.of(Material.WOOD, MaterialColor.WOOD).hardness(2.0F).sounds(BlockSoundGroup.WOOD)),
                new Identifier(name.getNamespace(), "stripped_" + name.getPath() + "_wood"), ItemGroup.BUILDING_BLOCKS);
        return this;
    }

    public WoodRegistry stairs() {
        this.stairs = RegistryUtils.register(new StairsBaseBlock(planks.getDefaultState()), new Identifier(name.getNamespace(), name.getPath() + "_stairs"),
                ItemGroup.BUILDING_BLOCKS);
        return this;
    }

    public WoodRegistry slab() {
        this.slab = RegistryUtils.register(new SlabBaseBlock(), new Identifier(name.getNamespace(), name.getPath() + "_slab"), ItemGroup.BUILDING_BLOCKS);
        return this;
    }

    public WoodRegistry planks() {
        this.planks = RegistryUtils.register(new BaseModBlock(FabricBlockSettings.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F)
                .sounds(BlockSoundGroup.WOOD).build()), new Identifier(name.getNamespace(), name.getPath() + "_planks"), ItemGroup.BUILDING_BLOCKS);
        return this;
    }

    public WoodRegistry patternedPlanks() {
        this.patternedPlanks = RegistryUtils.register(new BaseModBlock(FabricBlockSettings.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F)
                .sounds(BlockSoundGroup.WOOD).build()), new Identifier(name.getNamespace(), "patterned_" + name.getPath() + "_planks"), ItemGroup.BUILDING_BLOCKS);
        return this;
    }

    public WoodRegistry carvedPlanks() {
        this.carvedPlanks = RegistryUtils.register(new BaseModBlock(FabricBlockSettings.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F)
                .sounds(BlockSoundGroup.WOOD).build()), new Identifier(name.getNamespace(), "carved_" + name.getPath() + "_planks"), ItemGroup.BUILDING_BLOCKS);
        return this;
    }

    public WoodRegistry leaves() {
        leaves = RegistryUtils.register(new LeavesBaseBlock(), new Identifier(name.getNamespace(), name.getPath() + "_leaves"));
        return this;
    }

    public WoodRegistry coloredLeaves() {
        leaves = RegistryUtils.register(new LeavesBaseBlock(), new Identifier(name.getNamespace(), name.getPath() + "_leaves"));
        NeutroniaLeavesColors.COLORED_LEAVES_LIST.add(leaves);
        return this;
    }

    public WoodRegistry chest() {
        ChestBaseBlock chestBaseBlock = new ChestBaseBlock();
        this.chest = RegistryUtils.register(chestBaseBlock, new Identifier(name.getNamespace(), name.getPath() + "_chest"));
        chestBaseBlock.setChestTexture(new Identifier(name.getNamespace(), "textures/entity/chest/" + name.getPath() + ".png"));
        chestBaseBlock.setDoubleChestTexture(new Identifier(name.getNamespace(), "textures/entity/chest/" + name.getPath() + "_double.png"));
        chestBaseBlock.setTrappedChestTexture(new Identifier(name.getNamespace(), "textures/entity/chest/" + name.getPath() + "_trapped.png"));
        chestBaseBlock.setTrappedDoubleChestTexture(new Identifier(name.getNamespace(), "textures/entity/chest/" + name.getPath() + "_trapped_double.png"));
        if(FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) ClientInit.CHEST_BLOCKS.add(chestBaseBlock);
        return this;
    }

    public WoodRegistry sapling() {
        this.sapling = RegistryUtils.register(new SaplingBaseBlock(saplingGenerator), new Identifier(name.getNamespace(), name.getPath() + "_sapling"));
        return this;
    }

    public WoodRegistry waterloggedSapling() {
        this.sapling = RegistryUtils.register(new NeutroniaWaterloggedSaplingBlock(saplingGenerator), new Identifier(name.getNamespace(), name.getPath() +
                "_underwater_sapling"));
        return this;
    }

    public WoodRegistry fence() {
        this.fence = RegistryUtils.register(new FenceBaseBlock(planks.getDefaultState()), new Identifier(name.getNamespace(), name.getPath() + "_fence"));
        return this;
    }

    public WoodRegistry fenceGate() {
        this.fenceGate = RegistryUtils.register(new FenceGateBaseBlock(), new Identifier(name.getNamespace(), name.getPath() + "_fence_gate"), ItemGroup.REDSTONE);
        return this;
    }

    public WoodRegistry lectern() {
        this.lectern = RegistryUtils.register(new LecternBaseBlock(), new Identifier(name.getNamespace(), name.getPath() + "_lectern"));
        return this;
    }

    public WoodRegistry paperLantern() {
        this.paperLantern = RegistryUtils.register(new BaseModBlock(Block.Settings.copy(planks)), new Identifier(name.getNamespace(), name.getPath() + "_paper_lantern"));
        return this;
    }

    public WoodRegistry logCampfire() {
        this.logCampfire = RegistryUtils.register(new CampfireBaseBlock(), new Identifier(name.getNamespace(), name.getPath() + "_campfire"));
        return this;
    }

    public WoodRegistry strippedLogCampfire() {
        this.strippedLogCampfire = RegistryUtils.register(new CampfireBaseBlock(), new Identifier(name.getNamespace(), "stripped_" + name.getPath() +
                "_campfire"));
        return this;
    }

    public WoodRegistry barrel() {
        this.barrel = RegistryUtils.register(new BarrelBlock(Block.Settings.copy(planks)), new Identifier(name.getNamespace(), name.getPath() + "_barrel"));
        return this;
    }

    public WoodRegistry bookshelf() {
        this.bookshelf = RegistryUtils.register(new BaseModBlock(Block.Settings.copy(planks)), new Identifier(name.getNamespace(), name.getPath() + "_bookshelf"));
        return this;
    }

    public WoodRegistry door() {
        this.door = RegistryUtils.register(new DoorBaseBlock(Material.WOOD), new Identifier(name.getNamespace(), name.getPath() + "_door"), ItemGroup.REDSTONE);
        return this;
    }

    public WoodRegistry trapdoor() {
        this.trapdoor = RegistryUtils.register(new TrapdoorBaseBlock(Material.WOOD), new Identifier(name.getNamespace(), name.getPath() + "_trapdoor"),
                ItemGroup.REDSTONE);
        return this;
    }

    public WoodRegistry button() {
        this.button = RegistryUtils.register(new ButtonBaseBlock(true), new Identifier(name.getNamespace(), name.getPath() + "_button"), ItemGroup.REDSTONE);
        return this;
    }

    public WoodRegistry pressurePlate(PressurePlateBlock.Type type) {
        this.pressurePlate = RegistryUtils.register(new PressurePlateBaseBlock(Material.WOOD, type), new Identifier(name.getNamespace(),
                name.getPath() + "_pressure_plate"), ItemGroup.REDSTONE);
        return this;
    }

    public WoodRegistry ladder() {
        this.ladder = RegistryUtils.register(new CustomLadderBlock(), new Identifier(name.getNamespace(),
                name.getPath() + "_ladder"), ItemGroup.DECORATIONS);
        return this;
    }

}