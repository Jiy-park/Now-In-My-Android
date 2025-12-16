package com.jiy.convention.base

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.LibraryExtension
import com.jiy.convention.util.compileSdk
import com.jiy.convention.util.libs
import com.jiy.convention.util.minSdk
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

internal fun Project.androidBaseConfigure() {
  val androidExt = with(extensions) {
    findByType(ApplicationExtension::class.java)
      ?: findByType(LibraryExtension::class.java)
      ?: error("No Android extension found for module: $name")
  }

  androidExt.apply {
    compileSdk = libs.compileSdk
    defaultConfig {
      minSdk = libs.minSdk
      testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
      sourceCompatibility = JavaVersion.VERSION_11
      targetCompatibility = JavaVersion.VERSION_11
    }
  }
  extensions.configure<KotlinAndroidProjectExtension> {
    compilerOptions {
      jvmTarget.set(JvmTarget.JVM_11)
    }
  }
}