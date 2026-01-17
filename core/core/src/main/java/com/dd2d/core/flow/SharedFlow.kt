package com.dd2d.core.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn

fun <T> Flow<T>.shareInWhileSubscribed(
  scope: CoroutineScope,
  stopTimeoutMillis: Long = 5000,
  replay: Int = 0
): SharedFlow<T> {
  return shareIn(
    scope = scope,
    started = SharingStarted.WhileSubscribed(stopTimeoutMillis),
    replay = replay
  )
}

fun <T> Flow<T>.shareInEagerly(
  scope: CoroutineScope,
  replay: Int = 0
): SharedFlow<T> {
  return shareIn(
    scope = scope,
    started = SharingStarted.Eagerly,
    replay = replay
  )
}

fun <T> Flow<T>.shareInLazily(
  scope: CoroutineScope,
  replay: Int = 0
): SharedFlow<T> {
  return shareIn(
    scope = scope,
    started = SharingStarted.Lazily,
    replay = replay
  )
}