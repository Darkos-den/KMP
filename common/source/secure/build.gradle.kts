import com.darkos.depend.applyDependencies
import com.darkos.depend.implementation
import com.darkos.depend.stringDepend

val commonDependencyList = listOf(
    stringDepend {
        "implementation" to "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.8"
    },
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

                implementation(project(":common:feature:initial:initial-api"))
                implementation(project(":common:feature:auth:auth-api"))
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
                applyDependencies(androidDependencyList)

                implementation("androidx.security:security-crypto:1.1.0-alpha02")

                implementation(project(":common:feature:initial:initial-api"))
                implementation(project(":common:feature:auth:auth-api"))
            }
        }
    }
}
