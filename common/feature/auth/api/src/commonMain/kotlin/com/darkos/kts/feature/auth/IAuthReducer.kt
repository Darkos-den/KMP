package com.darkos.kts.feature.auth

import com.darkos.kts.feature.auth.model.mvu.AuthState
import com.darkos.mvu.Reducer

interface IAuthReducer : Reducer<AuthState>