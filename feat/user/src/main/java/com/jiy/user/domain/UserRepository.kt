package com.jiy.user.domain

import com.jiy.user.domain.model.Skill
import com.jiy.user.domain.model.User

interface UserRepository {
  suspend fun me(): User
  suspend fun getMySkillStack(): List<Skill>
}