package com.jiy.convention.plugin

import com.jiy.convention.base.BaseAndroidLibraryModulePlugin
import com.jiy.convention.base.implementationCompose
import com.jiy.convention.util.implementation
import com.jiy.convention.util.invoke
import com.jiy.convention.util.ksp
import org.gradle.kotlin.dsl.project

internal class ScreenModulePlugin: BaseAndroidLibraryModulePlugin(
  pluginScope = {
    apply("org.jetbrains.kotlin.plugin.compose")

    apply("com.google.devtools.ksp")
    apply("com.google.dagger.hilt.android")
    apply("org.jetbrains.kotlin.plugin.serialization")
  },
  libraryExtensionScope = {
    buildFeatures {
      compose = true
    }
  },
  dependenciesScope = { libs ->
    // 컴포즈 기본 디펜던시
    implementationCompose(libs)

    // kotlinx-serialization
    implementation(libs("kotlinx.serialization"))

    // hilt
    implementation(libs("hilt.android"))
    implementation(libs("hilt.navigation.compose"))
    ksp(libs("hilt.android.compiler"))

    // navigation
    implementation(libs("compose.navigation"))

    implementation(project(":core:core"))
    implementation(project(":core:ui"))
  },
)