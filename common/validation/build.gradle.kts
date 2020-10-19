import com.darkos.depend.applyDependencies
import com.darkos.depend.implementation
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("org.jetbrains.kotlin.kapt")

    id("com.android.library")
    id("app-config-android")
}

applyMultiPlatformSourceSets()

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

kotlin {
    android("android")

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                applyDependencies(commonDependencyList)
                implementation("com.darkosinc.mvu:core:1.0.0")
                implementation("com.darkosinc.mvu:validation-api:1.0.4")
                implementation("com.darkosinc.mvu:validation:1.0.3")
                implementation(project(":common:feature:login-email:login-email-api"))
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
                applyDependencies(androidDependencyList)
                implementation("com.darkosinc.mvu:core-jvm:1.0.0")
                implementation("com.darkosinc.mvu:validation-api-jvm:1.0.4")
                implementation("com.darkosinc.mvu:validation-jvm:1.0.3")
                implementation(project(":common:feature:login-email:login-email-api"))
            }
        }
    }
}
