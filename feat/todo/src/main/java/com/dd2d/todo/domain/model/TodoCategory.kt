package com.dd2d.todo.domain.model

import kotlin.uuid.Uuid

/**
 * Todo 항목의 카테고리 정보를 담는 데이터 클래스입니다.
 *
 * @property id 카테고리의 고유 식별자
 * @property name 카테고리 이름
 * @property colorHex 카테고리 상징 색상 (Hex 코드, 예: #FF0000)
 */
data class TodoCategory(
  val id: Uuid,
  val name: String,
  val colorHex: String,
)
