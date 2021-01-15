package com.darkos.kmp.source.remote

import com.darkos.kmp.feature.splash.api.ISplashRemote
import com.darkos.kmp.feature.splash.model.dto.RefreshDto
import com.darkos.kmp.feature.splash.model.dto.TokenDto
import com.darkos.kmp.source.remote.model.RefreshResponse
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*

class SplashRemote(
    private val remote: RemoteStorage
) : ISplashRemote {

    override suspend fun refreshAuthToken(refresh: String): RefreshDto {
        return remote.use {
            it.post<RefreshResponse>("$baseUrl/auth/refresh") {
                body = FormDataContent(
                    Parameters.build {
                        append("Refresh-token", refresh)
                    }
                )
            }
        }.let { response ->
            RefreshDto(
                auth = response.access.let {
                    TokenDto(
                        token = it.token,
                        expire = it.expire
                    )
                },
                refresh = response.refresh.let {
                    TokenDto(
                        token = it.token,
                        expire = it.expire
                    )
                }
            )
        }
    }
}