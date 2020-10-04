package com.company.core.di

import com.company.core.handler.validationHandler
import com.darkos.mvu.validation.ValidationHandler
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.singleton

val coreModule = Kodein.Module("Core"){
    bind<ValidationHandler>() with singleton { validationHandler }
}