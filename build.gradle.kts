import io.izzel.taboolib.gradle.*


plugins {
    java
    id("io.izzel.taboolib") version "2.0.11"
    id("org.jetbrains.kotlin.jvm") version "1.9.22"
}

taboolib {
    env {
        install(NMS_UTIL)
        install(EXPANSION_COMMAND_HELPER)
        install(BUKKIT_ALL)
        install(UNIVERSAL)
        install(KETHER)
    }
    description {
        name = "YuItemAction"
        desc("A simple plugin for custom item actions")
        contributors {
            name("L1An")
        }
    }
    version { taboolib = "6.1.1" }
    relocate("org.serverct.parrot.parrotx", "${project.group}.parrotx")
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("ink.ptms.core:v12004:12004:mapped")
    compileOnly("ink.ptms.core:v12004:12004:universal")
    compileOnly("com.google.code.gson:gson:2.8.7")
    compileOnly(kotlin("stdlib"))
    compileOnly(fileTree("libs"))
    taboo(files("libs/module-parrotx-1.5.5-6.jar"))
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    sourceSets.all {
        languageSettings {
            languageVersion = "2.0"
        }
    }
}

tasks.withType<Jar> {
    destinationDirectory.set(file("/Users/yuxin/minecraft/servers/1.19.4Test/plugins"))
    //destinationDirectory.set(file("$projectDir/build-jar"))
}