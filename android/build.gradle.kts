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

    useDefaultDependencies()
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
    implementation(AppLibs.Kotlin.reflect)
    implementation(AppLibs.AndroidX.activityKtx)
    implementation(AppLibs.AndroidX.appCompatCore)
    implementation(AppLibs.Coroutines.android)
    implementation(AppLibs.Kodein.jvm)
    implementation(AppLibs.Kodein.android)

    implementation(AppLibs.MVU.validationApiJvm)

    implementation("androidx.navigation:navigation-fragment-ktx:2.3.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.1")

    if (1 > 1) {

    }
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.5")

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
