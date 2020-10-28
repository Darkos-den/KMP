package com.darkos.projectName.domain.effectHandler

//class AuthEffectHandler(
//    private val remote: IAuthRemoteRepository,
//    private val secure: ISecureStorage
//) : EffectHandler {
//
//    override suspend fun call(effect: Effect): Message {
//        check(effect is AuthEffect) {
//            "this effect handler allow only AuthEffect"
//        }
//
//        return when (effect) {
//            is AuthEffect.ValidateLoginData -> {
//                effect.dto.let {
//                    AuthMessage.LoginValidation.Error(
//                        emailError = it.email
//                            .checkByRegex(EMAIL_REGEX) { "Email error message" },
//                        passwordError = it.password
//                            .checkByRegex(PASSWORD_REGEX) { "Password error message" }
//                    ).let { msg ->
//                        msg.takeIf { error ->
//                            error.passwordError == null && error.emailError == null
//                        }?.let {
//                            AuthMessage.LoginValidation.Success
//                        } ?: msg
//                    }
//                }
//            }
//            is AuthEffect.Login -> {
//                remote.login(effect.dto.email, effect.dto.password).let {
//                    when (it) {
//                        is RemoteResult.Success -> {
//                            secure.saveToken(it.data)
//                            AuthMessage.Login.Success
//                        }
//                        is RemoteResult.Error -> {
//                            AuthMessage.Login.Error(it.error.message.orEmpty())
//                        }
//                    }
//                }
//            }
//            is AuthEffect.CheckUser -> {
//                if (secure.getToken() == null) {
//                    AuthMessage.CheckUser.NotFound
//                } else {
//                    AuthMessage.CheckUser.Found
//                }
//            }
//        }
//    }
//
//    private fun String.checkByRegex(regex: Regex, block: () -> String): String? {
//        return this.takeIf {
//            regex.matches(it).not()
//        }?.let {
//            block()
//        }
//    }
//
//    companion object {
//        private val EMAIL_REGEX = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+".toRegex()
//        private val PASSWORD_REGEX = ".{6,}".toRegex()
//    }
//}