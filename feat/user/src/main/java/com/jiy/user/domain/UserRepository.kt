package com.jiy.user.domain

import com.jiy.user.domain.model.Career
import com.jiy.user.domain.model.User
import com.jiy.user.domain.model.Skill

interface UserRepository {
  suspend fun me(): User
  suspend fun getMySkillStack(): List<Skill>
  suspend fun getMyCareers(): List<Career>
}