package com.dd2d.json_placeholder.album.data._source.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
internal data class AlbumResponseDto(
  val id: Int,
  val userId: Int,
  val title: String,
)
