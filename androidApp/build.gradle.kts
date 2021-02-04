import com.darkos.dependencies.AppLibs

plugins {
    id("com.android.application")
    kotlin("android")
    id("dependencies")
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.darkos.kmp.androidApp"
        minSdkVersion(26)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
}

dependencies {
    applyAndroidDependencies()

    implementation(AppLibs.MVU.core)
    implementation(AppLibs.MVU.program)

    implementation(AppLibs.Kodein.core)
    implementation(AppLibs.Kodein.android)

    debugImplementation(project(AppLibs.Modules.Shared.debugFeatures))

    implementation(project(AppLibs.Modules.Shared.appDi))
    implementation(project(AppLibs.Modules.androidUi))
}

fun DependencyHandlerScope.applyAndroidDependencies() {
    implementation(AppLibs.material)
    implementation(AppLibs.AndroidX.appCompat)
    implementation(AppLibs.AndroidX.constraintLayout)
    implementation(AppLibs.Coroutines.core)
    implementation(AppLibs.AndroidX.lifecycle)
}