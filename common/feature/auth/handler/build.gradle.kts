import com.darkos.config.android.ModuleType
import com.darkos.dependencies.AppLibs.Coroutines
import com.darkos.dependencies.AppLibs.Kodein
import com.darkos.dependencies.AppLibs.MVU
import com.darkos.dependencies.AppLibs.Modules
import com.darkos.dependencies.AppLibs.Serialization

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("org.jetbrains.kotlin.kapt")
    id("kotlin-android-extensions")

    id("com.android.library")
    id("dependencies")
    id("android-config")
    id("multiplatform-config")
}

configureAndroid {
    moduleType = ModuleType.LIBRARY
}

configureMultiplatform {
    mvuCoreVersion = MVU.Version.core
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(MVU.Validation.api)
                implementation(Coroutines.common)
                implementation(Serialization.core)
                implementation(Serialization.protobuf)
                implementation(Kodein.erased)

                implementation(MVU.navigator)

                implementation(project(Modules.core))
                implementation(project(Modules.Auth.api))
                api(project(Modules.LoginEmail.effectHandler))
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(MVU.Validation.apiJvm)
                implementation(Coroutines.android)
                implementation(Serialization.core)

                implementation(MVU.navigatorAndroid)

                implementation(project(Modules.core))
                implementation(project(Modules.Auth.api))
                api(project(Modules.LoginEmail.effectHandler))
            }
        }
    }
}
