package com.darkos.kts.secure.repository.base

import com.netguru.kissme.Kissme

abstract class BaseSecureStorage {

    protected val storage: Kissme by lazy {
        Kissme(STORAGE_NAME)
    }

    companion object {
        private const val STORAGE_NAME = "secret"
        const val KEY_TOKEN = "token"
    }
}