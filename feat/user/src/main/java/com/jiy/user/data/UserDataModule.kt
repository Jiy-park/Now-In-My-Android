package com.jiy.user.data

import com.jiy.user.domain.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserDataModule {
  @Binds
  @Singleton
  abstract fun bindUserRepository(impl: UserRepositorySample): UserRepository
}