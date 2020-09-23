package com.company.projectName.domain.effectHandler

import com.company.projectName.entity.models.dto.LoginDTO
import com.company.projectName.entity.models.dto.TokenDTO
import com.company.projectName.login.LoginHandler
import com.company.projectName.login.LoginRemote

class LoginEffectHandler(
    remote: LoginRemote<LoginDTO, TokenDTO>
) : LoginHandler<LoginDTO, TokenDTO>(remote) {

    override suspend fun processLoginResult(result: TokenDTO) {
        result.token.let {
            //todo: save token
        }
    }
}