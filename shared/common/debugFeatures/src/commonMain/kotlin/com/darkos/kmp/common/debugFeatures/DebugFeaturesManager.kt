package com.darkos.kmp.common.debugFeatures

import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object DebugFeaturesManager {
    internal var logScreenChanges: Boolean = true

}