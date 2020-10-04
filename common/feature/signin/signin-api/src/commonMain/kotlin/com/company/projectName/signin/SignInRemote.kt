package com.company.projectName.signin

import com.company.projectName.login.LoginRemote
import com.company.projectName.signin.model.dto.LoginDTO
import com.company.projectName.signin.model.dto.TokenDTO

interface SignInRemote: LoginRemote<LoginDTO, TokenDTO>