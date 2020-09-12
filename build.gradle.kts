import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

buildscript {
    val kotlinVersion by extra("1.4.0")
    applyVersions()
    repositories {
        jcenter()
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Libs.Kotlin.GRADLE.full)
        classpath(Libs.AndroidGradle.GRADLE.full)
        classpath(Libs.Detekt.GRADLE.full)
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
    }
}

allprojects {
    repositories {
        jcenter()
        google()
        maven("https://jitpack.io")
        maven("https://darkos.bintray.com/mvu")
        maven(url = "https://dl.bintray.com/kotlin/ktor")
        maven(url = "https://dl.bintray.com/kotlin/kotlinx")
        maven(url = "https://dl.bintray.com/aakira/maven")
    }
}
