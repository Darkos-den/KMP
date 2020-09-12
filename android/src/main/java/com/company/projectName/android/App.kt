package com.company.projectName.android

import android.app.Application
import android.util.Log
import com.company.projectName.android.di.AppModule
import com.github.aakira.napier.DebugAntilog
import com.github.aakira.napier.Napier
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.generic.instance

class App : Application(), KodeinAware {

    init {
        Napier.base(DebugAntilog())
    }

    override val kodein = Kodein.lazy {
        import(AppModule.get(this@App))
    }

    override val kodeinTrigger = KodeinTrigger()

    override fun onCreate() {
        super.onCreate()

        kodeinTrigger.trigger()
    }
}