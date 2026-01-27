package com.dd2d.now_in_my_android.presentation.main.model

import com.dd2d.now_in_my_android.R

enum class MainScreenBottomNavItem(
  val label: String,
  val iconRes: Int,
  val selectedIconRes: Int,
) {
  Post(
    label = "게시물",
    iconRes = R.drawable.post_outline,
    selectedIconRes = R.drawable.post_fill,
  ),
  Album(
    label = "앨범",
    iconRes = R.drawable.album_outline,
    selectedIconRes = R.drawable.album_fill,
  ),
}