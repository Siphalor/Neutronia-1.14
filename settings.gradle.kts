pluginManagement {
    repositories {
        jcenter()
        maven(url = "http://maven.fabricmc.net") {
            name = "FabricMC"
        }
        gradlePluginPortal()
    }
}

include("questing_villagers")
include("Villages_&_Villagers")
include("Endecorations")
include("Decorations")
include("Questing_Villagers")