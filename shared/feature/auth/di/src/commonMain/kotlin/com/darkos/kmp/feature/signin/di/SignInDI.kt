package com.darkos.kmp.feature.signin.di

import com.darkos.kmp.feature.signin.SignInComponent
import com.darkos.kmp.feature.signin.SignInEffectHandler
import com.darkos.kmp.feature.signin.SignInReducer
import com.darkos.kmp.feature.signin.api.ISignInComponent
import com.darkos.kmp.feature.signin.api.ISignInEffectHandler
import com.darkos.kmp.feature.signin.api.ISignInReducer
import kotlinx.coroutines.InternalCoroutinesApi
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

class SignInDI {

    @OptIn(InternalCoroutinesApi::class)
    val module = DI.Module(TAG) {
        bind<ISignInReducer>() with provider { SignInReducer() }
        bind<ISignInEffectHandler>() with provider {
            SignInEffectHandler(
                remote = instance(),
                secure = instance(),
                errorHandler = instance(),
                navigation = instance()
            )
        }
        bind<ISignInComponent>() with provider {
            SignInComponent(
                effectHandler = instance(),
                reducer = instance()
            )
        }
    }

    companion object {
        private const val TAG = "Auth.SignIn"
    }
}