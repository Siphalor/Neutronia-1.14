package team.abnormals.neutronia.init;

import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.abnormals.neutronia.Neutronia;

public class NPaintingMotives {

    public static final PaintingMotive BOOKSHELF = register("bookshelf", 32, 64);
    public static final PaintingMotive TAIGA_LAKE = register("taiga_lake", 16, 16);

    private static PaintingMotive register(String string_1, int int_1, int int_2) {
        return Registry.register(Registry.MOTIVE, new Identifier(Neutronia.MODID, string_1), new PaintingMotive(int_1, int_2));
    }

}