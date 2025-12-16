package com.jiy.convention.plugin

import com.android.build.api.dsl.ApplicationExtension
import com.jiy.convention.base.androidBaseConfigure
import com.jiy.convention.base.implementationCompose
import com.jiy.convention.util.implementation
import com.jiy.convention.util.invoke
import com.jiy.convention.util.ksp
import com.jiy.convention.util.libs
import com.jiy.convention.util.targetSdk
import com.jiy.convention.util.versionCode
import com.jiy.convention.util.versionName
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

internal class ApplicationModulePlugin: Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      androidBaseConfigure()
      extensions.configure<ApplicationExtension> {
        defaultConfig {
          targetSdk = libs.targetSdk
          versionName = libs.versionName
          versionCode = libs.versionCode

          testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        }
        buildFeatures {
          compose = true
        }
      }

      dependencies {
        // compose
        implementationCompose(libs)

        // kotlinx-serialization
        implementation(libs("kotlinx.serialization"))

        // hilt
        implementation(libs("hilt.android"))
        implementation(libs("hilt.navigation.compose"))
        ksp(libs("hilt.android.compiler"))

        // navigation
        implementation(libs("compose.navigation"))
      }
    }
  }
}