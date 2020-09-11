package com.company.projectName.domain.common

import io.ktor.client.HttpClient

class WebClient {
    companion object {
        const val baseURL = "https://jsonplaceholder.typicode.com"
    }

    val client = HttpClient {
//        install(JsonFeature) { serializer = KotlinxSerializer() }
    }
}