package com.darkos.kmp.feature.signin.api

import com.darkos.kmp.feature.signin.model.dto.SignInDto
import com.darkos.kmp.feature.signin.model.dto.SignInResultDto

interface ISignInRemote {
    suspend fun signIn(dto: SignInDto): SignInResultDto
}