import com.darkos.dependencies.AppLibs
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlin-android-extensions")
    id("dependencies")
}

kotlin {
    android()
    ios()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(AppLibs.Coroutines.core)
                implementation(AppLibs.MVU.core)
                implementation(AppLibs.MVU.program)

                implementation(project(AppLibs.Modules.Shared.utils))
            }
        }
        val commonTest by getting
        val androidMain by getting
        val androidTest by getting
        val iosMain by getting
        val iosTest by getting
    }
}
android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerVersion = "1.4.21-2"
        kotlinCompilerExtensionVersion = "1.0.0-alpha11"
    }

    androidExtensions {
        isExperimental = true
    }
    (this as ExtensionAware).configure<KotlinJvmOptions> {
        jvmTarget = "1.8"
    }
}

dependencies {
    applyAndroidDependencies()
    applyCompose()
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

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    kotlinOptions.freeCompilerArgs += "-Xallow-jvm-ir-dependencies"
}