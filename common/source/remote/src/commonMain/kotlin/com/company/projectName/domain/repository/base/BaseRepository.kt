package com.company.projectName.domain.repository.base

import io.ktor.client.*
import io.ktor.client.features.logging.*

abstract class BaseRepository {

    protected val baseURL = "https://jsonplaceholder.typicode.com"

    fun createClient() = HttpClient {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }
}