package com.dd2d.core.stateful

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

fun <T> Flow<Stateful<T>>.catchError(): Flow<Stateful<T>> {
  return catch { exception ->
    emit(Stateful.Error(exception))
  }
}