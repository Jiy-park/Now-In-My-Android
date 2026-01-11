package com.dd2d.json_placeholder.album.data

import com.dd2d.json_placeholder.album.data._source.remote.AlbumApi
import com.dd2d.json_placeholder.album.domain.AlbumRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AlbumDataModule {
  companion object {
    @Provides
    @Singleton
    fun provideAlbumApi(@Named("Default") retrofit: Retrofit): AlbumApi {
      return retrofit.create(AlbumApi::class.java)
    }
  }

  @Binds
  @Singleton
  abstract fun bindAlbumRepository(impl: AlbumRepositoryImpl): AlbumRepository
}