import com.darkos.config.android.ModuleType
import com.darkos.dependencies.AppLibs.AndroidX
import com.darkos.dependencies.AppLibs.Coroutines
import com.darkos.dependencies.AppLibs.Kodein
import com.darkos.dependencies.AppLibs.Kotlin
import com.darkos.dependencies.AppLibs.Modules

plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-kapt")
    id("dependencies")
    id("android-config")
}

configureAndroid {
    moduleType = ModuleType.LIBRARY

    useDefaultDependencies()
    enableCompose(true)
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Kotlin.reflect)
    implementation(AndroidX.activityKtx)
    implementation(AndroidX.appCompatCore)
    implementation(Coroutines.android)
    implementation(Kodein.jvm)
    implementation(Kodein.android)

    implementation(project(Modules.core))
    implementation(project(Modules.Initial.api))
}
