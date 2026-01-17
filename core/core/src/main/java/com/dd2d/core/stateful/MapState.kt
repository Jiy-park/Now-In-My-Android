package com.dd2d.core.stateful

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <T, R> Flow<Stateful<T>>.mapSuccessState(block: suspend (T) -> R): Flow<Stateful<R>> {
  return map { state ->
    when(state) {
      is Stateful.Loading -> Stateful.Loading
      is Stateful.Error -> Stateful.Error(state.exception)
      is Stateful.Success -> Stateful.Success(block(state.data))
    }
  }.catchError()
}

fun <T> Flow<Stateful<T>>.mapErrorState(block: suspend (Throwable) -> T): Flow<Stateful<T>> {
  return map { state ->
    when(state) {
      is Stateful.Loading -> Stateful.Loading
      is Stateful.Error -> Stateful.Success(block(state.exception))
      is Stateful.Success -> Stateful.Success(state.data)
    }
  }.catchError()
}