package com.dd2d.core.stateful

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

sealed interface Stateful<out T> {
  data object Loading: Stateful<Nothing>
  data class Error(val exception: Throwable): Stateful<Nothing>
  data class Success<T>(val data: T): Stateful<T>
}

fun <T> statefulFlow(block: suspend () -> T): Flow<Stateful<T>> {
  return flow<Stateful<T>> { emit(Stateful.Success(block())) }
    .onStart { emit(Stateful.Loading) }
    .catch { emit(Stateful.Error(it)) }
}