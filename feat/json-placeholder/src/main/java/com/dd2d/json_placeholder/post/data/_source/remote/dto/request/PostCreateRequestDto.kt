package com.dd2d.json_placeholder.post.data._source.remote.dto.request

import kotlinx.serialization.Serializable

@Serializable
internal data class PostCreateRequestDto(
  val userId: Int,
  val title: String,
  val body: String,
)