package com.company.projectName.domain.repository

import com.company.projectName.domain.repository.base.BaseRepository
import com.company.projectName.entity.models.common.RemoteResult
import com.company.projectName.entity.source.IAuthRemoteRepository
import io.ktor.client.request.*

class AuthRepository : BaseRepository(), IAuthRemoteRepository {

    override suspend fun login(email: String, password: String): RemoteResult<String> {
        return execute {
            it.get("${baseURL}/posts")
        }
    }
}