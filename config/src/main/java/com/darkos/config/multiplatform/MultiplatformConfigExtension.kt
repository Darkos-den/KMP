package com.darkos.config.multiplatform

import org.gradle.api.Project
import javax.inject.Inject

open class MultiplatformConfigExtension @Inject constructor(
    private val project: Project
) {

    private val plugin: MultiplatformConfigPlugin?
        get() = project.plugins.findPlugin(MultiplatformConfigPlugin::class.java)

    var mvuCoreVersion: String = ""
        set(value) {
            applyDependencies(value)
        }

    private fun applyDependencies(mvuCoreVersion: String) {
        if (mvuCoreVersion.isNotEmpty()) {
            plugin?.applyDependencies(project, mvuCoreVersion)
        }
    }
}