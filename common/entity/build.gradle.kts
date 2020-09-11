import com.darkos.depend.applyDependencies
import com.darkos.depend.implementation

val commonDependencyList = listOf(
    implementation(Libs.Coroutines.COMMON)
)

val androidDependencyList = listOf(
    implementation(Libs.Coroutines.ANDROID)
)

//val iosDeps = listOf(
//    implementation(Libs.Coroutines.NATIVE),
//    implementation(Libs.Serialization.NATIVE)
////    *Libs.Ktor.defaultIos
//)

plugins {
    id("org.jetbrains.kotlin.multiplatform")
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
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
                applyDependencies(androidDependencyList)
            }
        }
    }
}
