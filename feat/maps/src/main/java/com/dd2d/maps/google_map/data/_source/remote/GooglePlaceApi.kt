package com.dd2d.maps.google_map.data._source.remote

import com.dd2d.maps.google_map.data._source.remote.dto.response.GooglePlaceResponseDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

internal interface GooglePlaceApi {
  @GET("places/{placeId}")
  @GET("v1/places/{placeId}")
  suspend fun getPlace(
    @Path("placeId") placeId: String,
    @Query("languageCode") languageCode: String = "ko-KR",
    @Header("X-Goog-FieldMask") fieldMask: String =
      "id,displayName,location,formattedAddress,types,businessStatus,rating,userRatingCount,priceLevel,regularOpeningHours,currentOpeningHours,internationalPhoneNumber,websiteUri,googleMapsUri,photos"
  ): GooglePlaceResponseDto
}