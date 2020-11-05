import com.darkos.config.android.ModuleType
import com.darkos.dependencies.AppLibs.AndroidX
import com.darkos.dependencies.AppLibs.Core
import com.darkos.dependencies.AppLibs.Coroutines
import com.darkos.dependencies.AppLibs.Kodein
import com.darkos.dependencies.AppLibs.MVU
import com.darkos.dependencies.AppLibs.Serialization

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("org.jetbrains.kotlin.kapt")

    id("com.android.library")
    id("dependencies")
    id("android-config")
    id("multiplatform-config")
}

configureAndroid {
    moduleType = ModuleType.LIBRARY
}

configureMultiplatform {
    mvuCoreVersion = MVU.core
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Coroutines.common)
                implementation(Kodein.erased)
                implementation(Serialization.core)
                implementation(Serialization.protobuf)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(Coroutines.android)
                implementation(Serialization.core)
                implementation(AndroidX.activityKtx)
                implementation(AndroidX.appCompatCore)

                implementation(Core.presentation)
                implementation(Core.program)
            }
        }
    }
}
