import com.darkos.dependencies.AppLibs
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("dependencies")
}

kotlin {
    android()
//    ios()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(AppLibs.Coroutines.core)
                implementation(AppLibs.MVU.core)
                implementation(AppLibs.MVU.program)

                implementation(project(AppLibs.Modules.Dashboard.api))
            }
        }
        val commonTest by getting
        val androidMain by getting
        val androidTest by getting {
            dependencies {
                implementation("org.junit.jupiter:junit-jupiter-api:5.7.0")//todo
                runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
            }
        }
//        val iosMain by getting
//        val iosTest by getting
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
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
    (this as ExtensionAware).configure<KotlinJvmOptions> {
        jvmTarget = "1.8"
    }
}