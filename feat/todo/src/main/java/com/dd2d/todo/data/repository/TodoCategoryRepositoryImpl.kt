package com.dd2d.todo.data.repository

import com.dd2d.todo.data._source.local.room.dao.CategoryDao
import com.dd2d.todo.data._source.local.room.entity.CategoryEntity
import com.dd2d.todo.domain.model.TodoCategory
import com.dd2d.todo.domain.model.TodoCategoryCreateData
import com.dd2d.todo.domain.model.TodoCategoryUpdateData
import com.dd2d.todo.domain.repository.TodoCategoryRepository
import javax.inject.Inject
import kotlin.uuid.Uuid

/**
 * [TodoCategoryRepository]의 Room 기반 구현체입니다.
 */
class TodoCategoryRepositoryImpl @Inject constructor(
  private val categoryDao: CategoryDao
) : TodoCategoryRepository {

  override suspend fun getCategories(): Result<List<TodoCategory>> = runCatching {
    categoryDao.getCategories().map { it.toDomain() }
  }

  override suspend fun getCategory(id: Uuid): Result<TodoCategory> = runCatching {
    categoryDao.getCategory(id)?.toDomain()
      ?: throw NoSuchElementException("Category with id $id not found")
  }

  override suspend fun createCategory(data: TodoCategoryCreateData): Result<Unit> = runCatching {
    categoryDao.insertCategory(
      CategoryEntity(
        id = Uuid.random(),
        name = data.name,
        colorHex = data.colorHex
      )
    )
  }

  override suspend fun updateCategory(id: Uuid, data: TodoCategoryUpdateData): Result<Unit> = runCatching {
    val existing = categoryDao.getCategory(id)
      ?: throw NoSuchElementException("Category with id $id not found")
    categoryDao.updateCategory(
      existing.copy(
        name = data.name ?: existing.name,
        colorHex = data.colorHex ?: existing.colorHex
      )
    )
  }

  override suspend fun deleteCategory(id: Uuid): Result<Unit> = runCatching {
    val existing = categoryDao.getCategory(id)
      ?: throw NoSuchElementException("Category with id $id not found")
    categoryDao.deleteCategory(existing)
  }

  private fun CategoryEntity.toDomain() = TodoCategory(
    id = id,
    name = name,
    colorHex = colorHex
  )
}
