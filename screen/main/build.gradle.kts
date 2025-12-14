import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)

  alias(libs.plugins.ksp)
  alias(libs.plugins.hilt.android)
  alias(libs.plugins.kotlinx.serialization)
}

android {
  namespace = "com.jiy.screen.main"
  compileSdk {
    version = release(36)
  }

  defaultConfig {
    minSdk = 26

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
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

  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.bundles.compose)
  testImplementation(libs.bundles.test)
  debugImplementation(libs.bundles.compose.debug)
  androidTestImplementation(libs.bundles.compose.test)

  implementation(libs.hilt.android)
  implementation(libs.hilt.navigation.compose)
  ksp(libs.hilt.android.compiler)

  implementation(libs.coil)
  implementation(libs.coil.network.okhttp)

  implementation(libs.shimmer)

  implementation(libs.kotlinx.serialization)

  implementation(project(":core:core"))
  implementation(project(":feat:user"))
}