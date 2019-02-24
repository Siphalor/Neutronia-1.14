package team.abnormals.neutronia;

public interface INeutroniaInfo extends IModInfo {

    @Override
    default String getModNamespace() {
        return "neutronia";
    }

}