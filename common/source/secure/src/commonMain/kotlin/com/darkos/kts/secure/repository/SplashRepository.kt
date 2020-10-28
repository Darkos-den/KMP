package com.darkos.kts.secure.repository

import com.darkos.kts.feature.splash.ISplashSecure
import com.darkos.kts.secure.common.NetworkDispatcher
import com.netguru.kissme.Kissme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SplashRepository : ISplashSecure {

    private val storage: Kissme by lazy {
        Kissme(STORAGE_NAME)
    }

    override suspend fun isActiveUserFound(): Boolean {
        return withContext(NetworkDispatcher) {
            storage.getString(KEY_TOKEN, null) != null
        }
    }

    companion object {
        private const val STORAGE_NAME = "secret"
        private const val KEY_TOKEN = "token"
    }
}