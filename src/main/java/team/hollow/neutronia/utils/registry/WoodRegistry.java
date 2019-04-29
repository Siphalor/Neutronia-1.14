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

public class WoodRegistry {

    private Block log;
    private Block wood;
    private Block strippedLog;
    private Block strippedWood;
    private Block stairs;
    private Block slab;
    private Block planks;
    private Block patternedPlanks;
    private Block carvedPlanks;
    private Block leaves;
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

    private WoodRegistry(Identifier name, SaplingGenerator saplingGenerator) {
        this.name = name;
        this.saplingGenerator = saplingGenerator;
    }

    private WoodRegistry(Identifier name) {
        this.name = name;
        this.saplingGenerator = null;
    }

    public Block getLog() {
        return log;
    }

    public Block getWood() {
        return wood;
    }

    public Block getStrippedLog() {
        return strippedLog;
    }

    public Block getStrippedWood() {
        return strippedWood;
    }

    public Block getStairs() {
        return stairs;
    }

    public Block getSlab() {
        return slab;
    }

    public Block getPlanks() {
        return planks;
    }

    public Block getPatternedPlanks() {
        return patternedPlanks;
    }

    public Block getCarvedPlanks() {
        return carvedPlanks;
    }

    public Block getLeaves() {
        return leaves;
    }

    public Block getChest() {
        return chest;
    }

    public Block getSapling() {
        return sapling;
    }

    public Block getFence() {
        return fence;
    }

    public Block getFenceGate() {
        return fenceGate;
    }

    public Block getLectern() {
        return lectern;
    }

    public Block getPaperLantern() {
        return paperLantern;
    }

    public Block getLogCampfire() {
        return logCampfire;
    }

    public Block getStrippedLogCampfire() {
        return strippedLogCampfire;
    }

    public Block getBarrel() {
        return barrel;
    }

    public Block getBookshelf() {
        return bookshelf;
    }

    public Block getDoor() {
        return door;
    }

    public Block getTrapdoor() {
        return trapdoor;
    }

    public Block getButton() {
        return button;
    }

    public Block getPressurePlate() {
        return pressurePlate;
    }

    public Block getLadder() {
        return ladder;
    }

    public static class Builder {

        public Identifier name;
        private WoodRegistry woodRegistry;

        public Builder(Identifier name) {
            this.name = name;
            woodRegistry = new WoodRegistry(name);
        }

        public Builder(Identifier name, SaplingGenerator saplingGenerator) {
            this.name = name;
            woodRegistry = new WoodRegistry(name, saplingGenerator);
        }

        public Builder log() {
            woodRegistry.log = team.hollow.abnormalib.utils.registry.RegistryUtils.register(new PillarBlock(FabricBlockSettings.of(Material.WOOD, MaterialColor.SPRUCE).hardness(2.0F).sounds(BlockSoundGroup.WOOD).build()),
                    new Identifier(name.getNamespace(), name.getPath() + "_log"), ItemGroup.BUILDING_BLOCKS);
            return this;
        }

        public Builder wood() {
            woodRegistry.wood = team.hollow.abnormalib.utils.registry.RegistryUtils.register(new BaseModBlock(FabricBlockSettings.of(Material.WOOD, MaterialColor.WOOD).hardness(2.0F).sounds(BlockSoundGroup.WOOD)),
                    new Identifier(name.getNamespace(), name.getPath() + "_wood"), ItemGroup.BUILDING_BLOCKS);
            return this;
        }

        public Builder strippedLog() {
            woodRegistry.strippedLog = team.hollow.abnormalib.utils.registry.RegistryUtils.register(new PillarBlock(FabricBlockSettings.of(Material.WOOD, MaterialColor.SPRUCE).hardness(2.0F).sounds(BlockSoundGroup.WOOD)
                    .build()), new Identifier(name.getNamespace(), "stripped_" + name.getPath() + "_log"), ItemGroup.BUILDING_BLOCKS);
            return this;
        }

        public Builder strippedWood() {
            woodRegistry.strippedWood = team.hollow.abnormalib.utils.registry.RegistryUtils.register(new BaseModBlock(FabricBlockSettings.of(Material.WOOD, MaterialColor.WOOD).hardness(2.0F).sounds(BlockSoundGroup.WOOD)),
                    new Identifier(name.getNamespace(), "stripped_" + name.getPath() + "_wood"), ItemGroup.BUILDING_BLOCKS);
            return this;
        }

        public Builder stairs() {
            woodRegistry.stairs = team.hollow.abnormalib.utils.registry.RegistryUtils.register(new StairsBaseBlock(woodRegistry.planks.getDefaultState()), new Identifier(name.getNamespace(), name.getPath() + "_stairs"),
                    ItemGroup.BUILDING_BLOCKS);
            return this;
        }

        public Builder slab() {
            woodRegistry.slab = team.hollow.abnormalib.utils.registry.RegistryUtils.register(new SlabBaseBlock(), new Identifier(name.getNamespace(), name.getPath() + "_slab"), ItemGroup.BUILDING_BLOCKS);
            return this;
        }

        public Builder planks() {
            woodRegistry.planks = team.hollow.abnormalib.utils.registry.RegistryUtils.register(new BaseModBlock(FabricBlockSettings.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F)
                    .sounds(BlockSoundGroup.WOOD).build()), new Identifier(name.getNamespace(), name.getPath() + "_planks"), ItemGroup.BUILDING_BLOCKS);
            return this;
        }

        public Builder patternedPlanks() {
            woodRegistry.patternedPlanks = team.hollow.abnormalib.utils.registry.RegistryUtils.register(new BaseModBlock(FabricBlockSettings.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F)
                    .sounds(BlockSoundGroup.WOOD).build()), new Identifier(name.getNamespace(), "patterned_" + name.getPath() + "_planks"), ItemGroup.BUILDING_BLOCKS);
            return this;
        }

        public Builder carvedPlanks() {
            woodRegistry.carvedPlanks = team.hollow.abnormalib.utils.registry.RegistryUtils.register(new BaseModBlock(FabricBlockSettings.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F)
                    .sounds(BlockSoundGroup.WOOD).build()), new Identifier(name.getNamespace(), "carved_" + name.getPath() + "_planks"), ItemGroup.BUILDING_BLOCKS);
            return this;
        }

        public Builder leaves() {
            woodRegistry.leaves = team.hollow.abnormalib.utils.registry.RegistryUtils.register(new LeavesBaseBlock(), new Identifier(name.getNamespace(), name.getPath() + "_leaves"));
            return this;
        }

        public Builder coloredLeaves() {
            woodRegistry.leaves = team.hollow.abnormalib.utils.registry.RegistryUtils.register(new LeavesBaseBlock(), new Identifier(name.getNamespace(), name.getPath() + "_leaves"));
            NeutroniaLeavesColors.COLORED_LEAVES_LIST.add(woodRegistry.leaves);
            return this;
        }

        public Builder chest() {
            ChestBaseBlock chestBaseBlock = new ChestBaseBlock();
            woodRegistry.chest = team.hollow.abnormalib.utils.registry.RegistryUtils.register(chestBaseBlock, new Identifier(name.getNamespace(), name.getPath() + "_chest"));
            chestBaseBlock.setChestTexture(new Identifier(name.getNamespace(), "textures/entity/chest/" + name.getPath() + ".png"));
            chestBaseBlock.setDoubleChestTexture(new Identifier(name.getNamespace(), "textures/entity/chest/" + name.getPath() + "_double.png"));
            chestBaseBlock.setTrappedChestTexture(new Identifier(name.getNamespace(), "textures/entity/chest/" + name.getPath() + "_trapped.png"));
            chestBaseBlock.setTrappedDoubleChestTexture(new Identifier(name.getNamespace(), "textures/entity/chest/" + name.getPath() + "_trapped_double.png"));
            if(FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) ClientInit.CHEST_BLOCKS.add(chestBaseBlock);
            return this;
        }

        public Builder sapling() {
            woodRegistry.sapling = team.hollow.abnormalib.utils.registry.RegistryUtils.register(new SaplingBaseBlock(woodRegistry.saplingGenerator), new Identifier(name.getNamespace(), name.getPath() + "_sapling"));
            return this;
        }

        public Builder waterloggedSapling() {
            woodRegistry.sapling = team.hollow.abnormalib.utils.registry.RegistryUtils.register(new NeutroniaWaterloggedSaplingBlock(woodRegistry.saplingGenerator), new Identifier(name.getNamespace(), name.getPath() +
                    "_underwater_sapling"));
            return this;
        }

        public Builder fence() {
            woodRegistry.fence = team.hollow.abnormalib.utils.registry.RegistryUtils.register(new FenceBaseBlock(woodRegistry.planks.getDefaultState()), new Identifier(name.getNamespace(), name.getPath() + "_fence"));
            return this;
        }

        public Builder fenceGate() {
            woodRegistry.fenceGate = team.hollow.abnormalib.utils.registry.RegistryUtils.register(new FenceGateBaseBlock(), new Identifier(name.getNamespace(), name.getPath() + "_fence_gate"), ItemGroup.REDSTONE);
            return this;
        }

        public Builder lectern() {
            woodRegistry.lectern = team.hollow.abnormalib.utils.registry.RegistryUtils.register(new LecternBaseBlock(), new Identifier(name.getNamespace(), name.getPath() + "_lectern"));
            return this;
        }

        public Builder paperLantern() {
            woodRegistry.paperLantern = team.hollow.abnormalib.utils.registry.RegistryUtils.register(new BaseModBlock(Block.Settings.copy(woodRegistry.planks)), new Identifier(name.getNamespace(), name.getPath() + "_paper_lantern"));
            return this;
        }

        public Builder logCampfire() {
            woodRegistry.logCampfire = team.hollow.abnormalib.utils.registry.RegistryUtils.register(new CampfireBaseBlock(), new Identifier(name.getNamespace(), name.getPath() + "_campfire"));
            return this;
        }

        public Builder strippedLogCampfire() {
            woodRegistry.strippedLogCampfire = team.hollow.abnormalib.utils.registry.RegistryUtils.register(new CampfireBaseBlock(), new Identifier(name.getNamespace(), "stripped_" + name.getPath() +
                    "_campfire"));
            return this;
        }

        public Builder barrel() {
            woodRegistry.barrel = team.hollow.abnormalib.utils.registry.RegistryUtils.register(new BarrelBlock(Block.Settings.copy(woodRegistry.planks)), new Identifier(name.getNamespace(), name.getPath() + "_barrel"));
            return this;
        }

        public Builder bookshelf() {
            woodRegistry.bookshelf = team.hollow.abnormalib.utils.registry.RegistryUtils.register(new BaseModBlock(Block.Settings.copy(woodRegistry.planks)), new Identifier(name.getNamespace(), name.getPath() + "_bookshelf"));
            return this;
        }

        public Builder door() {
            woodRegistry.door = team.hollow.abnormalib.utils.registry.RegistryUtils.register(new DoorBaseBlock(Material.WOOD), new Identifier(name.getNamespace(), name.getPath() + "_door"), ItemGroup.REDSTONE);
            return this;
        }

        public Builder trapdoor() {
            woodRegistry.trapdoor = team.hollow.abnormalib.utils.registry.RegistryUtils.register(new TrapdoorBaseBlock(Material.WOOD), new Identifier(name.getNamespace(), name.getPath() + "_trapdoor"),
                    ItemGroup.REDSTONE);
            return this;
        }

        public Builder button() {
            woodRegistry.button = team.hollow.abnormalib.utils.registry.RegistryUtils.register(new ButtonBaseBlock(true), new Identifier(name.getNamespace(), name.getPath() + "_button"), ItemGroup.REDSTONE);
            return this;
        }

        public Builder pressurePlate(PressurePlateBlock.Type type) {
            woodRegistry.pressurePlate = team.hollow.abnormalib.utils.registry.RegistryUtils.register(new PressurePlateBaseBlock(Material.WOOD, type), new Identifier(name.getNamespace(),
                    name.getPath() + "_pressure_plate"), ItemGroup.REDSTONE);
            return this;
        }

        public Builder ladder() {
            woodRegistry.ladder = RegistryUtils.register(new CustomLadderBlock(), new Identifier(name.getNamespace(),
                    name.getPath() + "_ladder"), ItemGroup.DECORATIONS);
            return this;
        }

        public WoodRegistry build() {
            return woodRegistry;
        }

    }

}