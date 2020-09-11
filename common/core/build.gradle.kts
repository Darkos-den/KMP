import com.darkos.depend.applyDependencies
import com.darkos.depend.implementation
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")

    id("com.android.library")
    id("app-config-android")
}

applyMultiPlatformSourceSets()

val commonDeps = listOf(
    implementation(Libs.Kotlin.STDLIB),
    implementation(Libs.Coroutines.COMMON)
)

kotlin {
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                applyDependencies(commonDeps)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(Libs.Coroutines.ANDROID.full)
            }
        }
    }
}
