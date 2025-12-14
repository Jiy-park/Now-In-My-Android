package com.jiy.user.domain.model

data class User(
  val id: Int,
  val name: String,
  val birth: String,
  val phoneNum: String,
  val email: String,
  val githubUrl: String,
  val profileImageUrl: String?,
  val shortIntroduction: String?,
)