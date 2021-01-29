package com.darkos.kmp.feature.signin.api

import com.darkos.kmp.feature.signin.model.dto.SignInDto
import com.darkos.kmp.feature.signin.model.dto.SignInResultDto
import com.darkos.kmp.feature.signin.model.exception.SignInException

interface ISignInRemote {
    /**
     * @param dto auth data
     *
     * @throws SignInException.ConnectionError if connection error
     * @throws SignInException.InvalidAuthData if invalid auth data
     *
     * @return user session info
     */
    suspend fun signIn(dto: SignInDto): SignInResultDto
}