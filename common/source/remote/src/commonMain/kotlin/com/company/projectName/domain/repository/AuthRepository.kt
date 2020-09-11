package com.company.projectName.domain.repository

import com.company.projectName.domain.repository.base.BaseRepository
import com.company.projectName.entity.models.common.RemoteResult
import com.company.projectName.entity.source.IAuthRemoteRepository
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.utils.io.core.*

class AuthRepository : BaseRepository(), IAuthRemoteRepository {

    override suspend fun login(email: String, password: String): RemoteResult<String> {
        return try {
            createClient().use {
                it.get<String>("${baseURL}/posts")
            }.let {
                RemoteResult.Success(it)
            }
        } catch (e: Exception) {
            RemoteResult.Error(e)
        }
    }
}