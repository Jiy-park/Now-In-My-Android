package com.dd2d.maps.google_map.data._source.remote

import com.dd2d.maps.google_map.data._source.remote.dto.request.GooglePlaceSearchByTextRequestDto
import com.dd2d.maps.google_map.data._source.remote.dto.response.GooglePlaceResponseDto
import com.dd2d.maps.google_map.data._source.remote.dto.response.GooglePlaceSearchPlaceListItemResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

internal interface GooglePlaceApi {
  companion object {
    const val PLACE_FIELD_MASK = "id,displayName,location,formattedAddress,types,businessStatus,rating,userRatingCount,priceLevel,regularOpeningHours,currentOpeningHours,internationalPhoneNumber,websiteUri,googleMapsUri,photos"
    const val SEARCH_BY_TEXT_FIELD_MASK = "places.id,places.displayName,places.formattedAddress,nextPageToken"
  }

  @GET("v1/places/{placeId}")
  suspend fun getPlace(
    @Path("placeId") placeId: String,
    @Query("languageCode") languageCode: String = "ko-KR",
    @Header("X-Goog-FieldMask") fieldMask: String = PLACE_FIELD_MASK
  ): GooglePlaceResponseDto

  @POST("v1/places:searchText")
  suspend fun searchByText(
    @Body body: GooglePlaceSearchByTextRequestDto,
    @Query("languageCode") languageCode: String = "ko",
    @Header("X-Goog-FieldMask") fieldMask: String = SEARCH_BY_TEXT_FIELD_MASK,
  ): GooglePlaceSearchPlaceListItemResponseDto
}