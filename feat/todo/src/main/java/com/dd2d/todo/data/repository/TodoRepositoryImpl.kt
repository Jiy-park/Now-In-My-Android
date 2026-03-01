package com.dd2d.todo.data.repository

import com.dd2d.todo.data._source.local.room.dao.TodoDao
import com.dd2d.todo.data._source.local.room.entity.TodoEntity
import com.dd2d.todo.data._source.local.room.relation.SubTodoWithChildren
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

  override suspend fun getTodos(categoryId: Uuid?, priority: TodoPriority?): Result<List<Todo>> = runCatching {
    // 목록 조회 시에는 1단계 자식 목록을 가져오지 않고, 존재 여부 플래그만 가져옵니다.
    todoDao.getRootTodosWithChildrenFlag(categoryId, priority).map { it.toDomain() }
  }

  override suspend fun getTodo(id: Uuid): Result<Todo> = runCatching {
    val target = todoDao.getTodo(id) ?: throw NoSuchElementException("Todo with id $id not found")
    
    // 상세 조회 시에는 바로 아래 자식(1 Depth) 목록을 가져옵니다.
    val subTodos = todoDao.getSubTodosWithChildrenFlag(id).map { it.toDomain() }
    
    target.toDomain(subTodos = subTodos)
  }

  override suspend fun createTodo(data: TodoCreateData): Result<Unit> = runCatching {
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

  override suspend fun updateTodo(id: Uuid, data: TodoUpdateData): Result<Unit> = runCatching {
    val existing = todoDao.getTodo(id)?.todo
      ?: throw NoSuchElementException("Todo with id $id not found")
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

  override suspend fun updateTodos(ids: List<Uuid>, data: TodoUpdateData): Result<Unit> = runCatching {
    val now = ZonedDateTime.now()
    val existingTodos = todoDao.getTodos(ids)
    if (existingTodos.size != ids.size) {
      throw NoSuchElementException("Some todos were not found")
    }
    val updatedTodos = existingTodos.map { it.todo }.map { existing ->
      existing.copy(
        title = data.title ?: existing.title,
        content = data.content ?: existing.content,
        categoryId = data.categoryId ?: existing.categoryId,
        priority = data.priority ?: existing.priority,
        deadline = data.deadline ?: existing.deadline,
        completedAt = data.completedAt ?: existing.completedAt,
        updatedAt = now
      )
    }
    todoDao.updateTodos(updatedTodos)
  }

  override suspend fun deleteTodo(id: Uuid): Result<Unit> = runCatching {
    val existing = todoDao.getTodo(id)?.todo
      ?: throw NoSuchElementException("Todo with id $id not found")
    todoDao.deleteTodo(existing)
  }

  override suspend fun getSubTodos(todoId: Uuid): Result<List<Todo>> = runCatching {
    // 특정 항목의 하위 목록을 요청할 때도 1 Depth와 그 다음 단계 존재 여부만 가져옵니다.
    todoDao.getSubTodosWithChildrenFlag(todoId).map { it.toDomain() }
  }

  override suspend fun getAllSubTodoIds(todoId: Uuid): Result<List<Uuid>> = runCatching {
    val resultIds = mutableListOf<Uuid>()
    val queue = ArrayDeque<Uuid>()

    // BFS(너비 우선 탐색) 반복문으로 모든 하위 ID를 수집합니다. (재귀 없음)
    // 타겟(todoId)의 직계 자식들부터 탐색을 시작합니다.
    val initialSubIds = todoDao.getDirectSubTodoIds(todoId)
    queue.addAll(initialSubIds)

    while (queue.isNotEmpty()) {
      val currentId = queue.removeFirst()
      resultIds.add(currentId)

      // 현재 항목의 자식들을 큐에 추가하여 탐색을 이어갑니다.
      val subIds = todoDao.getDirectSubTodoIds(currentId)
      queue.addAll(subIds)
    }
    resultIds
  }

  private fun SubTodoWithChildren.toDomain(): Todo {
    return todoWithCategory.toDomain(
      hasSubTodos = hasSubTodos
    )
  }

  private fun TodoWithCategory.toDomain(
    subTodos: List<Todo> = emptyList(),
    hasSubTodos: Boolean = false
  ): Todo {
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
      parentId = todo.parentId,
      subTodos = subTodos,
      hasSubTodos = hasSubTodos
    )
  }
}
