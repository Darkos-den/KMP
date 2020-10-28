package com.darkos.kts.secure.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual val NetworkDispatcher: CoroutineDispatcher
    get() = Dispatchers.IO