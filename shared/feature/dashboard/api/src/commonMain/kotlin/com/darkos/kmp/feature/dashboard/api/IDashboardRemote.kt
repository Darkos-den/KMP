package com.darkos.kmp.feature.dashboard.api

import com.darkos.kmp.feature.splash.model.dto.RefreshDto

interface IDashboardRemote {
    suspend fun refreshAuthToken(refresh: String): RefreshDto
}