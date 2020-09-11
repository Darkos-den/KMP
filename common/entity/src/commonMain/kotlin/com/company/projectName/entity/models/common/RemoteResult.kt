package com.company.projectName.entity.models.common

sealed class RemoteResult<T: Any> {
    class Success<T: Any>(
        val data: T
    ) : RemoteResult<T>()

    class Error<T: Any>(
        val error: Exception
    ) : RemoteResult<T>()
}