package team.hollow.neutronia;

public interface IMinecraftInfo extends IModInfo {

    @Override
    default String getModNamespace() {
        return "minecraft";
    }

}