package com.dd2d.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object Retrofit {
  @Provides
  @Singleton
  @Named("Default")
  fun provideRetrofit(): Retrofit {
    val client = OkHttpClient.Builder()
      .build()

    return Retrofit.Builder()
      .baseUrl(BuildConfig.BASE_URL)
      .client(client)
      .addConverterFactory(NetworkDefault.json.asConverterFactory("application/json".toMediaType()))
      .build()
  }

  @Provides
  @Singleton
  @Named("GooglePlace")
  fun provideGooglePlaceRetrofit(): Retrofit {
    val client = OkHttpClient.Builder()
      .addInterceptor { chain ->
        val request = chain.request()
        val newRequest = request.newBuilder()
          .header("X-Goog-Api-Key", BuildConfig.GOOGLE_PLACE_API_KEY)
          .build()

        chain.proceed(newRequest)
      }
      .build()

    return Retrofit.Builder()
      .baseUrl(BuildConfig.GOOGLE_PLACE_BASE_URL)
      .client(client)
      .addConverterFactory(NetworkDefault.json.asConverterFactory("application/json".toMediaType()))
      .build()
  }
}