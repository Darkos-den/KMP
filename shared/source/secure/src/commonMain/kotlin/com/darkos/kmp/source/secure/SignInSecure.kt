package com.darkos.kmp.source.secure

import com.darkos.kmp.feature.signin.api.ISignInSecure
import com.darkos.kmp.source.secure.common.SecureStorage

class SignInSecure(
    private val secure: SecureStorage
) : ISignInSecure {

    override suspend fun saveAuthToken(token: String, expire: Long) {
        secure.auth = SecureStorage.Token(token, expire)
    }

    override suspend fun saveRefreshToken(token: String, expire: Long) {
        secure.refresh = SecureStorage.Token(token, expire)
    }
}