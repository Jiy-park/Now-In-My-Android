package com.jiy.convention.plugin

import com.jiy.convention.base.BaseAndroidLibraryModulePlugin
import com.jiy.convention.util.implementation
import org.gradle.kotlin.dsl.project

internal class AndroidLibraryModulePlugin: BaseAndroidLibraryModulePlugin(
  pluginScope = {},
  libraryExtensionScope = {},
  dependenciesScope = {
    implementation(project(":core:core"))
  }
)