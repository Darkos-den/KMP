package com.company.projectName.signin.di

import com.company.projectName.entity.models.dto.LoginDTO
import com.company.projectName.entity.models.dto.TokenDTO
import com.company.projectName.login.di.LoginModule
import com.company.projectName.signin.LoginEffectHandler
import com.company.projectName.signin.feature
import org.kodein.di.Kodein
import org.kodein.di.erased.instance

object SignInModule {
    fun get() = Kodein.Module("SignIn"){
        LoginModule.get(
            handlerCreator = {
                LoginEffectHandler(
                    remote = instance()
                )
            },
            reducerCreator = {
                feature
            }
        ).let {
            import(it)
        }
    }
}