package com.darkos.kmp.common.validator

object Password : Validator {
    override fun validate(input: String): Boolean {
        return input.length > 4
    }
}