import net.fabricmc.loom.task.RemapJarTask
import net.fabricmc.loom.task.RemapSourcesJarTask

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

version = "${Constants.version}+${Constants.minecraftVersionVer}"
group = "team.hollow"

repositories {
	mavenCentral()
	mavenLocal()
	maven("https://jitpack.io")
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
	mappings(group = "net.fabricmc", name = "yarn", version = "${Minecraft.version}+${Fabric.Yarn.version}")
	modCompile(group = "net.fabricmc", name = "fabric-loader", version = Fabric.Loader.version)

	modCompile(group = "net.fabricmc.fabric-api", name = "fabric-api", version = Fabric.API.version)
	include(group = "net.fabricmc.fabric-api", name = "fabric-api", version = Fabric.API.version)

	modCompile(group = "team.hollow", name = "AbnormaLib", version = Dependencies.AbnormaLib.version)
	include(group = "team.hollow", name = "AbnormaLib", version = Dependencies.AbnormaLib.version)

	modCompile(group = "cloth-config", name = "ClothConfig", version = Dependencies.ClothConfig.version)
	include(group = "cloth-config", name = "ClothConfig", version = Dependencies.ClothConfig.version)

	modCompile(group = "io.github.prospector.modmenu", name = "ModMenu", version = Dependencies.ModMenu.version)

	modCompile("org.apache.maven:maven-artifact:3.6.0")

	modCompile(group = "io.github.cottonmc", name = "cotton", version = "0.6.7+1.14.1-SNAPSHOT")
}

tasks.getByName<ProcessResources>("processResources") {
    doFirst {
		filesMatching("fabric.mod.json") {
			expand(
					mutableMapOf(
							"version" to version
					)
			)
		}
	}
}

tasks.register("cleanResources") {
	group = "resources"

	doFirst {
		var clearFiles = arrayOf("assets/neutronia/blockstates", "assets/neutronia/models/item", "assets/neutronia/models/block", "data/neutronia/loot_tables/blocks", "run/datapacks/cotton (generated)")

		var resourcesDir = file("src/main/resources")

		for(clear in clearFiles) {
			resourcesDir.resolve(clear).deleteRecursively()
		}
	}
}

tasks.register("setupGenResources") {
	group = "resources"
    doFirst {
		(tasks.getByPath("runClient") as JavaExec).systemProperty("genResources", true)
	}
}

tasks.register("genResources") {
	group = "resources"

	dependsOn("setupGenResources")
	dependsOn("runClient").mustRunAfter("setupGenResources")

	doLast {
		(tasks.getByPath("runClient") as JavaExec).systemProperty("genResources", false)
	}
}


val javaCompile = tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

val jar = tasks.getByName<Jar>("jar") {
    from("LICENSE")
}

val remapJar = tasks.getByName<RemapJarTask>("remapJar")

val remapSourcesJar = tasks.getByName<RemapSourcesJarTask>("remapSourcesJar")

publishing {
	publications {
		create<MavenPublication>("mavenJava") {
			artifactId = "Neutronia"
			artifact(jar) {
				builtBy(remapJar)
			}
			artifact(sourcesJar.get()) {
				builtBy(remapSourcesJar)
			}
			pom {
				name.set("Neutronia")
				description.set(Constants.description)
				url.set(Constants.url)
				licenses {
					license {
						name.set("The Apache License, Version 2.0")
						url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
					}
				}
				developers {
					developer {
						id.set("minecraft_abnormals")
						name.set("Minecraft Abnormals")
					}
				}
			}
		}
	}
}