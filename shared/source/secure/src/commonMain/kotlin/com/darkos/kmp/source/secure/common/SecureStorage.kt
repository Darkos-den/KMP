package com.darkos.kmp.source.secure.common

import com.netguru.kissme.Kissme

class SecureStorage {
    data class Token(
        val token: String,
        val expire: Long
    )

    private val storage = Kissme(name = "my_secret_storage")

    private var isInit: Boolean
        get() = storage.getBoolean(STORAGE_STATE, false)
        set(value) = storage.putBoolean(STORAGE_STATE, value)

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

    var auth: Token
        get() = Token(
            token = authToken,
            expire = authExpire
        )
        set(value) {
            authToken = value.token
            authExpire = value.expire
        }

    var refresh: Token
        get() = Token(
            token = refreshToken,
            expire = refreshExpire
        )
        set(value) {
            refreshToken = value.token
            refreshExpire = value.expire
        }

    fun init() {
        if (isInit.not()) {
            isInit = true
        }
    }

    fun clear() {
        storage.clear()
    }

    companion object {
        private const val AUTH_TOKEN = "authToken"
        private const val AUTH_EXPIRE = "authExpire"
        private const val REFRESH_TOKEN = "refreshToken"
        private const val REFRESH_EXPIRE = "refreshExpire"
        private const val STORAGE_STATE = "storageState"
    }
}