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

    fun enableCompose(useDefaultDependency: Boolean = false) {
        plugin?.enableCompose(project)

        if (useDefaultDependency) {
            plugin?.applyComposeDependency(project)
        }
    }

    fun useDefaultDependencies() {
        plugin?.applyDefaultDependencies(project)
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