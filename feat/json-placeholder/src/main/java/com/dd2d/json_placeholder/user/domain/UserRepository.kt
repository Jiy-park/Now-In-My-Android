package com.dd2d.json_placeholder.user.domain

import com.dd2d.json_placeholder.user.domain.model.User

interface UserRepository {
  suspend fun getUser(id: Int): User
}