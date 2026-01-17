package com.dd2d.json_placeholder.post.domain.model

data class PostCreateData(
  val title: String,
  val body: String,
  val userId: Int,
)