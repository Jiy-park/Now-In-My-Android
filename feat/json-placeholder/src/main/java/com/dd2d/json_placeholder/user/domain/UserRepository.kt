package com.dd2d.json_placeholder.user.domain

import com.dd2d.json_placeholder.user.domain.model.User
import com.dd2d.json_placeholder.user.domain.model.UserTodo

interface UserRepository {
  suspend fun getUser(id: Int): User
  suspend fun getTodoList(userId: Int, completeFilter: Boolean?): List<UserTodo>
}