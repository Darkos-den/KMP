package com.darkos.kmp.feature.splash

import com.darkos.kmp.feature.splash.api.*
import com.darkos.kmp.feature.splash.model.SplashEffect
import com.darkos.kmp.feature.splash.model.SplashMessage
import com.darkos.kmp.feature.splash.model.exception.NetworkException
import com.darkos.kmp.feature.splash.model.exception.NotFoundException
import com.darkos.mvu.EffectHandler
import com.darkos.mvu.model.Effect
import com.darkos.mvu.model.Idle
import com.darkos.mvu.model.Message
import com.darkos.mvu.model.flow.FlowEffect
import kotlinx.coroutines.flow.Flow

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
            is SplashEffect.ProcessNetworkError -> {
                errorHandler.onNetworkError()
                Idle
            }
            is SplashEffect.ProcessAppError -> {
                errorHandler.onAppError(effect.message)
                Idle
            }
            is DoLogout -> {
                secure.clearSecureData()

                navigation.goToLogin()
                Idle
            }
            else -> throw UnsupportedOperationException(effect.toString())
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
            SplashMessage.NetworkError
        } catch (e: Exception) {
            SplashMessage.AppError(e.message.orEmpty())
        }
    }
}

class LogoutEffectHandler(
) : EffectHandler {
    override suspend fun call(effect: Effect): Message {
        if (effect is DoLogout) {
            TODO()
        } else {
            throw UnsupportedOperationException(effect.toString())
        }
    }

    override suspend fun <T> callAsFlow(effect: T): Flow<Message> where T : Effect, T : FlowEffect {
        throw UnsupportedOperationException()
    }

}