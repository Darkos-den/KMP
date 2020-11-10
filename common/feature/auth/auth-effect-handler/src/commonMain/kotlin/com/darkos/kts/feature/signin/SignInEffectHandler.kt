package com.darkos.kts.feature.signin

import com.darkos.core.messageProcessor.MessageProcessor
import com.darkos.core.model.CoreUiData
import com.darkos.core.model.exception.NetworkException
import com.darkos.core.mvu.andThenIdle
import com.darkos.kts.feature.signin.model.SignInNavigation
import com.darkos.kts.feature.signin.model.dto.TokenDTO
import com.darkos.kts.feature.signin.model.mvu.SignInEffect
import com.darkos.mvu.models.Effect
import com.darkos.mvu.models.Message
import com.darkos.mvu.navigator.Navigator
import com.darkos.mvu.validation.IValidationHandler

class SignInEffectHandler(
    remote: LoginByEmailRemote,
    validation: IValidationHandler,
    private val navigator: Navigator,
    private val secure: ISignInSecure,
    private val messageProcessor: MessageProcessor
) : LoginByEmailEffectHandler(remote, validation), ISignInEffectHandler {

    override suspend fun call(effect: Effect): Message {
        return when (effect) {
            is SignInEffect.Navigation.NavigateToSignUp -> {
                navigator.navigate(SignInNavigation.NavigateToSignUp)
                    .andThenIdle()
            }
            else -> {
                super.call(effect)
            }
        }
    }

    override suspend fun processError(error: Exception): Message {
        return when (error) {
            is NetworkException.ConnectionError -> {
                messageProcessor.showMessage(CoreUiData.internetErrorMessage)
            }
            else -> {
                messageProcessor.showMessage(error.message.orEmpty())
            }
        }.andThenIdle()
    }

    override suspend fun processSuccess(): Message {
        return navigator.navigate(SignInNavigation.NavigateToMain)
            .andThenIdle()
    }

    override suspend fun processLoginResult(result: TokenDTO) {
        secure.saveToken(result.token)
    }
}