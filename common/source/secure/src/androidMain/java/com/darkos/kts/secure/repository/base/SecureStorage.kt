package com.darkos.kts.secure.repository.base

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.security.crypto.MasterKeys

actual class SecureStorage {

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = EncryptedSharedPreferences.create(
            context,
            "secret_shared_prefs",
            createMasterKey(context),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private fun createMasterKey(context: Context) = MasterKey.Builder(context)
        .setKeyGenParameterSpec(MasterKeys.AES256_GCM_SPEC)
        .build()

    actual suspend fun getString(key: String, defValue: String?): String? {
        return sharedPreferences.getString(key, defValue)
    }

    actual suspend fun putString(key: String, value: String) {
        sharedPreferences.edit()
            .putString(key, value)
            .apply()
    }
}