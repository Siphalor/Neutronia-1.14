package team.abnormals.neutronia.blocks;

public interface INeutroniaBlock extends IModBlockInfo {

    @Override
    default String getModNamespace() {
        return "neutronia";
    }

}