import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	wrapper
	idea
	id("fabric-loom") version Constants.fabricLoomVersion
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

		modCompile(group = "io.github.prospector.modmenu", name = "ModMenu", version = "+")
		
		modCompile(group = "cloth_config", name = "ClothConfig", version = "+")
		include(group = "cloth_config", name = "ClothConfig", version = "+")

		modCompile(project(":neutronia_building"))
		include(project(":neutronia_building"))

		modCompile(group = "cloth", name = "ClothEvents", version = "+")

		include("blue.endless:jankson:${Constants.jaksonVersion}")
		implementation("blue.endless:jankson:${Constants.jaksonVersion}")

		modCompile(group = "net.fabricmc", name = "fabric", version = Fabric.API.version + ".+")
		compileOnly(group = "net.fabricmc", name = "fabric", version = Fabric.API.version + ".+")
		include(group = "net.fabricmc", name = "fabric", version = Fabric.API.version + ".+")
		implementation(kotlin("stdlib-jdk8"))
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

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
	jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
	jvmTarget = "1.8"
}