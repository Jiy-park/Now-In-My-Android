package com.jiy.screen.main.model

import com.jiy.screen.main.MainScreen

/** [MainScreen]에서 발생할 수 있는 내비게이션 이벤트 */
sealed interface MainScreenNavEvent {
  data class GitHub(val url: String): MainScreenNavEvent
  data class CareerDetail(val id: Int): MainScreenNavEvent
  data object CareerList: MainScreenNavEvent
}