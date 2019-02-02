package team.abnormals.neutronia.blocks;

public interface IMinecraftBlock extends IModBlockInfo {

    @Override
    default String getModNamespace() {
        return "minecraft";
    }

}