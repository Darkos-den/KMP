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

    ":common:feature:login-email:login-email-api",
    ":common:feature:login-email:login-email-reducer",
    ":common:feature:login-email:login-email-effect-handler",

    ":common:feature:initial:initial-api",
    ":common:feature:initial:initial-android",
    ":common:feature:initial:initial-reducer",
    ":common:feature:initial:initial-effect-handler",

    ":common:feature:auth:auth-api",
    ":common:feature:auth:auth-android",
    ":common:feature:auth:auth-reducer",
    ":common:feature:auth:auth-effect-handler"
)
