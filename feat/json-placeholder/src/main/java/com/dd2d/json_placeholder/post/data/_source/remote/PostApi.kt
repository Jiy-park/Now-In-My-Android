package com.dd2d.json_placeholder.post.data._source.remote

import com.dd2d.json_placeholder.post.data._source.remote.dto.request.PostCreateRequestDto
import com.dd2d.json_placeholder.post.data._source.remote.dto.request.PostUpdateRequestDto
import com.dd2d.json_placeholder.post.data._source.remote.dto.response.PostResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

internal interface PostApi {
  @GET("posts")
  suspend fun getPosts(
    @Query("userId") userId: Int?,
  ): Response<List<PostResponseDto>>

  @GET("posts/{id}")
  suspend fun getPost(
    @Path("id") id: Int
  ): Response<PostResponseDto>

  @POST("posts")
  suspend fun createPost(
    @Body body: PostCreateRequestDto
  ): Response<PostResponseDto>

  @PUT("posts/{id}")
  suspend fun updatePost(
    @Path("id") id: Int,
    @Body body: PostUpdateRequestDto
  ): Response<PostResponseDto>

  @DELETE
  suspend fun deletePost(
    @Path("id") id: Int
  ): Response<Unit>
}