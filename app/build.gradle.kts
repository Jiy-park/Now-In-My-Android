import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)

  alias(libs.plugins.ksp)
  alias(libs.plugins.hilt.android)
  alias(libs.plugins.kotlinx.serialization)
}

android {
  namespace = "com.dd2d.now_in_my_android"
  compileSdk {
    version = release(36)
  }

  defaultConfig {
    applicationId = "com.dd2d.now_in_my_android"
    minSdk = 26
    targetSdk = 36
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlin {
    compilerOptions {
      jvmTarget.set(JvmTarget.JVM_11)
    }
  }
  buildFeatures {
    compose = true
  }
}

dependencies {
  implementation(libs.androidx.activity.compose)

  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.bundles.compose)
  testImplementation(libs.bundles.test)
  debugImplementation(libs.bundles.compose.debug)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.bundles.compose.test)

  implementation(libs.coil)
  implementation(libs.coil.network.okhttp)

  implementation(libs.hilt.android)
  implementation(libs.hilt.navigation.compose)
  ksp(libs.hilt.android.compiler)

  implementation(project(":core:core"))
  implementation(project(":core:ui"))
  implementation(project(":feat:user"))
  implementation(project(":feat:career"))
  implementation(project(":screen:main"))
  implementation(project(":screen:career"))
}