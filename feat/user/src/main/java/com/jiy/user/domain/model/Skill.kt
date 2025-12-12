package com.jiy.user.domain.model

data class Skill(
  val id: Int,
  val name: String,
  val iconUrl: String?,
  val level: Int?,
  val description: String?,
)