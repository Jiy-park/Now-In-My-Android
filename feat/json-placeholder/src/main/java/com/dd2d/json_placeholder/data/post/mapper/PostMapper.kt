package com.dd2d.json_placeholder.data.post.mapper

import com.dd2d.json_placeholder.data.post._source.remote.dto.response.PostResponseDto
import com.dd2d.json_placeholder.domain.post.model.Post

internal fun PostResponseDto.toPost(): Post {
  return Post(
    id = id,
    userId = userId,
    title = title,
    body = body,
  )
}