plugins {
  `java-library`
  id("io.papermc.paperweight.userdev") version "1.5.5"
  id("xyz.jpenilla.run-paper") version "2.1.0" // Adds runServer and runMojangMappedServer tasks for testing
}

group = "dev.dejay.nominimapsorry"
version = "1.0.0-SNAPSHOT"
description = "Test plugin for paperweight-userdev"

java {
  toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

repositories {
    maven {
        url = uri("https://repo.dmulloy2.net/repository/public/")
    }
    maven {
        url = uri("https://repo.codemc.io/repository/maven-snapshots/")
    }
    maven {
        url = uri("https://jitpack.io")
    }
}

dependencies {
  paperweight.paperDevBundle("1.20.1-R0.1-SNAPSHOT")
  compileOnly("com.comphenix.protocol:ProtocolLib:4.8.0")
  compileOnly("com.github.retrooper.packetevents:spigot:2.0-SNAPSHOT")
}

tasks {
  assemble {
    dependsOn(reobfJar)
  }

  compileJava {
    options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything

    // Set the release flag. This configures what version bytecode the compiler will emit, as well as what JDK APIs are usable.
    options.release.set(17)
  }

  javadoc {
    options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
  }

  processResources {
    filteringCharset = Charsets.UTF_8.name() // We want UTF-8 for everything
    val props = mapOf(
      "name" to project.name,
      "version" to project.version,
      "description" to project.description,
      "apiVersion" to "1.20"
    )
    inputs.properties(props)
    filesMatching("plugin.yml") {
      expand(props)
    }
  }
}
