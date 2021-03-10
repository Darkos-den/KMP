package com.darkos.kmp.feature.item.categories.di

import com.darkos.kmp.feature.item.categories.CategoriesComponent
import com.darkos.kmp.feature.item.categories.CategoriesEffectHandler
import com.darkos.kmp.feature.item.categories.CategoriesReducer
import com.darkos.kmp.feature.item.categories.api.ICategoriesComponent
import com.darkos.kmp.feature.item.categories.api.ICategoriesEffectHandler
import com.darkos.kmp.feature.item.categories.api.ICategoriesReducer
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

class CategoriesDI {

    val module = DI.Module(TAG) {
        bind<ICategoriesReducer>() with provider {
            CategoriesReducer(
                drawerReducer = instance()
            )
        }
        bind<ICategoriesEffectHandler>() with provider {
            CategoriesEffectHandler(
                drawerEffectHandler = instance(),
                navigation = instance()
            )
        }
        bind<ICategoriesComponent>() with provider {
            CategoriesComponent(
                effectHandler = instance(),
                reducer = instance()
            )
        }
    }

    companion object {
        private const val TAG = "Categories"
    }
}