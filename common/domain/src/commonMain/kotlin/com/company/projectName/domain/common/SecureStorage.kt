package com.company.projectName.domain.common

import com.company.projectName.entity.source.secure.ISecureStorage
import com.netguru.kissme.Kissme

class SecureStorage : ISecureStorage {

    private val storage: Kissme by lazy {
        Kissme(STORAGE_NAME)
    }

    override fun saveToken(token: String) {
        storage.putString(KEY_TOKEN, token)
    }

    override fun getToken(): String? {
        return storage.getString(KEY_TOKEN, null)
    }

    companion object {
        private const val STORAGE_NAME = "secret"
        private const val KEY_TOKEN = "token"
    }
}