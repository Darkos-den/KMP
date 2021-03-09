package com.darkos.kmp.feature.dashboard.model

import com.darkos.mvu.model.Effect

sealed class DashboardEffect : Effect() {
    object AddItem : DashboardEffect()
}