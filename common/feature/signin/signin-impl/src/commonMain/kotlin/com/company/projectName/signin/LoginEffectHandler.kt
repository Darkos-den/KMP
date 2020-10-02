package com.company.projectName.signin

import com.company.projectName.login.LoginHandler
import com.company.projectName.login.LoginRemote
import com.company.projectName.signin.model.dto.LoginDTO
import com.company.projectName.signin.model.dto.TokenDTO

class LoginEffectHandler(
    remote: LoginRemote<LoginDTO, TokenDTO>
) : LoginHandler<LoginDTO, TokenDTO>(remote) {

    override suspend fun processLoginResult(result: TokenDTO) {
        result.token.let {
            //todo: save token
        }
    }
}