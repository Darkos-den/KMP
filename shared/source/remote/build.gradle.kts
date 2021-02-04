import com.darkos.dependencies.AppLibs
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlin-android-extensions")
    id("dependencies")
    kotlin("plugin.serialization")
}

kotlin {
    android()
    ios()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(AppLibs.Coroutines.core)
                api(AppLibs.MVU.core)
                implementation(AppLibs.MVU.program)

                implementation(AppLibs.Ktor.core)
                implementation(AppLibs.Ktor.serialization)
                implementation(AppLibs.Ktor.json)
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")

                implementation(project(AppLibs.Modules.Splash.api))
                implementation(project(AppLibs.Modules.Auth.api))
            }
        }
        val commonTest by getting
        val androidMain by getting {
            dependencies {
                implementation(AppLibs.Ktor.okHttp)
            }
        }
        val androidTest by getting
        val iosMain by getting {
            dependencies {
                implementation(AppLibs.Ktor.ios)
            }
        }
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

    androidExtensions {
        isExperimental = true
    }
    (this as ExtensionAware).configure<KotlinJvmOptions> {
        jvmTarget = "1.8"
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
}