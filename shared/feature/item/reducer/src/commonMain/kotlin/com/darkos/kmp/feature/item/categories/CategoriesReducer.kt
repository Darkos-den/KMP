package com.darkos.kmp.feature.item.categories

import com.darkos.kmp.feature.item.categories.api.ICategoriesReducer
import com.darkos.kmp.feature.item.categories.model.CategoriesEffect
import com.darkos.kmp.feature.item.categories.model.CategoriesMessage
import com.darkos.kmp.feature.item.categories.model.CategoriesState
import com.darkos.kmp.feature.drawer.api.IDrawerReducer
import com.darkos.kmp.feature.drawer.model.DrawerDestination
import com.darkos.kmp.feature.drawer.model.DrawerMessage
import com.darkos.kmp.feature.drawer.model.DrawerState
import com.darkos.mvu.common.none
import com.darkos.mvu.model.*

class CategoriesReducer(
    private val drawerReducer: IDrawerReducer
) : ICategoriesReducer {

    override fun update(state: CategoriesState, message: Message): StateCmdData<CategoriesState> {
        return when (message) {
            is DrawerMessage -> drawerReducer.update(
                state = DrawerState(DrawerDestination.Categories),
                message = message
            ) replaceState state
            is ComponentInitialized -> state.none()
            is Idle -> state.none()
            is RestoreState<*> -> state.none()
            else -> throw UnsupportedOperationException(message.toString())
        }
    }
}