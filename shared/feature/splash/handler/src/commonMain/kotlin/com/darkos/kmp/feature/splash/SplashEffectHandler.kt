package com.darkos.kmp.feature.splash

import com.darkos.kmp.feature.splash.api.*
import com.darkos.kmp.feature.splash.model.SplashEffect
import com.darkos.kmp.feature.splash.model.exception.NetworkException
import com.darkos.kmp.feature.splash.model.exception.NotFoundException
import com.darkos.mvu.model.Effect
import com.darkos.mvu.model.Idle
import com.darkos.mvu.model.Message

class SplashEffectHandler(
    private val remote: ISplashRemote,
    private val secure: ISplashSecure,
    private val navigation: ISplashNavigation,
    private val errorHandler: ErrorHandler
) : ISplashEffectHandler {

    override suspend fun call(effect: Effect): Message {
        return when (effect) {
            is SplashEffect.CheckAuthState -> {
                try {
                    if (secure.isAuthTokenValid()) {
                        navigation.goToHome()
                        Idle
                    } else {
                        checkRefreshToken()
                    }
                } catch (e: NotFoundException) {
                    navigation.goToLogin()
                    Idle
                }
            }
            is SplashEffect.RetryTokenRefresh -> {
                checkRefreshToken()
            }
            is SplashEffect.Logout -> {
                secure.clearSecureData()

                navigation.goToLogin()
                Idle
            }
            else -> throw IllegalArgumentException("effect not supported")
        }
    }

    private suspend fun checkRefreshToken(): Message {
        return try {
            if (secure.isRefreshTokenValid()) {
                remote.refreshAuthToken(secure.getRefreshToken()).let {
                    secure.saveAuthToken(it.auth.token, it.auth.expire)
                    secure.saveRefreshToken(it.refresh.token, it.refresh.expire)

                    navigation.goToHome()
                    Idle
                }
            } else {
                navigation.goToLogin()
                Idle
            }
        } catch (e: NetworkException) {
            errorHandler.onNetworkError()
            Idle
        } catch (e: Exception) {
            errorHandler.app(e.message.orEmpty())
        }
    }
}