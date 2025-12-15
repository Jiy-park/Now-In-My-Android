package com.jiy.career.data

import com.jiy.career.domain.CareerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class CareerDataModule {
  companion object {
    @Provides
    @Singleton
    fun provideCareerRepository(): CareerRepository = CareerRepositorySample
  }
}