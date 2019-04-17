plugins {
	wrapper
	idea
	id("fabric-loom") version Fabric.Loom.version
	id("maven-publish")
	kotlin("jvm") version "1.3.21"
}

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
	targetCompatibility = JavaVersion.VERSION_1_8
}

idea {
	module {
		excludeDirs.add(file("run"))
	}
}

base {
	archivesBaseName = Constants.name
}

version = "v${Constants.version}+${Constants.minecraftVersionVer}"
group = "team.hollow"

allprojects {

	apply(plugin = "wrapper")
	apply(plugin = "idea")
	apply(plugin = "fabric-loom")
	apply(plugin = "maven-publish")
	apply(plugin = "kotlin")

	repositories {
		mavenCentral()
		maven("https://jitpack.io")
		maven("https://tehnut.info/maven")
		maven("https://maven.fabricmc.net")
		maven("https://minecraft.curseforge.com/api/maven")
		maven("https://maven.jamieswhiteshirt.com/libs-release/")
	}

	dependencies {
        minecraft(group = "com.mojang", name = "minecraft", version = Minecraft.version)
		mappings(group = "net.fabricmc", name = "yarn", version = "${Minecraft.version}+build.${Fabric.Yarn.version}")
		modCompile(group = "net.fabricmc", name = "fabric-loader", version = Fabric.Loader.version)
		compileOnly(group = "net.fabricmc", name = "fabric-loader", version = Fabric.Loader.version)

		modCompile(group = "io.github.prospector.modmenu", name = "ModMenu", version = Dependencies.ModMenu.version)
		
		include(group = "blue.endless", name = "jankson", version= Jetbrains.Jakson.version)
		implementation(group = "blue.endless", name = "jankson", version= Jetbrains.Jakson.version)

		modCompile(group = "net.fabricmc", name = "fabric", version = Fabric.API.version + ".+")
		include(group = "net.fabricmc", name = "fabric", version = Fabric.API.version + ".+")
    }
}

tasks.getByName<ProcessResources>("processResources") {
	filesMatching("fabric.mod.json") {
		expand(
				mutableMapOf(
						"version" to version
				)
		)
	}
}