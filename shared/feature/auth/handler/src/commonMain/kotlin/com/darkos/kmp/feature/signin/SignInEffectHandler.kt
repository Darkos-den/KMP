package com.darkos.kmp.feature.signin

import com.darkos.kmp.common.errorHandler.ErrorEffect
import com.darkos.kmp.common.errorHandler.ErrorHandler
import com.darkos.kmp.common.errorHandler.runAndHandleErrors
import com.darkos.kmp.common.mvu.navigate
import com.darkos.kmp.common.validator.Validator
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
    private val navigation: ISignInNavigation,
    private val emailValidator: Validator,
    private val passwordValidator: Validator
) : ISignInEffectHandler {

    override suspend fun call(effect: Effect): Message {
        return when (effect) {
            is None -> {
                Idle
            }
            is SignInEffect.ProcessSignIn -> {
                validateSignInData(effect) ?: auth(effect)
            }
            is ErrorEffect -> {
                errorHandler.processErrorEffect(effect)
            }
            else -> {
                throw UnsupportedOperationException()
            }
        }
    }

    /**
     * Проводит валидацию данных, с помощью соответствующих валидаторов
     *
     * @return Возврашает сообщение об ошибке валидации либо null
     */
    private fun validateSignInData(effect: SignInEffect.ProcessSignIn): Message? {
        return SignInMessage.ValidationError(
            emailStatus = emailValidator.validate(effect.email),
            passwordStatus = passwordValidator.validate(effect.password)
        ).takeIf {
            (it.emailStatus && it.passwordStatus).not()
        }
    }

    /**
     * It makes a request to the server to authorize the user,
     * catches an error message if it occurs.
     *
     * If the authorization is successful, the user session information
     * is updated and navigation is made to the initial screen of the authorized user.
     */
    private suspend fun auth(effect: SignInEffect.ProcessSignIn): Message {
        return runAndHandleErrors {
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
            }.let {
                navigate(navigation::fromSignInToHome)
            }
        }
    }
}