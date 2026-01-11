package com.dd2d.json_placeholder.post.data.mapper

import com.dd2d.json_placeholder._core.data.getUserNameById
import com.dd2d.json_placeholder._core.data.getUserProfileImageById
import com.dd2d.json_placeholder.post.data._source.remote.dto.response.PostResponseDto
import com.dd2d.json_placeholder.post.domain.model.Post
import com.dd2d.json_placeholder.post.domain.model.PostAuthor

internal fun PostResponseDto.toPost(): Post {
  return Post(
    id = id,
    author = PostAuthor(
      id = userId,
      nickname = getUserNameById(userId),
      profileImageUrl = getUserProfileImageById(userId),
    ),
    title = title,
    body = body,
  )
}