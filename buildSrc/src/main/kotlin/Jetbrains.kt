object Jetbrains {
    private const val annotationsVersion = "16.0.3"
    const val annotations = "org.jetbrains:annotations:$annotationsVersion"

    object Kotlin {
        const val version = "1.3.30"
        const val stdLibJkd8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:$version"
    }

    object KotlinX {
        private const val coroutinesVersion = "1.2.0"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
        const val coroutinesJdk8 = "org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:$coroutinesVersion"
    }
    object Jakson {
        const val version = "1.1.0"
    }
}

object Fabric {
    object Loader {
        const val version = "0.4.1+build.126" // https://maven.fabricmc.net/net/fabricmc/fabric-loader/
    }

    object API {
        const val version = "0.2.7+build"
    }

    object Loom {
        const val version = "0.2.1-SNAPSHOT"
    }

    object Yarn {
        const val version = "+"
    }
    object LanguageKotlin {
        const val version = "${Jetbrains.Kotlin.version}+"
    }
}

object Dependencies {
    object ClothEvents {
        const val version = "0.3.1.23"
    }

    object ClothConfig {
        const val version = "0.1.2.6"
    }

    object ModMenu {
        const val version = "1.3.5-67"
    }

}

object Minecraft {
    const val version = "1.14 Pre-Release 5"
}