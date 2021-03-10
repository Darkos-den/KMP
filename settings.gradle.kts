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
    ":shared:feature:dashboard:api",
    ":shared:feature:dashboard:reducer",
    ":shared:feature:dashboard:handler",
    ":shared:feature:dashboard:component",
    ":shared:feature:dashboard:di"
)

include(
    ":shared:feature:item:api",
    ":shared:feature:item:reducer",
    ":shared:feature:item:handler",
    ":shared:feature:item:component",
    ":shared:feature:item:di"
)

include(
    ":shared:feature:workspace:api",
    ":shared:feature:workspace:reducer",
    ":shared:feature:workspace:handler",
    ":shared:feature:workspace:component",
    ":shared:feature:workspace:di"
)

include(
    ":shared:feature:drawer:api",
    ":shared:feature:drawer:reducer",
    ":shared:feature:drawer:handler",
    ":shared:feature:drawer:component",
    ":shared:feature:drawer:di"
)

include(
    ":shared:common:utils",
    ":shared:common:debugFeatures",
    ":shared:common:secured"
)

include(
    ":shared:source:secure",
    ":shared:source:remote"
)