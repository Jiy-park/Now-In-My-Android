package com.jiy.core.state

import com.jiy.core.exception.UIException

/**
 * 데이터 [T]에 상태를 부여합니다.
 *
 * 부여할 수 있는 상태로는 [Loading], [Error], [Success]가 있습니다.
 *
 * @param T 상태를 부여할 데이터
 * @see Loading
 * @see Error
 * @see Success
 * @see statefulFlow
 * @see statefulFlowWith
 */
sealed interface Stateful<out T> {
  /**
   * 데이터가 로딩중인 상태
   * */
  data object Loading: Stateful<Nothing>


  /**
   * 데이터가 [exception]에 의해 에러인 상태
   *
   * @property exception 상태의 원인이 되는 [UIException]
   */
  data class Error(val exception: UIException): Stateful<Nothing>


  /**
   *  데이터를 바로 사용할 수 있는 상태.
   *
   * @property data 데이터
   */
  data class Success<T>(val data: T): Stateful<T>
}