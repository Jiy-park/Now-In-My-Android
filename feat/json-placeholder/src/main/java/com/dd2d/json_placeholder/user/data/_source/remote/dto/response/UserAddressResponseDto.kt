package com.dd2d.json_placeholder.user.data._source.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
internal data class UserAddressResponseDto(
  val street: String,
  val suite: String,
  val city: String,
  val zipcode: String,
  val geo: UserAddressGeoResponseDto,
)
