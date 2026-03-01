package com.dd2d.todo.domain.model

import java.time.ZonedDateTime
import kotlin.uuid.Uuid

/**
 * 기존 Todo 항목 정보를 수정하기 위한 데이터 클래스입니다.
 * 모든 필드는 선택 사항이며, null이 아닌 값만 해당 속성이 수정됩니다.
 *
 * @property title 변경할 제목
 * @property content 변경할 내용
 * @property completedAt 변경할 완료 일시 (null이면 미완료 처리)
 * @property categoryId 변경할 카테고리 식별자
 * @property priority 변경할 우선순위 ([TodoPriority])
 * @property deadline 변경할 마감 기한
 */
data class TodoUpdateData(
  val title: String? = null,
  val content: String? = null,
  val completedAt: ZonedDateTime? = null,
  val categoryId: Uuid? = null,
  val priority: TodoPriority? = null,
  val deadline: ZonedDateTime? = null,
)
