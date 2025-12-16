package com.jiy.convention.util

import org.gradle.api.artifacts.VersionCatalog

private fun VersionCatalog.versionOf(name: String): String = findVersion(name).get().displayName
internal val VersionCatalog.minSdk: Int get() = versionOf("minSdk").toInt()
internal val VersionCatalog.compileSdk: Int get() = versionOf("compileSdk").toInt()
internal val VersionCatalog.targetSdk: Int get() = versionOf("targetSdk").toInt()
internal val VersionCatalog.versionCode: Int get() = versionOf("versionCode").toInt()
internal val VersionCatalog.versionName: String get() = versionOf("versionName")