package com.darkos.kts.feature.auth

import com.darkos.kts.feature.auth.IAuthEffectHandler
import com.darkos.kts.feature.signin.LoginByEmailRemote
import com.darkos.kts.feature.signin.LoginByEmailEffectHandler
import com.darkos.kts.feature.signin.model.dto.TokenDTO
import com.darkos.mvu.models.Message
import com.darkos.mvu.validation.IValidationHandler

class AuthEffectHandler(
    remote: LoginByEmailRemote,
    validation: IValidationHandler
) : LoginByEmailEffectHandler(remote, validation), IAuthEffectHandler {

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