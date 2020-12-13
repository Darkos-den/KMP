package com.darkos.config.android

import com.android.build.gradle.BaseExtension
import com.android.build.api.dsl.DefaultConfig
import org.gradle.api.Action
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidConfigPlugin : Plugin<Project> {

    internal object SdkVersion {
        const val compile = 30
        const val target = 30
        const val min = 26
    }

    override fun apply(target: Project) {
        target.extensions.create("configureAndroid", AndroidConfigExtension::class, target)

        target.tasks.withType(KotlinCompile::class) {
            kotlinOptions {
                jvmTarget = "1.8"
                useIR = true
                freeCompilerArgs = freeCompilerArgs + "-Xskip-prerelease-check"
            }
        }

        target.extensions.configure(BaseExtension::class) {
            defaultConfig {
                compileSdkVersion(14)
            }
        }

//        target.extensions.configure(BaseExtension::class) {
//            defaultConfig {
//                val x = this as BaseFlavor
//                x.minSdkVersion(14)
//            }
//        }

//        target.extensions.configure<BaseExtension>("android") {
//            compileSdkVersion(AndroidConfigPlugin.SdkVersion.compile)
//            defaultConfig {
//                minSdkVersion(AndroidConfigPlugin.SdkVersion.min)
//                targetSdkVersion(AndroidConfigPlugin.SdkVersion.target)
//                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//                multiDexEnabled = true
//            }
//            buildTypes {
//                getByName("release") {
//                    isMinifyEnabled = true
//                    proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
//                }
//                getByName("debug") {
//                    isMinifyEnabled = false
//                }
//            }
//            compileOptions {
//                sourceCompatibility = JavaVersion.VERSION_1_8
//                targetCompatibility = JavaVersion.VERSION_1_8
//            }
//        }
    }

    internal fun setupAsApplication(target: Project) {
        target.apply(plugin = "com.android.application")

        target.extensions.configure<BaseExtension>("") {
            defaultConfig(Action {
                (this as com.android.build.api.dsl.DefaultConfig).minSdk = 14
            })
        }
    }

    internal fun setupAsLibrary(target: Project) {
        target.apply(plugin = "com.android.library")

//        target.extensions.configure<LibraryExtension>("android") {
//            compileSdkVersion(SdkVersion.compile)
//            defaultConfig {
//                minSdkVersion(SdkVersion.min)
//                targetSdkVersion(SdkVersion.target)
//                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//                multiDexEnabled = true
//            }
//            buildTypes {
//                getByName("release") {
//                    isMinifyEnabled = true
//                    proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
//                }
//                getByName("debug") {
//                    isMinifyEnabled = false
//                }
//            }
//            compileOptions {
//                sourceCompatibility = JavaVersion.VERSION_1_8
//                targetCompatibility = JavaVersion.VERSION_1_8
//            }
//        }
    }

    internal fun enableLeakCanary(target: Project, version: String) {
        target.dependencies {
            this.add("debugImplementation", "com.squareup.leakcanary:leakcanary-android:$version")
        }
    }

    internal fun applyDefaultDependencies(target: Project, versions: Versions) {
        target.dependencies {
            val Libs = object {
                val reflect = "org.jetbrains.kotlin:kotlin-reflect:${versions.kotlin}"
                val activityKtx = "androidx.activity:activity-ktx:${versions.activityKtx}"
                val appcompat = "androidx.appcompat:appcompat:${versions.appcompat}"
                val coroutines =
                    "org.jetbrains.kotlinx:kotlinx-coroutines-android:${versions.coroutines}"
                val kodeinJvm = "org.kodein.di:kodein-di-generic-jvm:${versions.kodein}"
                val kodeinAndroid = "org.kodein.di:kodein-di-framework-android-x:${versions.kodein}"
                val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${versions.kotlin}"
                val mvuCore = "com.darkosinc.mvu:core-jvm:${versions.mvuCore}"
                val corePresentation =
                    "com.github.Darkos-den.core2:presentation:${versions.mvuProgram}"
                val coreProgram = "com.github.Darkos-den.core2:mvu-program:${versions.mvuProgram}"
                val navigationFragment =
                    "androidx.navigation:navigation-fragment-ktx:${versions.navigation}"
                val navigationUi = "androidx.navigation:navigation-ui-ktx:${versions.navigation}"
            }

            this.add("implementation", Libs.reflect)
            this.add("implementation", Libs.activityKtx)
            this.add("implementation", Libs.appcompat)
            this.add("implementation", Libs.coroutines)
            this.add("implementation", Libs.kodeinJvm)
            this.add("implementation", Libs.kodeinAndroid)
            this.add("implementation", Libs.stdlib)
            this.add("implementation", Libs.mvuCore)
            this.add("implementation", Libs.corePresentation)
            this.add("implementation", Libs.coreProgram)
            this.add("implementation", Libs.navigationFragment)
            this.add("implementation", Libs.navigationUi)
        }
    }

    internal fun enableCompose(
        target: Project,
        kotlinVersion: String,
        extensionVersion: String
    ) {
//        target.extensions.configure<BaseExtension>("android") {
//            buildFeatures.apply {
//                compose = true
//            }
//            composeOptions {
//                kotlinCompilerVersion = kotlinVersion
//                kotlinCompilerExtensionVersion = extensionVersion
//            }
//        }
    }

    internal fun applyComposeDependency(target: Project, version: String) {
        val Compose = object {
            val ui = "androidx.compose.ui:ui:$version"
            val tooling = "androidx.compose.ui:ui-tooling:$version"
            val foundation = "androidx.compose.foundation:foundation:$version"
            val material = "androidx.compose.material:material:$version"
            val materialIconsCore = "androidx.compose.material:material-icons-core:$version"
            val materialIconsExtended = "androidx.compose.material:material-icons-extended:$version"
            val livedata = "androidx.compose.runtime:runtime-livedata:$version"
        }

        target.dependencies {
            this.add("implementation", Compose.ui)
            this.add("implementation", Compose.tooling)
            this.add("implementation", Compose.foundation)
            this.add("implementation", Compose.material)
            this.add("implementation", Compose.materialIconsCore)
            this.add("implementation", Compose.materialIconsExtended)
            this.add("implementation", Compose.livedata)
        }
    }
}

fun BaseExtension.applyDefaultAndroidConfiguration() {
    compileSdkVersion(AndroidConfigPlugin.SdkVersion.compile)
    defaultConfig {
        com.android.build.gradle.internal.dsl.DefaultConfig
        com.android.build.api.dsl.DefaultConfig

        targetSdkVersion(AndroidConfigPlugin.SdkVersion.target)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

//fun BaseExtension.applyVersionInfo(
//    appId: String = "com.darkos.kts",
//    appVersionCode: Int = 1,
//    appVersionName: String = "0.0.1"
//) {
//    defaultConfig {
//        applicationId = appId
//        versionCode = appVersionCode
//        versionName = appVersionName
//    }
//}