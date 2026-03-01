package com.dd2d.todo.domain.model

/**
 * 기존 카테고리 정보를 수정하기 위한 데이터 클래스입니다.
 * 모든 필드는 선택 사항이며, null이 아닌 값만 수정됩니다.
 *
 * @property name 변경할 카테고리 이름
 * @property colorHex 변경할 카테고리 상징 색상 (Hex 코드)
 */
data class TodoCategoryUpdateData(
  val name: String? = null,
  val colorHex: String? = null,
)
