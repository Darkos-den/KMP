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
        minSdkVersion(24)
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

    applyCompose()

    implementation("org.kodein.di:kodein-di:7.1.0")//todo
    implementation("org.kodein.di:kodein-di-framework-android-x:7.1.0")//todo

    implementation(project(":shared:appDi"))

    implementation(project(":androidUi"))
}

fun DependencyHandlerScope.applyCompose() {
    implementation(AppLibs.AndroidX.Compose.ui)
    implementation(AppLibs.AndroidX.Compose.tooling)
    implementation(AppLibs.AndroidX.Compose.foundation)
    implementation(AppLibs.AndroidX.Compose.material)
    implementation(AppLibs.AndroidX.Compose.materialIconsCore)
    implementation(AppLibs.AndroidX.Compose.materialIconsExtended)
    implementation(AppLibs.AndroidX.Compose.livedata)
//    implementation(AppLibs.AndroidX.Compose.navigation)
}

fun DependencyHandlerScope.applyAndroidDependencies() {
    implementation(AppLibs.material)
    implementation(AppLibs.AndroidX.appCompat)
    implementation(AppLibs.AndroidX.constraintLayout)
    implementation(AppLibs.Coroutines.core)
    implementation(AppLibs.AndroidX.lifecycle)
}