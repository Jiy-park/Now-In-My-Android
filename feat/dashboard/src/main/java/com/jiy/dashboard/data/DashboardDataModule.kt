package com.jiy.dashboard.data

import com.jiy.dashboard.domain.DashboardRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DashboardDataModule {
  @Binds
  @Singleton
  abstract fun bindDashboardRepository(impl: DashboardRepositorySample): DashboardRepository
}