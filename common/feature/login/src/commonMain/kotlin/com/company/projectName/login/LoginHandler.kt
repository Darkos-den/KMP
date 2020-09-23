package com.company.projectName.login

import com.company.projectName.entity.models.common.RemoteResult
import com.company.projectName.login.model.mvu.LoginEffect
import com.company.projectName.login.model.mvu.LoginMessage
import com.darkos.mvu.EffectHandler
import com.darkos.mvu.models.Effect
import com.darkos.mvu.models.Message

abstract class LoginHandler<T : Any, R : Any>(
    private val remote: LoginRemote<T, R>
) : EffectHandler {

    abstract suspend fun processLoginResult(result: R)

    override suspend fun call(effect: Effect): Message {
        require(effect is LoginEffect.Login<*>) {
            "supported only LoginEffect.Login"
        }

        return remote.login(effect.data as T).let {
            when (it) {
                is RemoteResult.Error -> {
                    LoginMessage.Error(it.error)
                }
                is RemoteResult.Success -> {
                    processLoginResult(it.data)
                    LoginMessage.Success
                }
            }
        }
    }
}