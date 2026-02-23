package com.dd2d.todo.domain.model

import java.time.ZonedDateTime
import kotlin.uuid.Uuid

/**
 * 새로운 Todo 항목을 생성하기 위한 데이터 클래스입니다.
 *
 * @property title 제목
 * @property content 내용 (선택 사항)
 * @property categoryId 소속 카테고리의 식별자
 * @property priority 우선순위
 * @property deadline 마감 기한 (선택 사항)
 * @property parentId 부모 Todo의 식별자 (하위 Todo 생성 시 사용)
 */
data class TodoCreateData(
  val title: String,
  val content: String? = null,
  val categoryId: Uuid,
  val priority: TodoPriority = TodoPriority.MEDIUM,
  val deadline: ZonedDateTime? = null,
  val parentId: Uuid? = null,
)
