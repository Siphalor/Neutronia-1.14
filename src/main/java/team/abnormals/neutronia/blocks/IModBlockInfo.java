package team.abnormals.neutronia.blocks;

public interface IModBlockInfo {

    String getModNamespace();

    default String getPrefix() {
        return getModNamespace() + ":";
    }

}
