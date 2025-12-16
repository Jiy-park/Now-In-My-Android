package com.jiy.convention.plugin

import com.jiy.convention.base.BaseKotlinModulePlugin
import com.jiy.convention.util.implementation
import com.jiy.convention.util.invoke

internal class KotlinModulePlugin: BaseKotlinModulePlugin(
  dependenciesScope = { libs ->
    implementation(libs("kotlinx.coroutines.core"))
  }
)