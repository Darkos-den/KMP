import com.darkos.dependencies.AppLibs

plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-android-extensions")
    id("dependencies")
}

android {
    compileSdkVersion(30)
    defaultConfig {
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
    buildFeatures {
        viewBinding = true
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }

    androidExtensions {
        isExperimental = true
    }

    composeOptions {
        kotlinCompilerVersion = "1.4.21-2"
        kotlinCompilerExtensionVersion = "1.0.0-alpha11"
    }
}

dependencies {
    applyAndroidDependencies()

    implementation(AppLibs.MVU.core)
    implementation(AppLibs.MVU.program)

    applyCompose()

    implementation(AppLibs.Kodein.core)
    implementation(AppLibs.Kodein.android)

    implementation(project(AppLibs.Modules.Splash.api))
    implementation(project(AppLibs.Modules.Auth.api))
    implementation(project(AppLibs.Modules.Dashboard.api))
    implementation(project(AppLibs.Modules.Item.api))
    implementation(project(AppLibs.Modules.Workspace.api))
    implementation(project(AppLibs.Modules.Shared.utils))

    debugImplementation(project(AppLibs.Modules.Shared.debugFeatures))

    implementation(project(AppLibs.Modules.Shared.appDi))
}

fun DependencyHandlerScope.applyCompose() {
    implementation(AppLibs.AndroidX.Compose.ui)
    implementation(AppLibs.AndroidX.Compose.tooling)
    implementation(AppLibs.AndroidX.Compose.foundation)
    implementation(AppLibs.AndroidX.Compose.material)
    implementation(AppLibs.AndroidX.Compose.materialIconsCore)
    implementation(AppLibs.AndroidX.Compose.materialIconsExtended)
    implementation(AppLibs.AndroidX.Compose.livedata)
    implementation(AppLibs.AndroidX.Compose.navigation)
}

fun DependencyHandlerScope.applyAndroidDependencies() {
    implementation(AppLibs.material)
    implementation(AppLibs.AndroidX.appCompat)
    implementation(AppLibs.AndroidX.constraintLayout)
    implementation(AppLibs.Coroutines.core)
    implementation(AppLibs.AndroidX.lifecycle)
}