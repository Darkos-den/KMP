package com.darkos.kts.secure.repository

import com.darkos.kts.feature.signin.ISignInSecure
import com.darkos.kts.feature.splash.ISplashSecure
import com.darkos.kts.secure.common.NetworkDispatcher
import com.darkos.kts.secure.repository.base.BaseSecureStorage
import com.netguru.kissme.Kissme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SecureRepository : BaseSecureStorage(), ISplashSecure, ISignInSecure {

    override fun isActiveUserFound(): Boolean {
        return storage.getString(KEY_TOKEN, null) != null
    }

    override fun saveToken(token: String) {
        storage.putString(KEY_TOKEN, token)
    }
}