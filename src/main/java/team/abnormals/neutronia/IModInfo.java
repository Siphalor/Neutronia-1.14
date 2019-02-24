package team.abnormals.neutronia;

public interface IModInfo {

    String getModNamespace();

    default String getPrefix() {
        return getModNamespace() + ":";
    }

}
