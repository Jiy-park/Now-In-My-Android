package com.jiy.convention.util

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.getByType

internal val Project.libs: VersionCatalog
  get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal operator fun VersionCatalog.invoke(alias: String) = findLibrary(alias).get()

internal fun DependencyHandlerScope.implementation(dependencyNotation: Any) = add("implementation", dependencyNotation)
internal fun DependencyHandlerScope.api(dependencyNotation: Any) = add("api", dependencyNotation)
internal fun DependencyHandlerScope.ksp(dependencyNotation: Any) = add("ksp", dependencyNotation)
internal fun DependencyHandlerScope.androidTestImplementation(dependencyNotation: Any) = add("androidTestImplementation", dependencyNotation)
internal fun DependencyHandlerScope.testImplementation(dependencyNotation: Any) = add("testImplementation", dependencyNotation)
internal fun DependencyHandlerScope.debugImplementation(dependencyNotation: Any) = add("debugImplementation", dependencyNotation)