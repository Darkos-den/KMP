package com.company.projectName.signin

import com.company.projectName.login.LoginEffectHandler
import com.company.projectName.signin.model.dto.LoginDTO
import com.company.projectName.signin.model.dto.TokenDTO
import com.darkos.mvu.models.Message
import com.darkos.mvu.validation.IValidationHandler

class LoginEffectHandler(
    remote: SignInRemote,
    validation: IValidationHandler
) : LoginEffectHandler<LoginDTO, TokenDTO>(remote, validation) {

    override suspend fun processLoginResult(result: TokenDTO) {
        result.token.let {
            //todo: save token
        }
    }

    override fun processError(error: Exception): Message {
        TODO("Not yet implemented")
    }

    override fun processSuccess(): Message {
        TODO("Not yet implemented")
    }
}