package com.darkos.kmp.common.debugFeatures

import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object DebugScreenProcessor {
    var currentScreenName: String = ""

    fun onScreenChanged(screenName: String) {
        currentScreenName = screenName

        if (DebugFeaturesManager.logScreenChanges) {
            logScreenChanges(screenName)
        }
    }
}

expect fun logScreenChanges(screenName: String)