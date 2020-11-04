package com.darkos.dependencies

object AppLibs {

    object Kotlin {
        private const val version = "1.4.0"

        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:$version"
    }

    object AndroidX {
        const val activityKtx = "androidx.activity:activity-ktx:1.2.0-alpha15"
        const val appCompatCore = "androidx.appcompat:appcompat:1.1.0"

        object Compose {
            private const val version = "1.0.0-alpha05"

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
        private const val version = "6.5.5"

        const val jvm = "org.kodein.di:kodein-di-generic-jvm:$version"
        const val android = "org.kodein.di:kodein-di-framework-android-x:$version"
        const val erased = "org.kodein.di:kodein-di-erased:$version"
    }

    object Coroutines {
        private const val version = "1.4.0"

        const val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val native = "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:$version"
    }

    object Core {
        private const val version = "1.0.10"

        const val presentation = "com.github.Darkos-den.core2:presentation:$version"
        const val program = "com.github.Darkos-den.core2:mvu-program:$version"
    }

    object MVU {
        private const val core = "1.0.0"
        private const val validation = "1.0.4"

        const val coreJvm = "com.darkosinc.mvu:core-jvm:$core"

        const val validationApi = "com.darkosinc.mvu:validation-api:$validation"
        const val validationApiJvm = "com.darkosinc.mvu:validation-api-jvm:$validation"
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