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
include(":androidUi")

include(
    ":shared:feature:timer:api",
    ":shared:feature:timer:reducer",
    ":shared:feature:timer:handler",
    ":shared:feature:timer:component",
    ":shared:feature:timer:di"
)

