plugins {
  id("module.screen")
}

android {
  namespace = "com.jiy.screen.main"
}

dependencies {
  implementation(libs.coil)

  implementation(project(":feat:career"))
  implementation(project(":feat:user"))
}