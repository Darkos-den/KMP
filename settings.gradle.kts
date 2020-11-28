pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
        mavenCentral()
    }
//    resolutionStrategy {
//        eachPlugin {
//            if (requested.id.namespace == "com.android" || requested.id.name == "kotlin-android-extensions") {
//                useModule("com.android.tools.build:gradle:4.2.0-alpha16")
//            }
//        }
//    }
}
rootProject.name = "kts"

includeBuild("config")
includeBuild("dependencies")

include(
    ":android",
    ":common:core",
    ":common:validation",

    ":common:source:remote",
    ":common:source:secure",

    ":common:feature:login-email:api",
    ":common:feature:login-email:reducer",
    ":common:feature:login-email:handler",

    ":common:feature:initial:api",
    ":common:feature:initial:android",
    ":common:feature:initial:reducer",
    ":common:feature:initial:handler",

    ":common:feature:auth:api",
    ":common:feature:auth:android",
    ":common:feature:auth:reducer",
    ":common:feature:auth:handler"
)
