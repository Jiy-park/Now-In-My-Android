package com.dd2d.maps.google_map.data._source.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class GooglePlaceResponseDto(
  val id: String,
  val displayName: DisplayName?,
  val location: Location?,
  val formattedAddress: String?,
  val types: List<String>?,
  val businessStatus: String?,
  val rating: Double?,
  val userRatingCount: Int?,
  val priceLevel: String?,
  val regularOpeningHours: OpeningHours?,
  val currentOpeningHours: OpeningHours?,
  val internationalPhoneNumber: String?,
  val websiteUri: String?,
  val googleMapsUri: String?,
  val photos: List<Photo>?
)

@Serializable
data class DisplayName(
  val text: String,
  val languageCode: String?
)

@Serializable
data class Location(
  val latitude: Double,
  val longitude: Double
)

@Serializable
data class OpeningHours(
  val openNow: Boolean?,
  val weekdayDescriptions: List<String>?
)

@Serializable
data class Photo(
  val name: String,
  val widthPx: Int?,
  val heightPx: Int?,
  val authorAttributions: List<AuthorAttribution>?
)

@Serializable
data class AuthorAttribution(
  val displayName: String?,
  val uri: String?
)