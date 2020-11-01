import com.darkos.depend.applyDependencies
import com.darkos.depend.implementation

val commonDependencyList = listOf(
    implementation(Libs.Coroutines.COMMON),
    implementation(Libs.Serialization.CORE),
    implementation(Libs.Serialization.PROTOBUF),
    implementation(Libs.Kodein.ERASED)
)

val androidDependencyList = listOf(
    implementation(Libs.Coroutines.ANDROID),
    implementation(Libs.Serialization.CORE)
)

//val iosDeps = listOf(
//    implementation(Libs.Coroutines.NATIVE),
//    implementation(Libs.Serialization.NATIVE)
////    *Libs.Ktor.defaultIos
//)

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("org.jetbrains.kotlin.kapt")
    id("kotlin-android-extensions")

    id("com.android.library")
    id("app-config-android")
}

applyMultiPlatformSourceSets()

kotlin {
    android("android")

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                applyDependencies(commonDependencyList)
                implementation("com.darkosinc.mvu:core:1.0.0")

                implementation(project(":common:core"))
                implementation(project(":common:feature:initial:initial-api"))
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
                applyDependencies(androidDependencyList)
                implementation("com.darkosinc.mvu:core-jvm:1.0.0")

                implementation(project(":common:core"))
                implementation(project(":common:feature:initial:initial-api"))
            }
        }
    }
}
