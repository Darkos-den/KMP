import com.darkos.config.android.ModuleType
import com.darkos.dependencies.AppLibs.Coroutines
import com.darkos.dependencies.AppLibs.Kodein
import com.darkos.dependencies.AppLibs.Ktor
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
                implementation(Ktor.core)
                implementation(Ktor.logging)
                implementation(Ktor.json)
                implementation(Ktor.serialization)
                implementation(Coroutines.common)
                implementation(Serialization.core)
                implementation(Serialization.protobuf)
                implementation(Kodein.erased)

                implementation(project(Modules.core))
                implementation(project(Modules.LoginEmail.api))
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(Ktor.android)
                implementation(Ktor.loggingJvm)
                implementation(Ktor.jsonJvm)
                implementation(Ktor.serialization)
                implementation(Coroutines.android)
                implementation(Serialization.core)

                implementation(project(Modules.core))
                implementation(project(Modules.LoginEmail.api))
            }
        }
    }
}
