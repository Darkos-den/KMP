package com.darkos.config.multiplatform

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

class MultiplatformConfigPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.extensions.create(
            "configureMultiplatform",
            MultiplatformConfigExtension::class.java,
            target
        )

        target.apply(plugin = "org.jetbrains.kotlin.multiplatform")
        target.apply(plugin = "com.android.library")

        target.extensions.configure<BaseExtension>("android") {
            sourceSets {
                getByName("main").apply {
                    setRoot("src/androidMain")
                }
            }
        }

        target.extensions.configure<KotlinMultiplatformExtension>("kotlin") {
            targets.add(android("android"))
        }
        applyDependencies(
            target = target,
            common = {
                implementation(kotlin("stdlib-common"))
            },
            android = {
                implementation(kotlin("stdlib"))
            }
        )
    }

    internal fun applyDependencies(target: Project, mvuCoreVersion: String) {
        applyDependencies(
            target = target,
            common = {
                implementation("com.darkosinc.mvu:core:$mvuCoreVersion")
            },
            android = {
                implementation("com.darkosinc.mvu:core-jvm:$mvuCoreVersion")
            }
        )
    }

    private fun applyDependencies(
        target: Project,
        common: KotlinDependencyHandler.() -> Unit,
        android: KotlinDependencyHandler.() -> Unit
    ) {
        target.extensions.configure<KotlinMultiplatformExtension>("kotlin") {
            sourceSets.apply {
                getByName("commonMain") {
                    dependencies {
                        apply(common)
                    }
                }
                getByName("androidMain") {
                    dependencies {
                        apply(android)
                    }
                }
            }
        }
    }
}