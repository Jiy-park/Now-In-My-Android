package com.dd2d.json_placeholder.user.data

import com.dd2d.json_placeholder.user.data._source.remote.UserApi
import com.dd2d.json_placeholder.user.domain.UserRepository
import com.dd2d.json_placeholder.user.domain.model.User
import retrofit2.HttpException
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
  private val userApi: UserApi,
): UserRepository {
  override suspend fun getUser(id: Int): User {
    val response = userApi.getUser(id)
    if(!response.isSuccessful) {
      throw HttpException(response)
    }
    return response.body()
      ?.let { dto ->
        User(
          id = dto.id,
          nickname = dto.username,
          profileImageUrl = "https://i.pravatar.cc/150?img=${dto.id}"
        )
      }
      ?: throw IllegalStateException("Response body is null")
  }
}