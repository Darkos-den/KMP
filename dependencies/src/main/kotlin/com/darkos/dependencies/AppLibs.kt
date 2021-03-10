package com.darkos.dependencies

object AppLibs {

    const val leakcanaryVersion = "2.5"

    const val material = "com.google.android.material:material:1.2.1"

    object Kotlin {
        const val version = "1.4.21-2"
        const val extensionVersion = "1.0.0-alpha11"

        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:$version"
        const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:0.1.1"
    }

    object AndroidX {
        object Versions {
            const val activityKtx = "1.2.0-beta01"
            const val appCompat = "1.2.0"
        }

        const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.2.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
        const val crypto = "androidx.security:security-crypto:1.1.0-alpha02"
        const val navigation = "2.3.1"

        object Compose {
            const val version = Kotlin.extensionVersion

            const val ui = "androidx.compose.ui:ui:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val animation = "androidx.compose.animation:animation:$version"
            const val material = "androidx.compose.material:material:$version"
            const val materialIconsCore = "androidx.compose.material:material-icons-core:$version"
            const val materialIconsExtended =
                "androidx.compose.material:material-icons-extended:$version"
            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val livedata = "androidx.compose.runtime:runtime-livedata:$version"
            const val navigation = "androidx.navigation:navigation-compose:1.0.0-alpha04"
        }
    }

    object Kodein {
        const val version = "7.2.0"

        const val core = "org.kodein.di:kodein-di:$version"
        const val android = "org.kodein.di:kodein-di-framework-android-x:$version"
    }

    object Coroutines {
        const val version = "1.4.2"

        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.8"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val native = "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:$version"
    }

    object Ktor {
        private const val version = "1.5.0"

        const val core = "io.ktor:ktor-client-core:$version"
        const val serialization = "io.ktor:ktor-client-serialization:$version"
        const val serializationJvm = "io.ktor:ktor-client-serialization-jvm:$version"
        const val json = "io.ktor:ktor-client-json:$version"
        const val jsonJvm = "io.ktor:ktor-client-json-jvm:$version"
        const val logging = "io.ktor:ktor-client-logging:$version"
        const val loggingJvm = "io.ktor:ktor-client-logging-jvm:$version"
        const val okHttp = "io.ktor:ktor-client-okhttp:$version"
        const val android = "io.ktor:ktor-client-android:$version"
        const val ios = "io.ktor:ktor-client-ios:$version"
    }

    object MVU {
        object Version {
            const val core = "1.0.0-rc2"
            const val program = "1.0.0-rc2"

            const val navigator = "1.1.0"
        }

        const val core = "com.darkos.mvu:core:${Version.core}"
        const val program = "com.darkos.mvu:program:${Version.program}"
    }

    object Serialization {
        private const val version = "1.0.0-RC"

        const val common = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$version"
        const val runtime = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:$version"
        const val core = "org.jetbrains.kotlinx:kotlinx-serialization-core:$version"
        const val protobuf = "org.jetbrains.kotlinx:kotlinx-serialization-protobuf:$version"
        const val native = "org.jetbrains.kotlinx:kotlinx-serialization-native:$version"
    }

    object Modules {
        const val androidUi = ":androidUi"

        object Shared {
            const val appDi = ":shared:appDi"
            const val utils = ":shared:common:utils"
            const val debugFeatures = ":shared:common:debugFeatures"
        }

        object Splash {
            const val api = ":shared:feature:splash:api"
            const val di = ":shared:feature:splash:di"
            const val reducer = ":shared:feature:splash:reducer"
            const val handler = ":shared:feature:splash:handler"
            const val component = ":shared:feature:splash:component"
        }

        object Auth {
            const val api = ":shared:feature:auth:api"
            const val di = ":shared:feature:auth:di"
            const val reducer = ":shared:feature:auth:reducer"
            const val handler = ":shared:feature:auth:handler"
            const val component = ":shared:feature:auth:component"
        }

        object Dashboard {
            const val api = ":shared:feature:dashboard:api"
            const val di = ":shared:feature:dashboard:di"
            const val reducer = ":shared:feature:dashboard:reducer"
            const val handler = ":shared:feature:dashboard:handler"
            const val component = ":shared:feature:dashboard:component"
        }

        object Item {
            const val api = ":shared:feature:item:api"
            const val di = ":shared:feature:item:di"
            const val reducer = ":shared:feature:item:reducer"
            const val handler = ":shared:feature:item:handler"
            const val component = ":shared:feature:item:component"
        }

        object Drawer {
            const val api = ":shared:feature:drawer:api"
            const val di = ":shared:feature:drawer:di"
            const val reducer = ":shared:feature:drawer:reducer"
            const val handler = ":shared:feature:drawer:handler"
            const val component = ":shared:feature:drawer:component"
        }

        object Source {
            const val secure = ":shared:source:secure"
            const val remote = ":shared:source:remote"
        }
    }
}