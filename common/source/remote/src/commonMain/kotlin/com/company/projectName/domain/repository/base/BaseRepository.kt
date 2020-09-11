package com.company.projectName.domain.repository.base

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

abstract class BaseRepository {

    protected val baseURL = "https://jsonplaceholder.typicode.com"

    fun createClient() = HttpClient {
        Logging {
            logger = Logger.SIMPLE
            level = LogLevel.BODY
        }
        install(JsonFeature){
            serializer = KotlinxSerializer()
        }
    }
}