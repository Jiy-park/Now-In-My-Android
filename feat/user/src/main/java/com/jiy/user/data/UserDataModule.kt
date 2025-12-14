package com.jiy.user.data

import com.jiy.user.domain.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class UserDataModule {
  companion object {
    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository = UserRepositorySample
  }
}