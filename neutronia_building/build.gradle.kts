plugins {
	id("fabric-loom")
}
dependencies {
	minecraft(group = "com.mojang", name = "minecraft", version = Minecraft.version)
	mappings(group = "net.fabricmc", name = "yarn", version = "${Minecraft.version}+build.${Fabric.Yarn.version}")
	modCompile(group = "net.fabricmc", name = "fabric-loader", version = Fabric.Loader.version)
	compileOnly(group = "net.fabricmc", name = "fabric-loader", version = Fabric.Loader.version)

	modCompile(group = "io.github.prospector.modmenu", name = "ModMenu", version = "+")

	modCompile(group = "cloth_config", name = "ClothConfig", version = "+")
	include(group = "cloth_config", name = "ClothConfig", version = "+")

	modCompile(group = "cloth", name = "ClothEvents", version = "+")

	include("blue.endless:jankson:${Constants.jaksonVersion}")
	implementation("blue.endless:jankson:${Constants.jaksonVersion}")

	modCompile(group = "net.fabricmc", name = "fabric", version = Fabric.API.version + ".+")
	compileOnly(group = "net.fabricmc", name = "fabric", version = Fabric.API.version + ".+")
	include(group = "net.fabricmc", name = "fabric", version = Fabric.API.version + ".+")
	implementation(kotlin("stdlib-jdk8"))
}