package com.company.projectName.login.di

import com.company.projectName.login.LoginHandler
import com.darkos.mvu.models.MVUState
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.provider

//object LoginModule {
//    fun <T: MVUState>get() = Kodein.Module("Login"){
//        bind<LoginHandler<T, String>>() with  provider {
//
//        }
//    }
//}