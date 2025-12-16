plugins {
  id("module.screen")
}

android {
  namespace = "com.jiy.screen.career"
}

dependencies {
  implementation(libs.coil)

  implementation(project(":feat:career"))
  implementation(project(":feat:user"))
}