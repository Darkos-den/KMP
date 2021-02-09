package com.darkos.kmp.common.debugFeatures

import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object DebugFeaturesManager {
    var logScreenChanges: Boolean = true
    var useTestAccount: Boolean = false

    var selectedEnvironment

    var testAuthData: AuthData? = null

    class AuthData(
        val email: String,
        val password: String
    )
}