@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    /*
     * Foojay Toolchains Resolver Convention plugin.
     * Automatically detects, downloads, and configures the required JDKs for the project using the Foojay Disco API,
     * enabling Gradle's Java toolchain management.
     */
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "WarrantyRoster"
include(":app")