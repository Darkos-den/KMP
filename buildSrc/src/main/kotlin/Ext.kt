import com.android.build.gradle.BaseExtension
import org.gradle.api.Project

val Project.android: BaseExtension
    get() = extensions.findByName("android") as? BaseExtension
        ?: error("Project '$name' is not an Android module")

fun Project.androidApplicationConfig(
    appId: String = "com.darkos.kts",
    appVersionCode: Int = 1,
    appVersionName: String = "0.0.1"
) {
    android.run {
        defaultConfig {
            applicationId = appId
            versionCode = appVersionCode
            versionName = appVersionName
            multiDexEnabled = true
        }
    }
}

fun Project.applyMultiPlatformSourceSets(){
    android.run {
        sourceSets {
            getByName("main").apply {
                setRoot("src/androidMain")
            }
        }
    }
}

fun applyVersions(){
    Versions.ktor = "1.4.0"
    Versions.AndroidX.compose = "1.0.0-alpha04"
}