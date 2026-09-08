pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
    versionCatalogs {
        create("myLibs") {
            from(files("gradle/libs.versions.toml")) // Standard location
        }
    }
}

rootProject.name = "quiz"