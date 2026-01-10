package com.dd2d.json_placeholder.data.post._source.remote.dto.request

import kotlinx.serialization.Serializable

@Serializable
internal data class PostUpdateRequestDto(
  val userId: Int,
  val title: String,
  val body: String,
)