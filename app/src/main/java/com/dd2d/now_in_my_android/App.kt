package com.dd2d.now_in_my_android

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import coil3.request.crossfade
import com.google.android.libraries.places.api.Places
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application(), SingletonImageLoader.Factory {
  override fun newImageLoader(context: PlatformContext): ImageLoader {
    return ImageLoader.Builder(context)
      .crossfade(100)
      .memoryCache {
        MemoryCache.Builder()
          .maxSizePercent(context, 0.15)
          .strongReferencesEnabled(true)
          .weakReferencesEnabled(true)
          .build()
      }
      .diskCache {
        DiskCache.Builder()
          .directory(context.cacheDir.resolve("image_cache"))
          .maxSizeBytes(100 * 1024 * 1024)
          .build()
      }
      .build()
  }

  override fun onCreate() {
    super.onCreate()

    initGoogleMapPlaces()
  }

  private fun initGoogleMapPlaces() {
    Places.initializeWithNewPlacesApiEnabled(this, BuildConfig.MAPS_API_KEY)
  }
}