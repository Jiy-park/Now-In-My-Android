package com.dd2d.json_placeholder.user.data._source.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
internal data class UserCompanyResponseDto(
  val name: String,
  val catchPhrase: String,
  val bs: String
)
