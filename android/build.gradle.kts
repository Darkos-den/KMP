import com.darkos.config.ModuleType
import com.darkos.config.applyVersionInfo
import com.darkos.dependencies.AppLibs

plugins {
    id("com.android.application")
    kotlin("android")
    id("dependencies")
    id("android-config")
}

configureAndroid {
    moduleType = ModuleType.APPLICATION

    enableCompose(true)
}

android {
    val major = 0
    val minor = 0
    val path = 1

    val code = 1

    applyVersionInfo(
        appVersionCode = code,
        appVersionName = "$major$minor$path"
    )

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
        viewBinding = true
    }
}

dependencies {
    implementation(AppLibs.Kotlin.stdlib)
    implementation(AppLibs.Kotlin.reflect)
    implementation(AppLibs.AndroidX.activityKtx)
    implementation(AppLibs.AndroidX.appCompatCore)
    implementation(AppLibs.Coroutines.android)
    implementation(AppLibs.Kodein.jvm)
    implementation(AppLibs.Kodein.android)

    implementation("com.github.Darkos-den.core2:presentation:1.0.10")
    implementation("com.darkosinc.mvu:core-jvm:1.0.0")
    implementation("com.github.Darkos-den.core2:mvu-program:1.0.10")

    implementation(project(":common:core"))

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
