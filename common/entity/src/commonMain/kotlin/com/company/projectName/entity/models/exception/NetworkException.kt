package com.company.projectName.entity.models.exception

sealed class NetworkException(message: String) : Exception(message) {
    object ConnectionError : NetworkException("Connection error")
    object UnknownError : NetworkException("Unknown Error")

    sealed class RequestError(message: String) : NetworkException(message) {
        object NotFound : RequestError("Not found")
        object Unauthorized : RequestError("Unauthorized")
        object BadRequest : RequestError("Bad request")
        object ServerError : RequestError("Server error")
    }
}