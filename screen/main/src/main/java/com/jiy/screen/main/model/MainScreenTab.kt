package com.jiy.screen.main.model

import com.jiy.screen.main.R

enum class MainScreenTab(
  internal val selectedIconRes: Int,
  internal val unselectedIconRes: Int,
  internal val selectedText: String,

) {
  Portfolio(
    selectedIconRes = R.drawable.portfolio_fill,
    unselectedIconRes = R.drawable.portfolio_outline,
    selectedText = "포트폴리오",
  ),
  Playground(
    selectedIconRes = R.drawable.playground_fill,
    unselectedIconRes = R.drawable.playgound_outline,
    selectedText = "놀이터",
  ),
}
