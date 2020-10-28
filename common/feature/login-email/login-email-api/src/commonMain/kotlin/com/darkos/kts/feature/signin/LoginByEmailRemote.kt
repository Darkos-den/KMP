package com.darkos.kts.feature.signin

import com.darkos.kts.feature.signin.model.dto.LoginDTO
import com.darkos.kts.feature.signin.model.dto.TokenDTO
import com.darkos.mvu.login.LoginRemote

interface LoginByEmailRemote: LoginRemote<LoginDTO, TokenDTO>