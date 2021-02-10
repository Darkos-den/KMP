import com.darkos.dependencies.AppLibs
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("dependencies")
}

kotlin {
    android()
//    ios {
//        binaries {
//            framework {
//                baseName = "appDi"
//                export(project(AppLibs.Modules.Splash.api))
//                export(project(AppLibs.Modules.Splash.di))
//            }
//        }
//    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(AppLibs.Coroutines.core)
                implementation(AppLibs.MVU.core)
                implementation(AppLibs.MVU.program)

                api(project(AppLibs.Modules.Splash.api))
                api(project(AppLibs.Modules.Splash.di))

                api(project(AppLibs.Modules.Auth.api))
                api(project(AppLibs.Modules.Auth.di))

                api(project(AppLibs.Modules.Source.secure))
                api(project(AppLibs.Modules.Source.remote))

                api(project(AppLibs.Modules.Shared.utils))

                implementation(AppLibs.Kodein.core)
            }
        }
        val commonTest by getting
        val androidMain by getting
        val androidTest by getting
//        val iosMain by getting
//        val iosTest by getting
    }
}
android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(26)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    (this as ExtensionAware).configure<KotlinJvmOptions> {
        jvmTarget = "1.8"
    }
}

dependencies {
    debugImplementation(project(AppLibs.Modules.Shared.debugFeatures))
}

//val packForXcode by tasks.creating(Sync::class) {
//    group = "build"
//    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
//    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
//    val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
//    val framework =
//        kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
//    inputs.property("mode", mode)
//    dependsOn(framework.linkTask)
//    val targetDir = File(buildDir, "xcode-frameworks")
//    from({ framework.outputDirectory })
//    into(targetDir)
//}
//tasks.getByName("build").dependsOn(packForXcode)