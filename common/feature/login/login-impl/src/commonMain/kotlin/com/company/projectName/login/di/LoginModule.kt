package com.company.projectName.login.di

import com.company.projectName.login.LoginHandler
import com.company.projectName.login.LoginReducer
import com.darkos.mvu.models.MVUState
import org.kodein.di.Kodein
import org.kodein.di.bindings.NoArgBindingKodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.provider

object LoginModule {
    fun <State: MVUState, Request: Any, Result: Any>get(
        handlerCreator: NoArgBindingKodein<Any?>.() -> LoginHandler<Request, Result>,
        reducerCreator: NoArgBindingKodein<Any?>.() -> LoginReducer<State, Request>
    ) = Kodein.Module("Login"){
        bind<LoginHandler<Request, Result>>() with  provider {
            handlerCreator()
        }
        bind<LoginReducer<State, Request>>() with provider {
            reducerCreator()
        }
    }
}