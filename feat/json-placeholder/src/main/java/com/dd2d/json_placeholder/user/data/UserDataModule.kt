package com.dd2d.json_placeholder.user.data

import com.dd2d.json_placeholder.user.data._source.remote.UserApi
import com.dd2d.json_placeholder.user.domain.UserRepository
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
internal abstract class UserDataModule {
  companion object {
    @Provides
    @Singleton
    fun provideUserApi(@Named("Default") retrofit: Retrofit): UserApi {
      return retrofit.create(UserApi::class.java)
    }
  }

  @Binds
  @Singleton
  abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
}