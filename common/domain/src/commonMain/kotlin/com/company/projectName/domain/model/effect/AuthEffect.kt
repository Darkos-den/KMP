package com.company.projectName.domain.model.effect

import com.company.projectName.domain.model.dto.LoginDTO
import com.darkos.mvu.models.Effect

internal sealed class AuthEffect : Effect() {
    class ValidateLoginData(val dto: LoginDTO) : AuthEffect()
    class Login(val dto: LoginDTO) : AuthEffect()
}