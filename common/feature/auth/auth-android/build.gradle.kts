import com.darkos.config.android.ModuleType
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
    implementation(project(Modules.core))
    implementation(project(Modules.Auth.api))
}
