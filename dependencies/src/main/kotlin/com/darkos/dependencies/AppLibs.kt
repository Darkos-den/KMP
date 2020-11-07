package com.darkos.dependencies

object AppLibs {

    const val leakcanaryVersion = "2.5"

    object Kotlin {
        const val version = "1.4.0"
        const val extensionVersion = "1.0.0-alpha04"

        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:$version"
    }

    object AndroidX {
        object Versions {
            const val activityKtx = "1.2.0-beta01"
            const val appCompat = "1.1.0"
        }

        const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
        const val appCompatCore = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val crypto = "androidx.security:security-crypto:1.1.0-alpha02"
        const val navigation = "2.3.1"

        object Compose {
            const val version = "1.0.0-alpha05"

            const val ui = "androidx.compose.ui:ui:$version"
            const val animation = "androidx.compose.animation:animation:$version"
            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val material = "androidx.compose.material:material:$version"
            const val materialIconsCore = "androidx.compose.material:material-icons-core:$version"
            const val materialIconsExtended =
                "androidx.compose.material:material-icons-extended:$version"
            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val tooling = "androidx.ui:ui-tooling:$version"
        }
    }

    object Kodein {
        const val version = "6.5.5"

        const val jvm = "org.kodein.di:kodein-di-generic-jvm:$version"
        const val android = "org.kodein.di:kodein-di-framework-android-x:$version"
        const val erased = "org.kodein.di:kodein-di-erased:$version"
    }

    object Coroutines {
        const val version = "1.4.0"

        const val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.8"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val native = "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:$version"
    }

    object Core {
        const val version = "1.0.10"

        const val presentation = "com.github.Darkos-den.core2:presentation:$version"
        const val program = "com.github.Darkos-den.core2:mvu-program:$version"
    }

    object Ktor {
        private const val version = "1.4.0"

        const val core = "io.ktor:ktor-client-core:$version"
        const val serialization = "io.ktor:ktor-client-serialization:$version"
        const val serializationJvm = "io.ktor:ktor-client-serialization-jvm:$version"
        const val json = "io.ktor:ktor-client-json:$version"
        const val jsonJvm = "io.ktor:ktor-client-json-jvm:$version"
        const val logging = "io.ktor:ktor-client-logging:$version"
        const val loggingJvm = "io.ktor:ktor-client-logging-jvm:$version"
        const val okHttp = "io.ktor:ktor-client-okhttp:$version"
        const val android = "io.ktor:ktor-client-android:$version"
    }

    object MVU {
        object Version {
            const val core = "1.0.0"

            const val validationApi = "1.0.4"
            const val validationReducer = "1.0.3"
            const val validationHandler = "1.0.3"

            const val loginHandler = "1.0.1"
            const val loginReducer = "1.0.3"
            const val loginApi = "1.0.1"
        }

        object Validation {
            const val handler = "com.darkosinc.mvu:validation:${Version.validationHandler}"
            const val handlerJvm = "com.darkosinc.mvu:validation-jvm:${Version.validationHandler}"
            const val api = "com.darkosinc.mvu:validation-api:${Version.validationApi}"
            const val apiJvm = "com.darkosinc.mvu:validation-api-jvm:${Version.validationApi}"
            const val reducer = "com.darkosinc.mvu:validation-reducer:${Version.validationReducer}"
            const val reducerJvm =
                "com.darkosinc.mvu:validation-reducer-jvm:${Version.validationReducer}"
        }

        object Login {
            const val api = "com.darkosinc.mvu:login-api:${Version.loginApi}"
            const val apiJvm = "com.darkosinc.mvu:login-api-jvm:${Version.loginApi}"
            const val handler = "com.darkosinc.mvu:login-effect-handler:${Version.loginHandler}"
            const val handlerJvm =
                "com.darkosinc.mvu:login-effect-handler-jvm:${Version.loginHandler}"
            const val reducer = "com.darkosinc.mvu:login-reducer:${Version.loginReducer}"
            const val reducerJvm = "com.darkosinc.mvu:login-reducer-jvm:${Version.loginReducer}"
        }

        const val coreJvm = "com.darkosinc.mvu:core-jvm:${Version.core}"
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
        const val core = ":common:core"
        const val validation = ":common:validation"

        object Source {
            const val remote = ":common:source:remote"
            const val secure = ":common:source:secure"
        }

        object LoginEmail {
            const val api = ":common:feature:login-email:login-email-api"
            const val reducer = ":common:feature:login-email:login-email-reducer"
            const val effectHandler = ":common:feature:login-email:login-email-effect-handler"
        }

        object Initial {
            const val api = ":common:feature:initial:initial-api"
            const val android = ":common:feature:initial:initial-android"
            const val reducer = ":common:feature:initial:initial-reducer"
            const val effectHandler = ":common:feature:initial:initial-effect-handler"
        }

        object Auth {
            const val api = ":common:feature:auth:auth-api"
            const val android = ":common:feature:auth:auth-android"
            const val reducer = ":common:feature:auth:auth-reducer"
            const val effectHandler = ":common:feature:auth:auth-effect-handler"
        }
    }
}