package com.company.projectName.login.model

import com.darkos.mvu.models.MVUState

abstract class LoginState : MVUState(){
    abstract val progress: Boolean
    
    abstract fun copyWithProgress(newProgress: Boolean): LoginState
}

fun <T: LoginState>T.copyProgress(newProgress: Boolean) = this.copyWithProgress(newProgress) as T