import com.darkos.depend.applyDependencies
import com.darkos.depend.implementation

val androidDependencyList = listOf(
    implementation(Libs.Kotlin.STDLIB),
    implementation(Libs.Kotlin.REFLECT),
    implementation(Libs.AndroidX.Activity.KTX),
    implementation(Libs.AndroidX.AppCompat.CORE),
    *Libs.AndroidX.Compose.all,
    implementation(Libs.Coroutines.ANDROID),
    *Libs.Kodein.defaultAndroid
)

plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-kapt")
    id("app-config-android")
}

android {
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerVersion = "1.4.0"
        kotlinCompilerExtensionVersion = "1.0.0-alpha04"
    }

    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + "-Xallow-jvm-ir-dependencies" + "-Xskip-prerelease-check"
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    applyDependencies(androidDependencyList)
    implementation("com.darkosinc.mvu:core-jvm:1.0.0")

    implementation("com.github.Darkos-den.core2:presentation:1.0.10")
    implementation("com.github.Darkos-den.core2:mvu-program:1.0.10")

    implementation(project(":common:core"))
    implementation(project(":common:feature:initial:initial-api"))
}
