package com.darkos.kmp.common.debugFeatures

import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object DebugFeaturesManager {
    var logScreenChanges: Boolean = true

}