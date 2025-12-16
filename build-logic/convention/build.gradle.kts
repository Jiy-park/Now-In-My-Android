import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  `kotlin-dsl`
}

group = "com.limefriends.boilerplate.buildlogic"

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
  compilerOptions {
    jvmTarget = JvmTarget.JVM_11
  }
}

tasks {
  validatePlugins {
    enableStricterValidation = true
    failOnWarning = true
  }
}

dependencies{
  compileOnly(libs.android.gradle.plugin)
  compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
  plugins {
    register("kotlin module plugin") {
      description = "kotlin 모듈 플러그인"
      id = "module.kotlin"
      implementationClass = "com.jiy.convention.plugin.KotlinModulePlugin"
    }

    register("android library module plugin") {
      description = "Android library 모듈 플러그인"
      id = "module.android"
      implementationClass = "com.jiy.convention.plugin.AndroidLibraryModulePlugin"
    }

    register("compose library module plugin") {
      description = "compose library 모듈 플러그인"
      id = "module.compose"
      implementationClass = "com.jiy.convention.plugin.ComposeLibraryModulePlugin"
    }

    register("feat module plugin") {
      description = "feat 모듈 플러그인"
      id = "module.feat"
      implementationClass = "com.jiy.convention.plugin.FeatModulePlugin"
    }

    register("screen module plugin") {
      description = "screen 모듈 플러그인"
      id = "module.screen"
      implementationClass = "com.jiy.convention.plugin.ScreenModulePlugin"
    }
    register("application module plugin") {
      description = "application 모듈 플러그인"
      id = "module.application"
      implementationClass = "com.jiy.convention.plugin.ApplicationModulePlugin"
    }
  }
}