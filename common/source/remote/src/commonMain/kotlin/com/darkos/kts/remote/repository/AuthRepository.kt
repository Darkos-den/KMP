package com.darkos.kts.remote.repository

import com.darkos.kts.remote.repository.base.BaseRepository
import com.darkos.kts.entity.models.common.RemoteResult
import com.darkos.kts.entity.source.remote.IAuthRemoteRepository
import io.ktor.client.request.*

class AuthRepository : BaseRepository(), IAuthRemoteRepository {

    override suspend fun login(email: String, password: String): RemoteResult<String> {
        return execute {
            it.get("${baseURL}/posts")
        }
    }
}