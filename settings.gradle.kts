pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
        mavenCentral()
    }
}
rootProject.name = "kmp"

includeBuild("dependencies")

enableFeaturePreview("GRADLE_METADATA")

include(":androidApp")
include(":shared")

