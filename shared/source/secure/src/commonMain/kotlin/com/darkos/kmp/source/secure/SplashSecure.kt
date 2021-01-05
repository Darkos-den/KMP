package com.darkos.kmp.source.secure

import com.darkos.kmp.feature.splash.api.ISplashSecure
import com.darkos.kmp.feature.splash.model.exception.NotFoundException
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

class SplashSecure(
    private val secure: SecureStorage
) : ISplashSecure {

    override suspend fun isAuthTokenValid(): Boolean {
        if (secure.authToken.isEmpty()) {
            throw NotFoundException()
        }

        return Instant.fromEpochMilliseconds(secure.authExpire) > Clock.System.now()//todo: check
    }

    override suspend fun isRefreshTokenValid(): Boolean {
        return Instant.fromEpochMilliseconds(secure.refreshExpire) > Clock.System.now()//todo: check
    }

    override suspend fun getRefreshToken(): String {
        return secure.refreshToken
    }

    override suspend fun saveAuthToken(token: String, expire: Long) {
        secure.run {
            authToken = token
            authExpire = expire
        }
    }

    override suspend fun saveRefreshToken(token: String, expire: Long) {
        secure.run {
            refreshToken = token
            refreshExpire = expire
        }
    }

    override suspend fun clearSecureData() {
        secure.clear()
    }
}