package com.darkos.core.navigation

interface Navigator {
    suspend fun navigate(navigation: Navigation): Boolean
}