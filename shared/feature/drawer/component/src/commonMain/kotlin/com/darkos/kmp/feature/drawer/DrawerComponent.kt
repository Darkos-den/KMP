package com.darkos.kmp.feature.drawer

import com.darkos.kmp.feature.drawer.api.IDrawerComponent
import com.darkos.kmp.feature.drawer.api.IDrawerEffectHandler
import com.darkos.kmp.feature.drawer.api.IDrawerReducer
import com.darkos.kmp.feature.drawer.model.DrawerMessage
import com.darkos.kmp.feature.drawer.model.DrawerState
import com.darkos.mvu.component.MVUComponent
import com.darkos.mvu.model.RestoreState
import kotlinx.coroutines.InternalCoroutinesApi

class DrawerComponent(
    reducer: IDrawerReducer,
    effectHandler: IDrawerEffectHandler
) : MVUComponent<DrawerState>(
    reducer = reducer,
    effectHandler = effectHandler
), IDrawerComponent {

    override fun createInitialState(): DrawerState {
        return DrawerState
    }

    override fun restore(state: DrawerState) {//todo: move to core
        accept(RestoreState(state))
    }

    override fun onLogoutClick() {
        accept(DrawerMessage.LogoutClicked)
    }
}