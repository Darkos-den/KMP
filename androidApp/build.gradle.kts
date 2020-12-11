plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
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

    composeOptions {
        kotlinCompilerVersion = "1.4.20"
        kotlinCompilerExtensionVersion = "1.0.0-alpha08"
    }
}
dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.2.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.2.0")

    implementation("com.darkosinc.MVU:core:0.1.0")
    implementation("com.darkosinc.MVU:program:0.2.2")

    implementation("androidx.compose.ui:ui:1.0.0-alpha08")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.0.0-alpha08")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:1.0.0-alpha08")
    // Material Design
    implementation("androidx.compose.material:material:1.0.0-alpha08")
    // Material design icons
    implementation("androidx.compose.material:material-icons-core:1.0.0-alpha08")
    implementation("androidx.compose.material:material-icons-extended:1.0.0-alpha08")
    // Integration with observables
    implementation("androidx.compose.runtime:runtime-livedata:1.0.0-alpha08")
}