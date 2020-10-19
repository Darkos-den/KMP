package com.company.projectName.signin

import com.company.projectName.signin.model.dto.LoginDTO
import com.company.projectName.signin.model.dto.TokenDTO
import com.darkos.mvu.login.LoginRemote

interface LoginByEmailRemote: LoginRemote<LoginDTO, TokenDTO>