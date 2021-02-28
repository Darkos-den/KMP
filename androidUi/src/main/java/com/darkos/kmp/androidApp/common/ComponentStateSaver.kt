package com.darkos.kmp.androidApp.common

import androidx.compose.runtime.savedinstancestate.Saver
import androidx.compose.runtime.savedinstancestate.SaverScope
import com.darkos.mvu.component.ProgramComponent
import com.darkos.mvu.model.MVUState

class ComponentStateSaver<Savable : Any, S : MVUState, T : ProgramComponent<S>>(
    private val component: T,
    private val mapTo: (S) -> Savable,
    private val mapFrom: (Savable) -> S
) : Saver<S, Savable> {

    override fun restore(value: Savable): S {
        return mapFrom(value).also {
            component.restore(it)
        }
    }

    override fun SaverScope.save(value: S): Savable {
        return mapTo(value)
    }
}