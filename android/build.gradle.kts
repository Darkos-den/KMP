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

    implementation(AppLibs.Core.presentation)
    implementation(AppLibs.Core.program)
    implementation(AppLibs.MVU.coreJvm)
    implementation(AppLibs.MVU.validationApiJvm)

    implementation(project(AppLibs.Modules.validation))

    implementation(project(AppLibs.Modules.core))

    implementation(project(AppLibs.Modules.Source.remote))
    implementation(project(AppLibs.Modules.Source.secure))

    //feature modules
    implementation(project(AppLibs.Modules.Initial.android))
    implementation(project(AppLibs.Modules.Initial.api))
    implementation(project(AppLibs.Modules.Initial.reducer))
    implementation(project(AppLibs.Modules.Initial.effectHandler))

    implementation(project(AppLibs.Modules.Auth.android))
    implementation(project(AppLibs.Modules.Auth.api))
    implementation(project(AppLibs.Modules.Auth.reducer))
    implementation(project(AppLibs.Modules.Auth.effectHandler))
}
