package com.company.projectName.domain.effectHandler

import com.company.projectName.domain.model.effect.AuthEffect
import com.company.projectName.domain.model.message.AuthMessage
import com.company.projectName.entity.models.common.RemoteResult
import com.company.projectName.entity.source.IAuthRemoteRepository
import com.darkos.mvu.EffectHandler
import com.darkos.mvu.models.Effect
import com.darkos.mvu.models.Message

class AuthEffectHandler(
    private val remote: IAuthRemoteRepository
) : EffectHandler {

    override suspend fun call(effect: Effect): Message {
        check(effect is AuthEffect) {
            "this effect handler allow only AuthEffect"
        }

        return when (effect) {
            is AuthEffect.ValidateLoginData -> {
                effect.dto.let {
                    AuthMessage.LoginValidationError(
                        emailError = it.email
                            .checkByRegex(EMAIL_REGEX) { "Email error message" },
                        passwordError = it.password
                            .checkByRegex(PASSWORD_REGEX) { "Password error message" }
                    ).let { msg ->
                        msg.takeIf { error ->
                            error.passwordError == null && error.emailError == null
                        }?.let {
                            AuthMessage.LoginValidationSuccess
                        } ?: msg
                    }
                }
            }
            is AuthEffect.Login -> {
                remote.login(effect.dto.email, effect.dto.password).let {
                    when (it) {
                        is RemoteResult.Success -> {
                            AuthMessage.LoginSuccess
                        }
                        is RemoteResult.Error -> {
                            AuthMessage.LoginError(it.error.message.orEmpty())
                        }
                    }
                }
            }
        }
    }

    private fun String.checkByRegex(regex: Regex, block: () -> String): String? {
        return this.takeIf {
            regex.matches(it).not()
        }?.let {
            block()
        }
    }

    companion object {
        private val EMAIL_REGEX = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+".toRegex()
        private val PASSWORD_REGEX = ".{6,}".toRegex()
    }
}