package com.darkos.kts.entity.source.secure

interface ISecureStorage {
    fun saveToken(token: String)
    suspend fun getToken(): String?
}