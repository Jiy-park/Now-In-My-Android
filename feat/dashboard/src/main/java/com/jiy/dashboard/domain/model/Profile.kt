package com.jiy.dashboard.domain.model

data class Profile(
  val name: String,
  val phoneNum: String,
  val email: String,
  val profileImageUrl: String?,
  val shortIntroduction: String?,
)