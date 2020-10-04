package com.company.projectName.login.model

import com.darkos.mvu.models.Message

abstract class FieldValueChanged(
    val newValue: String
) : Message()