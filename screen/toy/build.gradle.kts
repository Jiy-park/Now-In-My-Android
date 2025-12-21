plugins {
  id("module.screen")
}

android {
  namespace = "com.jiy.screen.toy"
}

dependencies {
  implementation(libs.coil)
  implementation("dev.snipme:kodeview:0.9.0")
  implementation(project(":feat:toy"))
}