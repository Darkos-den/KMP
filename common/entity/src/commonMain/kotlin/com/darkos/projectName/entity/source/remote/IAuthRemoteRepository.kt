package com.darkos.projectName.entity.source.remote

import com.darkos.projectName.entity.models.common.RemoteResult

interface IAuthRemoteRepository {

    /**
     * Request user auth token by email
     */
    suspend fun login(email: String, password: String): RemoteResult<String>
}