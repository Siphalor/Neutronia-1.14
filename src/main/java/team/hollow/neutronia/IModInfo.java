package team.hollow.neutronia;

public interface IModInfo {

    String getModNamespace();

    default String getPrefix() {
        return getModNamespace() + ":";
    }

}
