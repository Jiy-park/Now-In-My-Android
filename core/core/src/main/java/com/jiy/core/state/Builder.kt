package com.jiy.core.state

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

/** [block]을 통해 반환된 데이터 [T]에 상태를 부여한 [Flow]를 반환합니다.
 *
 * @see Stateful
 * @see statefulFlowWith
 * */
fun <T> statefulFlow(block: suspend () -> T): Flow<Stateful<T>> {
  return flow<Stateful<T>> { emit(Stateful.Success(block())) }
    .onStart { emit(Stateful.Loading) }
    .catchError(code = "CC-B101")
}

/**
 * [T]를 제공하는 [block]을 통해 반환된 데이터 [R]에 상태를 부여한 [Flow]를 반환합니다.
 *
 * @see Stateful
 * @see statefulFlow
 */
fun <T, R> T.statefulFlowWith(block: suspend T.() -> R): Flow<Stateful<R>> {
  return flow<Stateful<R>> { emit(Stateful.Success(this@statefulFlowWith.block())) }
    .onStart { emit(Stateful.Loading) }
    .catchError(code = "CC-B201")
}