package team.abnormals.neutronia;

public interface IMinecraftInfo extends IModInfo {

    @Override
    default String getModNamespace() {
        return "minecraft";
    }

}