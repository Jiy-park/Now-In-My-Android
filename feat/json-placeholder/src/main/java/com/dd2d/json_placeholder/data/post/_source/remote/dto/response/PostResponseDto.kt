package com.dd2d.json_placeholder.data.post._source.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
internal data class PostResponseDto(
  val id: Int,
  val userId: Int,
  val title: String,
  val body: String,
)