package com.darkos.kts.domain.common

import com.darkos.kts.entity.source.secure.ISecureStorage
import com.netguru.kissme.Kissme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SecureStorage : ISecureStorage {

    private val storage: Kissme by lazy {
        Kissme(STORAGE_NAME)
    }

    override fun saveToken(token: String) {
        storage.putString(KEY_TOKEN, token)
    }

    override suspend fun getToken(): String? {
        return withContext(Dispatchers.Main){
            storage.getString(KEY_TOKEN, null)
        }
    }

    companion object {
        private const val STORAGE_NAME = "secret"
        private const val KEY_TOKEN = "token"
    }
}