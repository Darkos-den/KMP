package com.darkos.config

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

    fun enableCompose(useDefaultDependency: Boolean = false) {
        project.plugins.findPlugin(AndroidConfigPlugin::class.java)?.enableCompose(project)

        if (useDefaultDependency) {
            project.plugins.findPlugin(AndroidConfigPlugin::class.java)
                ?.applyComposeDependency(project)
        }
    }

    private fun onModuleTypeChanged(value: ModuleType) {
        project.plugins.findPlugin(AndroidConfigPlugin::class.java)?.let {
            when (value) {
                ModuleType.APPLICATION -> it.setupAsApplication(project)
                ModuleType.LIBRARY -> it.setupAsLibrary(project)
            }
        }
    }
}