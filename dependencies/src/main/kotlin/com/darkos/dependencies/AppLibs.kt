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
}