package com.darkos.kmp.source.secure

import com.netguru.kissme.Kissme

class SecureStorage {
    private val storage = Kissme(name = "my_secret_storage")

    var authToken: String
        get() = storage.getString(AUTH_TOKEN, "").orEmpty()
        set(value) = storage.putString(AUTH_TOKEN, value)

    var authExpire: Long
        get() = storage.getLong(AUTH_EXPIRE, 0L)
        set(value) = storage.putLong(AUTH_EXPIRE, value)

    var refreshToken: String
        get() = storage.getString(REFRESH_TOKEN, "").orEmpty()
        set(value) = storage.putString(REFRESH_TOKEN, value)

    var refreshExpire: Long
        get() = storage.getLong(REFRESH_EXPIRE, 0L)
        set(value) = storage.putLong(REFRESH_EXPIRE, value)

    fun clear() {
        storage.clear()
    }

    companion object {
        private const val AUTH_TOKEN = "authToken"
        private const val AUTH_EXPIRE = "authExpire"
        private const val REFRESH_TOKEN = "refreshToken"
        private const val REFRESH_EXPIRE = "refreshExpire"
    }
}