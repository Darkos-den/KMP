package com.company.projectName.auth

import com.company.projectName.signin.LoginByEmailRemote
import com.company.projectName.signin.LoginByEmailEffectHandler
import com.company.projectName.signin.model.dto.TokenDTO
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