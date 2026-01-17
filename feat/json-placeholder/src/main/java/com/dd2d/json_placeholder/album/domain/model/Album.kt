package com.dd2d.json_placeholder.album.domain.model

data class Album(
  val id: Int,
  val title: String,
  val author: AlbumAuthor,
)
