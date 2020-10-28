package com.darkos.kts.signin

import com.darkos.kts.signin.model.dto.LoginDTO
import com.darkos.kts.signin.model.dto.TokenDTO
import com.darkos.mvu.login.LoginRemote

interface LoginByEmailRemote: LoginRemote<LoginDTO, TokenDTO>