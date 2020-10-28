package com.darkos.kts.domain.repository.base

import com.darkos.kts.entity.models.common.RemoteResult
import com.darkos.kts.entity.models.exception.NetworkException
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.http.*
import io.ktor.utils.io.core.*

abstract class BaseRepository {

    protected val baseURL = "https://jsonplaceholder.typicode.com"

    private fun createClient() = HttpClient {
        Logging {
            logger = Logger.SIMPLE
            level = LogLevel.BODY
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    protected suspend fun <T : Any> execute(block: suspend (HttpClient) -> T): RemoteResult<T> {
        return try {
            createClient().use {
                block(it)
            }.let {
                RemoteResult.Success(it)
            }
        } catch (e: Exception) {
            processError(e).let {
                RemoteResult.Error(it)
            }
        }
    }

    private fun processError(e: Exception): NetworkException {
        return handleNetworkError(e) ?: run {
            if (e is ClientRequestException) {
                e.response?.status?.let {
                    when (it) {
                        HttpStatusCode.Unauthorized -> NetworkException.RequestError.Unauthorized
                        HttpStatusCode.BadRequest -> NetworkException.RequestError.BadRequest
                        HttpStatusCode.NotFound -> NetworkException.RequestError.NotFound
                        HttpStatusCode.InternalServerError -> NetworkException.RequestError.ServerError
                        else -> NetworkException.UnknownError
                    }
                } ?: NetworkException.UnknownError
            } else {
                NetworkException.UnknownError
            }
        }
    }

    private fun handleNetworkError(e: Exception): NetworkException? {
        return e.toString().takeIf {
            it.contains("java.net.UnknownHostException")
                    || it.contains("java.net.ConnectionException")
        }?.let {
            NetworkException.ConnectionError
        }
    }
}