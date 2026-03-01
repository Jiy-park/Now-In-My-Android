package com.dd2d.todo.di

import com.dd2d.todo.data.repository.TodoCategoryRepositoryImpl
import com.dd2d.todo.data.repository.TodoRepositoryImpl
import com.dd2d.todo.domain.repository.TodoCategoryRepository
import com.dd2d.todo.domain.repository.TodoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Todo 기능의 레포지토리 의존성을 연결하는 Hilt 모듈입니다.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class TodoRepositoryModule {

  @Binds
  @Singleton
  abstract fun bindTodoRepository(
    impl: TodoRepositoryImpl
  ): TodoRepository

  @Binds
  @Singleton
  abstract fun bindTodoCategoryRepository(
    impl: TodoCategoryRepositoryImpl
  ): TodoCategoryRepository
}
