package com.dd2d.todo.domain.model

/**
 * 새로운 카테고리를 생성하기 위한 데이터 클래스입니다.
 *
 * @property name 카테고리 이름
 * @property colorHex 카테고리 상징 색상 (Hex 코드)
 */
data class TodoCategoryCreateData(
  val name: String,
  val colorHex: String,
)
