pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
        mavenCentral()
    }
}
rootProject.name = "kmp"

enableFeaturePreview("GRADLE_METADATA")

include(":androidApp")
include(":shared")

