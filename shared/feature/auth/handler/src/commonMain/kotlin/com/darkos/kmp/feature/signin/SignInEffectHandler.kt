package com.darkos.kmp.feature.signin

import com.darkos.kmp.common.errorHandler.ErrorEffect
import com.darkos.kmp.common.errorHandler.ErrorHandler
import com.darkos.kmp.common.errorHandler.runAndHandleErrors
import com.darkos.kmp.common.validator.Email
import com.darkos.kmp.common.validator.Password
import com.darkos.kmp.feature.signin.api.ISignInEffectHandler
import com.darkos.kmp.feature.signin.api.ISignInNavigation
import com.darkos.kmp.feature.signin.api.ISignInRemote
import com.darkos.kmp.feature.signin.api.ISignInSecure
import com.darkos.kmp.feature.signin.model.SignInEffect
import com.darkos.kmp.feature.signin.model.SignInMessage
import com.darkos.kmp.feature.signin.model.dto.SignInDto
import com.darkos.mvu.model.Effect
import com.darkos.mvu.model.Idle
import com.darkos.mvu.model.Message
import com.darkos.mvu.model.None

class SignInEffectHandler(
    private val remote: ISignInRemote,
    private val secure: ISignInSecure,
    private val errorHandler: ErrorHandler,
    private val navigation: ISignInNavigation
) : ISignInEffectHandler {

    override suspend fun call(effect: Effect): Message {
        return when (effect) {
            is None -> {
                Idle
            }
            is SignInEffect.ProcessSignIn -> {
                validateSignInData(effect) ?: run {
                    runAndHandleErrors {
                        SignInDto(effect.email, effect.password).let {
                            remote.signIn(it)
                        }.let {
                            secure.run {
                                it.auth.let {
                                    saveAuthToken(it.token, it.expire)
                                }
                                it.refresh.let {
                                    saveRefreshToken(it.token, it.expire)
                                }
                            }

                            navigation.goToHome()
                            Idle
                        }
                    }
                }
            }
            is ErrorEffect -> {
                errorHandler.processErrorEffect(effect)
            }
            else -> {
                throw UnsupportedOperationException()
            }
        }
    }

    private fun validateSignInData(effect: SignInEffect.ProcessSignIn): Message? {
        return SignInMessage.ValidationError(
            emailStatus = Email.validate(effect.email),
            passwordStatus = Password.validate(effect.password)
        ).takeIf {
            (it.emailStatus && it.passwordStatus).not()
        }
    }
}