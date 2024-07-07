// Sorry for no libs.toml
plugins {
    kotlin("jvm") version "1.9.24"
    id("com.github.johnrengelman.shadow") version("8.1.1")

    id("io.papermc.paperweight.userdev") version "1.5.11"
    id("xyz.jpenilla.run-paper") version "2.2.3"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

val pluginName = name
val pluginVersion = "1.0-SNAPSHOT"
val packageGroup = "com.example"
val pluginMain = "$packageGroup.Plugin"
val pluginAuthors = listOf("Example Author")
val pluginDescription = "Example Plugin"


group = packageGroup
version = pluginVersion


repositories {
    mavenCentral()
    maven {
        name = "sonatype"
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }
    maven {
        name = "panda-repository"
        url = uri("https://repo.panda-lang.org/releases")
    }
}

dependencies {
    paperweight.paperDevBundle("1.20.4-R0.1-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // uncomment for access to configurate
//    implementation("org.spongepowered:configurate-yaml:4.1.2")

    // uncomment for access to cool command system
//    implementation("dev.rollczi:litecommands-bukkit:3.4.2")

    // uncomment for kotlin-coroutines support
//    implementation("com.github.shynixn.mccoroutine:mccoroutine-bukkit-api:2.18.0")
//    implementation("com.github.shynixn.mccoroutine:mccoroutine-bukkit-core:2.18.0")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")

    // uncomment for useful utilities
//    implementation("net.axay:kspigot:1.20.3")
}

val targetJavaVersion = 17
java {
    val javaVersion = JavaVersion.toVersion(targetJavaVersion)
    if (JavaVersion.current() < javaVersion) {
        toolchain.languageVersion = JavaLanguageVersion.of(targetJavaVersion)
    }
}

kotlin {
    jvmToolchain(targetJavaVersion)
}

tasks {
    build {
        dependsOn(shadowJar)
    }
    compileJava {
        options.encoding = "UTF-8"

        if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible) {
            options.release.set(targetJavaVersion)
        }
    }
    shadowJar {
        archiveBaseName.set("$pluginName v${project.version}.jar")
        // Configure shadowing here
    }
}

bukkit {
    main = pluginMain
    name = pluginName
    version = pluginVersion
    authors = pluginAuthors
    description = pluginDescription

    depend = depend?.plus(
        listOf(
            // Declare hard dependencies here
        )
    )

    softDepend = softDepend?.plus(
        listOf(
            // Declare soft dependencies here
        )
    )

    libraries = libraries?.plus(
        listOf(
            // Declare libraries here
        )
    )
}