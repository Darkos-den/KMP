package com.darkos.projectName.signin

import com.darkos.projectName.signin.model.dto.LoginDTO
import com.darkos.projectName.signin.model.dto.TokenDTO
import com.darkos.mvu.login.LoginRemote

interface LoginByEmailRemote: LoginRemote<LoginDTO, TokenDTO>