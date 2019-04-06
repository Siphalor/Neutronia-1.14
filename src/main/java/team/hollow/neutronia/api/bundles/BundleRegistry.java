package team.hollow.neutronia.api.bundles;

public class BundleRegistry {

    private static IBundle bundle;

    public BundleRegistry(IBundle bundle) {
        BundleRegistry.bundle = bundle;
    }



}
