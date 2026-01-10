package com.dd2d.json_placeholder.data.post

import com.dd2d.json_placeholder.data.post._source.remote.PostApi
import com.dd2d.json_placeholder.data.post._source.remote.dto.request.PostCreateRequestDto
import com.dd2d.json_placeholder.data.post._source.remote.dto.request.PostUpdateRequestDto
import com.dd2d.json_placeholder.data.post._source.remote.dto.response.PostResponseDto
import com.dd2d.json_placeholder.data.post.mapper.toPost
import com.dd2d.json_placeholder.domain.post.PostRepository
import com.dd2d.json_placeholder.domain.post.model.Post
import com.dd2d.json_placeholder.domain.post.model.PostCreateData
import com.dd2d.json_placeholder.domain.post.model.PostUpdateData
import retrofit2.HttpException
import javax.inject.Inject

internal class PostRepositoryImpl @Inject constructor(
  private val postApi: PostApi,
): PostRepository {
  override suspend fun getPostList(): List<Post> {
    val response = postApi.getPosts()
    if(!response.isSuccessful) {
      throw HttpException(response)
    }
    return response.body()?.map(PostResponseDto::toPost)?: emptyList()
  }

  override suspend fun getPost(id: Int): Post {
    val response = postApi.getPost(id)
    if(!response.isSuccessful) {
      throw HttpException(response)
    }
    return response.body()?.toPost()?: throw IllegalStateException("Response body is null")
  }

  override suspend fun createPost(data: PostCreateData): Post {
    val response = postApi.createPost(
      body = PostCreateRequestDto(
        userId = data.userId,
        title = data.title,
        body = data.body,
      )
    )
    if(!response.isSuccessful) {
      throw HttpException(response)
    }
    return response.body()?.toPost()?: throw IllegalStateException("Response body is null")
  }

  override suspend fun updatePost(
    id: Int,
    data: PostUpdateData
  ): Post {
    val response = postApi.updatePost(
      id = id,
      body = PostUpdateRequestDto(
        userId = data.userId,
        title = data.title,
        body = data.body,
      )
    )
    if(!response.isSuccessful) {
      throw HttpException(response)
    }
    return response.body()?.toPost()?: throw IllegalStateException("Response body is null")
  }

  override suspend fun deletePost(id: Int) {
    val response = postApi.deletePost(id)
    if(!response.isSuccessful) {
      throw HttpException(response)
    }
  }
}