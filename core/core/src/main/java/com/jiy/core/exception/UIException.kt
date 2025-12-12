package com.jiy.core.exception

/**
 * 유저에게 노출할 예외.
 *
 * 이 예외는 유저에게 알리고 싶은 예외가 있는 경우 사용될 수 있으며, 항상 노출됨을 인지하고 있어야 합니다.
 *
 * @property mainMessage 예외의 메인 메시지
 * @property subMessage 예외의 서브 메시지
 * @property code 예외를 구별하기 위한 코드
 * @param cause 이 예외를 유발한 원인 예외
 */
open class UIException(
  val mainMessage: String,
  val subMessage: String? = null,
  val code: String? = null,
  cause: Throwable? = null,
): Throwable(mainMessage, cause)