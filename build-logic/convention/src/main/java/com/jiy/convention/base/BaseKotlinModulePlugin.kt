package com.jiy.convention.base

import com.jiy.convention.util.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

internal open class BaseKotlinModulePlugin(
  private val dependenciesScope: DependencyHandlerScope.(versionCatalog: VersionCatalog) -> Unit  = {}
): Plugin<Project>  {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("java-library")
        apply("org.jetbrains.kotlin.jvm")
      }
      extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
      }
      extensions.configure<KotlinJvmProjectExtension> {
        compilerOptions {
          jvmTarget.set(JvmTarget.JVM_11)
        }
      }
      dependencies {
        dependenciesScope(libs)
      }
    }
  }
}