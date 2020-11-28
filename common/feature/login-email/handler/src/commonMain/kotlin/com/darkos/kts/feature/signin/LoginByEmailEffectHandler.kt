package com.darkos.kts.feature.signin

import com.darkos.kts.feature.signin.model.dto.LoginDTO
import com.darkos.kts.feature.signin.model.dto.TokenDTO
import com.darkos.mvu.login.LoginEffectHandler
import com.darkos.mvu.models.flow.FlowEffect
import com.darkos.mvu.models.flow.FlowMessage
import com.darkos.mvu.validation.IValidationHandler
import kotlinx.coroutines.flow.Flow

abstract class LoginByEmailEffectHandler(
    remote: LoginByEmailRemote,
    validation: IValidationHandler
) : LoginEffectHandler<LoginDTO, TokenDTO>(remote, validation) {
    override suspend fun call(effect: FlowEffect): Flow<FlowMessage> {
        throw IllegalStateException("this handler does not support calling as Flow")
    }
}