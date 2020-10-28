package com.darkos.projectName.signin

import com.darkos.projectName.signin.model.dto.LoginDTO
import com.darkos.projectName.signin.model.dto.TokenDTO
import com.darkos.mvu.login.LoginEffectHandler
import com.darkos.mvu.validation.IValidationHandler

abstract class LoginByEmailEffectHandler(
    remote: LoginByEmailRemote,
    validation: IValidationHandler
) : LoginEffectHandler<LoginDTO, TokenDTO>(remote, validation)