package com.darkos.kmp.common.debugFeatures

import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object DebugScreenProcessor {
    internal var currentScreenName: String = ""

    fun onScreenChanged(screenName: String) {
        currentScreenName = screenName

        if (DebugFeaturesManager.logScreenChanges) {
            logScreenChanges(screenName)
        }
    }
}

expect fun logScreenChanges(screenName: String)