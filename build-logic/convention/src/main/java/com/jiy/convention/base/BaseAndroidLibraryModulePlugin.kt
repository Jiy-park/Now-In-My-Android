package com.jiy.convention.base

import com.android.build.gradle.LibraryExtension
import com.jiy.convention.util.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.plugins.PluginManager
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

internal open class BaseAndroidLibraryModulePlugin(
  private val pluginScope: PluginManager.() -> Unit = {},
  private val libraryExtensionScope: LibraryExtension.() -> Unit = {},
  private val dependenciesScope: DependencyHandlerScope.(versionCatalog: VersionCatalog) -> Unit  = {},
): Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("com.android.library")
        apply("org.jetbrains.kotlin.android")
        pluginScope()
      }

      androidBaseConfigure()
      extensions.configure<LibraryExtension> {
        libraryExtensionScope()
      }
      dependencies {
        dependenciesScope(libs)
      }
    }
  }
}