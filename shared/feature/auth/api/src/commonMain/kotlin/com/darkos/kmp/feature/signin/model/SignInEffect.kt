package com.darkos.kmp.feature.signin.model

import com.darkos.kmp.common.errorHandler.ErrorMessage
import com.darkos.mvu.model.Effect
import com.darkos.mvu.model.Idle

sealed class SignInEffect : Effect() {

    /**
     * Single Эффект, запускающий процесс валидации данных и авторизацию пользователя
     * с помощью переданых данных.
     *
     * Возможные финальные сообщения:
     * @see SignInMessage.ValidationError в случае если возникла ошибка валидации
     * @see ErrorMessage.Network в случае если во время авторизации нет интернета
     * @see ErrorMessage.App в случае ошибки при обработке запроса на авторизацию
     * @see Idle в случае если сцнарий обработается без ошибок
     */
    class ProcessSignIn(
        val email: String,
        val password: String
    ) : SignInEffect()
}