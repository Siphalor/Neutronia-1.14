package team.hollow.neutronia.api.bundles;

public interface IBundle {

    String getBundleName();

    String getBundleVersion();

    void init();

}