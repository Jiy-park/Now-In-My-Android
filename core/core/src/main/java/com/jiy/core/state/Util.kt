package com.jiy.core.state

import com.jiy.core.exception.UIException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

/** [Stateful] 상태의 [Flow]에서 발생한 오류를 처리합니다.
 *
 * 업스트림 오류를 [UIException]으로 변환하고,
 * 이를 [Stateful.Error] 상태로 내려보냅니다.
 *
 * @param code [UIException]의 코드. 처리할 예외가 [UIException]이면서 [UIException.code] 값이 존재하는 경우 [code]는 무시됩니다.
 * */
fun <T> Flow<Stateful<T>>.catchError(
  code: String = "CC-U101"
): Flow<Stateful<T>> {
  return this.catch { throwable ->
    val exception = (throwable as? UIException)
      ?.let { origin ->
        when(origin.code == null) {
          false -> origin
          true -> {
            UIException(
              mainMessage = origin.mainMessage,
              subMessage = origin.subMessage,
              code = code,
              cause = origin.cause
            )
          }
        }
      }
      ?: UIException(
        mainMessage = "데이터 처리중 오류가 발생했습니다.",
        subMessage = "잠시후 다시 시도해주세요.",
        code = code,
        cause = throwable
      )

    emit(Stateful.Error(exception))
  }
}