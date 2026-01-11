package com.dd2d.json_placeholder.user.presentation.detail.model

import androidx.compose.runtime.Immutable

@Immutable
internal enum class UserDetailScreenTab(val label: String) {
  Todos(label = "TODO"),
  Posts(label = "Post"),
  Albums(label = "Album")
}