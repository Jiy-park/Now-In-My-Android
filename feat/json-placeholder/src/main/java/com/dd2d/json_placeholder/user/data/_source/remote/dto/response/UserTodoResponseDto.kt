package com.dd2d.json_placeholder.user.data._source.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
internal data class UserTodoResponseDto(
  val id: Int,
  val userId: Int,
  val title: String,
  val completed: Boolean,
)
