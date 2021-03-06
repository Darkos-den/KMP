package com.darkos.kmp.source.secure

import com.darkos.kmp.common.debugFeatures.IDebugSecure
import com.darkos.kmp.source.secure.common.SecureStorage

class DebugSecure(
    private val secure: SecureStorage
): IDebugSecure {

    override fun isUseDefaultAccount(): Boolean {
        return secure.useTestAccount
    }

    override fun setUseDefaultAccount(value: Boolean) {
        secure.useTestAccount = value
    }
}