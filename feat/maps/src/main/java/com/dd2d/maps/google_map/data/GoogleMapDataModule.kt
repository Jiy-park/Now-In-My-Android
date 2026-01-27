package com.dd2d.maps.google_map.data

import android.content.Context
import com.dd2d.maps.google_map.data._source.local.LocationSource
import com.dd2d.maps.google_map.data._source.remote.GooglePlaceApi
import com.dd2d.maps.google_map.domain.PlaceRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class GoogleMapDataModule {
  companion object {
    @Provides
    @Singleton
    fun provideLocationSource(@ApplicationContext context: Context): LocationSource {
      return LocationSource(context)
    }

    @Provides
    @Singleton
    fun provideGooglePlaceApi(@Named("GooglePlace") retrofit: Retrofit): GooglePlaceApi {
      return retrofit.create(GooglePlaceApi::class.java)
    }
  }

  @Binds
  @Singleton
  abstract fun bindPlaceRepository(impl: PlaceRepositoryImpl): PlaceRepository
}