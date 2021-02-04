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
                api(AppLibs.MVU.core)
                implementation(AppLibs.MVU.program)

                implementation(AppLibs.Kotlin.datetime)

                implementation(project(AppLibs.Modules.Splash.api))
                implementation(project(AppLibs.Modules.Auth.api))
                implementation(project(":shared:common:secured"))
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