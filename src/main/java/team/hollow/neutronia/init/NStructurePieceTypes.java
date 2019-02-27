package team.hollow.neutronia.init;

import net.minecraft.structure.StructurePieceType;
import net.minecraft.structure.generator.PillagerOutpostGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Locale;

public interface NStructurePieceTypes {

    StructurePieceType PILLAGER_OUTPOST = register(PillagerOutpostGenerator.Piece::new, "PCP");

    static StructurePieceType register(StructurePieceType structurePieceType_1, String string_1) {
        return Registry.register(Registry.STRUCTURE_PIECE, new Identifier("neutronia", string_1.toLowerCase(Locale.ROOT)), structurePieceType_1);
    }

}
