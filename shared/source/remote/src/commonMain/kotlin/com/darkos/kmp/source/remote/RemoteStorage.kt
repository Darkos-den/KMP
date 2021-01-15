package com.darkos.kmp.source.remote

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.utils.io.core.*

class RemoteStorage {
    private val httpClient: HttpClient
        get() = HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }

            HttpResponseValidator {
                validateResponse {
                    val statusCode = it.status.value
                    when (statusCode) {
                        in 300..399 -> throw RedirectResponseException(it, it.toString())
                        in 400..499 -> throw ClientRequestException(it, it.toString())
                        in 500..599 -> throw ServerResponseException(it, it.toString())
                    }

                    if (statusCode >= 600) {
                        throw ResponseException(it, it.toString())
                    }
                }
            }
        }

    class Config(
        var environment: Environment = Environment.DEVELOPMENT,
        var apiVersion: Int = 1
    ) {
        enum class Environment {
            DEVELOPMENT, PRODUCTION
        }
    }

    private var config = Config()

    fun configure(block: Config.() -> Unit) {
        config = Config().apply(block)
    }

    private val environment: String
        get() = when (config.environment) {
            Config.Environment.DEVELOPMENT -> "dev"
            Config.Environment.PRODUCTION -> "prod"
        }

    val baseUrl: String
        get() = baseUrl(config.apiVersion)

    fun baseUrl(customApiVersion: Int): String {
        return "https://myapi.$environment/$customApiVersion"
    }

    suspend fun <T> use(block: suspend RemoteStorage.(HttpClient) -> T): T {
        return httpClient.use {
            block(it)
        }
    }
}