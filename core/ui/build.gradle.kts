plugins {
  id("module.compose")
}

android {
  namespace = "com.jiy.ui"
}

dependencies {
  implementation(libs.coil)
  implementation(libs.coil.network.okhttp)

  implementation(libs.shimmer)

  implementation(libs.compose.navigation)
}