package com.joy.toy.data

import com.joy.toy.data.toy_core.ToyRepositorySample
import com.joy.toy.domain.toy_core.ToyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ToyDataModule {
  companion object {
    @Provides
    @Singleton
    fun provideToyRepository(): ToyRepository {
      return ToyRepositorySample
    }
  }
}