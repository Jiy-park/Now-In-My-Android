package com.dd2d.json_placeholder.user.data._source.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDto(
  val id: Int,
  val name: String,
  val username: String,
  val email: String,
  val address: UserAddressResponseDto,
  val phone: String,
  val website: String,
  val company: UserCompanyResponseDto,
)
