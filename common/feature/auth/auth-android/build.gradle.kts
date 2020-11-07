import com.darkos.config.android.ModuleType
import com.darkos.dependencies.AppLibs
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

    useDefaultDependencies(
        activityKtx = AppLibs.AndroidX.Versions.activityKtx,
        appcompat = AppLibs.AndroidX.Versions.appCompat,
        mvuCore = AppLibs.MVU.Version.core,
        coroutines = AppLibs.Coroutines.version,
        kodein = AppLibs.Kodein.version,
        kotlin = AppLibs.Kotlin.version,
        mvuProgram = AppLibs.Core.version,
        navigation = AppLibs.AndroidX.navigation
    )
    enableCompose(
        kotlinVersion = AppLibs.Kotlin.version,
        extensionVersion = AppLibs.Kotlin.extensionVersion,
        useDefaultDependency = true,
        version = AppLibs.AndroidX.Compose.version
    )
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.Auth.api))
}
