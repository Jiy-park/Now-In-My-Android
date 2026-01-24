import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.kotlin.ksp)
  alias(libs.plugins.android.hilt)
}

val properties = gradleLocalProperties(rootDir, providers)

android {
  namespace = "com.dd2d.network"
  compileSdk {
    version = release(36)
  }

  defaultConfig {
    minSdk = 26

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")

    buildConfigField(type = "String", name = "BASE_URL", value = "\"https://jsonplaceholder.typicode.com/\"")

    buildConfigField(type = "String", name = "GOOGLE_PLACE_BASE_URL", value = "\"https://places.googleapis.com/v1/\"")
    buildConfigField(type = "String", name = "GOOGLE_PLACE_API_KEY", value = "\"${properties["GOOGLE_PLACE_API_KEY"]}\"")
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
      jvmTarget = JvmTarget.JVM_11
    }
  }
  buildFeatures {
    buildConfig = true
  }
}

dependencies {
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)

  implementation(libs.retrofit)
  implementation(libs.converter.kotlinx.serialization)
  implementation(libs.kotlinx.serialization.json)

  implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)
}