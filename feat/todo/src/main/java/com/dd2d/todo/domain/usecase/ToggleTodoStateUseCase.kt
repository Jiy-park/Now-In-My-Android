package com.dd2d.todo.domain.usecase

import com.dd2d.todo.domain.model.Todo
import com.dd2d.todo.domain.model.TodoUpdateData
import com.dd2d.todo.domain.repository.TodoRepository
import java.time.ZonedDateTime
import javax.inject.Inject
import kotlin.uuid.Uuid


/**
 * Todo의 상태를 토글하고, 정책에 따라 상/하위 Todo의 상태를 연쇄적으로 업데이트하는 유즈케이스입니다.
 * Notion 스펙 (https://www.notion.so/3163235ed4f880b69d71ee1f24edba22)에 따라 구현되었습니다.
 */
class ToggleTodoStateUseCase @Inject constructor(
  private val todoRepository: TodoRepository
) {
  suspend operator fun invoke(todoId: Uuid): Todo {
    val targetTodo = todoRepository.getTodo(todoId)
    val now = ZonedDateTime.now()
    val isCompleting = !targetTodo.isComplete
    val targetCompletedAt = if (isCompleting) now else null

    // 1. 타겟 Todo 상태 업데이트
    todoRepository.updateTodo(todoId, TodoUpdateData(completedAt = targetCompletedAt))

    // 2. 하위 항목들 일괄 업데이트 (Cascading)
    val subTodoIds = getAllSubTodoIds(targetTodo.subTodos)
    if (subTodoIds.isNotEmpty()) {
      todoRepository.updateTodos(subTodoIds, TodoUpdateData(completedAt = targetCompletedAt))
    }

    // 3. 부모 항목 상태 업데이트 (Auto-complete/uncomplete check)
    if (targetTodo.parentId != null) {
      updateParentStates(targetTodo.parentId, now)
    }

    // 4. 업데이트된 최종 결과 반환
    return todoRepository.getTodo(todoId)
  }

  /**
   * 하위의 모든 Todo ID를 반복적으로 수집합니다.
   */
  private fun getAllSubTodoIds(subTodos: List<Todo>): List<Uuid> {
    val ids = mutableListOf<Uuid>()
    val stack = subTodos.toMutableList()

    while (stack.isNotEmpty()) {
      val todo = stack.removeLastOrNull()?: break
      ids.add(todo.id)
      if (todo.subTodos.isNotEmpty()) {
        stack.addAll(todo.subTodos)
      }
    }
    return ids
  }

  /**
   * 형제 항목들의 상태를 확인하며 상위로 올라가며 부모의 상태를 결정합니다.
   * 모든 형제가 완료 상태이면 부모도 완료 처리, 하나라도 미완료면 부모도 미완료 처리합니다.
   * (TC_TODO_TOGGLE_010: 이미 부모가 목표 상태인 경우 업데이트 생략)
   */
  private suspend fun updateParentStates(startParentId: Uuid, now: ZonedDateTime) {
    var currentParentId: Uuid? = startParentId

    while (currentParentId != null) {
      val parent = todoRepository.getTodo(currentParentId)
      val siblings = todoRepository.getSubTodos(currentParentId)

      val allSiblingsComplete = siblings.all { it.isComplete }
      val targetCompletedAt = if (allSiblingsComplete) now else null

      // 부모의 현재 상태가 목표 상태와 동일하면 더 이상 상위로 올라갈 필요가 없음 (최적화)
      val isParentAlreadyInTargetState = (parent.completedAt != null) == allSiblingsComplete
      if (isParentAlreadyInTargetState) {
        break
      }

      todoRepository.updateTodo(currentParentId, TodoUpdateData(completedAt = targetCompletedAt))
      currentParentId = parent.parentId
    }
  }
}
