package com.jiy.convention.plugin

import com.jiy.convention.base.BaseAndroidLibraryModulePlugin
import com.jiy.convention.base.implementationCompose
import com.jiy.convention.util.implementation
import org.gradle.kotlin.dsl.project

internal class ComposeLibraryModulePlugin: BaseAndroidLibraryModulePlugin(
  pluginScope = {
    apply("org.jetbrains.kotlin.plugin.compose")
  },
  libraryExtensionScope = {
    buildFeatures {
      compose = true
    }
  },
  dependenciesScope = { libs ->
    // 컴포즈 기본 디펜던시
    implementationCompose(libs)

    implementation(project(":core:core"))
  },
)