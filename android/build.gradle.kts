import com.darkos.config.android.ModuleType
import com.darkos.config.android.applyVersionInfo
import com.darkos.dependencies.AppLibs
import com.darkos.dependencies.AppLibs.Modules
import com.darkos.dependencies.AppLibs.Modules.Auth
import com.darkos.dependencies.AppLibs.Modules.Initial
import com.darkos.dependencies.AppLibs.Modules.Source

plugins {
    id("com.android.application")
    kotlin("android")
    id("dependencies")
    id("android-config")
}

configureAndroid {
    moduleType = ModuleType.APPLICATION

    useDefaultDependencies(
        activityKtx = AppLibs.AndroidX.Versions.activityKtx,
        appcompat = AppLibs.AndroidX.Versions.appCompat,
        mvuCore = AppLibs.MVU.Version.core,
        coroutines = AppLibs.Coroutines.version,
        kodein = AppLibs.Kodein.version,
        kotlin = AppLibs.Kotlin.version,
        mvuProgram = AppLibs.Core.version,
        navigation = AppLibs.AndroidX.navigation
    )
    enableCompose(
        kotlinVersion = AppLibs.Kotlin.version,
        extensionVersion = AppLibs.Kotlin.extensionVersion,
        useDefaultDependency = true,
        version = AppLibs.AndroidX.Compose.version
    )
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
        pickFirst("META-INF/login-api.kotlin_module")
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(AppLibs.MVU.Validation.api)

    implementation("com.darkosinc.mvu:navigator-androidRelease:1.0.1")

    implementation(project(Modules.validation))
    implementation(project(Modules.core))

    implementation(project(Source.remote))
    implementation(project(Source.secure))

    implementation(project(Initial.android))
    implementation(project(Initial.api))
    implementation(project(Initial.reducer))
    implementation(project(Initial.effectHandler))

    implementation(project(Auth.android))
    implementation(project(Auth.api))
    implementation(project(Auth.reducer))
    implementation(project(Auth.effectHandler))
}
