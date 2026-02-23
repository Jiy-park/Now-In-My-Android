package com.dd2d.todo.data.repository

import com.dd2d.todo.data._source.local.room.dao.TodoDao
import com.dd2d.todo.data._source.local.room.entity.TodoEntity
import com.dd2d.todo.data._source.local.room.relation.TodoWithCategory
import com.dd2d.todo.domain.model.Todo
import com.dd2d.todo.domain.model.TodoCategory
import com.dd2d.todo.domain.model.TodoCreateData
import com.dd2d.todo.domain.model.TodoPriority
import com.dd2d.todo.domain.model.TodoUpdateData
import com.dd2d.todo.domain.repository.TodoRepository
import java.time.ZonedDateTime
import javax.inject.Inject
import kotlin.uuid.Uuid

/**
 * [TodoRepository]의 Room 기반 구현체입니다.
 */
class TodoRepositoryImpl @Inject constructor(
  private val todoDao: TodoDao
) : TodoRepository {

  override suspend fun getTodos(categoryId: Uuid?, priority: TodoPriority?): List<Todo> {
    return todoDao.getRootTodos(categoryId, priority).map { it.toDomain() }
  }

  override suspend fun getTodo(id: Uuid): Todo {
    return todoDao.getTodo(id)?.toDomain() 
      ?: throw NoSuchElementException("Todo with id $id not found")
  }

  override suspend fun createTodo(data: TodoCreateData) {
    val now = ZonedDateTime.now()
    todoDao.insertTodo(
      TodoEntity(
        id = Uuid.random(),
        title = data.title,
        content = data.content,
        priority = data.priority,
        categoryId = data.categoryId,
        deadline = data.deadline,
        createdAt = now,
        updatedAt = now,
        completedAt = null,
        parentId = data.parentId
      )
    )
  }

  override suspend fun updateTodo(id: Uuid, data: TodoUpdateData) {
    val existing = todoDao.getTodo(id)?.todo ?: return
    todoDao.updateTodo(
      existing.copy(
        title = data.title ?: existing.title,
        content = data.content ?: existing.content,
        categoryId = data.categoryId ?: existing.categoryId,
        priority = data.priority ?: existing.priority,
        deadline = data.deadline ?: existing.deadline,
        completedAt = data.completedAt ?: existing.completedAt,
        updatedAt = ZonedDateTime.now()
      )
    )
  }

  override suspend fun deleteTodo(id: Uuid) {
    val existing = todoDao.getTodo(id)?.todo ?: return
    todoDao.deleteTodo(existing)
  }

  override suspend fun getSubTodos(todoId: Uuid): List<Todo> {
    return todoDao.getSubTodos(todoId).map { it.toDomain() }
  }

  private suspend fun TodoWithCategory.toDomain(): Todo {
    return Todo(
      id = todo.id,
      category = TodoCategory(
        id = category.id,
        name = category.name,
        colorHex = category.colorHex
      ),
      priority = todo.priority,
      title = todo.title,
      content = todo.content,
      deadline = todo.deadline,
      createdAt = todo.createdAt,
      updatedAt = todo.updatedAt,
      completedAt = todo.completedAt,
      // 하위 Todo가 있는 경우 재귀적으로 가져옵니다.
      subTodos = todoDao.getSubTodos(todo.id).map { it.toDomain() }
    )
  }
}
