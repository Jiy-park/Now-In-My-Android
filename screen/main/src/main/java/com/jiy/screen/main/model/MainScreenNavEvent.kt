package com.jiy.screen.main.model

/** [com.jiy.screen.main.portfolio.PortfolioScreen]에서 발생할 수 있는 내비게이션 이벤트 */
sealed interface MainScreenNavEvent {
  // 포트폴리오
  sealed interface Portfolio {
    data class GitHub(val url: String): MainScreenNavEvent
    data class CareerDetail(val id: Int): MainScreenNavEvent
    data object CareerList: MainScreenNavEvent
  }

  // 놀이터
  sealed interface Playground {
    data class ToyDetail(val id: Int): MainScreenNavEvent
    data object ToyList: MainScreenNavEvent
  }
}