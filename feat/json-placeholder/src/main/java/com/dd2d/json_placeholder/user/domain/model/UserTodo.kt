package com.dd2d.json_placeholder.user.domain.model

data class UserTodo(
  val id: Int,
  val userId: Int,
  val title: String,
  val isCompleted: Boolean,
)
