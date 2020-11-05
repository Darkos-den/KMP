plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    google()
    jcenter()
}

dependencies {
    val kotlinVersion by extra("1.4.0")
    val gradleVersion by extra("4.2.0-alpha15")

    implementation("com.android.tools.build:gradle:$gradleVersion")
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin-api:$kotlinVersion")
}

gradlePlugin {
    plugins {
        register("android-config") {
            id = "android-config"
            implementationClass = "com.darkos.config.android.AndroidConfigPlugin"
        }
        register("multiplatform-config") {
            id = "multiplatform-config"
            implementationClass = "com.darkos.config.multiplatform.MultiplatformConfigPlugin"
        }
    }
}

