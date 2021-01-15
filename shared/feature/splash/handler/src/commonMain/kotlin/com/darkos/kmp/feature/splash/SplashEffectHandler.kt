package com.darkos.kmp.feature.splash

import com.darkos.kmp.common.errorHandler.ErrorHandler
import com.darkos.kmp.common.errorHandler.NetworkException
import com.darkos.kmp.feature.splash.api.ISplashEffectHandler
import com.darkos.kmp.feature.splash.api.ISplashNavigation
import com.darkos.kmp.feature.splash.api.ISplashRemote
import com.darkos.kmp.feature.splash.api.ISplashSecure
import com.darkos.kmp.feature.splash.model.SplashEffect
import com.darkos.kmp.feature.splash.model.SplashMessage
import com.darkos.kmp.feature.splash.model.exception.NotFoundException
import com.darkos.mvu.Ui
import com.darkos.mvu.model.Effect
import com.darkos.mvu.model.Idle
import com.darkos.mvu.model.Message
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class SplashEffectHandler(
    private val remote: ISplashRemote,
    private val secure: ISplashSecure,
    private val navigation: ISplashNavigation,
    private val errorHandler: ErrorHandler
) : ISplashEffectHandler {

    private suspend fun navigate(block: () -> Unit): Message {
        withContext(Ui) {
            block()
        }
        return Idle
    }

    override suspend fun call(effect: Effect): Message {
        return when (effect) {
            is SplashEffect.CheckAuthState -> {
                delay(3_000)//todo: for test
                try {
                    if (secure.isAuthTokenValid()) {
                        navigate(navigation::goToHome)
                    } else {
                        checkRefreshToken()
                    }
                } catch (e: NotFoundException) {
                    navigate(navigation::goToLogin)
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
            else -> throw UnsupportedOperationException(effect.toString())
        }
    }

    private suspend fun checkRefreshToken(): Message {
        return try {
            if (secure.isRefreshTokenValid()) {
                remote.refreshAuthToken(secure.getRefreshToken()).let {
                    secure.saveAuthToken(it.auth.token, it.auth.expire)
                    secure.saveRefreshToken(it.refresh.token, it.refresh.expire)

                    navigate(navigation::goToHome)
                }
            } else {
                navigate(navigation::goToLogin)
            }
        } catch (e: NetworkException) {
            SplashMessage.NetworkError
        } catch (e: Exception) {
            SplashMessage.AppError(e.message.orEmpty())
        }
    }
}