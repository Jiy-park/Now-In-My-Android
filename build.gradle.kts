import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.kotlin.compose) apply false
  alias(libs.plugins.jetbrains.kotlin.jvm) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.hilt.android) apply false
  alias(libs.plugins.ksp) apply false
  alias(libs.plugins.kotlinx.serialization) apply false
}

allprojects {
  tasks.withType<KotlinCompile> {
    // 모든 코틀린 모듈에 적용
    compilerOptions {
      optIn.addAll(
        "kotlin.time.ExperimentalTime",
        "kotlinx.coroutines.ExperimentalCoroutinesApi",
      )
    }
  }

  plugins.withId("org.jetbrains.kotlin.plugin.compose") {
    // 컴포즈 플러그인 종속이 있을 떄 적용
    tasks.withType<KotlinCompile> {
      compilerOptions {
        optIn.add("androidx.compose.material3.ExperimentalMaterial3Api")
      }
    }
  }
}