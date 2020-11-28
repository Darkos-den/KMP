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
                api(MVU.Login.reducer)
                api(MVU.Validation.reducer)
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
                api(MVU.Login.reducerJvm)
                api(MVU.Validation.reducerJvm)
                implementation(Coroutines.android)
                implementation(Serialization.core)

                implementation(project(Modules.core))
                implementation(project(Modules.LoginEmail.api))
            }
        }
    }
}