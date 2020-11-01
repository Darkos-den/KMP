package com.darkos.kts.secure.repository.base

expect class SecureStorage {
    suspend fun getString(key: String, defValue: String?): String?
    suspend fun putString(key: String, value: String)
}