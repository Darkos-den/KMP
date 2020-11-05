package com.darkos.validation.di

import com.darkos.mvu.validation.IValidationHandler
import com.darkos.validation.appValidationHandler
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.singleton

val ValidationModule = Kodein.Module("Validation") {
    bind<IValidationHandler>() with singleton { appValidationHandler }
}