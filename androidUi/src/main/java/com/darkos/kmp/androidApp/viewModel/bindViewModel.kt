package com.darkos.kmp.androidApp

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.darkos.kmp.androidApp.common.AndroidRouter
import com.darkos.kmp.androidApp.common.Route
import org.kodein.di.*

inline fun <reified T : ViewModel> DI.Builder.bindViewModel(overrides: Boolean? = null): DI.Builder.TypeBinder<T> {
    return bind<T>(T::class.java.simpleName, overrides)
}

inline fun <reified VM, T> T.viewModel(): Lazy<VM>
        where T : DIAware,
              T : Fragment,
              VM : ViewModel {
    return lazy {
        ViewModelProvider(this, direct.instance())
            .get(VM::class.java).let {
                return@let it
            }
    }
}

inline fun <reified VM, T> T.viewModel(): Lazy<VM>
        where T : DIAware,
              T : Route,
              VM : ViewModel {
    return lazy {
        ViewModelProvider(this, direct.instance())
            .get(VM::class.java).let {
                return@let it
            }
    }
}

inline fun <reified VM, T> T.viewModel(): Lazy<VM>
        where T : DIAware,
              T : AppCompatActivity,
              VM : ViewModel {
    return lazy {
        ViewModelProvider(this as AppCompatActivity, direct.instance())
            .get(VM::class.java).let {
                return@let it
            }
    }
}