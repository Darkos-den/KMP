package com.company.projectName.domain.effectHandler

import com.company.projectName.domain.effectHandler.ValidateEffectHandler.Companion.FIELD_TYPE_EMAIL
import com.company.projectName.domain.effectHandler.ValidateEffectHandler.Companion.FIELD_TYPE_PASSWORD
import com.company.projectName.domain.model.exception.NotSupportedMessageException
import com.company.projectName.domain.model.mvu.auth.AuthEffect
import com.company.projectName.domain.model.mvu.general.GeneralEffect
import com.company.projectName.domain.model.mvu.navigation.NavigationEffect
import com.company.projectName.domain.model.mvu.sample.SampleEffect
import com.company.projectName.login.model.mvu.LoginEffect
import com.company.projectName.validation.ValidationEffectHandler
import com.company.projectName.validation.model.mvu.ValidationEffect
import com.darkos.mvu.EffectHandler
import com.darkos.mvu.models.Effect
import com.darkos.mvu.models.Message

internal class AppEffectHandler(
    private val generalEffectHandler: GeneralEffectHandler,
    private val navigationEffectHandler: NavigationEffectHandler,
    private val authEffectHandler: AuthEffectHandler,
    private val sampleEffectHandler: SampleEffectHandler,
    private val loginEffectHandler: LoginEffectHandler,
    private val validateEffectHandler: ValidateEffectHandler
) : EffectHandler {

    override suspend fun call(effect: Effect): Message {
        return when (effect) {
            is NavigationEffect -> navigationEffectHandler.call(effect)
            is GeneralEffect -> generalEffectHandler.call(effect)
            is AuthEffect -> authEffectHandler.call(effect)
            is SampleEffect -> sampleEffectHandler.call(effect)
            is ValidationEffect -> validateEffectHandler.call(effect)
            is LoginEffect -> loginEffectHandler.call(effect)
            else -> throw NotSupportedMessageException()
        }
    }
}