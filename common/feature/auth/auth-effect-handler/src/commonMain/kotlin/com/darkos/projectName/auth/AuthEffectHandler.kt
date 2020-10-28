package com.darkos.projectName.auth

import com.darkos.projectName.signin.LoginByEmailRemote
import com.darkos.projectName.signin.LoginByEmailEffectHandler
import com.darkos.projectName.signin.model.dto.TokenDTO
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