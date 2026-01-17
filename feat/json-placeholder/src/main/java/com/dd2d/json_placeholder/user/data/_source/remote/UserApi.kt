package com.dd2d.json_placeholder.user.data._source.remote

import com.dd2d.json_placeholder.user.data._source.remote.dto.response.UserResponseDto
import com.dd2d.json_placeholder.user.data._source.remote.dto.response.UserTodoResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface UserApi {
  @GET("users/{id}")
  suspend fun getUser(
    @Path("id") id: Int
  ): Response<UserResponseDto>

  @GET("users/{id}/todos")
  suspend fun getTodoList(
    @Path("id") id: Int,
    @Query("completed") completeFilter: Boolean?,
  ): Response<List<UserTodoResponseDto>>
}