package com.company.projectName.auth

import com.company.projectName.signin.SignInRemote
import com.company.projectName.signin.model.dto.LoginDTO
import com.company.projectName.signin.model.dto.TokenDTO
import com.darkos.mvu.validation.IValidationHandler

class AuthEffectHandler(
    remote: SignInRemote,
    validation: IValidationHandler
) : AuthEffectHandler<LoginDTO, TokenDTO>(remote, validation) {

    override suspend fun processLoginResult(result: TokenDTO) {
        result.token.let {
            //todo: save token
        }
    }
}