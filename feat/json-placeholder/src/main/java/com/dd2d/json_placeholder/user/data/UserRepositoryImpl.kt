package com.dd2d.json_placeholder.user.data

import com.dd2d.json_placeholder._core.data.getUserProfileImageById
import com.dd2d.json_placeholder.user.data._source.remote.UserApi
import com.dd2d.json_placeholder.user.domain.UserRepository
import com.dd2d.json_placeholder.user.domain.model.User
import com.dd2d.json_placeholder.user.domain.model.UserTodo
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
          profileImageUrl = getUserProfileImageById(dto.id)
        )
      }
      ?: throw IllegalStateException("Response body is null")
  }

  override suspend fun getTodoList(userId: Int, completeFilter: Boolean?): List<UserTodo> {
    val response = userApi.getTodoList(userId, completeFilter)
    if(!response.isSuccessful) {
      throw HttpException(response)
    }

    return response.body()
      ?.let { dtoList ->
        dtoList.map { dto ->
          UserTodo(
            id = dto.id,
            userId = dto.userId,
            title = dto.title,
            isCompleted = dto.completed,
          )
        }
      }
      ?: throw IllegalStateException("Response body is null")
  }
}