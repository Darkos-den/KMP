plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    google()
    jcenter()
}

dependencies {
    val kotlinVersion by extra("1.4.21")
    val gradleVersion by extra("7.0.0-alpha02")

//    implementation("com.android.tools.build:gradle:$gradleVersion")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")

    implementation(gradleApi())
    implementation(localGroovy())
//    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin-api:$kotlinVersion")
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

