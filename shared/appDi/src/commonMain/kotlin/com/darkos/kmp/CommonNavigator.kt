package com.darkos.kmp

import com.darkos.kmp.feature.dashboard.api.IDashboardNavigation
import com.darkos.kmp.feature.drawer.api.IDrawerNavigation
import com.darkos.kmp.feature.item.categories.api.ICategoriesNavigation
import com.darkos.kmp.feature.signin.api.ISignInNavigation
import com.darkos.kmp.feature.splash.api.ISplashNavigation

class CommonNavigator : ISplashNavigation, ISignInNavigation, IDashboardNavigation,
    IDrawerNavigation, ICategoriesNavigation {
    var mGoToLogin: () -> Unit = {}
    var mGoToDashboard: () -> Unit = {}
    var mGoToAddItem: () -> Unit = {}
    var mGoToCategories: ()->Unit = {}
    var mGoToSearch: ()->Unit = {}
    var mGoToProfile: ()->Unit = {}
    var mGoToContacts: ()->Unit = {}
    var mGoToWorkspace: ()->Unit = {}

    override fun fromSplashToLogin() {
        mGoToLogin()
    }

    override fun fromSplashToHome() {
        mGoToDashboard()
    }

    override fun fromSignInToHome() {
        mGoToDashboard()
    }

    override fun fromDrawerToSignIn() {
        mGoToLogin()
    }

    override fun fromDashboardToAddItem() {
        mGoToAddItem()
    }

    override fun fromDashboardToWorkspace() {
        mGoToWorkspace()
    }

    override fun fromDrawerToDashboard() {
        mGoToDashboard()
    }

    override fun fromDrawerToCategories() {
        mGoToCategories()
    }

    override fun fromDrawerToSearch() {
        mGoToSearch()
    }

    override fun fromDrawerToProfile() {
        mGoToProfile()
    }

    override fun fromDrawerToContacts() {
        mGoToContacts()
    }
}