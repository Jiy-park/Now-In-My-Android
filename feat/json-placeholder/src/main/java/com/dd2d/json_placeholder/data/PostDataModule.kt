package com.dd2d.json_placeholder.data

import com.dd2d.json_placeholder.data.post.PostRepositoryImpl
import com.dd2d.json_placeholder.data.post._source.remote.PostApi
import com.dd2d.json_placeholder.domain.post.PostRepository
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
internal abstract class PostDataModule {
  companion object {
    @Provides
    @Singleton
    fun providePostApi(@Named("Default") retrofit: Retrofit): PostApi {
      return retrofit.create(PostApi::class.java)
    }
  }

  @Binds
  @Singleton
  abstract fun bindPostRepository(impl: PostRepositoryImpl): PostRepository
}