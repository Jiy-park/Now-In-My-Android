package com.dd2d.json_placeholder.user.presentation.detail.model

import androidx.compose.runtime.Immutable
import com.dd2d.json_placeholder.user.domain.model.UserTodo

@Immutable
internal data class TodoListUIState(
  val todoList: List<UserTodo>,
  val completeFilter: Boolean?,
  val onFilterChange: (Boolean?) -> Unit
)