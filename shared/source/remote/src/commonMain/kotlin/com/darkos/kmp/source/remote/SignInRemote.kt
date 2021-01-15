package com.darkos.kmp.source.remote

import com.darkos.kmp.feature.signin.api.ISignInRemote
import com.darkos.kmp.feature.signin.model.dto.SignInDto
import com.darkos.kmp.feature.signin.model.dto.SignInResultDto
import com.darkos.kmp.feature.signin.model.dto.TokenDto
import com.darkos.kmp.source.remote.model.RefreshResponse
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*

class SignInRemote(
    private val remote: RemoteStorage
) : ISignInRemote {

    override suspend fun signIn(dto: SignInDto): SignInResultDto {
        return remote.use {
            it.post<RefreshResponse>("$baseUrl/sign-in") {
                body = FormDataContent(
                    Parameters.build {
                        append("email", dto.email)
                        append("password", dto.password)
                    }
                )
            }
        }.let {
            SignInResultDto(
                auth = it.access.let {
                    TokenDto(
                        token = it.token,
                        expire = it.expire
                    )
                },
                refresh = it.refresh.let {
                    TokenDto(
                        token = it.token,
                        expire = it.expire
                    )
                }
            )
        }
    }
}