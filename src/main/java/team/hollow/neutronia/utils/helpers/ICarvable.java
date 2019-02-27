package team.hollow.neutronia.utils.helpers;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.enums.CarvedFaceTypes;

public interface ICarvable {

    String getFormatString();

    CarvedFaceTypes fromIdentifier(Identifier identifier);

    ICarvable newInstance(Identifier identifier);

    Block getUncarvedBlock();

}
