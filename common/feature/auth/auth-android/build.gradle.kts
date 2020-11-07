import com.darkos.config.android.ModuleType
import com.darkos.dependencies.AppLibs.AndroidX
import com.darkos.dependencies.AppLibs.Coroutines
import com.darkos.dependencies.AppLibs.Kodein
import com.darkos.dependencies.AppLibs.Kotlin
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
    implementation(Kotlin.reflect)
    implementation(AndroidX.activityKtx)
    implementation(AndroidX.appCompatCore)
    implementation(Coroutines.android)
    implementation(Kodein.jvm)
    implementation(Kodein.android)

    implementation("androidx.navigation:navigation-fragment-ktx:2.3.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.1")

    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.5")

    implementation(project(Modules.core))
    implementation(project(Modules.Auth.api))
}
