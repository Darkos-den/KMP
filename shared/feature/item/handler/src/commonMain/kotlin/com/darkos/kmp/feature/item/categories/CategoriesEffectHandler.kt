package com.darkos.kmp.feature.item.categories

import com.darkos.kmp.common.mvu.navigate
import com.darkos.kmp.feature.item.categories.api.ICategoriesEffectHandler
import com.darkos.kmp.feature.item.categories.api.ICategoriesNavigation
import com.darkos.kmp.feature.item.categories.model.CategoriesEffect
import com.darkos.kmp.feature.drawer.api.IDrawerEffectHandler
import com.darkos.kmp.feature.drawer.model.DrawerEffect
import com.darkos.mvu.model.Effect
import com.darkos.mvu.model.Message

class CategoriesEffectHandler(
    private val drawerEffectHandler: IDrawerEffectHandler,
    private val navigation: ICategoriesNavigation
) : ICategoriesEffectHandler {

    override suspend fun call(effect: Effect): Message {
        return when (effect) {
            is DrawerEffect -> drawerEffectHandler.call(effect)
            else -> throw UnsupportedOperationException(effect.toString())
        }
    }
}