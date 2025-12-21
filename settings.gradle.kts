pluginManagement {
  repositories {
    google {
      content {
        includeGroupByRegex("com\\.android.*")
        includeGroupByRegex("com\\.google.*")
        includeGroupByRegex("androidx.*")
      }
    }
    mavenCentral()
    gradlePluginPortal()
    includeBuild("build-logic")
  }
}
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
  }
}

rootProject.name = "NowInMyAndroid"
include(":app")
include(":core:core")
include(":core:ui")

include(":feat:user")
include(":feat:career")
include(":feat:toy")

include(":screen:main")
include(":screen:career")
include(":screen:toy")
