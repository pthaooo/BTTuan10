pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        jcenter()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    // Define the dependency versions here
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
}

rootProject.name = "BT_29_3_2024"
include(":app")
 