package com.dd2d.json_placeholder.user.data._source.remote

import com.dd2d.json_placeholder.user.data._source.remote.dto.response.UserResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface UserApi {
  @GET("users/{id}")
  suspend fun getUser(
    @Path("id") id: Int
  ): Response<UserResponseDto>
}