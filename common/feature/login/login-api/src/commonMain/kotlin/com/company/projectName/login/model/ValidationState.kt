package com.company.projectName.login.model

import com.darkos.mvu.models.MVUState
import com.darkos.mvu.validation.model.Field

data class ValidationState(
    val fields: List<Field>
) : MVUState()