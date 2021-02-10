buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.21-2")
        classpath("com.android.tools.build:gradle:4.2.0-beta04")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.4.21-2")
    }
}
group = "com.darkos.kmp"
version = "1.0-SNAPSHOT"

val localPropsFile: File = project.rootProject.file("local.properties")
val localProperties =
    org.jetbrains.kotlin.konan.properties.loadProperties(localPropsFile.absolutePath)

val mUsername: String? by localProperties
val mPassword: String? by localProperties

repositories {
    mavenCentral()
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()

        maven(url = "https://kotlin.bintray.com/kotlinx")
        maven(url = "https://jitpack.io")
        maven(url = "https://dl.bintray.com/darkosinc/MVU")
        maven(url = "https://dl.bintray.com/kodein-framework/Kodein-DI")
        maven(url = "https://dl.bintray.com/badoo/maven")
        maven(url = "https://dl.bintray.com/netguru/maven")
        maven(url = "https://kotlin.bintray.com/kotlinx")

        maven(url = "https://darkos.jfrog.io/artifactory/mvu/"){
            credentials {
                username = mUsername
                password = mPassword
            }
        }
    }
}