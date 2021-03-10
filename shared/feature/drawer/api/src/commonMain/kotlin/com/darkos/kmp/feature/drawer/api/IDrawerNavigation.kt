package com.darkos.kmp.feature.drawer.api

interface IDrawerNavigation {
    fun fromDrawerToSignIn()
    fun fromDrawerToDashboard()
    fun fromDrawerToCategories()
    fun fromDrawerToSearch()
    fun fromDrawerToProfile()
    fun fromDrawerToContacts()
}