package com.company.projectName.entity.source.secure

interface ISecureStorage {
    fun saveToken(token: String)
    suspend fun getToken(): String?
}