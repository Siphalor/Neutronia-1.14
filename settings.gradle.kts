pluginManagement {
    repositories {
        jcenter()
        maven(url = "http://maven.fabricmc.net") {
            name = "FabricMC"
        }
        gradlePluginPortal()
    }
}


include("neutronia_building")