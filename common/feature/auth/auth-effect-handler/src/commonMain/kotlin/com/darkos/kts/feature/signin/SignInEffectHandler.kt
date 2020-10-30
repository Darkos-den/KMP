package com.darkos.kts.feature.signin

import com.darkos.core.messageProcessor.MessageProcessor
import com.darkos.core.navigation.Navigator
import com.darkos.kts.feature.auth.IAuthEffectHandler
import com.darkos.kts.feature.signin.model.dto.TokenDTO
import com.darkos.mvu.models.Effect
import com.darkos.mvu.models.Message
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
            else -> {
                super.call(effect)
            }
        }
    }

    override fun processError(error: Exception): Message {
        TODO("Not yet implemented")
    }

    override fun processSuccess(): Message {
        TODO("Not yet implemented")
    }

    override suspend fun processLoginResult(result: TokenDTO) {
        TODO("Not yet implemented")
    }
}