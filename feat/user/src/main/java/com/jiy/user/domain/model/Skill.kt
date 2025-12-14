package com.jiy.user.domain.model

data class Skill(
  val id: Int,
  val name: String,
  val iconUrl: String?,
  val level: SkillLevel,
  val description: String?,
)