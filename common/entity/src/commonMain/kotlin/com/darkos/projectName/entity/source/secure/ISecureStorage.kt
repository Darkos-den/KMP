package com.darkos.projectName.entity.source.secure

interface ISecureStorage {
    fun saveToken(token: String)
    suspend fun getToken(): String?
}