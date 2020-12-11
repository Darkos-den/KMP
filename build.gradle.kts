buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.20")
        classpath("com.android.tools.build:gradle:7.0.0-alpha02")
    }
}
group = "com.darkos.kmp"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
