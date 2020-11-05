package com.darkos.config.android

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidConfigPlugin : Plugin<Project> {

    internal object SdkVersion {
        const val compile = 30
        const val target = 30
        const val min = 26
    }

    override fun apply(target: Project) {
        target.extensions.create("configureAndroid", AndroidConfigExtension::class.java, target)

        target.tasks.withType(KotlinCompile::class.java) {
            kotlinOptions {
                jvmTarget = "1.8"
                freeCompilerArgs =
                    freeCompilerArgs + "-Xallow-jvm-ir-dependencies" + "-Xskip-prerelease-check"
            }
        }
    }

    internal fun setupAsApplication(target: Project) {
        target.apply(plugin = "com.android.application")
        target.apply(plugin = "kotlin-android")

        target.extensions.configure<BaseExtension>("android") {
            applyDefaultAndroidConfiguration()
        }
    }

    internal fun setupAsLibrary(target: Project) {
        target.apply(plugin = "com.android.library")

        target.extensions.configure<BaseExtension>("android") {
            applyDefaultAndroidConfiguration()
        }
    }

    internal fun applyDefaultDependencies(target: Project) {
        target.dependencies {
            //todo: versions
            this.add("implementation", "org.jetbrains.kotlin:kotlin-stdlib:1.4.0")
            this.add("implementation", "com.darkosinc.mvu:core-jvm:1.0.0")
            this.add("implementation", "com.github.Darkos-den.core2:presentation:1.0.10")
            this.add("implementation", "com.github.Darkos-den.core2:mvu-program:1.0.10")
        }
    }

    internal fun enableCompose(target: Project) {
        target.extensions.configure<BaseExtension>("android") {
            buildFeatures.apply {
                compose = true
            }
            composeOptions {
                kotlinCompilerVersion = kotlinVersion
                kotlinCompilerExtensionVersion = extensionVersion
            }
        }
    }

    internal fun applyComposeDependency(target: Project) {
        val Compose = object {
            private val version = "1.0.0-alpha05"

            val ui = "androidx.compose.ui:ui:$version"
            val animation = "androidx.compose.animation:animation:$version"
            val foundation = "androidx.compose.foundation:foundation:$version"
            val material = "androidx.compose.material:material:$version"
            val materialIconsCore = "androidx.compose.material:material-icons-core:$version"
            val materialIconsExtended = "androidx.compose.material:material-icons-extended:$version"
            val runtime = "androidx.compose.runtime:runtime:$version"
            val tooling = "androidx.ui:ui-tooling:$version"
        }

        target.dependencies {
            this.add("implementation", Compose.ui)
            this.add("implementation", Compose.animation)
            this.add("implementation", Compose.foundation)
            this.add("implementation", Compose.material)
            this.add("implementation", Compose.materialIconsCore)
            this.add("implementation", Compose.materialIconsExtended)
            this.add("implementation", Compose.runtime)
            this.add("implementation", Compose.tooling)
        }
    }

    companion object {
        //todo
        private const val kotlinVersion = "1.4.0"
        private const val extensionVersion = "1.0.0-alpha04"
    }
}

fun BaseExtension.applyDefaultAndroidConfiguration() {
    compileSdkVersion(AndroidConfigPlugin.SdkVersion.compile)
    defaultConfig {
        minSdkVersion(AndroidConfigPlugin.SdkVersion.min)
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

fun BaseExtension.applyVersionInfo(
    appId: String = "com.darkos.kts",
    appVersionCode: Int = 1,
    appVersionName: String = "0.0.1"
) {
    defaultConfig {
        applicationId = appId
        versionCode = appVersionCode
        versionName = appVersionName
    }
}