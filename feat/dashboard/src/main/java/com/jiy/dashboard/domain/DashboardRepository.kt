package com.jiy.dashboard.domain

import com.jiy.dashboard.domain.model.Career
import com.jiy.dashboard.domain.model.Profile
import com.jiy.dashboard.domain.model.Skill

interface DashboardRepository {
  suspend fun getProfile(): Profile
  suspend fun getSkillStack(): List<Skill>
  suspend fun getCareers(): List<Career>
}