package com.darkos.kmp.feature.splash.api

import com.darkos.kmp.feature.splash.model.dto.RefreshDto

interface ISplashRemote {
    suspend fun refreshAuthToken(refresh: String): RefreshDto
}