package com.company.core.di

import com.company.core.handler.validationHandler
import com.darkos.mvu.validation.IValidationHandler
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.singleton

val coreModule = Kodein.Module("Core"){
    bind<IValidationHandler>() with singleton { validationHandler }
}