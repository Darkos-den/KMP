package com.darkos.kmp.feature.drawer

import com.darkos.kmp.common.errorHandler.ErrorMessage
import com.darkos.kmp.common.errorHandler.processErrorMessage
import com.darkos.kmp.common.mvu.restoreStateAndEffect
import com.darkos.kmp.feature.drawer.api.IDrawerReducer
import com.darkos.kmp.feature.drawer.model.DrawerDestination
import com.darkos.kmp.feature.drawer.model.DrawerEffect
import com.darkos.kmp.feature.drawer.model.DrawerMessage
import com.darkos.kmp.feature.drawer.model.DrawerState
import com.darkos.mvu.common.none
import com.darkos.mvu.model.*

class DrawerReducer : IDrawerReducer {

    override fun update(state: DrawerState, message: Message): StateCmdData<DrawerState> {
        return when (message) {
            is ComponentInitialized -> state.none()
            is RestoreState<*> -> state.none()
            is ErrorMessage -> processErrorMessage(message) {
                state
            }
            is DrawerMessage.LogoutClicked -> state andEffect DrawerEffect.Logout
            is DrawerMessage.DashboardClicked -> {
                if(state.currentDestination == DrawerDestination.Dashboard){
                    return state.none()
                }
                state andEffect DrawerEffect.OpenDashboard
            }
            is DrawerMessage.CategoriesClicked -> {
                if(state.currentDestination == DrawerDestination.Categories){
                    return state.none()
                }
                state andEffect DrawerEffect.OpenCategories
            }
            is DrawerMessage.SearchClicked -> {
                if(state.currentDestination == DrawerDestination.Search){
                    return state.none()
                }
                state andEffect DrawerEffect.OpenSearch
            }
            is DrawerMessage.ProfileClicked -> {
                if(state.currentDestination == DrawerDestination.Profile){
                    return state.none()
                }
                state andEffect DrawerEffect.OpenProfile
            }
            is DrawerMessage.ContactClicked -> {
                if(state.currentDestination == DrawerDestination.Contact){
                    return state.none()
                }
                state andEffect DrawerEffect.OpenContacts
            }
            is Idle -> state.none()
            else -> throw UnsupportedOperationException(message.toString())
        }
    }
}