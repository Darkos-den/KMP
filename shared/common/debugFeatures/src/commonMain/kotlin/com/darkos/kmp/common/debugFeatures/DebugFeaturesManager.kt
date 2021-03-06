package com.darkos.kmp.common.debugFeatures

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DebugFeaturesManager(
    private val secure: IDebugSecure
) {

    //region logScreenChanges

    private val _logScreenChanges = MutableStateFlow(false)

    var logScreenChanges = _logScreenChanges.asStateFlow()

    fun setLogScreenChanges(value: Boolean){
        _logScreenChanges.value = value
    }

    //endregion

    //region logScreenChanges

    private val _useTestAccount = MutableStateFlow(false)

    var useTestAccount = _useTestAccount.asStateFlow()

    fun setUseTestAccount(value: Boolean){
        _useTestAccount.value = value
        secure.setUseDefaultAccount(value)
    }

    init {
        _useTestAccount.value = secure.isUseDefaultAccount()
    }

    //endregion



//    var selectedEnvironment//todo

    var testAuthData: AuthData? = null

    class AuthData(
        val email: String,
        val password: String
    )
}