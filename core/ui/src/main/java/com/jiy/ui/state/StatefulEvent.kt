package com.jiy.ui.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.jiy.core.exception.UIException
import com.jiy.core.state.Stateful

/**
 * 상태에 따라 이벤트를 수행합니다.
 *
 * @param state 상태
 * @param onEmpty 상태가 없을 때의 이벤트. [state] 값이 `null`일 때 상태가 없다고 판단합니다.
 * @param onLoading 로딩 상태일 때의 이벤트
 * @param onError 에러 상태일 때의 이벤트 [Stateful.Error.exception] 값이 람다로 주어집니다.
 * @param onSuccess 성공 상태일 때의 이벤트. [T] 값이 람다로 주어집니다.
 * @param onComplete 에러 또는 성공 상태일 때의 이벤트. 이 이벤트는 [onError], [onSuccess] 후에 수행됩니다.
 */
@Composable
fun <T> StatefulEvent(
  state: Stateful<T>?,
  onEmpty: suspend () -> Unit = {},
  onLoading: suspend () -> Unit = {},
  onError: suspend (exception: UIException) -> Unit = {},
  onSuccess: suspend (data: T) -> Unit = {},
  onComplete: suspend () -> Unit = {},
) {
  LaunchedEffect(state) {
    when(state) {
      null -> onEmpty()
      is Stateful.Loading -> onLoading()
      is Stateful.Error -> {
        onError(state.exception)
        onComplete()
      }
      is Stateful.Success -> {
        onSuccess(state.data)
        onComplete()
      }
    }
  }
}