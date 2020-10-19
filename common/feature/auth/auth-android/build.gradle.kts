import com.darkos.depend.applyDependencies
import com.darkos.depend.implementation

val androidDependencyList = listOf(
    implementation(Libs.Coroutines.ANDROID),
    implementation(Libs.Serialization.CORE)
)

plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-kapt")
    id("app-config-android")
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    applyDependencies(androidDependencyList)
    implementation("com.darkosinc.mvu:core-jvm:1.0.0")

    implementation(project(":common:core"))
    implementation(project(":common:entity"))
}
