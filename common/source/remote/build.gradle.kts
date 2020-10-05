import com.darkos.depend.applyDependencies
import com.darkos.depend.implementation

val commonDependencyList = listOf(
    implementation(Libs.Ktor.CORE),
    implementation(Libs.Ktor.LOGGING),
    implementation(Libs.Ktor.JSON),
    implementation(Libs.Ktor.SERIALIZATION),
    implementation(Libs.Coroutines.COMMON),
    implementation(Libs.Serialization.CORE),
    implementation(Libs.Serialization.PROTOBUF),
    implementation(Libs.Kodein.ERASED)
)

val androidDependencyList = listOf(
    implementation(Libs.Ktor.ANDROID),
    implementation(Libs.Ktor.LOGGING_JVM),
    implementation(Libs.Ktor.JSON_JVM),
    implementation(Libs.Ktor.SERIALIZATION_JVM),
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

                implementation("com.github.aakira:napier:1.3.0")

                implementation(project(":common:entity"))
                implementation(project(":common:feature:signin:signin-api"))
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
                applyDependencies(androidDependencyList)

                implementation("com.github.aakira:napier-android:1.3.0")

                implementation(project(":common:entity"))
                implementation(project(":common:feature:signin:signin-api"))
            }
        }
    }
}
