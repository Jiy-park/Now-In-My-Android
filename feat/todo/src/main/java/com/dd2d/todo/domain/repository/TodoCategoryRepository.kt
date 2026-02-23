package com.dd2d.todo.domain.repository

import com.dd2d.todo.domain.model.TodoCategory
import com.dd2d.todo.domain.model.TodoCategoryCreateData
import com.dd2d.todo.domain.model.TodoCategoryUpdateData
import kotlin.uuid.Uuid

/**
 * 카테고리 데이터에 접근하고 관리하는 레포지토리 인터페이스입니다.
 */
interface TodoCategoryRepository {
  /**
   * 모든 카테고리 목록을 조회합니다.
   *
   * @return 카테고리 목록
   */
  suspend fun getCategories(): List<TodoCategory>

  /**
   * 특정 ID의 카테고리 정보를 조회합니다.
   *
   * @param id 카테고리 식별자
   * @return 카테고리 정보
   */
  suspend fun getCategory(id: Uuid): TodoCategory

  /**
   * 새로운 카테고리를 생성합니다.
   *
   * @param data 생성할 카테고리 데이터
   */
  suspend fun createCategory(data: TodoCategoryCreateData)

  /**
   * 기존 카테고리 정보를 수정합니다.
   *
   * @param id 수정할 카테고리 식별자
   * @param data 수정할 카테고리 데이터
   */
  suspend fun updateCategory(id: Uuid, data: TodoCategoryUpdateData)

  /**
   * 특정 카테고리를 삭제합니다.
   *
   * @param id 삭제할 카테고리 식별자
   */
  suspend fun deleteCategory(id: Uuid)
}
