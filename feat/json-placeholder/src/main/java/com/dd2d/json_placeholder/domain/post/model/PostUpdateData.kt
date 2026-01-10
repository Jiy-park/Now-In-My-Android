package com.dd2d.json_placeholder.domain.post.model

data class PostUpdateData(
  val title: String,
  val body: String,
  val userId: Int,
)