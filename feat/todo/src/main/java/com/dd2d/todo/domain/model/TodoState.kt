package com.dd2d.todo.domain.model

/**
 * Todo 항목의 현재 진행 상태를 나타내는 열거형 클래스입니다.
 */
enum class TodoState {
  /** 대기 중 */
  PENDING,

  /** 진행 중 */
  IN_PROGRESS,

  /** 실패 */
  FAILURE,

  /** 완료 */
  SUCCESS,
}
