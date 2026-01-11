package com.dd2d.json_placeholder.album.data._source.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
internal data class AlbumPhotoResponseDto(
  val id: Int,
  val albumId: Int,
  val title: String,
  val url: String,
  val thumbnailUrl: String,
)
