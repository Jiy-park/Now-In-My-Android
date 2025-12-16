package com.jiy.convention.base

import com.jiy.convention.util.androidTestImplementation
import com.jiy.convention.util.debugImplementation
import com.jiy.convention.util.implementation
import com.jiy.convention.util.invoke
import com.jiy.convention.util.testImplementation
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.DependencyHandlerScope

internal fun DependencyHandlerScope.implementationCompose(libs: VersionCatalog) {
  // ui
  implementation(platform(libs("androidx.compose.bom")))
  implementation(libs("androidx.compose.ui"))
  implementation(libs("androidx.compose.ui.graphics"))
  implementation(libs("androidx.compose.material3"))

  // test
  testImplementation(libs("junit"))

  debugImplementation(libs("androidx.compose.ui.tooling"))
  debugImplementation(libs("androidx.compose.ui.tooling.preview"))

  androidTestImplementation(libs("androidx.junit"))
  androidTestImplementation(libs("androidx.espresso.core"))
  androidTestImplementation(libs("androidx.compose.ui.test.manifest"))
  androidTestImplementation(libs("androidx.compose.ui.test.junit4"))
}