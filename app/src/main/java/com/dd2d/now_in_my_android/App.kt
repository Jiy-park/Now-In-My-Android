package com.dd2d.now_in_my_android

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import coil3.request.crossfade
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application(), SingletonImageLoader.Factory {
  override fun newImageLoader(context: PlatformContext): ImageLoader {
    val context = this@App
    return ImageLoader.Builder(context)
      .crossfade(200)
      .memoryCache {
        MemoryCache.Builder()
          .maxSizePercent(context, 0.25)
          .build()
      }
      .diskCache {
        DiskCache.Builder()
          .directory(cacheDir.resolve("image_cache"))
          .maxSizePercent(0.02)
          .build()
      }
      .build()
  }
}