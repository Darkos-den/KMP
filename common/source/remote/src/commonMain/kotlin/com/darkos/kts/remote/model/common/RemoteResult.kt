package com.darkos.kts.remote.model.common

sealed class RemoteResult<T : Any> {
    class Success<T : Any>(
        val data: T
    ) : RemoteResult<T>()

    class Error<T : Any>(
        val error: Exception
    ) : RemoteResult<T>()
}

fun <T : Any, M : Any> RemoteResult<T>.map(mapper: (T) -> M): RemoteResult<M> {
    return when (this) {
        is RemoteResult.Error -> RemoteResult.Error(this.error)
        is RemoteResult.Success -> {
            RemoteResult.Success(mapper(this.data))
        }
    }
}

fun <T : Any, M : Any> RemoteResult<List<T>>.mapAsList(mapper: (T) -> M): RemoteResult<List<M>> {
    return when (this) {
        is RemoteResult.Error -> RemoteResult.Error(this.error)
        is RemoteResult.Success -> {
            RemoteResult.Success(this.data.map(mapper))
        }
    }
}