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

repositories {
	mavenCentral()
	mavenLocal()
	maven("https://tehnut.info/maven")
	maven("https://maven.fabricmc.net")
	maven("https://minecraft.curseforge.com/api/maven")
	maven("https://maven.jamieswhiteshirt.com/libs-release/")
    maven("http://maven.sargunv.s3-website-us-west-2.amazonaws.com/")
	maven("http://server.bbkr.space:8081/artifactory/libs-snapshot")
	maven("https://jitpack.io")
}

dependencies {
	minecraft(group = "com.mojang", name = "minecraft", version = Minecraft.version)
	mappings(group = "net.fabricmc", name = "yarn", version = "${Minecraft.version}+build.${Fabric.Yarn.version}")
	modCompile(group = "net.fabricmc", name = "fabric-loader", version = Fabric.Loader.version)

	modCompile(group = "net.fabricmc", name = "fabric", version = Fabric.API.version + ".+")
	include(group = "net.fabricmc", name = "fabric", version = Fabric.API.version + ".+")

	modCompile(group = "cloth-config", name = "ClothConfig", version = Dependencies.ClothConfig.version)
	include(group = "cloth-config", name = "ClothConfig", version = Dependencies.ClothConfig.version)
    modCompile(group = "com.github.siphalor", name = "tweed-api", version = Dependencies.Tweed.version)
	include(group = "com.github.siphalor", name = "tweed-api", version = Dependencies.Tweed.version)

	modCompile(group = "io.github.prospector.modmenu", name = "ModMenu", version = Dependencies.ModMenu.version)

	modCompile("org.apache.maven:maven-artifact:3.6.0")

	modCompile(group = "io.github.cottonmc", name = "cotton", version = "0.6.1+1.14-SNAPSHOT")

	//modCompile(group = "team.hollow", name = "AbnormaLib", version = "+")
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

tasks.getByName<JavaExec>("runClient") {
	if(System.getProperty("genResources") != null) {
		this.systemProperty("genResources", true)
	}
}