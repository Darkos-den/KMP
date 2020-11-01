package com.darkos.kts.secure.repository.base

abstract class BaseSecureRepository(
    protected val storage: SecureStorage
) {

    companion object {
        const val KEY_TOKEN = "token"
    }
}