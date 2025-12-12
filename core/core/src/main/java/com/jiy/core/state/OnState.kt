package com.jiy.core.state

import com.jiy.core.exception.UIException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

/**
 * [Flow]의 상태별 이벤트를 등록합니다.
 *
 * 각 이벤트에서 예외가 발생하면 [catchError]를 통해 [Stateful.Error] 상태로 전환됩니다.
 *
 * @param onLoading 로딩 상태일 때의 이벤트
 * @param onError 에러 상태일 때의 이벤트
 * @param onSuccess 성공 상태일 떄의 이벤트
 */
fun <T> Flow<Stateful<T>>.onState(
  onLoading: suspend () -> Unit,
  onError: suspend (exception: UIException) -> Unit,
  onSuccess: suspend (data: T) -> Unit,
): Flow<Stateful<T>> {
  return this
    .onEach { state ->
      when(state) {
        is Stateful.Loading -> onLoading()
        is Stateful.Error -> onError(state.exception)
        is Stateful.Success -> onSuccess(state.data)
      }
    }
    .catchError(code = "CC-OS101")
}

/**
 * [Flow]에 [Stateful.Loading] 상태가 들어올 때의 이벤트를 등록합니다.
 *
 * 이벤트 처리중 예외가 발생하면 [catchError]를 통해 [Stateful.Error] 상태로 전환됩니다.
 *
 * @param onLoading 로딩 상태일 때의 이벤트
 */
fun <T> Flow<Stateful<T>>.onLoading(
  onLoading: suspend () -> Unit,
): Flow<Stateful<T>> {
  return this
    .onEach { state ->
      if(state is Stateful.Loading) {
        onLoading()
      }
    }
    .catchError(code = "CC-OS201")
}

/**
 * [Flow]에 [Stateful.Error] 상태가 들어올 때의 이벤트를 등록합니다.
 *
 * 이벤트 처리중 예외가 발생하면 [catchError]를 통해 [Stateful.Error] 상태로 전환됩니다.
 *
 * @param onError 에러 상태일 때의 이벤트
 */
fun <T> Flow<Stateful<T>>.onError(
  onError: suspend (exception: UIException) -> Unit,
): Flow<Stateful<T>> {
  return this
    .onEach { state ->
      if(state is Stateful.Error) {
        onError(state.exception)
      }
    }
    .catchError(code = "CC-OS301")
}

/**
 * [Flow]에 [Stateful.Success] 상태가 들어올 때의 이벤트를 등록합니다.
 *
 * 이벤트 처리중 예외가 발생하면 [catchError]를 통해 [Stateful.Error] 상태로 전환됩니다.
 *
 * @param onSuccess 성공 상태일 때의 이벤트
 */
fun <T> Flow<Stateful<T>>.onSuccess(
  onSuccess: suspend (data: T) -> Unit,
): Flow<Stateful<T>> {
  return this
    .onEach { state ->
      if(state is Stateful.Success) {
        onSuccess(state.data)
      }
    }
    .catchError(code = "CC-OS401")
}