import net.fabricmc.loom.task.RemapJar
import net.fabricmc.loom.task.RemapSourcesJar

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
	maven("https://jitpack.io")
	mavenLocal()
	maven("https://tehnut.info/maven")
	maven("https://maven.fabricmc.net")
	maven("https://minecraft.curseforge.com/api/maven")
	maven("https://maven.jamieswhiteshirt.com/libs-release/")
    maven("http://maven.sargunv.s3-website-us-west-2.amazonaws.com/")

}

dependencies {
	minecraft(group = "com.mojang", name = "minecraft", version = Minecraft.version)
	mappings(group = "net.fabricmc", name = "yarn", version = "${Minecraft.version}+build.1")
	modCompile(group = "net.fabricmc", name = "fabric-loader", version = Fabric.Loader.version)

	modCompile(group = "net.fabricmc", name = "fabric", version = Fabric.API.version + ".+")
	include(group = "net.fabricmc", name = "fabric", version = Fabric.API.version + ".+")

	modCompile("org.apache.maven:maven-artifact:3.6.0")

	modCompile(group = "team.hollow", name = "AbnormaLib", version = "+")
	include(group = "team.hollow", name = "AbnormaLib", version = "+")
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

val remapJar = tasks.getByName<RemapJar>("remapJar")

val remapSourcesJar = tasks.getByName<RemapSourcesJar>("remapSourcesJar")

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