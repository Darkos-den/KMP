plugins {
    id("com.android.application")
    kotlin("android")
}
group = "com.darkos.kmp"
version = "1.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
    maven(url = "https://dl.bintray.com/darkosinc/MVU")
}
dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.2.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.2.0")

    implementation("com.darkosinc.MVU:core:0.1.0")
    implementation("com.darkosinc.MVU:program:0.0.5")
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
    buildFeatures {
        viewBinding = true
    }
}