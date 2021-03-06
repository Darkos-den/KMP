package com.darkos.kmp.common.debugFeatures

import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.native.concurrent.ThreadLocal

class DebugScreenProcessor(
    private val debugFeaturesManager: DebugFeaturesManager
) {
    var currentScreenName = MutableStateFlow("")

    fun onScreenChanged(screenName: String) {
        currentScreenName.value = screenName

        if (debugFeaturesManager.logScreenChanges.value) {
            logScreenChanges(screenName)
        }
    }
}

expect fun logScreenChanges(screenName: String)