plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.kotlinx.serialization)
  alias(libs.plugins.hilt.android)
  alias(libs.plugins.ksp)
  alias(libs.plugins.google.service)
  id("module.application")
}

android {
  namespace = "com.dd2d.now_in_my_android"

  buildTypes {
    debug {
      applicationIdSuffix = ".debug"
      versionNameSuffix = "-debug"
    }
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
}

dependencies {
  implementation(libs.androidx.activity.compose)

  implementation(libs.coil)
  implementation(libs.coil.network.okhttp)

  rootProject.subprojects
    .filter { project ->
      project.path.startsWith(":feat:")
          || project.path.startsWith(":screen:")
    }
    .forEach { project ->
      implementation(project(project.path))
    }

  implementation(project(":core:core"))
  implementation(project(":core:ui"))
}