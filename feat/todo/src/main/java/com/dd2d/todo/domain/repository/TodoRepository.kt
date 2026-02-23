package com.dd2d.todo.domain.repository

import com.dd2d.todo.domain.model.Todo
import com.dd2d.todo.domain.model.TodoCreateData
import com.dd2d.todo.domain.model.TodoPriority
import com.dd2d.todo.domain.model.TodoUpdateData
import kotlin.uuid.Uuid

/**
 * Todo 항목과 하위 Todo 항목의 데이터에 접근하고 관리하는 레포지토리 인터페이스입니다.
 */
interface TodoRepository {
  /**
   * 필터 조건에 맞는 최상위(Root) Todo 목록을 조회합니다.
   *
   * @param categoryId 특정 카테고리로 필터링할 경우 해당 ID
   * @param priority 특정 우선순위로 필터링할 경우 해당 값
   * @return 필터링된 최상위 Todo 목록
   */
  suspend fun getTodos(
    categoryId: Uuid? = null,
    priority: TodoPriority? = null
  ): List<Todo>

  /**
   * 특정 ID를 가진 Todo의 상세 정보를 조회합니다.
   *
   * @param id Todo 식별자
   * @return 해당 Todo 정보
   */
  suspend fun getTodo(id: Uuid): Todo

  /**
   * 새로운 Todo(또는 하위 Todo)를 생성합니다.
   * [TodoCreateData.parentId]가 존재하면 해당 부모의 하위 Todo로 생성됩니다.
   *
   * @param data 생성할 Todo 데이터
   */
  suspend fun createTodo(data: TodoCreateData)

  /**
   * 기존 Todo 항목의 정보를 수정합니다.
   *
   * @param id 수정할 Todo 식별자
   * @param data 수정할 Todo 데이터
   */
  suspend fun updateTodo(id: Uuid, data: TodoUpdateData)

  /**
   * 특정 Todo 항목을 삭제합니다.
   * 부모 Todo가 삭제될 경우 하위 Todo들에 대한 처리 정책은 구현에 따릅니다.
   *
   * @param id 삭제할 Todo 식별자
   */
  suspend fun deleteTodo(id: Uuid)

  /**
   * 특정 Todo의 모든 하위 Todo 목록을 조회합니다.
   *
   * @param todoId 부모 Todo의 식별자
   * @return 하위 Todo 목록
   */
  suspend fun getSubTodos(todoId: Uuid): List<Todo>
}
