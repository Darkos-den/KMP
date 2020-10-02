package com.company.projectName.validation

fun ValidationHandler(block: ValidationEffectHandler.Builder.() -> Unit): ValidationEffectHandler =
    ValidationEffectHandler.Builder().apply(block).build()