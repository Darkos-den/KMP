package com.darkos.kmp.common.debugFeatures

sealed class Environment {
    object Local : Environment()
    object Dev : Environment()
    object Prod : Environment()
}