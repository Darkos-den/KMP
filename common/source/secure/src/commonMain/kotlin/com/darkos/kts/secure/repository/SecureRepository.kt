package com.darkos.kts.secure.repository

import com.darkos.kts.feature.signin.ISignInSecure
import com.darkos.kts.feature.splash.ISplashSecure
import com.darkos.kts.secure.repository.base.BaseSecureRepository
import com.darkos.kts.secure.repository.base.SecureStorage

class SecureRepository(
    storage: SecureStorage
) : BaseSecureRepository(storage), ISplashSecure, ISignInSecure {

    override suspend fun isActiveUserFound(): Boolean {
        return storage.getString(KEY_TOKEN, null) != null
    }

    override suspend fun saveToken(token: String) {
        storage.putString(KEY_TOKEN, token)
    }
}