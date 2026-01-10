package com.dd2d.json_placeholder.post.data.mapper

import com.dd2d.json_placeholder.post.data._source.remote.dto.response.PostResponseDto
import com.dd2d.json_placeholder.post.domain.model.Post
import com.dd2d.json_placeholder.post.domain.model.PostAuthor

internal fun PostResponseDto.toPost(): Post {
  return Post(
    id = id,
    author = PostAuthor(
      id = userId,
      nickname = getUserNameById(userId),
      profileImageUrl = "https://i.pravatar.cc/150?img=$userId",
    ),
    title = title,
    body = body,
  )
}

// json placeholder에서 제공하지 않아 임의로 생성
private fun getUserNameById(id: Int): String {
  val users = listOf(
    1 to "Bret",
    2 to "Antonette",
    3 to "Samantha",
    4 to "Karianne",
    5 to "Kamren",
    6 to "Leopoldo_Corkery",
    7 to "Elwyn.Skiles",
    8 to "Maxime_Nienow",
    9 to "Delphine",
    10 to "Moriah.Stanton",
  )

  return users.find { it.first == id }?.second ?: "user$id"
}