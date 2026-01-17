package com.dd2d.core.stateful

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

fun <T> Flow<Stateful<T>>.onState(
  onLoading: suspend () -> Unit,
  onError: suspend (Throwable) -> Unit,
  onSuccess: suspend (T) -> Unit,
): Flow<Stateful<T>> {
  return onEach { state ->
    when(state) {
      is Stateful.Loading -> onLoading()
      is Stateful.Error -> onError(state.exception)
      is Stateful.Success -> onSuccess(state.data)
    }
  }.catchError()
}

fun <T> Flow<Stateful<T>>.onLoadingState(
  onLoading: suspend () -> Unit,
): Flow<Stateful<T>> {
  return onEach { state ->
    if(state is Stateful.Loading) {
      onLoading()
    }
  }.catchError()
}

fun <T> Flow<Stateful<T>>.onErrorState(
  onError: suspend (Throwable) -> Unit,
): Flow<Stateful<T>> {
  return onEach { state ->
    if(state is Stateful.Error) {
      onError(state.exception)
    }
  }.catchError()
}

fun <T> Flow<Stateful<T>>.onSuccessState(
  onSuccess: suspend (T) -> Unit,
): Flow<Stateful<T>> {
  return onEach { state ->
    if(state is Stateful.Success) {
      onSuccess(state.data)
    }
  }.catchError()
}

fun <T> Flow<Stateful<T>>.onCompleteState(
  onComplete: suspend () -> Unit,
): Flow<Stateful<T>> {
  return onEach { state ->
    if(state is Stateful.Error || state is Stateful.Success) {
      onComplete()
    }
  }.catchError()
}

