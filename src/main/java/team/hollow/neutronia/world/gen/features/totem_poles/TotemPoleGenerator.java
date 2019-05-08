package team.hollow.neutronia.world.gen.features.totem_poles;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.*;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.Heightmap;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import team.hollow.neutronia.ExampleMod;

import java.util.List;
import java.util.Random;

public class TotemPoleGenerator {

    public static final Identifier id = new Identifier("neutronia:totempoles");

    //This tells the game to create the structure defined above as a piece
    public static void addParts(StructureManager structureManager, BlockPos blockPos, BlockRotation rotation, List<StructurePiece> list_1, Random random, DefaultFeatureConfig featureConfig) {
        list_1.add(new Piece(structureManager, id, blockPos, rotation));
    }

    //To summarize this code creates and then builds the piece
    public static class Piece extends SimpleStructurePiece {
        private BlockRotation rotation;
        private Identifier template;

        public Piece(StructureManager structureManager, Identifier identifier, BlockPos blockPos, BlockRotation rotation) {
            super(ExampleMod.TOTEM_POLE_PIECE_TYPE, 0);

            this.pos = blockPos;
            this.rotation = rotation;
            this.template = identifier;

            this.setStructureData(structureManager);
        }

        public Piece(StructureManager structureManager, CompoundTag compoundTag) {
            super(ExampleMod.TOTEM_POLE_PIECE_TYPE, compoundTag);
            this.template = new Identifier(compoundTag.getString("Template"));
            this.rotation = BlockRotation.valueOf(compoundTag.getString("Rot"));
            this.setStructureData(structureManager);
        }

        @Override
        protected void toNbt(CompoundTag compoundTag) {
            super.toNbt(compoundTag);
            compoundTag.putString("Template", this.template.toString());
            compoundTag.putString("Rot", this.rotation.name());
        }

        public void setStructureData(StructureManager structureManager) {
            Structure structure = structureManager.getStructureOrBlank(this.template);
            StructurePlacementData structurePlacementData = (new StructurePlacementData()).setRotation(this.rotation).setMirrored(BlockMirror.NONE).setPosition(pos).addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);
            this.setStructureData(structure, this.pos, structurePlacementData);
        }

        @Override
        protected void handleMetadata(String string, BlockPos blockPos, IWorld iWorld, Random random, MutableIntBoundingBox mutableIntBoundingBox) {

        }

        //And finally this builds the totem pole
        @Override
        public boolean generate(IWorld iWorld, Random random, MutableIntBoundingBox mutableIntBoundingBox, ChunkPos chunkPos) {
            int yHeight = iWorld.getTop(Heightmap.Type.WORLD_SURFACE_WG, this.pos.getX() + 8, this.pos.getZ() + 8);
            this.pos = this.pos.add(0, yHeight - 1, 0);
            return super.generate(iWorld, random, mutableIntBoundingBox, chunkPos);
        }
    }
}
