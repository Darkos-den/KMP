package com.company.projectName.login

import com.darkos.mvu.models.MVUState

fun <State : MVUState, Request : Any> LoginFeature(block: LoginReducer.Builder<State, Request>.() -> Unit) =
    LoginReducer.Builder<State, Request>().apply(block).build()