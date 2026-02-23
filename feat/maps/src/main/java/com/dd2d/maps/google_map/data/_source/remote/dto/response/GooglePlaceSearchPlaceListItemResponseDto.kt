package com.dd2d.maps.google_map.data._source.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class GooglePlaceSearchPlaceListItemResponseDto(
  val places: List<GooglePlaceSearchPlaceResponseDto>
)

@Serializable
data class GooglePlaceSearchPlaceResponseDto(
  val id: String,
  val displayName: DisplayName,
  val formattedAddress: String,
)