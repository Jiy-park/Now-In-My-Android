package com.dd2d.todo.domain.model

/**
 * Todo 항목의 우선순위를 나타내는 열거형 클래스입니다.
 */
enum class TodoPriority {
  /** 낮음 */
  LOW,

  /** 보통 */
  MEDIUM,

  /** 높음 */
  HIGH,

  /** 매우 높음 */
  HIGHEST,
}
