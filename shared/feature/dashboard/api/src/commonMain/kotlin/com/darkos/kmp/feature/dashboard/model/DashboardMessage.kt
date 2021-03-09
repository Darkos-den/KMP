package com.darkos.kmp.feature.dashboard.model

import com.darkos.mvu.model.Message

sealed class DashboardMessage : Message() {
    object AddClick : DashboardMessage()
}