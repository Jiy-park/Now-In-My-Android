package com.dd2d.maps.google_map.data._source.remote.dto.request

import kotlinx.serialization.Serializable

@Serializable
internal data class GooglePlaceSearchByTextRequestDto(
  val textQuery: String,
  val locationBias: PlaceSearchLocationBias?,
  val languageCode: String,
  val regionCode: String,
)

@Serializable
internal data class PlaceSearchLocationBias(
  val circle: PlaceSearchLocationBiasCircle,
)

@Serializable
internal data class PlaceSearchLocationBiasCircle(
  val center: PlaceSearchLocationBiasCircleCenter,
  val radius: Int,
)

@Serializable
internal data class PlaceSearchLocationBiasCircleCenter(
  val latitude: Double,
  val longitude: Double,
)