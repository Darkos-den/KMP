val kotlinVersion = "1.4.0"
val gradleVersion = "4.2.0-alpha08"

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

gradlePlugin {
    plugins {
        register("app-config-android") {
            id = "app-config-android"
            implementationClass = "config.AppConfigAndroidPlugin"
        }
    }
}

repositories {
    google()
    jcenter()
    maven(url = "https://plugins.gradle.org/m2/")
    maven(url = "https://jitpack.io")
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions {
    languageVersion = kotlinVersion
}

dependencies {
    implementation("com.android.tools.build:gradle:${gradleVersion}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}")
    api("com.github.Darkos-den:depend:1.0.4")
}
