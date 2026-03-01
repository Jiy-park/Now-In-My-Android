package com.dd2d.todo.di

import android.content.Context
import androidx.room.Room
import com.dd2d.todo.data._source.local.room.TodoDatabase
import com.dd2d.todo.data._source.local.room.dao.CategoryDao
import com.dd2d.todo.data._source.local.room.dao.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Todo 기능의 데이터베이스 및 DAO 관련 의존성을 제공하는 Hilt 모듈입니다.
 */
@Module
@InstallIn(SingletonComponent::class)
object TodoDatabaseModule {

  @Provides
  @Singleton
  fun provideTodoDatabase(
    @ApplicationContext context: Context
  ): TodoDatabase {
    return Room.databaseBuilder(
      context,
      TodoDatabase::class.java,
      TodoDatabase.DATABASE_NAME
    ).build()
  }

  @Provides
  fun provideTodoDao(database: TodoDatabase): TodoDao = database.todoDao()

  @Provides
  fun provideCategoryDao(database: TodoDatabase): CategoryDao = database.categoryDao()
}
