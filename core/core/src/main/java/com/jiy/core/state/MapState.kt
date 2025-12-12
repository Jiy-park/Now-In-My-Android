package com.jiy.core.state

import com.jiy.core.exception.UIException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * [Stateful.Success]의 데이터 [T]를 [R]로 변환합니다.
 *
 * [block]에서 변환 과정에서 예외가 발생하면 [catchError]를 통해 [Stateful.Error] 상태로 전환됩니다.
 *
 * @param T 변환 전 데이터
 * @param R 변환 후 데이터
 */
fun <T, R> Flow<Stateful<T>>.mapSuccess(block: suspend (T) -> R): Flow<Stateful<R>> {
  return this
    .map { state ->
      when(state) {
        is Stateful.Loading -> Stateful.Loading
        is Stateful.Error -> Stateful.Error(state.exception)
        is Stateful.Success -> Stateful.Success(block(state.data))
      }
    }
    .catchError(code = "CC-MS101")
}

/**
 * [Stateful.Error]의 예외를 [T]로 변환합니다.
 *
 * [block]에서 변환 과정에서 예외가 발생하면 [catchError]를 통해 [Stateful.Error] 상태로 전환됩니다.
 *
 * @param T 데이터
 */
fun <T> Flow<Stateful<T>>.mapError(block: suspend (exception: UIException) -> T): Flow<Stateful<T>> {
  return this
    .map { state ->
      when(state) {
        is Stateful.Loading -> Stateful.Loading
        is Stateful.Error -> Stateful.Success(block(state.exception))
        is Stateful.Success -> Stateful.Success(state.data)
      }
    }
    .catchError(code = "CC-MS201")
}