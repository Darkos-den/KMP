package com.darkos.kmp.feature.splash

import com.darkos.kmp.common.auth.AuthManager
import com.darkos.kmp.common.auth.AuthResult
import com.darkos.kmp.common.debugFeatures.DebugFeaturesManager
import com.darkos.kmp.common.errorHandler.ErrorEffect
import com.darkos.kmp.common.errorHandler.ErrorHandler
import com.darkos.kmp.common.errorHandler.runAndHandleErrors
import com.darkos.kmp.common.mvu.navigate
import com.darkos.kmp.feature.splash.api.ISplashEffectHandler
import com.darkos.kmp.feature.splash.api.ISplashNavigation
import com.darkos.kmp.feature.splash.api.ISplashRemote
import com.darkos.kmp.feature.splash.api.ISplashSecure
import com.darkos.kmp.feature.splash.model.SplashEffect
import com.darkos.kmp.feature.splash.model.exception.NotFoundException
import com.darkos.mvu.model.Effect
import com.darkos.mvu.model.Message
import kotlinx.coroutines.delay

class SplashEffectHandler(
    private val remote: ISplashRemote,
    private val secure: ISplashSecure,
    private val navigation: ISplashNavigation,
    private val errorHandler: ErrorHandler,
    private val authManager: AuthManager,
    private val debugFeaturesManager: DebugFeaturesManager
) : ISplashEffectHandler {

    override suspend fun call(effect: Effect): Message {
        return when (effect) {
            is SplashEffect.CheckAuthState -> {
                delay(3_000)//todo: for test

                if (authManager.hasAuthorizedUser()) {
                    navigate(navigation::fromSplashToHome)
                } else {
                    secure.getRefreshToken()
                    if(debugFeaturesManager.useTestAccount.value){
                        authByDefaultAccount()
                    }else {
                        navigate(navigation::fromSplashToLogin)
                    }
                }
            }
            is SplashEffect.RetryTokenRefresh -> {
                checkRefreshToken()
            }
            is ErrorEffect -> {
                errorHandler.processErrorEffect(effect)
            }
            else -> throw UnsupportedOperationException(effect.toString())
        }
    }

    private suspend fun authByDefaultAccount(): Message {
        return authManager.signInByEmail("root@darkos.com", "12345678").let {
            when (it) {
                is AuthResult.Success -> {
                    navigate(navigation::fromSplashToHome)
                }
                is AuthResult.Canceled,
                is AuthResult.Error -> {
                    navigate(navigation::fromSplashToLogin)
                }
            }
        }
    }

    private suspend fun checkRefreshToken(): Message {
        return runAndHandleErrors {
            if (secure.isRefreshTokenValid()) {
                remote.refreshAuthToken(secure.getRefreshToken()).let {
                    secure.saveAuthToken(it.auth.token, it.auth.expire)
                    secure.saveRefreshToken(it.refresh.token, it.refresh.expire)

                    navigate(navigation::fromSplashToHome)
                }
            } else {
                navigate(navigation::fromSplashToLogin)
            }
        }
    }
}