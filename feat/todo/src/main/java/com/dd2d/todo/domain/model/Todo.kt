package com.dd2d.todo.domain.model

import java.time.ZonedDateTime
import kotlin.uuid.Uuid

/**
 * 개별 Todo 항목의 정보를 담는 데이터 클래스입니다.
 *
 * @property id Todo 항목의 고유 식별자
 * @property category 소속 카테고리 ([TodoCategory])
 * @property priority 우선순위 ([TodoPriority])
 * @property title 제목
 * @property content 세부 내용 (선택 사항)
 * @property deadline 마감 기한 (선택 사항)
 * @property createdAt 생성 일시
 * @property updatedAt 최근 수정 일시
 * @property completedAt 완료 일시 (null이면 미완료)
 * @property subTodos 하위 Todo 목록
 */
data class Todo(
  val id: Uuid,
  val category: TodoCategory,
  val priority: TodoPriority,
  val title: String,
  val content: String?,
  val deadline: ZonedDateTime?,
  val createdAt: ZonedDateTime,
  val updatedAt: ZonedDateTime,
  val completedAt: ZonedDateTime?,
  val subTodos: List<Todo>
) {
  /** 완료 여부 */
  val isComplete: Boolean get() = completedAt != null
}