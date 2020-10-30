import com.darkos.depend.applyDependencies
import com.darkos.depend.implementation

plugins {
    id("com.android.application")
    kotlin("android")
    id("app-config-android")
}

val dependencyList = listOf(
    implementation(Libs.Kotlin.STDLIB),
    implementation(Libs.Kotlin.REFLECT),
    implementation(Libs.AndroidX.Activity.KTX),
    implementation(Libs.AndroidX.AppCompat.CORE),
    *Libs.AndroidX.Compose.all,
    implementation(Libs.Coroutines.ANDROID),
    *Libs.Kodein.defaultAndroid
)

androidApplicationConfig()

android {
    defaultConfig {
        vectorDrawables.useSupportLibrary = true
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

    packagingOptions {
        pickFirst("META-INF/kotlinx-serialization-runtime.kotlin_module")
        pickFirst("META-INF/kotlinx-io.kotlin_module")
        pickFirst("META-INF/atomicfu.kotlin_module")
        pickFirst("META-INF/kotlinx-coroutines-io.kotlin_module")
        pickFirst("META-INF/ktor-http.kotlin_module")
        pickFirst("META-INF/ktor-utils.kotlin_module")
        pickFirst("META-INF/kotlinx-coroutines-core.kotlin_module")
        pickFirst("META-INF/ktor-client-core.kotlin_module")
        pickFirst("META-INF/ktor-http-cio.kotlin_module")
        pickFirst("META-INF/mvu.kotlin_module")
        pickFirst("META-INF/validation.kotlin_module")
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerVersion = "1.4.0"
        kotlinCompilerExtensionVersion = "1.0.0-alpha04"
    }

    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + "-Xallow-jvm-ir-dependencies" + "-Xskip-prerelease-check"
    }
}

dependencies {
    applyDependencies(dependencyList)
    implementation("com.github.Darkos-den.core2:presentation:1.0.10")
    implementation("com.darkosinc.mvu:core-jvm:1.0.0")
    implementation("com.github.Darkos-den.core2:mvu-program:1.0.10")

    implementation("com.github.aakira:napier-android:1.3.0")

    implementation(project(":common:core"))
    implementation(project(":common:entity"))

    implementation(project(":common:source:remote"))
    implementation(project(":common:source:secure"))

    //feature modules
    implementation(project(":common:feature:initial:initial-android"))
    implementation(project(":common:feature:initial:initial-api"))
    implementation(project(":common:feature:initial:initial-reducer"))
    implementation(project(":common:feature:initial:initial-effect-handler"))

    implementation(project(":common:feature:auth:auth-android"))
    implementation(project(":common:feature:auth:auth-api"))
    implementation(project(":common:feature:auth:auth-reducer"))
    implementation(project(":common:feature:auth:auth-effect-handler"))

    implementation("com.darkosinc.mvu:validation-api-jvm:1.0.4")
    implementation(project(":common:validation"))
}
