package com.darkos.kmp.source.remote

import com.darkos.kmp.feature.splash.api.ISplashRemote
import com.darkos.kmp.feature.splash.model.dto.RefreshDto
import com.darkos.kmp.feature.splash.model.dto.TokenDto

class SplashRemote(
    private val remote: RemoteStorage
) : ISplashRemote {

    override suspend fun refreshAuthToken(refresh: String): RefreshDto {
        //TODO("Not yet implemented")
        return RefreshDto(
            auth = TokenDto(
                token = "12345",
                expire = 1609935703381L
            ),
            refresh = TokenDto(
                token = "12345",
                expire = 1709935703381L
            )
        )
    }
}