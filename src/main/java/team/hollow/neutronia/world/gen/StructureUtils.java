package team.hollow.neutronia.world.gen;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Property;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import net.minecraft.structure.rule.RandomBlockStateMatchRuleTest;

import java.util.ArrayList;
import java.util.List;

public class StructureUtils {

    @SuppressWarnings({
            "unchecked", "rawtypes"
    })
    private static List<StructureProcessorRule> getSameState(Block block, float probability, Block replace) {
        List<StructureProcessorRule> out = new ArrayList<>();
        for (BlockState bs : block.getStateFactory().getStates()) {
            BlockState rbs = replace.getDefaultState();
            for (Property prop : bs.getProperties()) {
                rbs = rbs.with(prop, bs.get(prop));
            }

            out.add(
                    new StructureProcessorRule(
                            new RandomBlockStateMatchRuleTest(bs, probability),
                            AlwaysTrueRuleTest.INSTANCE,
                            rbs
                    )
            );
        }
        return out;
    }

    private static List<StructureProcessorRule> getMossify(float mossify) {
        List<StructureProcessorRule> decay = new ArrayList<>();

        decay.add(
                new StructureProcessorRule(
                        new RandomBlockMatchRuleTest(Blocks.STONE_BRICKS, mossify),
                        AlwaysTrueRuleTest.INSTANCE,
                        Blocks.MOSSY_STONE_BRICKS.getDefaultState()
                )
        );
        decay.addAll(getSameState(Blocks.STONE_BRICK_SLAB, mossify, Blocks.MOSSY_STONE_BRICK_SLAB));
        decay.addAll(getSameState(Blocks.STONE_BRICK_STAIRS, mossify, Blocks.MOSSY_STONE_BRICK_STAIRS));
        decay.addAll(getSameState(Blocks.STONE_BRICK_WALL, mossify, Blocks.MOSSY_STONE_BRICK_WALL));

        decay.add(new StructureProcessorRule(
                new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, mossify),
                AlwaysTrueRuleTest.INSTANCE,
                Blocks.MOSSY_COBBLESTONE.getDefaultState()
        ));
        decay.addAll(getSameState(Blocks.COBBLESTONE_SLAB, mossify, Blocks.MOSSY_COBBLESTONE_SLAB));
        decay.addAll(getSameState(Blocks.COBBLESTONE_STAIRS, mossify, Blocks.MOSSY_COBBLESTONE_STAIRS));
        decay.addAll(getSameState(Blocks.COBBLESTONE_WALL, mossify, Blocks.MOSSY_COBBLESTONE_WALL));
        return decay;
    }

    private static List<StructureProcessorRule> getCrack(float crack) {
        List<StructureProcessorRule> decay = new ArrayList<>();

        decay.add(
                new StructureProcessorRule(
                        new RandomBlockMatchRuleTest(Blocks.STONE_BRICKS, crack),
                        AlwaysTrueRuleTest.INSTANCE,
                        Blocks.COBBLESTONE.getDefaultState()
                )
        );
        decay.addAll(getSameState(Blocks.STONE_BRICK_SLAB, crack, Blocks.COBBLESTONE_SLAB));
        decay.addAll(getSameState(Blocks.STONE_BRICK_STAIRS, crack, Blocks.COBBLESTONE_STAIRS));
        decay.addAll(getSameState(Blocks.STONE_BRICK_WALL, crack, Blocks.COBBLESTONE_WALL));

        decay.add(new StructureProcessorRule(
                new RandomBlockMatchRuleTest(Blocks.MOSSY_STONE_BRICKS, crack),
                AlwaysTrueRuleTest.INSTANCE,
                Blocks.MOSSY_COBBLESTONE.getDefaultState()
        ));
        decay.addAll(getSameState(Blocks.MOSSY_STONE_BRICK_SLAB, crack, Blocks.MOSSY_COBBLESTONE_SLAB));
        decay.addAll(getSameState(Blocks.MOSSY_STONE_BRICK_STAIRS, crack, Blocks.MOSSY_COBBLESTONE_STAIRS));
        decay.addAll(getSameState(Blocks.MOSSY_STONE_BRICK_WALL, crack, Blocks.MOSSY_COBBLESTONE_WALL));
        return decay;
    }

}
