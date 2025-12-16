plugins {
  id("module.application")
}

android {
  namespace = "com.dd2d.now_in_my_android"

  buildTypes {
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