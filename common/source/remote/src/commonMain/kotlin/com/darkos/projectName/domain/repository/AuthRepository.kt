package com.darkos.projectName.domain.repository

import com.darkos.projectName.domain.repository.base.BaseRepository
import com.darkos.projectName.entity.models.common.RemoteResult
import com.darkos.projectName.entity.source.remote.IAuthRemoteRepository
import io.ktor.client.request.*

class AuthRepository : BaseRepository(), IAuthRemoteRepository {

    override suspend fun login(email: String, password: String): RemoteResult<String> {
        return execute {
            it.get("${baseURL}/posts")
        }
    }
}