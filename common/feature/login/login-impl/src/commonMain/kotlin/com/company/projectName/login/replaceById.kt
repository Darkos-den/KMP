package com.company.projectName.login

import com.darkos.mvu.validation.model.Field

fun List<Field>.replaceById(id: Long, newValue: Field): List<Field> {
    return this.map {
        if (it.id == id) {
            newValue
        } else {
            it
        }
    }
}