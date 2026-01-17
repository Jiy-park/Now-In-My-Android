package com.dd2d.json_placeholder.post.domain.model

import javax.annotation.concurrent.Immutable

@Immutable
data class Post(
  val id: Int,
  val author: PostAuthor,
  val title: String,
  val body: String,
)
