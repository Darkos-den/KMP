package com.company.projectName.login

import com.company.projectName.entity.models.common.RemoteResult

interface LoginRemote<T : Any, R : Any> {
    suspend fun login(data: T): RemoteResult<R>
}