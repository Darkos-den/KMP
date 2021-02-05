package com.darkos.kmp.common.debugFeatures

import android.util.Log

actual fun logScreenChanges(screenName: String) {
    Log.d("[DebugFeature][ScreenProcessor]", screenName)
}