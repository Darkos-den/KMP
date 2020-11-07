package com.darkos.config.android

import org.gradle.api.Project
import javax.inject.Inject

open class AndroidConfigExtension @Inject constructor(
    private val project: Project
) {
    var moduleType = ModuleType.APPLICATION
        set(value) {
            field = value
            onModuleTypeChanged(value)
        }

    private val plugin: AndroidConfigPlugin?
        get() = project.plugins.findPlugin(AndroidConfigPlugin::class.java)

    fun enableCompose(
        kotlinVersion: String,
        extensionVersion: String,
        useDefaultDependency: Boolean = false,
        version: String = "1.0.0-alpha05"
    ) {
        plugin?.enableCompose(project, kotlinVersion, extensionVersion)

        if (useDefaultDependency) {
            plugin?.applyComposeDependency(project, version)
        }
    }

    fun useDefaultDependencies(
        activityKtx: String,
        appcompat: String,
        coroutines: String,
        kodein: String,
        kotlin: String,
        mvuCore: String,
        mvuProgram: String,
        navigation: String
    ) {
        Versions(
            activityKtx = activityKtx,
            appcompat = appcompat,
            coroutines = coroutines,
            kodein = kodein,
            kotlin = kotlin,
            mvuCore = mvuCore,
            mvuProgram = mvuProgram,
            navigation = navigation
        ).let {
            plugin?.applyDefaultDependencies(project, it)
        }
    }

    fun enableLeakCanary(version: String) {
        plugin?.enableLeakCanary(project, version)
    }

    private fun onModuleTypeChanged(value: ModuleType) {
        plugin?.let {
            when (value) {
                ModuleType.APPLICATION -> it.setupAsApplication(project)
                ModuleType.LIBRARY -> it.setupAsLibrary(project)
            }
        }
    }
}