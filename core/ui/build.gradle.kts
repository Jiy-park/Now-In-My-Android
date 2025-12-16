plugins {
  id("module.compose")
}

android {
  namespace = "com.jiy.ui"

  defaultConfig {
    buildConfigField(type = "String", name = "VERSION_NAME", value = "\"${libs.versions.versionName.get()}\"")
  }

  buildFeatures {
    buildConfig = true
  }
}

dependencies {
  implementation(libs.coil)
  implementation(libs.coil.network.okhttp)

  implementation(libs.shimmer)

  implementation(libs.compose.navigation)
}