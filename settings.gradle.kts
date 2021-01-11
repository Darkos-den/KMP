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

include(":shared:appDi")

include(
    ":shared:feature:splash:api",
    ":shared:feature:splash:reducer",
    ":shared:feature:splash:handler",
    ":shared:feature:splash:component",
    ":shared:feature:splash:di"
)

include(
    ":shared:feature:auth:api",
    ":shared:feature:auth:reducer",
    ":shared:feature:auth:handler",
    ":shared:feature:auth:component",
    ":shared:feature:auth:di"
)

include(
    ":shared:common:utils"
)

include(
    ":shared:source:secure",
    ":shared:source:remote"
)