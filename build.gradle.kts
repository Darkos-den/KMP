buildscript {
    repositories {
        jcenter()
        google()
        mavenCentral()
    }

    dependencies {
        val kotlinVersion by extra("1.4.0")
        val gradleVersion by extra("4.2.0-alpha15")

        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.android.tools.build:gradle:$gradleVersion")
//        classpath(Libs.Detekt.GRADLE.full)//todo
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
    }
}

allprojects {
    repositories {
        jcenter()
        google()
        maven("https://jitpack.io")
        maven("https://dl.bintray.com/darkosinc/MVU")
        maven(url = "https://dl.bintray.com/kotlin/ktor")
        maven(url = "https://dl.bintray.com/kotlin/kotlinx")
        maven(url = "https://dl.bintray.com/aakira/maven")
        maven(url = "https://dl.bintray.com/netguru/maven")
    }
}
