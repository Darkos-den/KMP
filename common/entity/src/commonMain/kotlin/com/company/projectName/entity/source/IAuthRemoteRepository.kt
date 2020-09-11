package com.company.projectName.entity.source

import com.company.projectName.entity.models.common.RemoteResult

interface IAuthRemoteRepository {

    /**
     * Request user auth token by email
     */
    suspend fun login(email: String, password: String): RemoteResult<String>
}