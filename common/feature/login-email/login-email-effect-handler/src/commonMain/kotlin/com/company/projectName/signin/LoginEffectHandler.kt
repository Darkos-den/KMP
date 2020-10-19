package com.company.projectName.signin

import com.company.projectName.signin.model.dto.LoginDTO
import com.company.projectName.signin.model.dto.TokenDTO
import com.darkos.mvu.login.LoginEffectHandler
import com.darkos.mvu.validation.IValidationHandler

abstract class LoginEffectHandler(
    remote: LoginByEmailRemote,
    validation: IValidationHandler
) : LoginEffectHandler<LoginDTO, TokenDTO>(remote, validation)