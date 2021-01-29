package com.darkos.kmp.feature.signin.api

import com.darkos.kmp.feature.signin.model.SignInEffect
import com.darkos.kmp.feature.signin.model.SignInMessage

/**
 * Сценарии:
 *
 *  - Ввод пользовательских данных
 *
 *  стартовые сообщения:
 *  @see SignInMessage.EmailChanged изменение email поля - обновлняет состояние, если поле
 *  было в состоянии ошибки - сбрасываем это состояние ошибки для этого поля, не порождает эффект
 *  @see SignInMessage.PasswordChanged изменение password поля - обновлняет состояние, если поле
 *  было в состоянии ошибки - сбрасываем это состояние ошибки для этого поля, не порождает эффект
 *
 *  - Авторизация
 *
 *  стартовые сообщения:
 *  @see SignInMessage.SubmitClicked переводит экран в состояние прогресса,
 *  порождает эффект [SignInEffect.ProcessSignIn]
 *
 *  эффекты:
 *  @see SignInEffect.ProcessSignIn
 */
fun about(): String {
    return "SignIn feature"
}